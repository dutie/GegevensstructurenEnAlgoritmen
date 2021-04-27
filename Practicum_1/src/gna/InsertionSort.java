package gna;

/**
 * Performs sort by using the Insertion Sort algorithm.
 * 
 */
public class InsertionSort extends SortingAlgorithm {
	/**
	 * Sorts the given array using insertion sort.
	 * 
	 * @see super
	 */
	public long sort(Comparable[] array) throws IllegalArgumentException {
		if (array == null) {
			throw new IllegalArgumentException("argument 'array' must not be null.");
		}
		AmountOfCompares amount_of_compares = new AmountOfCompares(0);
		int n = array.length;
		for (int i = 1; i < n ; i++){
			for(int j = i; j > 0 && less(array[j], array[j-1]); j--){
				amount_of_compares.add_one();
				exch(array, j, j-1);
			}
		}
		return amount_of_compares.get();
	}

	public Comparable[] sort_array(Comparable[] array) throws IllegalArgumentException {
		long amount_of_compares = sort(array);
		return array;
	}

	/**
	 * Constructor.
	 */
	public InsertionSort() {
	}
}
