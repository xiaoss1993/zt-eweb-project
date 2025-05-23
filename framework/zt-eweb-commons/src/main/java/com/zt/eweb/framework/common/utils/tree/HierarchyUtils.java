package com.zt.eweb.framework.common.utils.tree;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;


public class HierarchyUtils {

    //Suppresses default constructor, Don't let anyone instantiate this class.
    private HierarchyUtils() {
    }

    public static class HierarchyFunctions<T, V, R> {

        /**
         * 判断是否为root函数
         * 必选
         */
        private Function<V, Boolean> isRootFunction;

        /**
         * 获取 pid函数
         * 必选
         */
        private Function<T, V> getPidFunction;

        /**
         * 获取 id函数
         * 必选
         */
        private Function<T, V> getIdFunction;

        /**
         * 获取children函数
         * 可选,存在时读取对应元素的子元素
         */
        private Function<T, List<T>> getChildrenFunction;

        /**
         * 转换函数
         * 可选
         */
        private Function<T, R> transferFunction;

        /**
         * 设置children函数
         * 必选
         */
        private BiConsumer<R, List<R>> setChildrenFunction;


        /**
         * 过滤条件
         * 可选
         */
        private Predicate<T> filterPredicate;

        /**
         * 是否启用转换
         * 可选,未指定时当transferFunction不为空为true
         */
        private Supplier<Boolean> isEnableTransfer;


        /**
         * 是否以root元素作为根
         * 可选,默认false,当开启时必须存在root且只允许存在一个
         */
        private Supplier<Boolean> isWithRoot;

        public Function<V, Boolean> getIsRootFunction() {
            return isRootFunction;
        }

        public void setIsRootFunction(Function<V, Boolean> isRootFunction) {
            this.isRootFunction = isRootFunction;
        }

        public Function<T, V> getGetPidFunction() {
            return getPidFunction;
        }

        public void setGetPidFunction(Function<T, V> getPidFunction) {
            this.getPidFunction = getPidFunction;
        }

        public Function<T, V> getGetIdFunction() {
            return getIdFunction;
        }

        public void setGetIdFunction(Function<T, V> getIdFunction) {
            this.getIdFunction = getIdFunction;
        }

        public Function<T, List<T>> getGetChildrenFunction() {
            return getChildrenFunction;
        }

        public void setGetChildrenFunction(Function<T, List<T>> getChildrenFunction) {
            this.getChildrenFunction = getChildrenFunction;
        }

        public Function<T, R> getTransferFunction() {
            return transferFunction;
        }

        public void setTransferFunction(Function<T, R> transferFunction) {
            this.transferFunction = transferFunction;
        }

        public BiConsumer<R, List<R>> getSetChildrenFunction() {
            return setChildrenFunction;
        }

        public void setSetChildrenFunction(BiConsumer<R, List<R>> setChildrenFunction) {
            this.setChildrenFunction = setChildrenFunction;
        }

        public Predicate<T> getFilterPredicate() {
            return filterPredicate;
        }

        public void setFilterPredicate(Predicate<T> filterPredicate) {
            this.filterPredicate = filterPredicate;
        }

        public Supplier<Boolean> getIsEnableTransfer() {
            return isEnableTransfer;
        }

        public void setIsEnableTransfer(Supplier<Boolean> isEnableTransfer) {
            this.isEnableTransfer = isEnableTransfer;
        }

        public Supplier<Boolean> getIsWithRoot() {
            return isWithRoot;
        }

        public void setIsWithRoot(Supplier<Boolean> isWithRoot) {
            this.isWithRoot = isWithRoot;
        }
    }

    /**
     * 将源数据列表转换为树形结构
     * <p>
     * 若对结果有排序需要,可通过 HierarchySortUtils 进行排序
     * <p>
     *
     * @param sourceList 源数据列表，不能存在为Null的子元素
     * @param functions  函数
     * @param <T>        源数据类型
     * @param <R>        转换结果类型
     * @param <V>        ID属性类型
     * @return 转换结果
     * @see HierarchySortUtils
     */
    public static <T, R, V> List<R> getHierarchyResult(final List<T> sourceList, final HierarchyFunctions<T, V, R> functions) {
        return getHierarchyResult(sourceList, functions, null);
    }

    /**
     * 将源数据列表转换为树形结构
     * <p>
     * 若对结果有排序需要,可通过 HierarchySortUtils 进行排序
     * <p>
     *
     * @param sourceList 源数据列表，不能存在为Null的子元素
     * @param functions  函数
     * @param comparator 可选 存在时会对筛选后的源数据列表进行排序
     * @param <T>        源数据类型
     * @param <R>        转换结果类型
     * @param <V>        ID属性类型
     * @return 转换结果
     * @see HierarchySortUtils
     */
    public static <T, R, V> List<R> getHierarchyResult(final List<T> sourceList, final HierarchyFunctions<T, V, R> functions, final Comparator<? super T> comparator) {
        //检查参数
        Objects.requireNonNull(functions, "functions must be not null");

        Function<V, Boolean> isRootFunction = functions.getIsRootFunction();
        Function<T, V> getPidFunction = functions.getGetPidFunction();
        Function<T, V> getIdFunction = functions.getGetIdFunction();
        Function<T, List<T>> getChildrenFunction = functions.getGetChildrenFunction();
        Function<T, R> transferFunction = functions.getTransferFunction();
        BiConsumer<R, List<R>> setChildrenFunction = functions.getSetChildrenFunction();
        Predicate<T> filterPredicate = functions.getFilterPredicate();

        Objects.requireNonNull(isRootFunction, "is root function must be not null");
        Objects.requireNonNull(getPidFunction, "get pid function must be not null");
        Objects.requireNonNull(getIdFunction, "get id function must be not null");
        Objects.requireNonNull(setChildrenFunction, "set children function must be not null");

        //是否启用转换
        boolean isEnableTransfer = HierarchyHelper.getBooleanValue(functions.getIsEnableTransfer(), transferFunction != null);
        if (isEnableTransfer) {
            Objects.requireNonNull(transferFunction, "when enable transfer, transfer function must be not null");
        }

        boolean isWithRoot = HierarchyHelper.getBooleanValue(functions.getIsWithRoot(), false);

        //检查数据是否为空
        if (sourceList == null || sourceList.isEmpty()) {
            return Collections.emptyList();
        }

        List<T> toResolveSourceList = null;
        Map<V, List<T>> toResolveSourceIdChildrenMap = null;
        try {
            //获取当前要处理的元素列表
            toResolveSourceList = HierarchyHelper.getApplySourceList(sourceList, getChildrenFunction, filterPredicate);

            //进行排序数据列表
            if (comparator != null && toResolveSourceList.size() > 1) {
                Collections.sort(toResolveSourceList, comparator);
            }

            //获取元素id所对应的子元素(但不包含root pid)
            toResolveSourceIdChildrenMap = HierarchyHelper.initAndGetIdChildrenResultMap(toResolveSourceList, getIdFunction, getPidFunction, isRootFunction);

            //处理数据
            List<R> rootList = isWithRoot ? new ArrayList<>(2) : null;
            List<R> results = new ArrayList<>(512);

            if (!isEnableTransfer) {
                for (T toResolveSource : toResolveSourceList) {
                    resolveHierarchyWithoutEnableTransfer(results, toResolveSource
                            , toResolveSourceIdChildrenMap, rootList
                            , isRootFunction, getPidFunction
                            , getIdFunction, setChildrenFunction);
                }
            } else {
                for (T toResolveSource : toResolveSourceList) {
                    R transferResult = HierarchyHelper.getTransferResult(transferFunction, toResolveSource);
                    resolveHierarchyWithEnableTransfer(results, toResolveSource, transferResult
                            , toResolveSourceIdChildrenMap, rootList
                            , isRootFunction, getPidFunction
                            , getIdFunction, transferFunction
                            , setChildrenFunction);
                }
            }

            if (!isWithRoot) {
                //不包含root时直接返回
                return results;
            }

            //检查rootList是否合法
            HierarchyHelper.checkRootList(rootList);

            //设置root子元素
            HierarchyHelper.resolveAndSetChildren(setChildrenFunction, rootList.get(0), results);

            return rootList;
        } finally {

            if (toResolveSourceList != null) {
                toResolveSourceList.clear();
            }

            if (toResolveSourceIdChildrenMap != null) {
                toResolveSourceIdChildrenMap.clear();
            }

        }
    }


    /**
     * 处理未转换的数据
     *
     * @param results
     * @param toResolveSource
     * @param toResolveSourceIdChildrenMap
     * @param rootList
     * @param isRootFunction
     * @param getPidFunction
     * @param getIdFunction
     * @param setChildrenFunction
     * @param <T>
     * @param <R>
     * @param <V>
     */
    private static <T, R, V> void resolveHierarchyWithoutEnableTransfer(final List<R> results, final T toResolveSource
            , final Map<V, List<T>> toResolveSourceIdChildrenMap, final List<R> rootList
            , final Function<V, Boolean> isRootFunction, final Function<T, V> getPidFunction
            , final Function<T, V> getIdFunction, final BiConsumer<R, List<R>> setChildrenFunction) {
        V id = getIdFunction.apply(toResolveSource);
        V pid = getPidFunction.apply(toResolveSource);
        R transferResult = (R) toResolveSource;
        boolean isRoot = isRootFunction.apply(id);
        if (isRoot) {
            //启用root时且当前元素为root放入rootList
            if (rootList != null) {
                HierarchyHelper.addRoot(rootList, transferResult);
            }
            return;
        }

        boolean isRootDirectChild = isRootFunction.apply(pid);
        if (isRootDirectChild) {
            //是root直接子元素时
            results.add(transferResult);
        }

        //获取当前元素的子元素列表
        List<R> transferChildrenList = (List<R>) toResolveSourceIdChildrenMap.get(id);
        //处理children
        HierarchyHelper.resolveAndSetChildren(setChildrenFunction, transferResult, transferChildrenList);

    }

    /**
     * 处理启用转换的数据
     *
     * @param results
     * @param toResolveSource
     * @param transferResult
     * @param toResolveSourceIdChildrenMap
     * @param rootList
     * @param isRootFunction
     * @param getPidFunction
     * @param getIdFunction
     * @param transferFunction
     * @param setChildrenFunction
     * @param <T>
     * @param <R>
     * @param <V>
     */
    private static <T, R, V> void resolveHierarchyWithEnableTransfer(final List<R> results, final T toResolveSource, final R transferResult
            , final Map<V, List<T>> toResolveSourceIdChildrenMap, final List<R> rootList
            , final Function<V, Boolean> isRootFunction, final Function<T, V> getPidFunction
            , final Function<T, V> getIdFunction, Function<T, R> transferFunction
            , final BiConsumer<R, List<R>> setChildrenFunction) {

        V id = getIdFunction.apply(toResolveSource);
        V pid = getPidFunction.apply(toResolveSource);
        boolean isRoot = isRootFunction.apply(id);
        if (isRoot) {
            //启用root时且当前元素为root放入rootList
            if (rootList != null) {
                HierarchyHelper.addRoot(rootList, transferResult);
            }
            return;
        }

        boolean isRootDirectChild = isRootFunction.apply(pid);
        if (isRootDirectChild) {
            //是root直接子元素时
            results.add(transferResult);

            //处理当前元素的子元素列表
            resolveWithChildren(toResolveSourceIdChildrenMap
                    , getIdFunction, transferFunction
                    , setChildrenFunction
                    , transferResult, id);
        }
    }


    private static <T, R, V> void resolveWithChildren(final Map<V, List<T>> toResolveSourceIdChildrenMap
            , final Function<T, V> getIdFunction
            , final Function<T, R> transferFunction
            , final BiConsumer<R, List<R>> setChildrenFunction
            , final R transferResult, final V id) {
        List<T> currentSourceChildrenList = toResolveSourceIdChildrenMap.get(id);

        if (currentSourceChildrenList == null || currentSourceChildrenList.isEmpty()) {
            return;
        }

        List<R> transferChildrenList = new ArrayList<>(currentSourceChildrenList.size());
        for (T currentSourceChild : currentSourceChildrenList) {
            R transferChildResult = HierarchyHelper.getTransferResult(transferFunction, currentSourceChild);
            //处理各子元素的子元素
            resolveWithChildren(toResolveSourceIdChildrenMap
                    , getIdFunction, transferFunction
                    , setChildrenFunction
                    , transferChildResult, getIdFunction.apply(currentSourceChild));

            transferChildrenList.add(transferChildResult);
        }
        //处理children
        HierarchyHelper.resolveAndSetChildren(setChildrenFunction, transferResult, transferChildrenList);
    }

}
