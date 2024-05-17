package org.iesvdm.sudoku;

//import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
public class SudokuTest {

    @Test
    void failTest() {
        Sudoku sudoku = new Sudoku();
        sudoku.fillBoardBasedInCluesRandomlySolvable();
        //sudoku.fillBoardBasedInCluesRandomly();
        sudoku.printBoard();
    }

    @Test
    void fillBoardRandomly(){
        //When
        Sudoku sudoku = new Sudoku();
        int gridSize = 6;
        int numClues = 52;

        //do
        sudoku.fillBoardBasedInCluesRandomly();
        sudoku.printBoard();

        //Then
        assertThat(gridSize <= 9 && numClues <= 63).isTrue();
    }
    @Test
    void fillBoardBasedInCluesRandomly(){
        //When
        Sudoku sudoku = new Sudoku();
        int numClues = 60;
        //do
        sudoku.fillBoardBasedInCluesRandomly();
        sudoku.printBoard();

        //Then
        assertThat(numClues <= 63).isTrue();
    }


    @Test
    void fillBoardBasedInCluesRandomlySolvable(){
        //When
        Sudoku sudoku = new Sudoku();
        int gridSize = 3;
        int numClues = 38;

        //do
        sudoku.fillBoardBasedInCluesRandomlySolvable();
        sudoku.printBoard();

        //Then
        sudoku.solveBoard();
        assertThat(sudoku.solveBoard()).isNotNull();
    }

    @Test
    void  fillBoardSolvable() {
        //When
        Sudoku sudoku = new Sudoku();
        int gridSize = 1;
        int numClues = 10;

        //do
        sudoku.setGridSize(gridSize);
        sudoku.setNumClues(numClues);

        sudoku.fillBoardRandomly();
        sudoku.printBoard();

        //Then
        assertThat(sudoku.solveBoard()).isEqualTo(true);
    }

    @Test
    void fillBoardUnsolvable() {
        //When
        Sudoku sudoku = new Sudoku();
        int gridSize = 8;
        int numClues = 60;

        //do
        sudoku.setGridSize(gridSize);
        sudoku.setNumClues(numClues);

        sudoku.fillBoardUnsolvable();
        sudoku.printBoard();

        //Then
        assertThat(sudoku.solveBoard()).isEqualTo(false);
    }

    @Test
    void copyBoard() {
        //When
        Sudoku sudoku = new Sudoku();
        int gridSize = 3;
        int[][] boardNew = new int[gridSize][gridSize];

        //do
        sudoku.copyBoard(boardNew);

        //Then
        assertThat(sudoku.solveBoard()).isEqualTo(boardNew);
    }

    @Test
    void printBoard() {
        //When
        Sudoku sudoku = new Sudoku();
        int gridSize = 1;
        int numClues = 10;

        //do
        sudoku.setGridSize(gridSize);
        sudoku.setNumClues(numClues);

        sudoku.fillBoardRandomly();
        sudoku.printBoard();

        //Then
        assertThat(sudoku.solveBoard()).isEqualTo(true);
    }

    @Test
    void isNumberInRow(){
        //When
        Sudoku sudoku = new Sudoku();
        int gridSize = 2;
        int numClues = 20;
        int number = 1;
        int row = 1;

        //do
        sudoku.setGridSize(gridSize);
        sudoku.setNumClues(numClues);

        sudoku.fillBoardRandomly();
        sudoku.printBoard();

        //Then
        assertThat(sudoku.isNumberInRow(number, row)).isTrue();
    }

    @Test
    void isNumberInColumn(){
        //When
        Sudoku sudoku = new Sudoku();
        int gridSize = 2;
        int numClues = 20;
        int number = 0;
        int column = 0;

        //do
        sudoku.setGridSize(gridSize);
        sudoku.setNumClues(numClues);

        sudoku.fillBoardRandomly();
        sudoku.printBoard();

        //Then
        assertThat(sudoku.isNumberInColumn(number, column)).isTrue();
    }

    @Test
    void isNumberInBox(){
        //When
        Sudoku sudoku = new Sudoku();
        int gridSize = 5;
        int numClues = 10;
        int row = 2;
        int column = 2;
        int number = 4;
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;

        //do
        sudoku.setGridSize(localBoxRow);
        sudoku.setNumClues(localBoxColumn);

        sudoku.fillBoardRandomly();
        sudoku.printBoard();

        assertThat(sudoku.isNumberInBox(number, row, column)).isTrue();

    }

    @Test
    void isValidPlacement(){
        //When
        Sudoku sudoku = new Sudoku();
        int gridSize = 5;
        int numClues = 10;
        int row = 2;
        int column = 2;
        int number = 4;
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;

        //do
        sudoku.setGridSize(localBoxRow);
        sudoku.setNumClues(localBoxColumn);

        sudoku.fillBoardRandomly();
        sudoku.printBoard();

        assertThat(sudoku.isNumberInBox(number, row, column) && sudoku.isNumberInRow(number, row) && sudoku.isNumberInColumn(number, column)).isFalse();
    }

    @Test
    void solveBoard(){
        //When
        Sudoku sudoku = new Sudoku();
        int gridSize = 5;
        int numClues = 10;

        //do
        sudoku.setGridSize(gridSize);
        sudoku.setNumClues(numClues);

        sudoku.fillBoardRandomly();
        sudoku.printBoard();

        //Then
        assertThat(sudoku.solveBoard()).isEqualTo(false);
    }

}
