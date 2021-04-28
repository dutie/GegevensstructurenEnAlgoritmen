package gna;

import libpract.StdIn;
import libpract.PriorityFunc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Main
{
	public static void main( String[] args ) throws FileNotFoundException {
		Board initial;
		if(args != null){
			File myObj = new File(args[0]);
			Scanner myReader = new Scanner(myObj);
			int N = Integer.parseInt(myReader.nextLine());
			int[][] tiles = new int[N][N];
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					tiles[i][j] = Integer.parseInt(myReader.next());

			initial = new Board(tiles);

		}else {
			int N = StdIn.readInt();
			int[][] tiles = new int[N][N];

			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					tiles[i][j] = StdIn.readInt();

			initial = new Board(tiles);
		}
		if (!initial.isSolvable())
		{
			System.out.println("No solution possible");
		}
		else
		{
			StopWatch stopWatch = new StopWatch();
			Solver solver = new Solver(initial, PriorityFunc.MANHATTAN);
			double elapsedTime = stopWatch.elapsedTime();

//			for (Board board : solver.solution())
//				System.out.println(board);

			System.out.println("elapsed time: "+ elapsedTime);
			System.out.println("Minimum number of moves = " + Integer.toString(solver.solution().size() - 1));
		}
	}
}


