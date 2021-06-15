package com.test;

import com.test.myinterface.MyLambdaInterface;

import org.apache.commons.collections.CollectionUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author Silence
 */
public class TestMerge {
    public static void main(String[] args) {
        System.out.println("用于测试合并");
        //构造测试数据
        List<Student> list = new ArrayList<>();
        Student stu = new Student();
        stu.setId(1L);
        stu.setName("ZS");
        list.add(stu);
        Student stu2 = new Student();
        stu2.setId(2L);
        stu2.setName("ls");
        list.add(stu2);
        list.add(null);
        System.out.println(list);
        System.out.println("------------------------------");
        //非空判断
        if (CollectionUtils.isNotEmpty(list)) {
            List<Person> perList = list.stream()
                    //过滤条件
                    .filter(Objects::nonNull)
                    //构造信息
                    .map(s -> {
                        Person person = new Person();
                        //重新赋值
                        person.setId(s.getId());
                        person.setAge(18);
                        person.setName(s.getName());
                        return person;
                    })
                    //返回新集合
                    .collect(Collectors.toList());
            //打印
            perList.forEach(System.out::println);
        }
        //测试lambda
        MyLambdaInterface aBlockOfCode = System.out::println;
        aBlockOfCode.doSomeShit("测试lambda");
        //lambda方式创建线程
        Thread t = new Thread(() -> System.out.println("创建一个线程"));
        Supplier<Thread> supplier = Thread::new;
        Thread thread = supplier.get();
        System.out.println(thread);
        t.start();
        //消费者一个入参无返回值
        Consumer<Integer> consumer = System.out::println;
        consumer.accept(10 * 10);
        //提供者 无入参有返回值
        Supplier<String> s = () -> "测试提供者";
        String s1 = s.get();
        System.out.println(s1);


    }
}
