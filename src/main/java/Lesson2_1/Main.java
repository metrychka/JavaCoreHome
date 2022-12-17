package Lesson2_1;

public class Main {
    public static void main(String[] args) throws MyArraySizeException, MyArrayDataException {
        arrStringFill();


    }

    private static void arrStringFill() throws MyArraySizeException, MyArrayDataException {
        int a = 0;
        String[][] arrString = {
         {"1", "2", "3", "10"},
         {"1", "15", "4", "5"},
         {"1", "2", "3", "7"},
         {"1", "2", "3", "4"}
        };
        if (arrString.length != 4) throw new MyArraySizeException("Неверное количество строк");
        for (int i = 0; i < arrString.length; i++) {
            if (arrString[i].length != 4) throw new MyArraySizeException(String.format("Неверное количество столбцов в %d-й" +
                    " строке", i));
            System.out.println();
            for (int j = 0; j < arrString.length; j++) {
                System.out.print(arrString[i][j]);
                if (itsNumber(arrString[i][j]) == true) {
                    a += Integer.parseInt(arrString[i][j]);
                } else {
                    throw new MyArrayDataException(i, j);
                }

            }

        }
        System.out.println();
        System.out.println( "Сумма массива = " + a);
    }

    static boolean itsNumber(String s ){
        return s.matches("\\d+\\.\\d+") || s.matches("\\d+");
    }

}



