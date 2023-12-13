package org.example.dz3;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Student user1 = new Student("Роман", 10, 3.5);
        Student user2 = new Student("Назар", 12, 4.5);
        System.out.println(user1);
        System.out.println(user2);
        System.out.println("теперь сохраним обьекты и выведем результат заново: ");

        try(FileOutputStream fileOutputStream = new FileOutputStream("1.bin");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            objectOutputStream.writeObject(user1);
            objectOutputStream.writeObject(user2);
        }

        try(FileInputStream fileInputStream = new FileInputStream("1.bin");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
            user1 = (Student)objectInputStream.readObject();
            System.out.println(user1);
            user2 = (Student)objectInputStream.readObject();
            System.out.println(user2);
        }
        System.out.println("поле GPA по нолям из за того что мы заранее задали чтобы оно не сохранялось");

    }
}
