package Lesson2_1;

public class MyArrayDataException extends Exception{
    MyArrayDataException(int row, int col) {
        super(String.format("Неверные данные находятся в ячейке [%d, %d]\n", row, col));

    }
}
