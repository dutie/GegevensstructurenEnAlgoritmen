package gna;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Tests that test whether the implementations of the sorting algorithms do sort.
 */
public class SortingAlgorithmsTest {


	@Test
	public void testQuickSort(){
		testQuickSort_isSorted();
		testQuickSort_isInExpectedCompares();
	}
	@Test
	public void testQuickSort_isSorted(){
		QuickSort qs = new QuickSort();
		Comparable[] unsorted_arr = {10,9,8,7,6,5,4,3,2,1};
		Comparable[] sorted_arr   = {1,2,3,4,5,6,7,8,9,10};

		assertEquals(sorted_arr, qs.sort_array(unsorted_arr));
	}
	@Test
	public void testQuickSort_isInExpectedCompares() {
		QuickSort qs = new QuickSort();
		Comparable[] unsorted_arr = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
		Comparable[] sorted_arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

		/**
		 * Quicksort Hoare has n + 1 compares.
		 * Quicksort is rather sensitive for (partially) sorted arrays.
		 * The average case of 1.39 n * log_2(10)
		 * Thus here we expect around 58 compares when we trace the algorithm on paper.
		 */
		long estimated_compares = 58;

		assertEquals(estimated_compares, qs.sort(unsorted_arr));
	}
	/**
	 * This test takes a long time to run.
	 * It is commented to prevent Ant to run this on initial check up.
	 */
	@Test
	public void testQuickSort_randomSizeCompares() throws IOException {
		int[] n_array = java.util.stream.IntStream.rangeClosed(0, 1000).toArray();

		QuickSort qs  = new QuickSort();
		SavingData savingData = new SavingData("quicksort");

		for(int n : n_array) {
			Comparable[] list = new Comparable[n];
			Random random = new Random();

			for (int i = 0; i < n; i++) {
				list[i] = random.nextInt(1000000);
			}
			long compares = qs.sort(list);


			assertTrue(qs.isSorted(list));
			savingData.add_to_data(compares, n);

		}
		savingData.write_to_csv();

	}

//	@Test
//	public void testQuickSort_doubling_ratio() throws IOException {
//		QuickSort qs = new QuickSort();
//		SortingAlgorithm.DoublingTest db = new SortingAlgorithm.DoublingTest();
//
//		SortingAlgorithm.TimeRatio tr = db.main(qs);
//		SavingData savingData = new SavingData("quicksort_dbr");
//		savingData.add_to_data_and_write(tr);
//	} TODO: Uncomment when longer tests are required

	@Test
	public void testSelectionSort(){
		SelectionSort ss = new SelectionSort();
		Comparable[] unsorted_arr = {10,9,8,7,6,5,4,3,2,1};
		Comparable[] sorted_arr   = {1,2,3,4,5,6,7,8,9,10};

		assertEquals(sorted_arr, ss.sort_array(unsorted_arr));
	}
	@Test
	public void testSelectionSort_randomSizeCompares() throws IOException {
		int[] n_array = java.util.stream.IntStream.rangeClosed(0, 1000).toArray();

		SelectionSort ss  = new SelectionSort();
		SavingData savingData = new SavingData("selectionsort");

		for(int n : n_array) {
			Comparable[] list = new Comparable[n];
			Random random = new Random();

			for (int i = 0; i < n; i++) {
				list[i] = random.nextInt(1000000);
			}
			long compares = ss.sort(list);


			assertTrue(ss.isSorted(list));
			savingData.add_to_data(compares, n);

		}
		savingData.write_to_csv();

	}

	@Test
	public void testInsertionSort(){
		InsertionSort is = new InsertionSort();
		Comparable[] unsorted_arr = {10,9,8,7,6,5,4,3,2,1};
		Comparable[] sorted_arr   = {1,2,3,4,5,6,7,8,9,10};

		assertEquals(sorted_arr, is.sort_array(unsorted_arr));
	}

	@Test
	public void testInsertionSort_randomSizeCompares() throws IOException {
		int[] n_array = java.util.stream.IntStream.rangeClosed(0, 1000).toArray();

		InsertionSort is  = new InsertionSort();
		SavingData savingData = new SavingData("insertionsort");

		for(int n : n_array) {
			Comparable[] list = new Comparable[n];
			Random random = new Random();

			for (int i = 0; i < n; i++) {
				list[i] = random.nextInt(1000000);
			}
			long compares = is.sort(list);


			assertTrue(is.isSorted(list));
			savingData.add_to_data(compares, n);

		}
		savingData.write_to_csv();

	}

	/**
	 * This test takes a long time to run.
	 * It is commented to prevent Ant to run this on initial check up.
	 */
//	@Test
//	public void testInsertionSort_doubling_ratio() throws IOException {
//		InsertionSort is = new InsertionSort();
//		SortingAlgorithm.DoublingTest db = new SortingAlgorithm.DoublingTest();
//
//		SortingAlgorithm.TimeRatio tr = db.main(is);
//		SavingData savingData = new SavingData("insertionsort_dbr");
//		savingData.add_to_data_and_write(tr);
//	} TODO: Uncomment when longer tests are required



}
