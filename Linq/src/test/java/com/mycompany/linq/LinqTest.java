/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.linq;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author tuyen
 */
public class LinqTest {

    @Test
    public void testCountNumber() {

        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        long expResult = 3;

        Linq<Integer> linq = new Linq(numbers);
        long result = linq.count(o -> o > 1);

        assertEquals(expResult, result);
    }

    @Test
    public void testCountUser() {

        List<User> users = new ArrayList<>();
        users.add(new User("van thanh", 30, "thanh@gmail.om"));
        users.add(new User("phạm tú", 15, "tu@gmail.om"));
        users.add(new User("nguyễn huy", 60, "huy@gmail.om"));
        long expResult = 2;

        Linq<User> linq = new Linq(users);
        long result = linq.count(o -> o.getAge() > 18);

        assertEquals(expResult, result);
    }

}
