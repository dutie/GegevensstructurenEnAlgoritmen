package gna;

import libpract.PriorityFunc;

import java.util.Collection;
import java.util.Arrays;
import java.util.HashSet;

public class Board {
	private int[][] tiles;
	private int[][] solution;

	// construct a board from an N-by-N array of tiles
	public Board(int[][] tiles) {
		this.tiles = deepCopy(tiles);

		this.setMovesMade(0);
		this.setPreviousState(null);
	}

	// construct a board from an N-by-N array of tiles and the previous state
	public Board(int[][] tiles, Board previousState) {
		this.tiles = deepCopy(tiles);

		this.setMovesMade(previousState.getMovesMade() + 1);
		this.setPreviousState(previousState);
	}

	// return number of blocks out of place
	public int hamming()
	{
		int currentNumber = 1;
		int hammingSum = 0;

		for (int i = 0; i < this.tiles.length; i++) {
			for (int j = 0; j < this.tiles[0].length; j++) {
				if (this.tiles[i][j] != 0 && this.tiles[i][j] != currentNumber) {
					hammingSum++;
				}
				currentNumber++;
			}
		}

		return hammingSum;
	}

	// return sum of Manhattan distances between blocks and goal
	public int manhattan() {
		int manhattanDistance = 0;
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles.length; j++) {
				if (tiles[i][j] != 0) {
					manhattanDistance += Math.abs(i - ((tiles[i][j] - 1) / tiles.length))
							+ Math.abs(j - ((tiles[i][j] - 1) % tiles.length));
				}
			}
		}
		return manhattanDistance;
	}

	// Does this board equal y. Two boards are equal when they both were constructed
	// using tiles[][] arrays that contained the same values.
	@Override
	public boolean equals(Object y) {
		if (!(y instanceof Board))
			return false;

		Board other = (Board) y;
		return Arrays.deepEquals(tiles, other.tiles);
	}

	// Since we override equals(), we must also override hashCode(). When two objects are
	// equal according to equals() they must return the same hashCode. More info:
	// - http://stackoverflow.com/questions/27581/what-issues-should-be-considered-when-overriding-equals-and-hashcode-in-java/27609#27609
	// - http://www.ibm.com/developerworks/library/j-jtp05273/
	@Override
	public int hashCode() {
		return Arrays.deepHashCode(tiles);
	}

	// return a Collection of all neighboring board positions
	public Collection<Board> neighbors() {
		Collection<Board> neighboursCollection = new HashSet<>();

		int[] emptySpotCoordinates = findEmptySpot(this.tiles);
		int i = emptySpotCoordinates[0];
		int j = emptySpotCoordinates[1];

		int[][] newTiles = deepCopy(this.tiles);

		if (i != 0) {
			newTiles[i][j] = newTiles[i - 1][j];
			newTiles[i - 1][j] = 0;

			Board newBoard = new Board(newTiles, this);
			if (!newBoard.equals(this.getPreviousState()) && (newBoard.isSolvable())) {
				neighboursCollection.add(newBoard);
			}

			newTiles[i - 1][j] = newTiles[i][j];
			newTiles[i][j] = 0;
		}
		if (i != this.tiles.length - 1) {
			newTiles[i][j] = newTiles[i + 1][j];
			newTiles[i + 1][j] = 0;

			Board newBoard = new Board(newTiles, this);
			if (!newBoard.equals(this.getPreviousState())&& (newBoard.isSolvable())) {
				neighboursCollection.add(newBoard);
			}

			newTiles[i + 1][j] = newTiles[i][j];
			newTiles[i][j] = 0;
		}
		if (j != 0) {
			newTiles[i][j] = newTiles[i][j - 1];
			newTiles[i][j - 1] = 0;

			Board newBoard = new Board(newTiles, this);
			if (!newBoard.equals(this.getPreviousState())&& (newBoard.isSolvable())) {
				neighboursCollection.add(newBoard);
			}

			newTiles[i][j - 1] = newTiles[i][j];
			newTiles[i][j] = 0;
		}
		if (j != this.tiles[0].length - 1) {
			newTiles[i][j] = newTiles[i][j + 1];
			newTiles[i][j + 1] = 0;

			Board newBoard = new Board(newTiles, this);
			if (!newBoard.equals(this.getPreviousState())&& (newBoard.isSolvable())) {
				neighboursCollection.add(newBoard);
			}

			newTiles[i][j + 1] = newTiles[i][j];
			newTiles[i][j] = 0;
		}

		return neighboursCollection;
	}

	// return a string representation of the board
	public String toString() {
		StringBuilder tilesString = new StringBuilder();

		for (int[] tile : this.tiles) {
			for (int j = 0; j < this.tiles[0].length; j++) {
				tilesString.append(tile[j]).append(" ");
				if (j == this.tiles[0].length - 1) {
					tilesString.append('\n');
				}
			}
		}

		return tilesString.toString();
	}

	// is the initial board solvable?
	public boolean isSolvable() {
		int[][] tilesCopy = deepCopy(this.tiles);

		int[] emptySpotCoordinates = findEmptySpot(tilesCopy);
		if (emptySpotCoordinates[0] == -1 || emptySpotCoordinates[1] == -1) {
			return false;
		}

		for (; emptySpotCoordinates[0] < tilesCopy.length - 1; emptySpotCoordinates[0]++) {
			tilesCopy[emptySpotCoordinates[0]][emptySpotCoordinates[1]] = tilesCopy[emptySpotCoordinates[0] + 1][emptySpotCoordinates[1]];
			tilesCopy[emptySpotCoordinates[0] + 1][emptySpotCoordinates[1]] = 0;
		}
		for (; emptySpotCoordinates[1] < tilesCopy[0].length - 1; emptySpotCoordinates[1]++) {
			tilesCopy[emptySpotCoordinates[0]][emptySpotCoordinates[1]] = tilesCopy[emptySpotCoordinates[0]][emptySpotCoordinates[1] + 1];
			tilesCopy[emptySpotCoordinates[0]][emptySpotCoordinates[1] + 1] = 0;
		}

		int[] permutationIndexes = new int[tilesCopy.length * tilesCopy[0].length - 1];
		int index = 1;

		for (int i = 0; i < tilesCopy.length; i++) {
			for (int j = 0; j < tilesCopy[0].length; j++) {
				if (tilesCopy[i][j] != 0) {
					permutationIndexes[tilesCopy[i][j] - 1] = (index++);
				}

			}
		}

		int sign = 1;

		for (int i = 1; i <= permutationIndexes.length; i++) {
			for (int j = i + 1; j <= permutationIndexes.length; j++) {
				sign *= Math.signum((double) (permutationIndexes[j - 1] - permutationIndexes[i - 1]) / (j - i));
			}
		}

		return sign > 0;
	}





	// Returns the position of a value in the board.
	public int getPositionOfTile(int value){
		int[] arr = transformArray(deepCopy(this.tiles));
		for(int position = 0; position < arr.length; position++){
			if(arr[position] == value) return position;
		}

		throw new IllegalArgumentException("[Is Solvable] Value is not found...");
	}

	// Transforms 2D array to 1D array.
	public int[] transformArray(int[][] array){
		int[] arr = new int[array.length*array.length];
		int pos = 0;
		for (int[] ints : array) {
			for (int value = 0; value < array.length; value++) {

				arr[pos] = ints[value];
				pos++;
			}
		}
		return arr;
	}

	// Variable for registering the amount of moves are made to reach this board state.
	private int movesMade;

	public int getMovesMade() {
		return movesMade;
	}

	public void setMovesMade(int movesMade) {
		this.movesMade = movesMade;
	}

	private Board previousState;

	public Board getPreviousState() {
		return previousState;
	}

	public void setPreviousState(Board previousState) {
		this.previousState = previousState;
	}

	// Deep copy a two dimensional array.
	private int[][] deepCopy(int[][] tiles)
	{
		return java.util.Arrays.stream(tiles).map(int[]::clone).toArray($ -> tiles.clone());
	}

	// Returns the dimensions of this board.
	public int[] getDimensions()
	{
		return new int[]{this.tiles.length, this.tiles[0].length};
	}

	// Find the position of the empty spot.
	private int[] findEmptySpot(int[][] tiles)
	{
		int[] result = new int[]{-1, -1};

		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				if (tiles[i][j] == 0)
				{
					result = new int[]{i, j};
					break;
				}
			}
		}

		return result;
	}

	// Create the solution according to the dimension of this board.
	public void setSolution(){
		int[] size = getDimensions();
		int sizeColumn = size[0];
		int sizeRow    = size[1];

		int[][] solutionBoard = new int[sizeRow][sizeColumn];

		int cnt = 1;
		int max = sizeColumn * sizeRow;
		for(int i = 0; i < sizeRow; i++){
			for(int j = 0; j < sizeColumn; j++){
				if(cnt == max){
					cnt = 0;
				}
				solutionBoard[i][j] = cnt;
				cnt+=1;

			}
		}

		this.solution = solutionBoard;
	}

	// Returns the solution to this board.
	public int[][] getSolution() {
		return solution;
	}

	public int getPriority(PriorityFunc priority) {
		if (priority == PriorityFunc.HAMMING) return this.hamming();
		else return this.manhattan();
	}

	public void updateMovesMade(Board state){
		this.previousState = state;
		this.movesMade = state.getMovesMade()+1;
	}
}

