package algorithm.sorting;

import java.util.Random;

/**
* This class contains various sorting algorithms
*/
public class Sorting {

	/**
	* Sorts the specified array according to the ordering induced by the compareTo() method in &Theta;(n<sup>2</sup>)
	* <p>
	* Implements the selectionsort algorithm.
	* <ul>
	* <li> Worst/Average/Best-case cost: &Theta;(n<sup>2</sup>)
	* </ul>
	* @param A the array to be sorted
	* @param <T> class of the object in the array
	*/
	public static <T extends Comparable<T>> void selectionsort(T A[]) {
		for (int i = 0; i < A.length - 1; i++) {
			int m = i;
			for (int j = i + 1; j<A.length; j++) {
				if (A[j].compareTo(A[m]) < 0) {
					m = j;
				}
			}
			if (m != i) {
				swap(A, i, m);
			}
		}
	}

	/**
	* Sorts the specified array into ascending numerical order in &Theta;(n<sup>2</sup>)
	* <p>
	* Implements the selectionsort algorithm.
	* <ul>
	* <li> Worst/Average/Best-case cost: &Theta;(n<sup>2</sup>)
	* </ul>
	* @param A the array to be sorted
	*/
	public static void selectionsort(int A[]) {
		for (int i = 0; i < A.length - 1; i++) {
			int m = i;
			for (int j = i + 1; j < A.length; j++) {
				if (A[j] < A[m]) {
					m = j;
				}
			}
			if (m != i) {
				swap(A, i, m);
			}
		}
	}
	private static void swap(int[] A, int i, int j) {
		int tmp;
		tmp = A[i];
		A[i] = A[j];
		A[j] = tmp;
	}

	/**
	* Sorts the specified array according to the ordering induced by the compareTo() method in O(n<sup>2</sup>)
	* <p>
	* Implements the insertionsort algorithm.
	* <ul>
	* <li> Worst/Average-case cost: &Theta;(n<sup>2</sup>)
	* <li> Best-case cost: &Theta;(n)
	* </ul>
	* @param A the array to be sorted
	* @param <T> class of the object in the array
	*/
	public static <T extends Comparable<T>> void insertionsort(T A[]) {
		for (int i = 1; i < A.length; i++) {
			int j = i;
			while (j > 0 && A[j].compareTo(A[j - 1]) < 0) {
				swap(A, j, j - 1);
				j--;
			}
		}
	}

	/**
	* Sorts the specified array into ascending numerical order in O(n<sup>2</sup>)
	* <p>
	* Implements the insertionsort algorithm.
	* <ul>
	* <li> Worst/Average-case cost: &Theta;(n<sup>2</sup>)
	* <li> Best-case cost: &Theta;(n)
	* </ul>
	* @param A the array to be sorted
	*/
	public static void insertionsort(int A[]) {
		for (int i = 1; i < A.length; i++) {
			int j = i;
			while (j > 0 && A[j]< A[j - 1]) {
				swap(A, j, j - 1);
				j--;
			}
		}
	}


	/**
	* Sorts the specified array according to the ordering induced by the compareTo() method in &Theta;(nlogn)
	* <P>	
	* Implements the mergesort algorithm.
	* <ul>
	* <li> Worst/Average/Best-case cost: &Theta;(nlogn)
	* </ul>
	* @param A the array to be sorted
	* @param <T> class of the object in the array
	*/
	public static <T extends Comparable<T>> void mergesort(T A[]) {
		genMergesort(A, 0, A.length - 1);
	}

	private static <T extends  Comparable<T>> void genMergesort(T[] A, int p, int r) {
		if (p < r) {
			int q =  p + (r - p)/2;
			genMergesort(A, p, q);
			genMergesort(A, q + 1, r);
			genMerge(A, p, q ,r);
		}
	}


	private static <T extends  Comparable<T>> void genMerge(T[] A, int p, int q, int r) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[r - p + 1];
		int i = p;
		int j = q + 1;
		int k = 0;
		while ((i <= q) && (j <= r)) {
			if (A[i].compareTo(A[j]) < 0) {
				array[k] = A[i];
				i++;
			}
			else{
				array[k] = A[j];
				j++;
			}
			k++;
		}
		while (i <= q) {
			array[k] = A[i];
			k++;
			i++;
		}
		while (j <= r) {
			array[k] = A[j];
			k++;
			j++;
		}
		for (k = 0; k < r - p + 1; k++) {
			A[p + k] = array[k];
		}
	}



	/**
	* Sorts the specified array into ascending numerical order in &Theta;(nlogn)
	* <p>
	* Implements the mergesort algorithm.
	* <ul>
	* <li> Worst/Average/Best-case cost: &Theta;(nlogn)
	* </ul>
	* @param A the array to be sorted
	*/
	public static void mergesort(int[] A) {
		mergesort2(A, 0, A.length - 1);
	}

	private static void mergesort2(int[] A, int p, int r) {
		if (p < r) {
			int q =  p + (r - p)/2;
			mergesort2(A, p, q);
			mergesort2(A, q + 1, r);
			merge(A, p, q ,r);
		}
	}

	private static void merge(int[] A, int p, int q, int r) {
		int[] B = new int[r - p + 1];
		int i = p;
		int j = q + 1;
		int k = 0;
		while ((i <= q) && (j <= r)) {
			if (A[i] <= A[j]) {
				B[k] = A[i];
				i++;
			}
			else{
				B[k] = A[j];
				j++;
			}
			k++;
		}
		while (i <= q) {
			B[k] = A[i];
			k++;
			i++;
		}
		while (j <= r) {
			B[k] = A[j];
			k++;
			j++;
		}
		for (k = 0; k < r - p + 1; k++) {
			A[p + k] = B[k];
		}
	}
	/**
	* Sorts the specified array according to the ordering induced by the compareTo() method in O(n<sup>2</sup>) and O(nlogn) on the average
	* <p>
	* Implements the quicksort algorithm.
	* <ul>
	* <li> Worst-case cost:  &Theta;(n<sup>2</sup>)
	* <li> Average/Best-case cost: &Theta;(nlogn)
	* </ul>
	* @param A the array to be sorted
	* @param <T> class of the object in the array
	*/
	public static <T extends Comparable<T>> void quicksort(T[] A) {
		genQuicksort2(A, 0, A.length - 1);
	}

	private static <T extends Comparable<T>> void genQuicksort2(T[] A, int p, int r) {
		if (p < r) {
			int q = genPartition(A, p, r);
			genQuicksort2(A, p, q - 1);
			genQuicksort2(A, q + 1, r);
		}
	}

	private static <T extends Comparable<T>> int genPartition(T[] A, int p, int r) {
		Random random_number = new Random();
		int pivotN = random_number.nextInt(r - p + 1) + p; // Generate a random pivot index
		swap(A, pivotN, r); // Move the pivot to the end
		T pivot = A[r]; // Pivot element
		int i = p - 1;
		for (int j = p; j < r; j++) {
			if (A[j].compareTo(pivot) <= 0) { // Include equality for stability
				i++;
				swap(A, i, j);
			}
		}
		swap(A, i + 1, r); // Place pivot in its correct position
		return i + 1;
	}

	/**
	* Sorts the specified array into ascending numerical order in O(n<sup>2</sup>) and O(nlogn) on the average
	* <p>
	* Implements the quicksort algorithm.
	* <ul>
	* <li> Worst-case cost:  &Theta;(n<sup>2</sup>)
	* <li> Average/Best-case cost: &Theta;(nlogn)
	* </ul>
	* @param A the array to be sorted
	*/
	public static void quicksort(int[] A) {
		quicksort2(A, 0, A.length - 1);
	}

	private static void quicksort2(int[] A, int p, int r) {

		if (p < r) {
			int q = partition(A, p, r);
			quicksort2(A, p, q - 1);
			quicksort2(A, q + 1, r);
		}
	}
	private static int partition(int[] A, int p, int r) {
		Random random_number = new Random();
		int pivotN = random_number.nextInt(r - p + 1) + p; // Generate a random pivot index
		swap(A, pivotN, r); // Move the pivot to the end
		int pivot = A[r]; // Pivot element
		int i = p - 1;
		for (int j = p; j < r; j++) {
			if (A[j] <= pivot) { // Include equality for stability
				i++;
				swap(A, i, j);
			}
		}
		swap(A, i + 1, r); // Place pivot in its correct position
		return i + 1;
	}

	/**
	* Sorts the specified array into ascending numerical order in &Theta;(n+k)
	* <p>
	* Implements the countingsort algrithm.
	* <ul>
	* <li> Worst/Average/Best-case cost: &Theta;(n+k), where k = max(<code>A</code>)-min(<code>A</code>)+1
	* </ul>
	* @param A the array to be sorted
	*/
	public static void countingsort(int[] A) {
		int a = Integer.MIN_VALUE;
		int b = Integer.MAX_VALUE;
		//searches max and min value in the array
		for (int i = 0; i < A.length; i++) {
			a = Math.max(a, A[i]);
			b = Math.min(b, A[i]);
		}
		//declare an array of 1/2 length of A
		int k = a - b + 1;
		int[] B = new int[k];
		//populate B with the number of elements in A of that index
		for (int i = 0; i < A.length; i++) {
			B[A[i] - b]++;
		}
		int j = 0;
		for (int i = 0; i < B.length; i++) {
			while (B[i] > 0) {
				A[j] = i + b;
				B[i]--;
				j++;
			}
		}
	}
	
	/**
	* Sorts the specified array according to the ordering induced by the compareTo() method in O(n log n)
	* using the heapsort algorithm.
	* <ul>
	* <li> Worst-case: &Theta;(n log n)
	* <li> Best-case: &Theta;(n)		
	* </ul>
	* @param A the array to be sorted
	* @param <T> class of the object in the array
	*/
	public static <T extends Comparable<T>> void heapsort(T A[]) {
		heapify(A, A.length - 1, 0);
		for (int c = (A.length - 1); c > 0; c--) {
			T k = findmax(A);
			deletemax(A, c);
			A[c] = k;
		}
	}
	
	/**
	* Transforms the array A with n elements in an arrayheap.
	* It proceeds recursively by fist creating sub arrayheaps rooted at index i
	*/	
	private static <T extends Comparable<T>> void heapify(T A[], int n, int i) {
		if (i >= n) return;
		heapify(A, n, left(i));
		heapify(A, n, right(i));
		fixheap(A, n, i);
	}
	
	/**
	* Returns the index of the left son
	*/	
	private static int left(int i) {
		return ( 2*i + 1 );
	}

	/**
	* Returns the index of the right son
	*/	
	private static int right(int i) {
		return ( 2*i + 2 );
	}
		
	/**
	* Fix the sub arrayheap rooted at position i of a the arrayheap A of length c, 
	* assuming only the root can be ill-placed
	*/		
	private static <T extends Comparable<T>> void fixheap(T A[], int c, int i) {
		int l = left(i), r = right(i);
		if (l > c) return;
		int max = l;
		if (r <= c && A[l].compareTo(A[r]) < 0)
			max = r;
		if (A[i].compareTo(A[max]) < 0) {
			swap(A, i, max);
			fixheap(A, c, max);
		}
	}
	
	/**
	* Returns the maximal element from an arrayheap A
	*/	
	private static <T> T findmax(T A[]) {
		return A[0];
	}

	/**
	* Removes the maximal element in an arrayheap A of length c
	*/		
	private static <T extends Comparable<T>> void deletemax(T A[], int c) {
		if (c <= 0) return;
		A[0] = A[c];
		c--;
		fixheap(A, c, 0);
	}	

	/**
	* Swaps the two elements in positions i and j in the array A
	*/		
	private static <T> void swap(T A[], int i, int j) {
		T tmp = A[i];
		A[i] = A[j];
		A[j] = tmp;
	}	
            
}
