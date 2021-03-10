package com.sean.webcrawler;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

// 單純測試，跟專案沒關
public class JobTest01 {
    /**
     *
     * Generates all combinations and output them,
     *
     * selecting n elements from data
     *
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    private static int count=0;
    public void combinations(List<Integer> selected, List<Integer> data, int n) {
        if (n == 0) {
            logger.info(selected.toString());
            return;
        }
        if (data.isEmpty()) {
            // output empty list
            return;
        }
        selected.add(data.get(0));
        combinations(selected, data.subList(1, data.size()), n - 1);

        // un-select elem
        // ent 0

        selected.remove(selected.size() - 1);
        combinations(selected, data.subList(1, data.size()), n);
    }

    public void print(List<Integer> selected) {
        for (Integer i : selected) {
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.println();

    }

    @Test
    public void test1(){
        JobTest01 test01 = new JobTest01();
        List<Integer> list=new ArrayList<Integer>();
        for (int i = 0; i < 12; i++) {
            list.add((i+1));
        }
        test01.combinations(new ArrayList<>(), list, 6);
        logger.info(String.valueOf(count));
    }

}
