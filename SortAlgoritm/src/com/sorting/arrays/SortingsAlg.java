package com.sorting.arrays;

import java.util.Arrays;

public class SortingsAlg {

	public static void main(String[] args) {
		int arr[] = { 3, 2, 4, 25, 3, 9, 0, -4 };

		bubbleSort(arr);

		print(arr, " -->  Bubble sorting");
		
		selectionSort(arr);
		
		print(arr, " -->  Selection sorting");
		
	}

	private static void selectionSort(int[] arr) {
		
		for (int lastIndex = 0 ; lastIndex >=0 ; lastIndex--) {
			
			int large = 0;
			
			  for (int i = 0; i<=lastIndex ; i++) {
				  
				  if (arr[i] > arr[lastIndex])
					  large = i;
			  }
			  
			  swap(arr, large, lastIndex);
		}
		
		
	}

	private static void bubbleSort(int[] arr) {

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
