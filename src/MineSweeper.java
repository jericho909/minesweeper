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
     static ArrayList<Integer> playerMove(String[][] arr) {
         ArrayList<Integer> list = new ArrayList<>();
         Scanner input = new Scanner(System.in);
         System.out.println("Lütfen hamleniz için koordinat seçiniz: ");
         int row, col;

         boolean validMove = false;

         while (!validMove) {
             System.out.println("Satır giriniz: ");
             row = input.nextInt();
             System.out.println("Sütun giriniz: ");
             col = input.nextInt();

             if (row < arr.length + 1 && row >= 0 && col < arr[0].length + 1 && col >= 0) {
                 if (!Objects.equals(arr[row - 1][col - 1], "  -  ")) {
                     System.out.println("Bu koordinat zaten seçilmiş. Lütfen farklı bir koordinat seçiniz.");
                 } else {
                     list.add(row);
                     list.add(col);
                     validMove = true;
                 }
             } else {
                 System.out.println("Geçersiz koordinatlar. Lütfen tekrar deneyiniz.");
             }
         }

         return list;
     }

    //copy() methodu shallow copy oluşturduğu için orijinal dizi de değişyordu.
    //deepcopy() hem array'in boyutu hem de elemenlarını kopyalarak bunun önüne geçiyor
    static String[][] deepCopy(String[][] original) {
        if (original == null) return null;

        String[][] copy = new String[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return copy;
    }
//    Random class'ı ile bomba sayımız kadar for döngüsü devam ediyor, her döngüde üst limit satır ya da sütun uzunluğu
//     olarak rastgele numara oluşturulup o koordinata bomba yerleştiriliyor. bu dizi yeni deepcopy() ile orijinal diziyle
//     aynı büyüklükte bir dizide saklanıyor
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

     //seçilen koordinatta bomba var mı diye kontrol ediyoruz
     static boolean checkIfMine(int a, int b, String[][] arr){
         return Objects.equals(arr[a][b], "  *  ");
     }

    static void checkNeighbours(int a, int b, String[][] arrBoard, String[][] arrBoardWithMines) {
        int numberOfMines = 0;
        int rows = arrBoard.length;
        int cols = arrBoard[0].length;
        // directions dizisi, mevcut hücrenin etrafındaki konumları belirtiyor
        int[][] directions = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1},           {0, 1},
                {1, -1}, {1, 0}, {1, 1}
        };

        for (int[] dir : directions) {
            //hücrenin etrafındaki bütün koordinatları geziyoruz
            int newRow = a + dir[0];
            int newCol = b + dir[1];

            //şartları kontrol ediyoruz. örneğin sol üst köşedeki nokta için üsütne veya soluna doğru gidilmesi hata
            // vereceği için onları değerlendirmeye almıyoruz
            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {

                if (Objects.equals(arrBoardWithMines[newRow][newCol], "  *  ")) {
                    numberOfMines++;
                }
            }
        }


        if (!Objects.equals(arrBoardWithMines[a][b], "  *  ")) {
            arrBoard[a][b] = "  " + numberOfMines + "  ";
        }
        //oynama alanını yeniliyoruz
        showBoard(arrBoard);
    }




}

