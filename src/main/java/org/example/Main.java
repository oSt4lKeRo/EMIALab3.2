package org.example;

import java.util.ArrayList;
import java.util.Random;

import static org.example.HelperClass.*;

public class Main {

	static class Value {

		int index;
		int value;

		public Value(int index, int value) {
			this.index = index;
			this.value = value;
		}

		public void clearValue(int elem) {
			value = elem;
			index = 0;
		}

	}


	static ArrayList<ArrayList<Integer>> generateMatrix(int index) {

		//////////////////////////////////////Условия рандома
		int n = 10;
		int m = 4;
		int T1 = 10;
		int T2 = 15;
		int diff = T2 - T1;
		Random random = new Random();

		ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();

		//////////////////////////////////////Генерация матрицы
		if (index == 1) {
			for (int i = 0; i < m; i++) {

				ArrayList<Integer> arrayList = new ArrayList<>();
				matrix.add(arrayList);

			}

			for (int i = 0; i < n; i++) {

				int value = random.nextInt(diff + 1) + T1;
				matrix.get(random.nextInt(m)).add(value);

			}
		} else {

			ArrayList<Integer> arrayList = new ArrayList<>();
			ArrayList<Integer> arrayList1 = new ArrayList<>();
			ArrayList<Integer> arrayList2 = new ArrayList<>();
			ArrayList<Integer> arrayList3 = new ArrayList<>();

			arrayList.add(8);

			arrayList1.add(5);
			arrayList1.add(11);
			arrayList1.add(11);

			arrayList2.add(10);
			arrayList2.add(7);

			arrayList3.add(19);

			matrix.add(arrayList);
			matrix.add(arrayList1);
			matrix.add(arrayList2);
			matrix.add(arrayList3);
		}
		return matrix;
	}

	static int[] calculateSum(ArrayList<ArrayList<Integer>> matrix, int[] sumLoad) {

		for (int i = 0; i < matrix.size(); i++) {
			int sumValue = 0;
			for (int j = 0; j < matrix.get(i).size(); j++) {

				sumValue += matrix.get(i).get(j);

			}
			sumLoad[i] = sumValue;
		}

		return sumLoad;
	}


	public static void main(String[] args) {

		ArrayList<ArrayList<Integer>> matrix = generateMatrix(2);


		while (true) {

			int[] sumLoad = new int[matrix.size()];
			//////////////////////////////////////Подсчет суммы нагрузки на процессор
			sumLoad = calculateSum(matrix, sumLoad);

			//////////////////////////////////////Поиск минимальной и максимальной нагрузки на процессоры
			Value max = new Value(0, sumLoad[0]);
			Value min = new Value(0, sumLoad[0]);

			searchMinAndMax(max, min, sumLoad);
			printMatrix(matrix, sumLoad);

			int err = max.value - min.value;

			//////////////////////////////////////Первая часть алгоритма
			boolean check = true;
			for (Integer i : matrix.get(max.index)) {
				if (i < err) {

					check = false;
					matrix.get(min.index).add(i);
					matrix.get(max.index).remove(i);
					break;

				}
			}

			//////////////////////////////////////Вторая часть алгоритма
			if (check) {
				boolean lastCheck = false;

				for (int i = 0; i < matrix.get(max.index).size(); i++) {
					boolean forCheck = false;
					for (int j = 0; j < matrix.get(min.index).size(); j++) {

						System.out.println(matrix.get(max.index).get(i) + " > " + matrix.get(min.index).get(j));
						if (matrix.get(max.index).get(i) > matrix.get(min.index).get(j)) {
							System.out.println(matrix.get(max.index).get(i) + " - "  + matrix.get(min.index).get(j) + " < " + err);
							if ((matrix.get(max.index).get(i) - matrix.get(min.index).get(j)) < err) {

								int buf = matrix.get(min.index).get(j);
								matrix.get(min.index).remove(j);
								matrix.get(min.index).add(matrix.get(max.index).get(i));

								matrix.get(max.index).remove(i);
								matrix.get(max.index).add(buf);
								lastCheck = true;
								forCheck = true;
								printMatrix(matrix);
								break;
							}
						}
						System.out.println();
					}
					if(forCheck){
						break;
					}
				}

				//////////////////////////////////////Условие завершение и подвод результата
				if (!lastCheck) {
					sumLoad = calculateSum(matrix, sumLoad);
					System.out.println("\nResult:");
					printResult(sumLoad);
					return;
				}
			}

			System.out.println();
			printMatrix(matrix);
			System.out.println("\n");

		}
	}
}