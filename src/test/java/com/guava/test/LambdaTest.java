package com.guava.test;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author LittleSun
 * @version 1.0
 * @date 2021-06-04 11:09
 */
public class LambdaTest {
    @Test
    public void test() {
        List<String> list = new ArrayList<>();
        list.add("123");
        list.add("234");
        list.add("345");
        //手动创建线程池
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d").build();
        ExecutorService pool = new ThreadPoolExecutor(3, 3, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), threadFactory);
        try {
            //创建同步计数器
            CountDownLatch cdl = new CountDownLatch(3);
            //打印数据源中的数据
            pool.execute(() -> list.parallelStream().forEach(s -> {
                System.out.println(s);
                //减少锁存器的计数，如果计数达到零，则释放所有等待线程。
                //如果当前计数大于零，则将其递减。 如果新计数为零，则将重新启用所有等待线程以进行线程调度。
                //如果当前计数等于零，那么什么也不会发生
                cdl.countDown();
            }));
            //让当前线程处于阻塞状态，直到锁存器计数为零（或者线程中断）
            cdl.await();
            System.out.println("count值： " + cdl.getCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
        TestCase.assertTrue(true);
    }
}

