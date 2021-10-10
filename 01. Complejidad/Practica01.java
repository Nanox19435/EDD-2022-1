import java.util.Arrays;

/**
* Práctica 1 del curso de Estructuras de Datos.
* @author Emmanuel Cruz Hernández.
* @version 2.0 Septiembre 2021.
* @since Laboratorio de Estructuras de Datos 2022-1.
*/
public class Practica01{

	/** 
	* Hace la mezcla de dos arreglos ordenados desde la primera posición hasta
	* una posición límite
	* @param array1 el primer arreglo a mezlar
	* @param n el límite de mezcla del primer arreglo
	* @param array2 el segundo arreglo a mezclar
	* @param m el límite de mezcla del segundo arreglo.
	* @return un arreglo ordenado de longitud m+n con la mezcla definida.
	*/
	public static int[] mergeSortedArray(int[] array1, int n, int[] array2, int m){
		if(n > array1.length || m > array2.length)
			throw new RuntimeException("Límites no válidos");
		
		int[] result = new int[n + m]; 
		//Usamos dos apuntadores para saber en que punto de ambos arreglos nos encontramos.
		//el apuntador 1 solo se usará con el arreglo y, y el apuntador 2 con el arreglo 2.
		int pointer1 = 0;
		int pointer2 = 0;

		
		for(int i = 0; i < result.length; i++) {
			//Aca, checamos que arreglo tiene el elemento menor en la posición a la que apuntamos.
			if (array1[pointer1] < array2[pointer2]) {
				result[i] = array1[pointer1];
				pointer1++;
				//Si llegamos al final del arreglo, recorremos lo que falta del otro arreglo y lo pegamos.
				if (pointer1 > n) {
					while (++i < result.length) {
						result[i] = array2[pointer2];
						pointer2++;
					}
					break;
				}
			} else {
				//El proceso es homólogo al bloque anterior.
				result[i] = array2[pointer2];
				pointer2++;
				if (pointer2 > m) {
					while (++i < result.length) {
						System.out.println(pointer1);
						result[i] = array1[pointer1];
						pointer1++;
					}
					break;
				}
			} 
		}

		return result;
	}

    /**
    * Verifica si un tablero contiene los números desde 0 hasta n-1 en cada fila y cada columna.
    * @param board el tablero de nxn que contiene elementos dentro del rango [0, n-1].
    * @return true si el tablero contiene los números desde 0 hasta n-1 en cada fila y columna,
	* false en otro caso.
    */
    public static boolean isValidBoard(int[][] board){
		int length = board.length;

		//La idea de este algoritmo es usar la fórmula de Gauss para la suma de los n-1 números naturales consecutivos desde 0
		//para hallar el resultado. También, regresamos false si board contiene un número fuera del rango  [0, n-1], de esta forma,
		//nos aseguramos que no se pueda llegar al resultado de la formula, sumando sum y n ceros. 
		final int sum = (length*(length-1))/2;

		for (int i = 0; i < board.length; i++) {
			int column_sum = 0;
			int row_sum = 0;
			for (int j = 0; j < board.length; j++) {
				//Otra optimización se da aquí, usando un único par de fors para recorrer a la vez las columnas y los renglones.
				int k = board[i][j];
				if (k < 0 || length <= k) return false;
				column_sum += k;

				k = board[j][i];
				if (k < 0 || length <= k) return false;
				row_sum += k;
			}
			if (row_sum != sum || column_sum != sum) return false;
		}

		return true;
	}

	/**
	* Rota position cantidad de veces los elementos de un arreglo
	* hacia el vecino izquierdo.
	* @param num el arreglo de enteros a rotar.
	* @param position la cantidad de espacios a rotar.
	*/
	public static void rotateArray(int[] num, int position){
		//Clonamos para no perder el orden original de los datos de entrada.
		int[] clone = num.clone();
		for(int i = 0; i < clone.length; i++) {
			//Utilizamos el modulo euclidiano como una función f(int) -> int que convierte
			//un indice en el arreglo original a un indice en el arreglo rotado.
			int index = euclidean_modulo(position + i, clone.length);
			num[i] = clone[index];
		}
	}

	//Esta función es una implementación de: https://doc.rust-lang.org/std/primitive.i32.html#method.rem_euclid,
	//donde r = euclidian_modulo(a, b) ===> 0 <= r < b.
	private static int euclidean_modulo(int a, int b) {
		int result = a%b;
		if (result < 0) result += b;
		return result;
	}

	public static void main(String[] args) {

		String directorio1 = "Examples/ArrayExamples/";
		String directorio2 = "Examples/BoardExamples/";

		// EJEMPLOS DE ACTIVIDAD 1
		System.out.println("\nEJEMPLOS DE ACTIVIDAD 1\n");

		int[] arrayA1 = ArrayReader.readArray(directorio1 + "ArrayA1.txt");
		int[] arrayA2 = ArrayReader.readArray(directorio1 + "ArrayA2.txt");
		int[] resultA = mergeSortedArray(arrayA1, 3, arrayA2, 5);
		System.out.println("Resultado A: "+Arrays.toString(resultA));

		int[] arrayB1 = ArrayReader.readArray(directorio1 + "ArrayB1.txt");
		int[] arrayB2 = ArrayReader.readArray(directorio1 + "ArrayB2.txt");
		int[] resultB = mergeSortedArray(arrayB1, 5, arrayB2, 5);
		System.out.println("Resultado B: "+Arrays.toString(resultB));

		int[] arrayC1 = ArrayReader.readArray(directorio1 + "ArrayC1.txt");
		int[] arrayC2 = ArrayReader.readArray(directorio1 + "ArrayC2.txt");
		int[] resultC = mergeSortedArray(arrayC1, 4, arrayC2, 6);
		System.out.println("Resultado C: "+Arrays.toString(resultC));



		// EJEMPLOS DE ACTIVIDAD 2
		System.out.println("\nEJEMPLOS DE ACTIVIDAD 2\n");

		int[][] boardA = ArrayReader.readMatrix(directorio2 + "BoardA.txt");
		boolean boardResultA = isValidBoard(boardA);
		System.out.println("El tablero A es válido: "+boardResultA);

		int[][] boardB = ArrayReader.readMatrix(directorio2 + "BoardB.txt");
		boolean boardResultB = isValidBoard(boardB);
		System.out.println("El tablero B es válido: "+boardResultB);

		int[][] boardC = ArrayReader.readMatrix(directorio2 + "BoardC.txt");
		boolean boardResultC = isValidBoard(boardC);
		System.out.println("El tablero C es válido: "+boardResultC);

		int[][] boardD = ArrayReader.readMatrix(directorio2 + "BoardD.txt");
		boolean boardResultD = isValidBoard(boardD);
		System.out.println("El tablero D es válido: "+boardResultD);


		// EJEMPLOS DE ACTIVIDAD 3
		System.out.println("\nEJEMPLOS DE ACTIVIDAD 3\n");

		rotateArray(arrayA1, 5);
		rotateArray(arrayB1, 0);
		rotateArray(arrayC1, 6);

		System.out.println("Arreglo A1 rotado 5 veces: " + Arrays.toString(arrayA1));
		System.out.println("Arreglo B1 rotado 0 veces: " + Arrays.toString(arrayB1));
		System.out.println("Arreglo C1 rotado 6 veces: " + Arrays.toString(arrayC1));

		System.out.println("\n\nFIN DE EJEMPLOS\n");
	}
}