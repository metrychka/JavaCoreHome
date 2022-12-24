package Lesson4.Task1;


//1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
 //       Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
//        Посчитать, сколько раз встречается каждое слово.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Dictionary {
    public static void main(String[] args) {
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("слово");
        stringArrayList.add("сила");
        stringArrayList.add("ключь");
        stringArrayList.add("кино");
        stringArrayList.add("сигарета");
        stringArrayList.add("слово");
        stringArrayList.add("кофе");
        stringArrayList.add("сила");
        stringArrayList.add("дорога");
        stringArrayList.add("ночь");
        stringArrayList.add("диван");
        System.out.println(stringArrayList);

        HashMap<String, Integer> wordsUniq = new HashMap<>();
        for (int i = 0; i < 10; i++ ) {
            if (wordsUniq.containsKey(stringArrayList.get(i))) {
                wordsUniq.put(stringArrayList.get(i),wordsUniq.get(stringArrayList.get(i))+1);
            } else {
                wordsUniq.put(stringArrayList.get(i),1);
            }
        }
        System.out.println(wordsUniq);
    }
}
