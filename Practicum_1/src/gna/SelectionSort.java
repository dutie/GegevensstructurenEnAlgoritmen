package gna;

/**
 * Performs sort by using the Selection Sort algorithm.
 * 
 */
public class SelectionSort extends SortingAlgorithm {
	/**
	 * Sorts the given array using selection sort.
	 * 
	 * @see super
	 */
	public long sort(Comparable[] array) throws IllegalArgumentException {
		if (array == null) {
			throw new IllegalArgumentException("argument 'array' must not be null.");
		}
		AmountOfCompares amount_of_compares = new AmountOfCompares(0);
		int n = array.length;
		for (int i = 0; i < n; i++){
			int min = i;
			for (int j = i+1; j < n; j++){
				if (less(array[j], array[min])) min = j;
				amount_of_compares.add_one();
			}
			exch(array, i, min);
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
	public SelectionSort() {
	}
}
