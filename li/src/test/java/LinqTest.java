import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

class LinqTest {

    @Test
    void count2() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(3);
        numbers.add(6);
        numbers.add(1);
        numbers.add(2);
        numbers.add(7);

        Linq<Integer> linq = new Linq(numbers);
        long result = linq.count(o -> o > 1);

        long expResult = 4;

        Assert.assertEquals(expResult, result);
    }

    @org.junit.jupiter.api.Test
    void count() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(3);
        numbers.add(6);
        numbers.add(1);
        numbers.add(2);
        numbers.add(7);

        Linq<Integer> linq = new Linq(numbers);
        long result = linq.count(o -> o > 1);

        long expResult = 4;

        Assert.assertEquals(expResult, result);
    }
}