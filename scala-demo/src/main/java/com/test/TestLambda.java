package com.test;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author LittleSun
 * @version 1.0
 * @date 2021-05-12 11:11
 */
public class TestLambda {

    /**
     * 配置数据源
     */
    private static final List<Person> PERSON_LIST = Arrays.asList(new Person(1L, "张三", 18)
            , new Person(2L, "李四", 21),
            new Person(3L, "王五", 30));

    public static void main(String[] args) {
        // testDeBug();
        //test5();
        testStringUtils();

    }

    public static void test() {
        Runnable runnable = () -> System.out.println("555");
        runnable.run();
        //Function 接口是对接受一个T类型参数,返回R类型的结果的方法的抽象,通过调用apply方法执行内容。
        Function<String, String> f = x -> x.trim() + "呵呵呵";
        String apply = f.apply("2");
        System.out.println(apply);
        //消费者一个入参无返回值
        Consumer<Integer> consumer = m -> System.out.println("去电影院看电影花了" + m + "元");
        consumer.accept(10 * 10);
        //提供者 无入参有返回值
        Supplier<String> supplier = () -> {
            System.out.println("提供者方法体执行");
            return "我是提供者";
        };
        System.out.println(supplier.get());
        //简写
        Supplier<String> supplier2 = () -> "我是提供者简写版";
        System.out.println(supplier2.get());
        //predicate<T,Boolean> 断言接口，该接口对应的方法为接收一个参数，返回一个Boolean类型值，多用于判断与过滤
        Predicate<String> p = x -> x.length() > 3;
        System.out.println(p.test("呵呵呵"));
        Consumer<String> c = System.out::println;
        c.accept("sss");
    }

    /**
     * 线程池使用
     */
    public static void test2() {
        //使用lambda方式创建线程池
        //Function<Integer, ExecutorService> function = x -> Executors.newFixedThreadPool(x);
        //使用方法引用简写
        Function<Integer, ExecutorService> function2 = Executors::newFixedThreadPool;
        ExecutorService pool = function2.apply(PERSON_LIST.size());
        //创建同步计数器
        CountDownLatch cdl = new CountDownLatch(PERSON_LIST.size());
        System.out.println(cdl.getCount());
        try {
            //打印数据源中的数据
            pool.execute(() -> PERSON_LIST.forEach(System.out::println));
            //让当前线程处于阻塞状态，直到锁存器计数为零（或者线程中断）
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //关闭线程池
            pool.shutdown();
            //减少锁存器的计数，如果计数达到零，则释放所有等待线程。
            //如果当前计数大于零，则将其递减。 如果新计数为零，则将重新启用所有等待线程以进行线程调度。
            //如果当前计数等于零，那么什么也不会发生
            cdl.countDown();
        }
    }

    /**
     * 手动创建线程池
     */
    public static void test3() {
        //手动创建线程池
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d").build();
        ExecutorService pool = new ThreadPoolExecutor(PERSON_LIST.size(), PERSON_LIST.size(), 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), threadFactory);
        //创建同步计数器
        CountDownLatch cdl = new CountDownLatch(PERSON_LIST.size());
        try {
            //打印数据源中的数据
            pool.execute(() -> PERSON_LIST.parallelStream().forEach(System.out::println));
            //让当前线程处于阻塞状态，直到锁存器计数为零（或者线程中断）
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //减少锁存器的计数，如果计数达到零，则释放所有等待线程。
            //如果当前计数大于零，则将其递减。 如果新计数为零，则将重新启用所有等待线程以进行线程调度。
            //如果当前计数等于零，那么什么也不会发生
            cdl.countDown();
            //关闭线程池
            pool.shutdown();
        }
    }

    /**
     * Files.lines初体验
     */
    public static void test4() {
        try {
            //读取文件  读中文会报错
            Stream<String> lines = Files.lines(Paths.get("E:/data.txt"));
            String s = lines.collect(Collectors.joining());
            //创建集合存放字符串
            List<String> strings = new ArrayList<>();
            //计数从第一位开始截取
            int count = 1;
            for (int i = 0; i < s.length(); i++) {
                //截取 把这个字符串单个截取成多个字符串
                strings.add(s.substring(i, count));
                //每次自增
                count++;
            }
            System.out.println(strings);
            //倒叙打印
            strings.stream()
                    //排序规则 倒叙
                    .sorted(Comparator.comparing(String::toString).reversed())
                    //打印
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void test5() {
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            integers.add(i);
        }
        long start = System.currentTimeMillis();
        List<String> strings = integers.parallelStream()
                .collect(ArrayList::new, (arrayList, i) -> arrayList.add(i.toString()), List::addAll);
        System.out.println(strings);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + "ms");
        long start2 = System.currentTimeMillis();
        List<String> newList = integers.parallelStream().map(Objects::toString).collect(Collectors.toList());
        System.out.println(newList);
        long end2 = System.currentTimeMillis();
        System.out.println("耗时：" + (end2 - start2) + "ms");
        System.out.println(CollectionUtils.isEqualCollection(strings, newList));
    }

    public static void testStream() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            list.add(i);
        }
        System.out.println(list.size());
        List<Integer> streamList = list.parallelStream()
                .map(Integer::new).collect(Collectors.toList());
        System.out.println(streamList.size());

    }

    public static void testDeBug() {
        int[] ints = IntStream.of(10, 20, 40, 30, 56, 80)
                .filter(e -> e > 20)
                .map(e -> e * 2)
                .sorted()
                .toArray();
        for (int i : ints) {
            System.out.println(i);
        }
    }

    public static void testStringUtils() {
        List<String> list = new ArrayList<>();
        list.add(null);
        for (int i = 0; i < 10000; i++) {
            list.add(i + "");
        }
        //串行流处理
        long start2 = System.currentTimeMillis();
        List<String> collect2 = list.stream()
                .filter(StringUtils::isNotBlank)
                .map(s -> s + "非并发处理后").collect(Collectors.toList());
        long end2 = System.currentTimeMillis();
        System.out.println("非并发处理耗时：" + (end2 - start2) + "ms");
        System.out.println("集合处理后的长度" + collect2.size());
        //并行流处理
        long start = System.currentTimeMillis();
        List<String> collect = list.parallelStream()
                .filter(StringUtils::isNotBlank)
                .map(s -> s + "并发处理后").collect(Collectors.toList());
        long end = System.currentTimeMillis();
        System.out.println("并发处理耗时：" + (end - start) + "ms");
        System.out.println("集合处理后的长度" + collect.size());
        //线程池处理
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d").build();
        ExecutorService pool = new ThreadPoolExecutor(5, 5,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), threadFactory);
//        pool.execute(() -> {
//            long start3 = System.currentTimeMillis();
//            List<String> collect3 = list.stream()
//                    .filter(StringUtils::isNotBlank)
//                    .map(s -> s + "线程池并发处理后").collect(Collectors.toList());
//            long end3 = System.currentTimeMillis();
//            System.out.println("线程池并发处理耗时：" + (end3 - start3) + "ms");
//            System.out.println("集合处理后的长度" + collect3.size());
//        });
        Callable<Object> callable = () -> {
            System.out.println("长任务");
            TimeUnit.SECONDS.sleep(5);
            return null;
        };
        Callable<String> callable2 = () -> {
            System.out.println("短任务");
            return "短任务";
        };

        pool.submit(callable2);
        pool.submit(callable);
        pool.submit(callable2);
        pool.submit(callable2);
        //关闭线程池
        pool.shutdown();
        try {
            while (!pool.awaitTermination(1, TimeUnit.SECONDS)) {
                System.out.println("线程池没有关闭");
                System.out.println("isTerminated:" + pool.isTerminated());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean terminated = pool.isTerminated();
        if (terminated) {
            System.out.println("任务已完成" + terminated);
        } else {
            System.out.println("任务未完成" + terminated);
        }


    }


}

