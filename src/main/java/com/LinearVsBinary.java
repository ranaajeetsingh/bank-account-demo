package com;

import java.util.Scanner;

public class LinearVsBinary {
	private static int searcharray[] = { 1, 3, 4, 6, 8, 9, 12, 14, 15, 18, 22, 24, 25, 30, 40, 42, 51, 55, 64, 68, 70,
			71, 83, 90, 92, 94, 99 };
	private static int linearSteps = 0;
	private static int binarySteps = 0;
	
	public static void main(String[] args) {
		System.out.println("Enter value to serach");
		Scanner scanner = new Scanner(System.in);
		int value = scanner.nextInt();
		
		int llocation = linearSearch(searcharray, value);
		int blocation = binarySearch(searcharray, 0, searcharray.length - 1, value);
		
		System.out.println("Location of element: " + llocation);
		System.out.println("Number of steps in linear search: " + linearSteps);
		System.out.println("Number of steps in binary search: " + binarySteps);
	}
	
	public static int linearSearch(int[] arr, int data) {
		for (int i = 0; i < arr.length; i++) {
			linearSteps++;
			if (arr[i] == data) {
				return i;
			}
		}
		return -1;
	}
	
	public static int binarySearch(int[] arr, int startIndex, int endIndex, int data) {
		binarySteps++;
		if (endIndex >= startIndex) {
			int mid = startIndex + (endIndex - startIndex) / 2;
			if (arr[mid] == data)
				return mid;
			else if (arr[mid] < data)
				return binarySearch(arr, mid + 1, endIndex, data);
			else if (arr[mid] > data)
				return binarySearch(arr, startIndex, mid - 1, data);
		}
		return -1;
	}
}
