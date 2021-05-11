package gna;

import java.util.*;

public class Board {

	private String[][] tiles;
	private int energy;
	private int[][] costs;
	private List<String> pathSequence = new ArrayList<>();

	/**
	 * Constructor for an n-by-m, or rows-by-cols board.
	 *
	 * @param tiles  2D-matrix of tiles.
	 * @param energy The starting energy of the runner.
	 */
	public Board(String[][] tiles, int energy) {
		this.tiles = tiles;
		this.energy = energy;
		costs = new int[tiles.length][tiles[0].length];
		makeCosts(tiles.length, tiles[0].length);
		getPathSequence();
	}

	public void makeCosts(int row_goal, int col_goal) {
		for (int row = 0; row < row_goal; row++) {
			for (int col = 0; col < col_goal; col++) {
				if (row == 0 && col == 0) {
					costs[0][0] = 0;
				} else {
					costs[row][col] = getCost(tiles[row][col]);

				}
				System.out.print(costs[row][col] + " ");
			}
			System.out.print("\n");
		}

	}


	private int getCost(String type_tile) {
		return switch (type_tile) {
			case "." -> 1;
			case "M" -> 2;
			case "H" -> 3;
			case "E" -> -1;
			default -> Integer.MAX_VALUE;
		};
	}


	/**
	 * @return Number of columns
	 */
	public int cols() {
		return tiles[0].length;
	}

	/**
	 * @return Number of rows
	 */
	public int rows() {
		return tiles.length;
	}

	/**
	 * Gets an array consisting of "RIGHT" and "DOWN" that describes the path from start (0, 0) to
	 * finish (rows, cols) such that the energy at the finish is optimal, and is not equal to or lower than
	 * zero anywhere along the path.
	 *
	 * @return Array consisting of "RIGHT" and "DOWN"
	 */
	public String[] getPathSequence() {
		this.energy = this.energy - bestPath(this.costs.clone());
		System.out.println("Remaining energy: " + energy);
		String[] str = this.pathSequence.toArray(new String[this.pathSequence.size()]);
		return str;
	}


	int bestPath(int[][] cost) {
		int row = cost.length;
		int col = cost[0].length;

		for (int i = 1; i < row; i++) {
			if(cost[i-1][0] != Integer.MAX_VALUE) {
				cost[i][0] += cost[i - 1][0];
			}
		}

		for (int j = 1; j < col; j++) {
			if(cost[0][j-1] != Integer.MAX_VALUE) {
				cost[0][j] += cost[0][j - 1];
			}
		}

		System.out.print("\n");

		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				if(cost[i][j] != Integer.MAX_VALUE) {
					System.out.print("Cost A: " + cost[i][j] +"\n");

						if (cost[i - 1][j] < cost[i][j - 1]) {
							this.pathSequence.add("DOWN: " + tiles[i][j]);
							cost[i][j] += cost[i - 1][j];

						} else {
							this.pathSequence.add("RIGHT: " + tiles[i][j]);
							cost[i][j] += cost[i][j - 1];

						}


						System.out.print("Cost B: " + cost[i][j] + "\n");
					}
				}
			}


		Arrays.stream(cost).forEach(i -> {
			Arrays.stream(i).forEach(j -> System.out.print(j + " "));
			System.out.print("\n");
		});

		// Returning the value in
		// last cell
		return cost[row - 1][col - 1];
	}


	/**
	 * Returns the energy that the runner has when he reaches the finish line.
	 *
	 * @return The energy at the finish (rows, cols).
	 */
	public int getFinishTileEnergy() {

		return this.energy;
	}

	/**
	 * Is solvable if the amount of energy left in finish tile is higher than 0, and there
	 * exists a path such that the energy along this path is strictly above zero as well.
	 *
	 * @return True if there is a valid path from start to finish, false otherwise.
	 */
	public boolean isSolvable() {
		if (this.tiles[this.tiles.length - 1][this.tiles[0].length - 1].equals("X")) return false;
		if(this.energy < 0) return false;
		return true;
	}

	/**
	 * Override equals and hashCode.
	 */
	@Override
	public boolean equals(Object y) {
		// TODO: Implement
		return false;

	}

	@Override
	public int hashCode() {
		// TODO: Implement
		return 0;
	}

	/**
	 * Return a printable String representation of the board
	 */
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder(256);
		for (int row = 0; row < this.rows(); row++) {
			for (int col = 0; col < this.cols(); col++) {
				str.append(tiles[row][col]);
				str.append(" ");
			}
			str.append("\n");
		}

		return str.toString();
	}

	public String[][] getTiles() {
		return tiles;
	}

	public void setTiles(String[][] tiles) {
		this.tiles = tiles;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}
}
