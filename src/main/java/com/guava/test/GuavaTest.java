package com.guava.test;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ThreadFactory;

/**
 * @author LittleSun
 * @version 1.0
 * @date 2021-05-25 14:33
 */
public class GuavaTest {
    public void  test(){
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d").build();
    }
}
