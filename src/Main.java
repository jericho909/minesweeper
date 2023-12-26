import java.util.*;

public class Main {
    public static void main(String[] args) {

        ArrayList<Integer> playerChoice = MineSweeper.welcomePlayer();
        // In Java, a method can only return a single value. Therefore, the user's preferences are collected in an ArrayList
        // and extracted from there. As there are only two options, getFirst() and getLast() always extract the data in the same order: row and column.
        int row = playerChoice.getFirst();
        int col = playerChoice.getLast();
        int numberOfCells = col  * row;
        int numberOfMines = (col * row) / 4;
        int numberOfPlayerMoves = 0;

        // The game board is created and displayed to the user.
        String[][] gameBoard = MineSweeper.createBoard(row,col);
        String[][] gameBoardWithMines = MineSweeper.generateMines(gameBoard);
        MineSweeper.showBoard(gameBoard);

        boolean foundMine = false;
        //Start of the game loop
        do {

        //I used an ArrayList for the reasons mentioned above
        ArrayList<Integer> playerMoveChoice = MineSweeper.playerMove(gameBoard);
        int playerMoveRow = playerMoveChoice.getFirst() - 1; // We decrement by 1 because the user provides coordinates as seen in the matrix, not as indices.
        int playerMoveCol = playerMoveChoice.getLast() - 1;

        numberOfPlayerMoves++;



        //If the user uncovers a mine with their move, we exit the game loop and display the game over message.
        if (MineSweeper.checkIfMine(playerMoveRow,playerMoveCol,gameBoardWithMines)){
            System.out.println("Mayına bastın! Oyunu kaybettin :/");
            MineSweeper.showBoard(gameBoardWithMines);
            foundMine = true;
            break;
        }
        //değerlendirme formu 14 - 15
        MineSweeper.checkNeighbours(playerMoveRow, playerMoveCol, gameBoard, gameBoardWithMines);
        if (numberOfCells - numberOfPlayerMoves == numberOfMines) {
            System.out.println("Kazandın! Tebrikler!");
            break;
        }
        } while (!foundMine);

    }
}