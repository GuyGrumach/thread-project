package com.Maman15.Ex1;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Please enter N(Amount of numbers) : ");
        int n = scanner.nextInt();
        System.out.println("Please enter M(Amount of workers) : ");
        int m = scanner.nextInt();
        int[] arr = genereateRandomArray(n);
        //printArr(arr, true);
        //Initalize the manager
        Manager manager = new Manager(arr, m);
        //Start Measure
        long startTime = System.currentTimeMillis();
        //Start manager to work
        manager.start();
        //Wait for manager
        manager.join();
        long endTime = System.currentTimeMillis();
        int[] result = manager.getResult();
        //printArr(result, false);
        System.out.println("Elapsed Time in milli seconds: " + (endTime - startTime));
    }

    private static int[] genereateRandomArray(int n) {
        Random random = new Random();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(100);
        }
        return arr;
    }

    private static void printArr(int arr[], boolean isBefore) {
        System.out.print(isBefore ? "Before = " : "After = ");
        for (int j : arr) {
            System.out.print(j + ", ");
        }
        System.out.println("\n");
    }
}

