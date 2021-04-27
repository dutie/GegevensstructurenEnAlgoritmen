package gna;

import java.util.Arrays;

/**
 * Performs sort by using the Quick Sort algorithm.
 *
 */
public class QuickSort extends SortingAlgorithm{
	/**
	 * Sorts the given array using quick sort.
	 * 
	 * @see super
	 */
	public long sort(Comparable[] array) throws IllegalArgumentException {
		if (array == null) {
			throw new IllegalArgumentException("argument 'array' must not be null.");
		}

		long amount_of_compares = sort(array, 0, array.length - 1, new AmountOfCompares(0));

		return  amount_of_compares;

	}

	public Comparable[] sort_array(Comparable[] array){
		long amount_of_compares = sort(array, 0, array.length - 1, new AmountOfCompares(0));
		return array;
	}

	private static long sort(Comparable[] array, int lo, int hi, AmountOfCompares amount_of_compares){

		if(hi <= lo) return 0;
		int j = partition(array, lo, hi, amount_of_compares);
		sort(array, lo, j-1, amount_of_compares);
		sort(array, j+1, hi, amount_of_compares);

		return amount_of_compares.get();
	}
	private static int partition(Comparable[] array, int lo, int hi, AmountOfCompares amount_of_compares){
		int i = lo;
		int j = hi+1;
		Comparable v = array[lo];

		while(true){
			amount_of_compares.add_one();

			while(less(array[++i],v)){
				if (i == hi) break;
				amount_of_compares.add_one();
			}
			amount_of_compares.add_one();
			while(less(v,array[--j])){
				if (j == lo) break;
				amount_of_compares.add_one();
			}
			if (i >= j) break;
			exch(array, i, j);
		}

		exch(array, lo, j);
		return  j;
	}



	/**
	 * Constructor.
	 */
	public QuickSort() {
	}
}
