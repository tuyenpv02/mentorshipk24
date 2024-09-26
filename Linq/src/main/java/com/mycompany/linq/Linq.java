/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.linq;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Linq<T> {

    private List<T> elements;

    public Linq(List<T> elements) {
        this.elements = elements;
    }

    public long count(Predicate<T> predicate) {
        long count = 0;
        for (T item : elements) {
            if (predicate.test(item)) {
                count++;
            }
        }
        return count;
    }

    public Linq<T> where(Predicate<T> predicate) {
        List<T> lst = new ArrayList<>();
        for (T item : elements) {
            if (predicate.test(item)) {
                lst.add(item);
            }
        }
        return new Linq<T>(lst);
    }

    public T first(Predicate<T> predicate) {
        if (this.elements.size() < 0) {
            return null;
        }
        T firstItem = null;
        for (T item : elements) {
            if (predicate.test(item)) {
                firstItem = item;
                break;
            }
        }
        return firstItem;
    }
    
    public <R> Linq<R> distinct(T keySelector) {
        List<R> result = new ArrayList<>();
        for (T item : elements) {
            result.add(selector.apply(item));
        }
        return new Linq<>(result);
    }

    public <R> Linq<R> select(Function<T, R> selector) {
        List<R> result = new ArrayList<>();
        for (T item : elements) {
            result.add(selector.apply(item));
        }
        return new Linq<>(result);
    }

    public List<T> toList() {
        return this.elements;
    }

}
