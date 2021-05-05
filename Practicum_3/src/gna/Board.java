package gna;

public class Board {

    private String[][] tiles;
    private int energy;

	/**
     * Constructor for an n-by-m, or rows-by-cols board.
     * @param tiles  2D-matrix of tiles.
     * @param energy The starting energy of the runner.
     */
	public Board( String[][] tiles, int energy )
	{
        // TODO: Implement
	}
	
	/**
	 * 
	 * @return Number of columns
	 */
	public int cols()
	{
		// TODO: Implement
		return 0;
	}
	
	/**
	 * 
	 * @return Number of rows
	 */
	public int rows()
	{
		// TODO: Implement
		return 0;
	}

    /**
     * Gets an array consisting of "RIGHT" and "DOWN" that describes the path from start (0, 0) to
     * finish (rows, cols) such that the energy at the finish is optimal, and is not equal to or lower than 
     * zero anywhere along the path.
     * @return Array consisting of "RIGHT" and "DOWN"
     */
    public String[] getPathSequence() {
        // TODO: Implement
        return null;
    }

    /**
     * Returns the energy that the runner has when he reaches the finish line.
     * @return The energy at the finish (rows, cols).
     */
    public int getFinishTileEnergy() {
        // TODO: Implement
        return 0;
    }

    /**
     * Is solvable if the amount of energy left in finish tile is higher than 0, and there
     * exists a path such that the energy along this path is strictly above zero as well.
     * @return True if there is a valid path from start to finish, false otherwise.
     */
    public boolean isSolvable() {
        // TODO: Implement
        return false;
    }

    /**
     * Override equals and hashCode.
     */
	@Override
	public boolean equals(Object y)
	{        
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
	public String toString()
	{
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
}
