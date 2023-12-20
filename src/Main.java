import java.util.*;

public class Main {
    public static void main(String[] args) {

        ArrayList<Integer> playerChoice = MineSweeper.welcomePlayer();
        //return java'da sadece tek veri döndürebildiği için kullanıcının tercihleri
        //arraylist'te toplanıp öyle çıkartılıyor. sadece iki seçenek olduğu için
        //getFirst() ve getLast() her zaman aynı sırada verileri çıkartıyor. satır ve sütun.
        int row = playerChoice.getFirst();
        int col = playerChoice.getLast();
        int numberOfCells = col  * row;
        int numberOfMines = (col * row) / 4;
        int numberOfPlayerMoves = 0;

        //oyun tahtası oluşturulup kullanıcıya gösteriliyor
        String[][] gameBoard = MineSweeper.createBoard(row,col);
        String[][] gameBoardWithMines = MineSweeper.generateMines(gameBoard);
        MineSweeper.showBoard(gameBoard);

        boolean foundMine = false;
        //oyun döngüsünün başlangıcı
        do {
        //yukarıda anlatılan sebeple arraylist kullandım yine.
        ArrayList<Integer> playerMoveChoice = MineSweeper.playerMove(gameBoard);
        int playerMoveRow = playerMoveChoice.getFirst() - 1; // kullanıcı indis olarak değil, matriste gördüğü koordinatları verdiği için 1 eksiltiyoruz
        int playerMoveCol = playerMoveChoice.getLast() - 1;

        numberOfPlayerMoves++;



        //kullanıcı hamlesi ile mayın bulduysa oyun döngüsünden çıkıp, game over mesajı gösteriyoruz
        if (MineSweeper.checkIfMine(playerMoveRow,playerMoveCol,gameBoardWithMines)){
            System.out.println("Mayına bastın! Oyunu kaybettin :/");
            MineSweeper.showBoard(gameBoardWithMines);
            foundMine = true;
            break;
        }

        MineSweeper.checkNeighbours(playerMoveRow, playerMoveCol, gameBoard, gameBoardWithMines);
        if (numberOfCells - numberOfPlayerMoves == numberOfMines + 1) {
            System.out.println("Kazandın! Tebrikler!");
            break;
        }
        } while (!foundMine);

    }
}