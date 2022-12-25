package Lesson5;

/*
Пример:
        Value 1;Value 2;Value 3
        100;200;123
        300,400,500
        Для хранения данных использовать класс вида:
public class AppData {
    private String[] header;
    private int[][] data;

    // ...
*/

import java.util.Arrays;

public class AppData {
    private String [] header;
    private Integer[][] data;

    public AppData(String[] header, Integer[][] data) {
        this.header = header;
        this.data = data;
    }

    public AppData(String s, String s1, String parseInt) {
    }

    public String[] getHeader() {
        return header;
    }

    public void setHeader(String[] header) {
        this.header = header;
    }

    public Integer[][] getData() {
        return data;
    }

    public void setData(Integer[][] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AppData{" +
                "header=" + Arrays.toString(header) +
                ", data=" + Arrays.toString(data) +
                '}';
    }

    }


