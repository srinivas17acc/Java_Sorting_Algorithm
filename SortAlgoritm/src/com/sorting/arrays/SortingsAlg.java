package com.sorting.arrays;

import java.util.Arrays;

public class SortingsAlg {

	/*
	 * 
	 * Sorting Algorithm
	 * 
	 * 1.Bubble  2.Selection  3.Insertion  4.Shell
	 * 
	 * 5.Merge  6.Quick 7.Count  8.Radix
	 * 
	 */

	public static void main(String[] args) {

		SortingsAlg sort = new SortingsAlg();

		int bubble[] = { 3, 2, 4, 25, 3, 9, 0, -4 };
		sort.bubbleSort(bubble);
		print(bubble, " -->  Bubble sorting");

		int selection[] = { 1, 2, 23, 2, 4, 25, 3, 9, 0, -4 };
		sort.selectionSort(selection);
		print(selection, " -->  Selection sorting");

		int insertion[] = { -12, -45, 3, 2, 4, 25, 3, 9, 0, -4 };
		sort.insertionSort(insertion);
		print(insertion, " -->  Insertion sorting");

		int shell[] = { -21, -9, 3, 2, 4, 25, 3, 9, 0, -4 };
		sort.shellSorting(shell);
		print(shell, " -->  Shell sorting");

		int merge[] = { 3, 2, 4, 25, 3, 9, 0, -4 };
		sort.mergeSorting(merge, 0, merge.length - 1);
		print(merge, " -->  Merge sorting");

		int quick[] = { 3, 2, -40, 31, 25, 3, 9, 0, -4, 2 };
		sort.quickSorting(quick, 0, quick.length);
		print(quick, " -->  Quick sorting");

		int count[] = { 9, 15, 8, 2, 1, 8, 1, 1, 1, 3, 8, 9, 2 };
		int max = 0;
		for (int i = 0; i < count.length; i++) {
			if (count[i] > max)
				max = count[i];
		}
		sort.countSorting(count, count.length, max);
		print(count, " -->  Count sorting");

		int redix[] = { 23, 345, 231, 200, 502, 40 };
		int large = 0;

		for (int i = 0; i < redix.length; i++) {
			if (redix[i] > large)
				large = redix[i];
		}
		sort.redixSorting(redix, redix.length, large);
		print(redix, " -->  Radix sorting");

	}

	private void redixSorting(int[] arr, int len, int max) {
		for (int pos = 1; pos / max > 0; pos *= 10) {
			countSort(arr, len, pos);
		}
	}

	private void countSort(int[] arr, int len, int pos) {
		int count[] = new int[10];
		int temp[] = new int[arr.length];
		for (int i = 0; i < len; i++) {
			count[(arr[i] / pos) % 10]++;
		}

		for (int i = 0; i < count.length; i++) {
			count[i] += count[i - 1];
		}

		for (int i = len - 1; i >= 0; i--) {
			temp[--count[(arr[i] / pos) % 10]] = arr[i];
		}

		System.arraycopy(temp, 0, arr, 0, temp.length);

	}

	private void countSorting(int[] arr, int len, int k) {
		int[] count = new int[k + 1];
		int[] temp = new int[arr.length];

		for (int i = 0; i < len; i++) {
			count[arr[i]]++;
		}

		for (int i = 1; i <= k; i++) {
			count[i] = count[i] + count[i - 1];
		}

		for (int i = len - 1; i >= 0; i--) {
			temp[--count[arr[i]]] = arr[i];
		}

		System.arraycopy(temp, 0, arr, 0, temp.length);
	}

	private void quickSorting(int[] arr, int start, int end) {
		if (end - start < 2)
			return;

		int partionIndex = partition(arr, start, end);
		quickSorting(arr, start, partionIndex);
		quickSorting(arr, partionIndex + 1, start);
	}

	private int partition(int[] arr, int start, int end) {
		int pivot = arr[start];
		int i = start;
		int j = end;

		while (i < j) {

			while (i < j && arr[--j] >= pivot)
				;
			if (i < j)
				arr[i] = arr[j];

			while (i < j && arr[++i] <= pivot)
				;
			if (i < j)
				arr[j] = arr[i];

		}
		arr[j] = pivot;

		return j;
	}

	private void mergeSorting(int[] arr, int start, int end) {
		if (start < end) {
			int mid = (start + end) / 2;
			mergeSorting(arr, start, mid);
			mergeSorting(arr, mid + 1, end);
			merge(arr, start, mid, end);
		}
	}

	private void merge(int[] arr, int start, int mid, int end) {
		int first = mid - start + 1;
		int second = end - mid;

		int[] left = new int[first];
		int[] right = new int[second];

		for (int i = 0; i < first; i++) {
			left[i] = arr[start + i];
		}
		for (int i = 0; i < second; i++) {
			right[i] = arr[mid + 1 + i];
		}

		int a = 0;
		int b = 0;
		int k = start;

		while (first > a && second > b) {
			if (left[a] <= right[b]) {
				arr[k++] = left[a++];
			} else {
				arr[k++] = right[b++];
			}
		}

		while (first > a) {
			arr[k++] = left[a++];
		}
		while (second > b) {
			arr[k++] = right[b++];
		}
	}

	private void shellSorting(int[] arr) {
		for (int gap = arr.length / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < arr.length; i++) {
				int element = arr[i];
				int j = i;
				while (j >= gap && arr[j - gap] > element) {
					arr[j] = arr[j - gap];
					j -= gap;
				}
				arr[j] = element;
			}
		}

	}

	private void insertionSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int element = arr[i];
			int k;
			for (k = i; k > 0 && arr[k - 1] > element; k--) {
				arr[k] = arr[k - 1];
			}
			arr[k] = element;
		}
	}

	private void selectionSort(int[] arr) {

		for (int lastIndex = arr.length - 1; lastIndex > 0; lastIndex--) {
			int large = 0;
			for (int i = 1; i <= lastIndex; i++) {
				if (arr[i] > arr[large])
					large = i;
			}
			swap(arr, large, lastIndex);
		}
	}

	private void bubbleSort(int[] arr) {
		for (int lastIndex = arr.length - 1; lastIndex >= 0; lastIndex--) {
			for (int firstIndex = 0; firstIndex < lastIndex; firstIndex++) {
				if (arr[lastIndex] < arr[firstIndex]) {
					swap(arr, lastIndex, firstIndex);
				}
			}
		}
	}

	private static void print(int[] arr, String str) {
		System.out.println(Arrays.toString(arr) + str);
		System.out.println();
	}

	private static void swap(int[] arr, int i, int j) {
		if (i == j)
			return;
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
