package Lesson5;

/*      1. Реализовать сохранение данных в csv файл;
        2. Реализовать загрузку данных из csv файла. Файл читается целиком.
        Структура csv файла:
        | Строка заголовок с набором столбцов |
        | Набор строк с целочисленными значениями |
        | * Разделитель между столбцами - символ точка с запятой (;) |

        Пример:
        Value 1;Value 2;Value 3
        100;200;123
        300,400,500
        Для хранения данных использовать класс вида:
public class AppData {
    private String[] header;
    private int[][] data;

    // ...
}*/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        String saveHeader = "номер;сумма;остаток";
        String saveData = "1;2;3";
        String saveData2 = "10;20;30";
        Writer output;
        output = new BufferedWriter(new FileWriter("test2.csv"));
        output.append(saveHeader + "\n");
        output.append(saveData  + "\n");
        output.append(saveData2);
        output.close();

        String[] header = new String[0];
        Integer[][] data = new Integer[0][0];

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("test2.csv"))) {
            header = bufferedReader.readLine().split(";");
            ArrayList<Integer[]> dataList = new ArrayList<>();
            String tempString;
            while ((tempString = bufferedReader.readLine()) != null) {
                dataList.add(stringToDataRow(tempString));

            }
            data = dataList.toArray(new Integer[][]{{}});
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
      AppData appData = new AppData(header, data);
        System.out.println(Arrays.toString(appData.getHeader()));
        System.out.println(Arrays.deepToString(appData.getData()));
    }
    private static Integer[] stringToDataRow(String str) {
        String[] strings = str.split(";");

        Integer[] integers = new Integer[strings.length];
        for (int i = 0; i < strings.length; i++) {
            integers[i] = Integer.parseInt(strings[i]);
        }
        return integers;
    }

}
