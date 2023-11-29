package org.example.dz1;

import java.awt.*;
import java.util.ArrayList;

public class DZ1 {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(8);
        list.add(4);

        String aver;

        aver = list.stream().filter(chislo -> chislo % 2 == 0).mapToInt(Integer::intValue).average().toString();
        System.out.println(aver);
    }
}
