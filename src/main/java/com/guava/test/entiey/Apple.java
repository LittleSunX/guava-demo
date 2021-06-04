package com.guava.test.entiey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LittleSun
 * @version 1.0
 * @date 2021-06-04 9:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Apple {
    private int id;
    private String name;
    private double price;
    private double weight;

    public Apple(int id) {
        this.id = id;
    }

}
