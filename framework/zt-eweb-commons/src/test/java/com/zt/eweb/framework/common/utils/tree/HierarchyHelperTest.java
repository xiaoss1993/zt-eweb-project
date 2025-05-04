package com.zt.eweb.framework.common.utils.tree;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class HierarchyHelperTest {

    @Test(expected = IllegalArgumentException.class)
    public void checkRootListByEmptyList() {
        HierarchyHelper.checkRootList(Collections.emptyList());
    }

    @Test
    public void checkRootList() {
        HierarchyHelper.checkRootList(Arrays.asList(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkRootListByMore() {
        HierarchyHelper.checkRootList(Arrays.asList(1, 2));
    }


}