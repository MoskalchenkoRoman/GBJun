package org.example.dz2;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        Animal[] animals = {
                new Dog("Мухтар", 2, "дворовый"),
                new Cat("Кися", 7, Color.red)
        };

        for(Animal animal : animals){
            System.out.println("Зверюга " + animal.getName() + ", возраст " + animal.getAge() + " года/лет ");

            Class<?> animalClass = animal.getClass();

            Method makeSoundMethod = null;

            try {
                makeSoundMethod = animalClass.getMethod("makeSound");
                makeSoundMethod.invoke(animal);
            } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
