package gna;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class UnitTests {

    @Test
    public void testBoard() {
        String[][] tiles = new String[][]{
                {".", ".", "M", ".", ".", "."},
                {"H", "M", ".", ".", "X", "X"},
                {"H", ".", "E", ".", "E", "E"},
                {"E", "E", "X", ".", ".", "."}};
        int energy = 4;
        Board board = new Board(tiles, energy);

        String var1 = String.format("Expected an energy amount of %d and got %d", energy, board.getEnergy());
        String var2 = String.format("Expected 6 columns and got %d", board.cols());
        String var3 = String.format("Expected 4 rows and got %d", board.rows());
        assertEquals(var1, 4, board.getEnergy());
        assertEquals(var2, 6, board.cols());
        assertEquals(var3, 4, board.rows());
    }

    @Test
    public void testGraphCreation() {
        String[][] tiles = new String[][]{
                {".", "M"},
                {"H", "X"}};
        int energy = 4;
        Board board = new Board(tiles, energy);
    }

    @Test
    public void shortestPathEasy() {
        String[][] tiles = new String[][]{
                {".", ".", "M", ".", ".", "."},
                {"H", "M", ".", ".", ".", "M"},
                {"H", ".", "E", ".", "E", "E"},
                {"E", "E", "X", ".", ".", "."}};
        int energy = 4;
        Board board = new Board(tiles, energy);


      Arrays.stream(board.getPathSequence()).sequential().forEach(System.out::println);

    }
}
