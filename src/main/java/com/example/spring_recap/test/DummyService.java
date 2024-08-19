package com.example.spring_recap.test;

import org.springframework.util.StringUtils;

import java.util.List;

public class DummyService {

    public int doSomething(List<String> list, int index) {
        System.out.println("List has " + list.size() + " elements.");
        String string = list.get(index);

        if (StringUtils.isEmpty(string)) {
            return 0;
        } else {
            return string.length();
        }
    }
}
