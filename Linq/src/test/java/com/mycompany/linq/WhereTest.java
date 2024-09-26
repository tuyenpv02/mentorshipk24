package com.mycompany.linq;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class WhereTest {

    @Test
    public void testWhereUserByName() {
        List<User> users = new ArrayList<>();
        users.add(new User("van", 30, "thanh@gmail.om"));
        users.add(new User("van2", 32, "thanh@gmail.om"));
        users.add(new User("tú", 15, "tu@gmail.om"));
        users.add(new User("huy", 60, "huy@gmail.om"));

        Linq<User> linq = new Linq<>(users);
        List<User> result = linq.where(o -> o.getAge() > 18).toList();
     
        assertEquals(3, result.size());

    }
}
