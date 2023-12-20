import java.util.*;

public class MineSweeper {

    //oyun alanı oluşturmak için method
    static String[][] createBoard(int row, int col) {
        String[][] gameBoard = new String[row][col];
        for (String[] strings : gameBoard) {
            Arrays.fill(strings, "  -  ");
        }
        return gameBoard;
    }

    //oyuncudan gelen hamle sonrası oyun alanı değiştirmek için method, bu method oyun alanını gösterecek methodu da içinde çağırıyor
    static void changeBoard (int row, int col, String[][] arr){
        arr[row][col] = "  *  ";
        showBoard(arr);
    }

    //oyun alanını gösterecek method
    static void showBoard(String[][] arr){
        for (String[] x : arr)
        {
            for (String y : x)
            {
                System.out.print(y + "     ");
            }
            System.out.println();
        }
        System.out.println("===========================");
    }

    //oyun başladığı zaman kullanıcıdan oyun alanı için giriş isteyen method, return sadece tek veri döndürdüğü için seçimleri arraylistte toplanıp
    //listeden extract ediliyor
    static ArrayList<Integer> welcomePlayer(){
        System.out.println("Mayın tarlası oyununa hoş geldiniz.");
        ArrayList<Integer> list = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        System.out.println("Lütfen oyun alanının boyutlarını giriniz.");
        System.out.println("Satır sayısı: ");
        int row = input.nextInt();
        list.add(row);
        System.out.println("Sütun sayısı: ");
        int col = input.nextInt();
        list.add(col);

        return list;
    }
     //kullanıcının hamle yapmasını sağlayan method ,return sadece tek veri döndürdüğü için seçimler arraylistte toplanıp
     //listeden extract ediliyor
     static ArrayList<Integer> playerMove(String[][] arr){
         ArrayList<Integer> list = new ArrayList<>();
         Scanner input = new Scanner(System.in);
         System.out.println("Lütfen hamleniz için koordinat seçiniz: ");
         int row, col;
         while (true){
             System.out.println("Satır giriniz: ");
             row = input.nextInt();
             if (row < arr.length + 1 && row >= 0) {
                 list.add(row);
                 break;
             } else {
                 System.out.println("Seçtiğiniz satır koordinatlar içerisinde değil. Lütfen tekrar deneyiniz.");
             }
         }

         while (true){
             System.out.println("Sütun giriniz: ");
             col = input.nextInt();
             if (col < arr[0].length + 1 && col >= 0) {
                 list.add(col);
                 break;
             } else {
                 System.out.println("Seçtiğiniz sütun koordinatlar içerisinde değil. Lütfen tekrar deneyiniz.");
             }
         }

         return list;
     }

    static String[][] deepCopy(String[][] original) {
        if (original == null) return null;

        String[][] copy = new String[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return copy;
    }


    static String[][] generateMines(String[][] arr){
        int x = arr.length;
        int y = arr[0].length;

        int numberofMines = (x * y) / 4;
        String[][] arrayWithMines = deepCopy(arr);
        Random rand = new Random();

        for (int i = 0; i < numberofMines; i++){
            int randomRow = rand.nextInt(x);
            int randomCol = rand.nextInt(y);
            arrayWithMines[randomRow][randomCol] = "  *  ";
        }

        return arrayWithMines;
     }

     static boolean checkIfMine(int a, int b, String[][] arr){
         return Objects.equals(arr[a][b], "  *  ");
     }

}

