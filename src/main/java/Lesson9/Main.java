package Lesson9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Student> student = new ArrayList<>();
        student.add(new Student("Anna", Arrays.asList(new Courses("Math"), new Courses("Biology"), new Courses("Testing"))));
        student.add(new Student("Timmy", Arrays.asList(new Courses("Math"), new Courses("IT"), new Courses("Testing"))));
        student.add(new Student("Oleg", Arrays.asList(new Courses("Math"), new Courses("IT"))));
        student.add(new Student("Dima", Arrays.asList(new Courses("Physics"), new Courses("Biology"), new Courses("Testing"), new Courses("Testing1"))));

        //1. Написать функцию, принимающую список Student и возвращающую список уникальных курсов, на которые подписаны студенты.
        System.out.println(student.stream()
                .map(s -> s.getCourse())
                .flatMap(f -> f.stream())
                .collect(Collectors.toSet()));


        //2. Написать функцию, принимающую на вход список Student и возвращающую список из трех самых любознательных (любознательность определяется количеством курсов).
        System.out.println(student.stream()
                .sorted((s1, s2) -> s2.getCourse().size() - s1.getCourse().size())
                .limit(2)
                .collect(Collectors.toList()));

     //3. Написать функцию, принимающую на вход список Student и экземпляр Course, возвращающую список студентов, которые посещают этот курс.
        Courses course = new Courses("Math");
        System.out.println(student.stream()
                .filter(s -> s.getCourse().contains(course))
                .collect(Collectors.toList()));
    }
    }


