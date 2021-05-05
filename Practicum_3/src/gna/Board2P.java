package gna;
public class Board2P {

    private String[][] tiles;
    private int energyP1;
    private int energyP2;

	/**
     * Constructor for an n-by-m or rows-by-cols board.
     * @param tiles  2D-matrix of tiles.
     * @param energyP1 The starting energy of runner 1 starting in (0, 0).
     * @param energyP2 The starting energy of runner 2 starting in (rows, 0).
     */
	public Board2P( String[][] tiles, int energyP1, int energyP2 )
	{
        // TODO: Implement
	}
	
	public int cols()
	{
		// TODO: Implement
		return 0;
	}

	public int rows()
	{
		// TODO: Implement
		return 0;
	}

    /**
     * Gets an array consisting of "RIGHT" and "DOWN" that describes the path of runner 1 from start (0, 0) to
     * finish (rows, cols) such that the energy at the finish is optimal, and is not equal to or lower than 
     * zero anywhere along the path, and crosses the path of runner 2 exactly once.
     * @return Array consisting of "RIGHT" and "DOWN"
     */
    public String[] getPathSequenceP1() {
        // TODO: Implement
        return null;
    }

    /**
     * Gets an array consisting of "RIGHT" and "UP" that describes the path of runner 2 from start (rows, 0) to
     * finish (0, cols) such that the energy at the finish is optimal, and is not equal to or lower than 
     * zero anywhere along the path, and crosses the path of runner 1 exactly once.
     * @return Array consisting of "RIGHT" and "UP"
     */
    public String[] getPathSequenceP2() {
        // TODO: Implement
        return null;
    }

    /**
     * Returns the energy that the runner 1 has when he reaches the finish line (rows, cols).
     * @return The energy at the finish (rows, cols).
     */
    public int getFinishTileEnergyP1() {
        // TODO: Implement
        return 0;
    }

    /**
     * Returns the energy that the runner 2 has when he reaches the finish line (0, cols).
     * @return The energy at the finish (0, cols).
     */
    public int getFinishTileEnergyP2() {
        // TODO: Implement
        return 0;
    }

    /**
     * Is solvable if the amount of energy left of both runners is higher than 0, and there
     * exists a path for both runners such that the energy along these paths is strictly 
     * above zero as well and they cross each other exactly once.
     * @return True if there is a valid path for both runners from start to finish, false otherwise.
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
