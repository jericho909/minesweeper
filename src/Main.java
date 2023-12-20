import java.util.*;

public class Main {
    public static void main(String[] args) {

        ArrayList<Integer> playerChoice = MineSweeper.welcomePlayer();
        //return java'da sadece tek veri dödürebildiği için kullanıcının tercihleri
        //arraylist'te toplanıp öyle çıkartılıyor. sadece iki seçenek olduğu için
        //getFirst() ve getLast() her zaman aynı sırada verileri çıkartıyor. satır ve sütun.
        int row = playerChoice.getFirst();
        int col = playerChoice.getLast();

        //oyun tahtası oluşturulup kullanıcıya gösteriliyor
        String[][] gameBoard = MineSweeper.createBoard(row,col);
        MineSweeper.showBoard(gameBoard);

        //yukarıda anlatılan sebeple arraylist kullandım yine.
        ArrayList<Integer> playerMoveChoice = MineSweeper.playerMove(gameBoard);
        int playerMoveRow = playerMoveChoice.getFirst() - 1; // kullanıcı indis olarak değil, matriste gördüğü koordinatları verdiği için 1 eksiltiyoruz
        int playerMoveCol = playerMoveChoice.getLast() - 1;

        //kullanıcının hamlesi ile oyun alanı üzerinde hamle yaptıran method. alanı değiştirdikten sonra göstermek için showBoard
        //methodu bu methodun içinde bulunuyor. sürekli aynı methodu çağırmaktan daha elegant bir çözüm gibi geldi.
        MineSweeper.changeBoard(playerMoveRow, playerMoveCol, gameBoard);

    }
}