package com.mini2S.common.util;

public class Paging {
    public static long calculatePageCount(Long totalList, int size){
        return ((totalList - 1) / size) + 1;
    }
}