package gna;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Do not remove the "implements libpract.SortingAlgorithm".
public abstract class SortingAlgorithm implements libpract.SortingAlgorithm{
	/**
	 * Sorts the given array.
	 * 
	 * Note: to know whether "array[i] < array[j]", one writes "array[i].compareTo(array[j]) < 0".
	 * 
	 * @throws IllegalArgumentException
	 * 	array == null
	 * @post
	 * 	After execution of this method, array is sorted. That means:
	 *  - any element of the original array has the same amount of occurrences in the new array,
	 *    e.g. if the original array has twice the element x, the resulting element has twice the element x.
	 *  - any elements of the resulting array has the same amount of occurrences in the original array
	 *  - for any elements x and y in the resulting array, x occurs before y if and only if x is
	 *    less than or equal to y, according to the compareTo method.
	 * @return The number of comparisons (i.e. calls to compareTo) performed by the algorithm.
	 */
	public abstract long sort(Comparable[] array) throws IllegalArgumentException;


	public static boolean isSorted(Comparable[] array){
		for (int i = 1; i < array.length; i++){
			if (less(array[i], array[i-1])) return false;
		}
		return true;
	}
	public static boolean less(Comparable v, Comparable w){
		return v.compareTo(w) < 0;
	}

	public static void show(Comparable[] array){
		for (int i = 0; i < array.length; i++){
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
	public static void exch(Comparable[] array, int i, int j) {
		Comparable comp = array[i];
		array[i] = array[j];
		array[j] = comp;
	}

	public class AmountOfCompares{
		private long amount_of_compares;

		public AmountOfCompares(long amount_of_compares) {
			this.amount_of_compares = amount_of_compares;
		}
		public long get(){
			return this.amount_of_compares;
		}
		public void add_one(){
			this.amount_of_compares += 1;
		}
	}

	public static class Stopwatch{
		private final long start;

		public Stopwatch(){
			start = System.currentTimeMillis();
		}
		public double elapsedTime(){
			long now = System.currentTimeMillis();
			return (now - start) / 1000.0;
		}
	}

	public static class DoublingTest{
		public static double timeTrial(int n, SortingAlgorithm algorithm,
									   TimeRatio tr){
			int MAX = 1000000;
			Comparable[] a = new Comparable[n];
			Random random = new Random();
			for (int i = 0; i < n; i++){
				a[i] = random.nextInt(MAX);
			}
			Stopwatch timer = new Stopwatch();
			long count = algorithm.sort(a);
			tr.addComparison(count);
			return timer.elapsedTime();
		}


		public static TimeRatio main (SortingAlgorithm algorithm){
			TimeRatio tr = new TimeRatio();
			double prev = timeTrial(125, algorithm, tr);
			double ratio = 0;
			List<Double> ratios = new ArrayList<Double>();
			List<Double> times  = new ArrayList<Double>();
			boolean not_converged = true;
			DecimalFormat df = new DecimalFormat("#.##");
			System.out.println("Elements Time Dbr");
			for(int n = 250; not_converged; n*=2){
				tr.addElement(n);
				double time = timeTrial(n, algorithm, tr);
				System.out.print(n + " " + time);
				System.out.print(" " + time/prev);
				System.out.println();
				double new_ratio = time/prev;
				System.out.println();
				System.out.println("" + df.format(ratio) +"=="+ df.format(new_ratio));
				System.out.println("" + tr.compares.get(tr.compares.size()-1));
				System.out.println();
				int max = 256000;
				if(algorithm instanceof QuickSort){
					max = 65536000;
				}
				if((Math.abs((ratio - new_ratio)) < 0.001 && ratio != 0.0) || n >= max){
					not_converged = false;
					break;
				}
				ratios.add(ratio);
				times.add(time);
				ratio = new_ratio;
				prev = time;
			}

			tr.addRatios_addTimes(ratios, times);

			return  tr;
		}
	}


	public static class TimeRatio{
		List<Double> ratios;
		List<Double> times;
		List<Long> compares;
		List<Long> elements;

		public TimeRatio() {
			this.ratios = new ArrayList<>();
			this.times = new ArrayList<>();
			this.compares = new ArrayList<>();
			this.elements = new ArrayList<>();
		}

		public void setRatios(List<Double> ratios) {
			this.ratios = ratios;
		}

		public void setTimes(List<Double> times) {
			this.times = times;
		}

		public void setCompares(List<Long> compares){
			this.compares = compares;
		}

		public void addComparison(long count) {
			this.compares.add(count);
		}

		public void addRatios_addTimes(List<Double> ratios, List<Double> times) {
			this.ratios = ratios;
			this.times  = times;
		}

		public void setElements(List<Long> elements) {
			this.elements = elements;
		}


		public void addElement(long n) {
			this.elements.add(n);
		}
	}
}
