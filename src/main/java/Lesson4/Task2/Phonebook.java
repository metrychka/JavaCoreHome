package Lesson4.Task2;

import java.util.ArrayList;
import java.util.HashMap;

/*
Написать простой класс Телефонный Справочник, который хранит в себе список фамилий и телефонных номеров.
В этот телефонный справочник с помощью метода add() можно добавлять записи, а с помощью метода get()
 искать номер телефона по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов
 (в случае однофамильцев), тогда при запросе такой фамилии должны выводиться все телефоны.
 Желательно не добавлять лишний функционал (дополнительные поля (имя, отчество, адрес),
  взаимодействие с пользователем через консоль и т.д). Консоль использовать только для
  вывода результатов проверки телефонного справочника.
         */
public class Phonebook {
    private HashMap<String, ArrayList<String>> phoneBook = new HashMap<>();

    public void add(String surName, String phoneNumber) {
        ArrayList<String> phonesForLastName = phoneBook.getOrDefault(surName, new ArrayList<>());
        phonesForLastName.add(phoneNumber);
        phoneBook.put(surName, phonesForLastName);
    }

    public ArrayList<String> get(String surName) {
        return phoneBook.get(surName);
    }

    public static void main(String[] args) {
        Phonebook phoneBook = new Phonebook();
        phoneBook.add("Kotic", "123654789");
        phoneBook.add("Vector", "987456");
        phoneBook.add("Kotic", "123654789");
        phoneBook.add("Kotic", "654987");
        phoneBook.add("Breza", "6541223");

        System.out.println(phoneBook.get("Kotic"));
    }
}