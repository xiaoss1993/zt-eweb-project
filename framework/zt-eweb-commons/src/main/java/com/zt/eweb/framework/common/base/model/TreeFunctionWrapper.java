
package com.zt.eweb.framework.common.base.model;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * 树结构函数包装类.
 *
 * @param <T> 树节点类型
 * @param <I> 节点 key 类型
 * @author Hccake
 * @since 2.0.0
 */
public class TreeFunctionWrapper<T, I> {

	/**
	 * 节点 key 提取器。
	 */
	private final Function<T, I> keyExtractor;

	/**
	 * 父节点 key 提取器。
	 */
	private final Function<T, I> parentKeyExtractor;

	/**
	 * 子节点设置器。
	 */
	private final BiConsumer<T, List<T>> childrenSetter;

	/**
	 * 子节点获取器。
	 */
	private final Function<T, List<T>> childrenGetter;

	public TreeFunctionWrapper(Function<T, I> keyExtractor, Function<T, I> parentKeyExtractor,
			BiConsumer<T, List<T>> childrenSetter, Function<T, List<T>> childrenGetter) {
		this.keyExtractor = keyExtractor;
		this.parentKeyExtractor = parentKeyExtractor;
		this.childrenSetter = childrenSetter;
		this.childrenGetter = childrenGetter;
	}

	public Function<T, I> keyExtractor() {
		return this.keyExtractor;
	}

	public Function<T, I> parentKeyExtractor() {
		return this.parentKeyExtractor;
	}

	public BiConsumer<T, List<T>> childrenSetter() {
		return this.childrenSetter;
	}

	public Function<T, List<T>> childrenGetter() {
		return this.childrenGetter;
	}

}
