package com.kelaskoding.utils;

public class RandomNumber {
    
    public static long getRandom(long min, long max){
        return min + (long) (Math.random() *max - min);
    }
}
