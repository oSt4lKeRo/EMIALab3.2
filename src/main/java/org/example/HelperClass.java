package org.example;

import org.example.Main.Value;
import java.util.ArrayList;

public class HelperClass {

	static void searchMinAndMax(Value max, Value min, int[] sumLoad){

		max.clearValue(sumLoad[0]);
		min.clearValue(sumLoad[0]);

		for (int i = 0; i < sumLoad.length; i++) {

			if (sumLoad[i] > max.value) {

				max.value = sumLoad[i];
				max.index = i;

			} else if (sumLoad[i] < min.value) {

				min.value = sumLoad[i];
				min.index = i;

			}
		}

	}


	static void printMatrix(ArrayList<ArrayList<Integer>> matrix){

		for(ArrayList<Integer> i : matrix){
			for(int j : i){
				System.out.print(j + " ");
			}
			System.out.println();

		}

	}

	static void printMatrix(ArrayList<ArrayList<Integer>> matrix, int[] sum){

		for(int i = 0; i < matrix.size(); i++){
			System.out.print("p[" + i + "]: ");
			for(int j : matrix.get(i)){
				System.out.print(j + "\t");
			}
			System.out.println("\t| " + sum[i]);

		}

	}


	static void printResult(int[] sumLoad){

		for(int i = 0; i < sumLoad.length; i++){
			System.out.println("p[" + i + "]: " + sumLoad[i]);
		}
	}

}
