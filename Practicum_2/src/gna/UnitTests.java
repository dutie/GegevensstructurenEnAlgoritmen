package gna;

import libpract.PriorityFunc;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * A number of JUnit tests for Solver.
 *
 * Feel free to modify these to automatically test puzzles or other functionality
 */
public class UnitTests {

  @Test
  public void test_if_board_is_made_correctly(){
    System.out.println("#===Testing the function that checks if the requested board is made.===#");
    int [][] b1t = {{1,2},{3,0}};
    Board b1 = new Board(b1t);
    b1.setSolution();
    Board b2 = new Board(b1.getSolution());

    Assert.assertEquals(b1, b2);
  }

  @Test
  public void test_if_neighbors_are_correct(){
    System.out.println("#===Testing the function that creates neighbors.===#");
    int [][] b1t = {
            {1,2},
            {3,0}};
    int [][] b2t = {
            {1,0},
            {3,2}};
    int [][] b3t = {
            {1,2},
            {0,3}};
    Board b1 = new Board(b1t);
    Board b2 = new Board(b2t);
    Board b3 = new Board(b3t);

    boolean neighborsAreCorrect = true;
    for(Board neighbor : b1.neighbors()){
      if(!(neighbor.equals(b3) || neighbor.equals(b2))){
        neighborsAreCorrect = false;
      }
    }
    Assert.assertTrue(neighborsAreCorrect);

  }

  @Test
  public void test_if_costs_are_correct(){
    System.out.println("#===Testing the function that makes the costs.===#");
    int [][] b1t = {
            {1,2},
            {3,0}};
    Board b1 = new Board(b1t);
    String var1 = String.format("De eerste hamming heeft een kost van %d ipv 0.", b1.hamming());
    String var2 = String.format("De eerste manhattan heeft een kost van %d ipv 0.", b1.manhattan());

    Assert.assertEquals(var1,0, b1.hamming());
    Assert.assertEquals(var2, 0, b1.manhattan());

    int [][] b2t = {
            {0,2},
            {3,1}};
    Board b2 = new Board(b2t);
    String var3 = String.format("De tweede hamming heeft een kost van %d ipv 1.", b2.hamming());
    String var4 = String.format("De tweede manhattan heeft een kost van %d ipv 2.", b2.manhattan());

    Assert.assertEquals(var3, 1, b2.hamming());
    Assert.assertEquals(var4, 2, b2.manhattan());
  }

  @Test
  public void test_when_solvable(){
    System.out.println("#===Testing the function that checks if the board can be solved (given a solvable board).===#");
    int [][] array = {{2, 8, 7,11},{5, 0, 4,15},{13 ,9,14, 3},{1,10, 6,12}};
    isSolvable(true, array);
  }

  @Test
  public void test_when_not_solvable(){
    System.out.println("#===Testing the function that checks if the board can be solved (given an unsolvable board).===#");
    int [][] array = {{1,2,3},{4,6,5},{7,8,0}};
    isSolvable(false, array);
  }

  public void isSolvable(Boolean solvable, int[][] array) {

    Board initial = new Board(array);
    System.out.println(initial.toString());
    if (!initial.isSolvable())
    {
      System.out.println("No solution possible");
    }
    else {
      System.out.println("At least one solution possible");
    }

    Assert.assertEquals(solvable, initial.isSolvable());
  }


  public void getNeighbors(Board initial, int counter) {
    System.out.println("Original puzzle board " + counter + ":" );
    System.out.println(initial.toString());
    for (Board b : initial.neighbors()) {
      counter +=1;

      System.out.println("\t- Neighboring puzzle board number "+counter+":\r\n" + b.toString());
      System.out.println("\t\t+ Hamming: " + b.hamming());
      System.out.println("\t\t+ Manhattan: " + b.manhattan());

      System.out.println("\t\t" + "+ Number " + counter + "'s neighbors:" );
      for (Board n : b.neighbors()){
        System.out.println("\t\t\t- Neighboring puzzle board:\r\n" + n.toString());
        System.out.println("\t\t\t\t+ Hamming: " + n.hamming());
        System.out.println("\t\t\t\t+ Manhattan: " + n.manhattan());
      }
    }
    System.out.println("-".repeat(40));
  }

  @Test
  public void test_solution_creator(){
    int [][] arraySmall = {{1,5,2},{4,0,3},{7,8,6}};
    int [][] solutionBoard = {{1,2,3},{4,5,6},{7,8,0}};

    Board problem = new Board(arraySmall);

    problem.setSolution();
    arraySmall = problem.getSolution();

    Assert.assertEquals(solutionBoard, arraySmall);
  }

  @Test
  public void test_priorityqueue_easy() {
// int [][] arraySmall = {{1,5,2},{4,0,3},{7,8,6}};
//    int[][] arraySmall = {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
    int[][] arraySmall =
            {{1, 2, 3, 4, 5, 7,14
           },{8, 9,10,11,12,13, 6
           },{15,16,17,18,19,20,21
           },{22,23,24,25,26,27,28
           },{29,30,31,32,0,33,34
           },{36,37,38,39,40,41,35
           },{43,44,45,46,47,48,42,}};

    Board initial = new Board(arraySmall);
    System.out.println(initial.toString());

    if (!initial.isSolvable()) {
      System.out.println("No solution possible");
    } else {
      Solver solver = new Solver(initial, PriorityFunc.HAMMING);

      System.out.print("The initial ");
      for (Board b : solver.solution()) {
        System.out.println("Move: \r\n");
        System.out.print(b + "\r\n");
      }

    }

  }

  @Test
  public void test04EasyPuzzle22Manhattan() {
    int[][] var1 = new int[][]{{1, 2, 3, 4, 5}, {12, 6, 8, 9, 10}, {0, 7, 13, 19, 14}, {11, 16, 17, 18, 15}, {21, 22, 23, 24, 20}};
    int[][] var2 = new int[][]{{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}, {21, 22, 23, 24, 0}};
    Board var3 = new Board(var1);
    Board var4 = new Board(var2);
    Solver var5 = new Solver(var3, PriorityFunc.MANHATTAN);
    ArrayList var6 = new ArrayList(var5.solution());
    String var7 = "De eerste bordconfiguratie bij Solver.solution() op puzzle22.txt is niet gelijk aan de initiele bordconfiguratie.";
    Assert.assertEquals(var7, var3, var6.get(0));
    var7 = "De laatste bordconfiguratie bij Solver.solution() op puzzle22.txt is geen correcte oplossing.";
    Assert.assertEquals(var7, var4, var6.get(var6.size() - 1));
    int var8 = var5.solution().size() - 1;
    var7 = String.format("Je optimale aantal verplaatsingen bij puzzle22.txt is %d terwijl dit 12 moet zijn.", var8);
    Assert.assertEquals(var7, 12, var8);
  }


  @Test
  public void testingGivenPuzzles(){
//    Main.main(new String[]{"boards/puzzle3x3-impossible.txt"});
    Main.main(new String[]{"boards/puzzle04.txt"});
    Main.main(new String[]{"boards/puzzle20.txt"});
    Main.main(new String[]{"boards/puzzle22.txt"});
    Main.main(new String[]{"boards/puzzle24.txt"});
    Main.main(new String[]{"boards/puzzle26.txt"});
    Main.main(new String[]{"boards/puzzle28.txt"});
    Main.main(new String[]{"boards/puzzle30.txt"});
    Main.main(new String[]{"boards/puzzle32.txt"});
    Main.main(new String[]{"boards/puzzle34.txt"});
    Main.main(new String[]{"boards/puzzle36.txt"});
    Main.main(new String[]{"boards/puzzle38.txt"});
    Main.main(new String[]{"boards/puzzle40.txt"});
    Main.main(new String[]{"boards/puzzle42.txt"});
  }

}
