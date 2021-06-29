package com.guava.test;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.IntFunction;

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
            final CountDownLatch cdl = new CountDownLatch(list.size());
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

    /**
     * 线程池execute方法使用
     */
    @Test
    public void test2() {
        List<String> list = new ArrayList<>();
        list.add("123");
        list.add("234");
        list.add("345");
        //手动创建线程池
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d").build();
        ExecutorService pool = new ThreadPoolExecutor(3, 3, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), threadFactory);
        try {
            //创建同步计数器
            CountDownLatch cdl = new CountDownLatch(list.size());
            //打印数据源中的数据
            pool.execute(() -> list.parallelStream().forEach(s -> {
                System.out.println(s);
                //减少锁存器计数到0释放所有线程
                cdl.countDown();
            }));
            //阻塞当前线程直到锁存器计数为零
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
        TestCase.assertTrue(true);
    }

    /**
     * 线程池submit方法使用
     */
    @Test
    public void test3() {
        List<String> list = new ArrayList<>();
        list.add("123");
        list.add("234");
        list.add("345");
        //手动创建线程池
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d").build();
        ExecutorService pool = new ThreadPoolExecutor(3, 3, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), threadFactory);
        try {
            //打印数据源中的数据
            Future<?> future = pool.submit(() -> list.parallelStream().forEach(System.out::println));
            if (future.get() == null) {
                System.out.println("task已完成");
                return;
            }
            System.out.println("task失败" + future.get());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
        TestCase.assertTrue(true);
    }

    @Test
    public void test4() {
        IntFunction<ExecutorService> func = Executors::newFixedThreadPool;
        ExecutorService pool = func.apply(1);
        try {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            pool.execute(() -> {
                System.out.println("线程池建立成功");
                countDownLatch.countDown();
            });
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.shutdown();
        TestCase.assertTrue(true);
    }
}

