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
			if (array1[pointer1] < array2[pointer2]) {
				result[i] = array1[pointer1];
				pointer1++;
				if (pointer1 > n) {
					result[i] = array2[pointer2];
					pointer2++;
				}
			} else {
				result[i] = array2[pointer2];
				pointer2++;
				if (pointer2 > m) {
					result[i] = array1[pointer1];
					pointer1++;
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
		for (int i = 0; i < length ; i++) {
			for (int j = 0; j < length ; j++ ) {
				boolean verificador = false;
				// Verifica sobre las filas
				for(int k = 0 ; k < length; k++){
					if(board[i][k] == j){
						verificador = true;
						break;
					}
				}
				if(!verificador){
					return false;
				}
				verificador = false;
				// Verifica sobre las columnas
				for(int k = 0 ; k < length; k++){
					if(board[k][i] == j){
						verificador = true;
						break;
					}
				}
				if(!verificador){
					return false;
				}
			}
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