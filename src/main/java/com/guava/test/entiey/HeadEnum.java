package com.guava.test.entiey;

/**
 * Created Time 2022-05-07
 *
 * @author LittleSun
 * @version 1.0
 */
public enum HeadEnum {
    //111
    T1(1),
    //222
    T2(2);

    private final Integer value;

    HeadEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}