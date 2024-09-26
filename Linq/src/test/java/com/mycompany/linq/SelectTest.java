/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.linq;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author tuyen
 */
public class SelectTest {

    @Test
    public void testWhereUserByName() {
        List<User> users = new ArrayList<>();
        users.add(new User("van", 30, "thanh@gmail.om"));
        users.add(new User("van2", 32, "thanh@gmail.om"));
        users.add(new User("tú", 15, "tu@gmail.om"));
        users.add(new User("huy", 60, "huy@gmail.om"));

        Linq<User> linq = new Linq<>(users);
        List<Integer> result = linq.select(o -> o.getAge()).toList();

        assertEquals(30, result.get(0));  
        assertEquals(32, result.get(1));

    }
}
