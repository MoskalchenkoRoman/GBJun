package org.example.dz2;

import java.awt.*;

public class Cat extends Animal{
    private Color color;
    public Cat(String name, int age, Color color) {
        super(name, age);
        this.color = color;
    }

    public void makeSound() {
        System.out.println("МУР-МУР");
    }

    public void hunting(String name){
        System.out.println(name + "охотится на мышей");
    }
}
