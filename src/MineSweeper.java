import java.util.*;
//değerlendirme formu 5
public class MineSweeper {

    //method to create the gameboard
    //değerlendirme formu 6
    static String[][] createBoard(int row, int col) {
        String[][] gameBoard = new String[row][col];
        for (String[] strings : gameBoard) {
            Arrays.fill(strings, "  -  ");
        }
        return gameBoard;
    }


    //method for displaying the gameboard
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


    //When the game starts, the method prompts the user for input regarding the game area. In Java, since the return
    //statement can only yield a single value, the choices are gathered within an ArrayList and then extracted from the list.
    //değerlendirme formu 7
    static ArrayList<Integer> welcomePlayer(){
        boolean isValidRow = false;
        boolean isValidCol = false;
        System.out.println("Mayın tarlası oyununa hoş geldiniz.");
        ArrayList<Integer> list = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        System.out.println("Lütfen oyun alanının boyutlarını giriniz.");

        do {
        System.out.println("Satır sayısı: ");
        int row = input.nextInt();
        if (row >= 2){
            list.add(row);
            isValidRow = true;
        } else {
            System.out.println("Satır sayısı 2'den küçük olamaz.");
        }
        } while (!isValidRow);

        do {
        System.out.println("Sütun sayısı: ");
        int col = input.nextInt();
        if (col >= 2){
            list.add(col);
            isValidCol = true;
        } else {
            System.out.println("Sütun sayısı 2'den az olamaz.");
        }
        }while (!isValidCol);

        return list;
    }
    //The method to prompt the user for selecting the grid on the gameboard. In Java, since the return
    //statement can only yield a single value, the choices are gathered within an ArrayList and then extracted from the list.
     //değerlendirme formu 9
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

             if (row < arr.length + 1 && row > 0 && col < arr[0].length + 1 && col > 0) {
                 if (!Objects.equals(arr[row - 1][col - 1], "  -  ")) {
                     System.out.println("Bu koordinat zaten seçilmiş. Lütfen farklı bir koordinat seçiniz.");
                 } else {
                     list.add(row);
                     list.add(col);
                     validMove = true;
                 }
             } else {
                 //değerlendirme formu 10
                 System.out.println("Geçersiz koordinatlar. Lütfen tekrar deneyiniz.");
             }
         }

         return list;
     }

    // Because the copy() method creates a shallow copy, the original array was also being altered.
    // The deepcopy() method prevents this by copying both the size and elements of the array.
    static String[][] deepCopy(String[][] original) {
        if (original == null) return null;

        String[][] copy = new String[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return copy;
    }

    // With the Random class, a for loop continues until it reaches the number of bombs we have. In each iteration, a random number
    // within the upper limit of the row or column length is generated, and a bomb is placed at that coordinate. This
    // array is stored in a new array of the same size as the original using the deepcopy() method.
    //değerlendirme formu 8
    static String[][] generateMines(String[][] arr) {
        int x = arr.length;
        int y = arr[0].length;

        int numberOfMines = (x * y) / 4;
        String[][] arrayWithMines = deepCopy(arr);
        Random rand = new Random();

        int minesPlaced = 0;
        while (minesPlaced < numberOfMines) {
            int randomRow = rand.nextInt(x);
            int randomCol = rand.nextInt(y);

            if (!arrayWithMines[randomRow][randomCol].equals("  *  ")) {
                arrayWithMines[randomRow][randomCol] = "  *  ";
                minesPlaced++;
            }
        }

        return arrayWithMines;
    }


    // We are checking if there is a bomb at the selected coordinate.
    //değerlendirme formu 13
     static boolean checkIfMine(int a, int b, String[][] arr){
         return Objects.equals(arr[a][b], "  *  ");
     }

    static void checkNeighbours(int a, int b, String[][] arrBoard, String[][] arrBoardWithMines) {
        int numberOfMines = 0;
        int rows = arrBoard.length;
        int cols = arrBoard[0].length;
        // The 'directions' array specifies the positions around the current cell.
        int[][] directions = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1},           {0, 1},
                {1, -1}, {1, 0}, {1, 1}
        };

        for (int[] dir : directions) {
            // We are iterating through all the coordinates around the cell.
            int newRow = a + dir[0];
            int newCol = b + dir[1];


            // We are checking the conditions. For instance, for the point in the top-left corner, moving upwards or to the left
            // would cause an error, so we are not considering those movements.
            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {

                if (Objects.equals(arrBoardWithMines[newRow][newCol], "  *  ")) {
                    numberOfMines++;
                }
            }
        }
        //değerlendirme formu 12

        if (!Objects.equals(arrBoardWithMines[a][b], "  *  ")) {
            arrBoard[a][b] = "  " + numberOfMines + "  ";
        }
        //Updating the gameboard
        //değerlendirme formu 11
        showBoard(arrBoard);
    }




}

