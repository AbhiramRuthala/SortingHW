// Name: Abhiram Ruthala
// Computing ID: kas4kj@virginia.edu
// Homework Name: HW-13
// Resources used:

import java.util.ArrayList;
import java.util.List;
/* Author: Briana Morrison, Hunter Platt */

public class SortingAlgorithms {

	/*
	 * Swaps the elements and indices i and j in list
	 * */
	private static <T> void swap(ArrayList<T> list, int i, int j) {
		if (i < 0 || i >= list.size())
			return;
		if (j < 0 || j >= list.size())
			return;
		T temp = list.get(i);
		list.set(i,list.get(j));
		list.set(j, temp);
	}
	
	// ####################
	// ## INSERTION SORT ## ----------------------------------------------------------------------
	// ####################
	// ## IMPORTANT: the code we've given you below has a small bug!
	// ##   You will need to look at this code and/or test, and fix the bug.
	// ####################
	/**
	 * Usually a slow sorting algorithm. Insertion sort. 
	 * @param list - An array of items
	 */
	public static <T extends Comparable<T>> void insertionSort(ArrayList<T> list) {
		for (int i = 0; i < list.size(); i++) {
			T val = list.get(i);
			int j = i-1;
			while (j >= 0 && val.compareTo(list.get(j)) < 0) {
				list.set(j, list.get(j+1));
				j--;
			}
			list.set(j+1, val);
		}
	}

	//=================================================================================


	// ################
	// ## MERGE SORT ## ----------------------------------------------------------------------
	// ################	
	/**
	 * Fully recursive Merge sort and associated helper method.
	 * The second method below provides the portion of the arrayList
	 * (i.e., index i to j inclusive) that we want to sort.
	 * 
	 * @param list - An arrayList of items
	 */
	public static<T extends Comparable<T>> void mergeSort(ArrayList<T> list) {
		mergeSort(list, 0, list.size() - 1);
	}
	public static<T extends Comparable<T>> void mergeSort(ArrayList<T> list, int i, int j) {
		//TODO: write the body of this method
//        while(!list.isEmpty()) { //holy this function will crash. find another base case to run the while loop on.
//            for(int k = i; k <= j; k++) {
//                for(int l = i+1; l <= j; l++) {
//                    if(list.get(k).compareTo(list.get(l)) < 0) {
//                        list.set(k, list.get(l));
//                    }
//                }
//            }
//
//        }

        if (i < j) {
            int m = (i + j) / 2;
            mergeSort(list, i, m);
            mergeSort(list, m+1, j);
            merge(list, i, m, j);
        }
	}
	
	/**
	 * Merge method for Merge Sort algorithm.
	 * Your mergeSort algorithm will call this method as appropriate to do the merging.
	 * @param list - An arrayList of items
	 * @param i - lower bound index
	 * @param mid - middle index
	 * @param j - upper bound index 
	 */
	public static<T extends Comparable<T>> void merge(ArrayList<T> list, int i, int mid, int j) {
//		for(int k = i; k < mid; k++) {
//            for(int l = mid; l < j; l++) {
//                if(list.get(k).compareTo(list.get(j)) < 0){
//                    list.set(k, list.get(l)); //list.get 2nd parameter is a value parameter. that makes a HUGE difference!
//                    list.set(l+1, list.get(j));
//                }
//            }
//
//        }

        List<T> leftArray = new ArrayList<T>();
        List<T> rightArray = new ArrayList<>();

        for(int k=i; k <= mid; k++) {
            leftArray.add(list.get(k));
        }

        for(int h=mid+1; h <= j; h++) {
            rightArray.add(list.get(h));
        }

        int leftIndex = 0;
        int rightIndex = 0;
        while (leftIndex < leftArray.size() && rightIndex < rightArray.size()) {
            if(leftArray.get(leftIndex).compareTo(rightArray.get(rightIndex)) <= 0) {
                list.set(leftIndex++, leftArray.get(leftIndex++));

            } else if (leftArray.get(leftIndex).compareTo(rightArray.get(rightIndex)) >= 0) {
                list.set(leftIndex++, rightArray.get(rightIndex++));
            }
        }
        
        while (leftIndex < leftArray.size()) {
            list.set(leftIndex++, leftArray.get(leftIndex++));
        }
        while (rightIndex < rightArray.size()) {
            list.set(rightIndex++, rightArray.get(rightIndex++));
        }

	}

	//=================================================================================

	// #######################
	// ## HYBRID MERGE SORT ## ----------------------------------------------------------------------
	// #######################
	/**
	 * Hybrid recursive Merge sort and associated helper method.
	 * The second method below provides the portion of the arrayList
	 * (i.e., index i to j inclusive) that we want to sort.
	 * For this implementation, when the size of the portion of the arrayList
	 * to be sorted is less than 100 items, call insertionSort method to
	 * sort that chunk of the arrayList.
	 *
	 *
	 * @param list - An arrayList of items
	 */
	public static<T extends Comparable<T>> void mergeSortHybrid(ArrayList<T> list) {
		mergeSortHybrid(list, 0, list.size() - 1);
	}
	public static<T extends Comparable<T>> void mergeSortHybrid(ArrayList<T> list, int i, int j) {
		//TODO: write the body of this method

		// When the size of array to be sorted is < 100, call insertionSort rather than recurse
        if(j-i+1 < 100) {
            insertionSort(list);
        }

        if(i < j) {
            int m = (i + j) / 2;
            mergeSortHybrid(list, i, m);
            mergeSortHybrid(list, m, j);
            merge(list, i, m, j);
        }



//        if(list.size() < 100) {
//            insertionSort(list);
//        }
//
//        for(int k = i; k < j; k++) {
//            for(int l = (j-i)/2; l < j; l++) {
//                if(list.get(k).compareTo(list.get(l)) < 0) {
//                    list.set(k, list.get(l));
//                    list.set(l, list.get(k));
//                }
//            }
//        }
	}

	//=================================================================================
	
	// ###############
	// ## QUICKSORT ## ----------------------------------------------------------------------
	// ###############	
	/**
	 * Fully recursive Quicksort and associated helper method.
	 * The second method below provides the portion of the arrayList
	 * (i.e., index i to j inclusive) that we want to sort.
	 * >>> Use any partition scheme that you like. 
	 * 
	 * @param list - An arrayList of items
	 */
	public static<T extends Comparable<T>> void quickSort(ArrayList<T> list) {
		quickSort(list, 0, list.size() - 1);
	}
	public static<T extends Comparable<T>> void quickSort(ArrayList<T> list, int i, int j) {
		//TODO: write the body of this method
        if(i <j){
            int sense = partition(list, i, j);
            quickSort(list, i, sense-1);
            quickSort(list, sense+1, j);
        }
	}
	
	/**
	 * Partition method for Quicksort - Use any valid partition algorithm that you like.
	 * Your quickSort algorithm will call this method as appropriate to do the partitioning.
	 * @param list - An arrayList of items
	 * @param i - lower bound
	 * @param j - upper bound
	 */
	public static<T extends Comparable<T>> int partition(ArrayList<T> list, int i, int j) {
		//TODO: write the body of this method
//        for(int k = i; k < j; k++) {
//            if(list.get(k).compareTo(list.get(j)) < 0) {
//                list.set(k, list.get(j)); //not super sure on this second parameter here.
//                list.set(j, list.get(k));
//            }
//        }

        T pivot = list.get(j);
        int boundary = i-1;
        for(int k = i; k < j; k++) {
            if(list.get(k).compareTo(pivot) <= 0) {
                boundary++;
                swap(list, boundary, k);
            }
        }

        swap(list, boundary+1, j);
        return boundary +1;

	}
	
	//=================================================================================

        // ######################
	// ## HYBRID QUICKSORT ## ----------------------------------------------------------------------
	// ######################
	/**
	 * Hybrid Quicksort and associated helper method.
	 * The second method below provides the portion of the arrayList
	 * (i.e., index i to j inclusive) that we want to sort.
	 * >>> Use any partition scheme that you like.
	 * For this implementation, when the size of the portion of the arrayList
	 * to be sorted is less than 100 items, call insertionSort method to
	 * sort that chunk of the arrayList.
	 *
	 * @param list - An arrayList of items
	 */
	public static<T extends Comparable<T>> void quickSortHybrid(ArrayList<T> list) {
		quickSortHybrid(list, 0, list.size() - 1);
	}
	public static<T extends Comparable<T>> void quickSortHybrid(ArrayList<T> list, int i, int j) {
		//TODO: write the body of this method
		// When the size of array to be sorted is < 100, call insertionSort rather than recurse
        if(j-i+1 < 100) {
            insertionSort(list);
            return;
        }
        if(i < j ) {
            int pivot = partition(list, i, j);
            quickSortHybrid(list, i, pivot - 1);
            quickSortHybrid(list, pivot + 1, j);
        }



//        for(int k = i; k <= j; k++) {
//            if(list.get(k).compareTo(list.get(j)) < 0) {
//                list.set(k, list.get(j));
//                list.set(j, list.get(k));
//            }
//            if(k == j) {
//                list.set(k, list.get(j)); //idk what to put here
//            }
//        }
	}

	/**
	 * Partition method for Quicksort - Use any valid partition algorithm that you like.
	 * Your quickSort algorithm will call this method as appropriate to do the partitioning.
	 * @param list - An arrayList of items
	 * @param i - lower bound
	 * @param j - upper bound
	 */
	public static<T extends Comparable<T>> int partitionHybrid(ArrayList<T> list, int i, int j) {
		//TODO: write the body of this method
        //call pivot from end.
        for(int k = i; k < j; k++) {
            if(list.get(k).compareTo(list.get(j)) < 0) {
                list.set(k, list.get(j)); //not super sure on this second parameter here.
                list.set(j, list.get(k));
            }
        }
		return 0; // be sure to return the right value and not 0
	}

}
