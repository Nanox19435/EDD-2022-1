package fciencias.edatos.practica04;

public class Ordenamientos {

	public static void quickSort(int[] arr) {
		quickSort(arr, 0, arr.length-1);
	}

	public static void quickSort(int[] arr, int low, int high){
        if(arr.length <= 0) return;
        if(low >= high) return;
        int left = low;
        int right = high;

        int temp = arr[left];   
        while (left < right){
            while(left < right && arr[right] >= temp){  
                right--;
            }
            arr[left] = arr[right];
            while(left < right && arr[left] <= temp){  
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = temp;

        quickSort(arr, low, left-1);
        quickSort(arr, left+1, high);
}



public static int find ( int[] arr, int elem, int lo, int hi){
        if (lo > hi) {
            return -1;
        }
        int mid = lo + ((hi - lo) / 2);
        if (arr[mid] == elem) {
            return mid;
        }
        if (arr[mid] < elem) {
            return find(arr, elem, mid + 1, hi);
        } else {
            return find(arr, elem, lo, mid - 1);
        }
}

	/**
	 * Ordena un arreglo de forma ascendente con merge sort.
	 * @param arr el arreglo a ordenar.
	 */
	public static void mergeSort(int[] arr){
		mergeSort(arr, 0, arr.length-1);
	}

	/**
	 * Auxiliar de mergeSort para dividir y mezclar.
	 * @param arr el arreglo con los elementos a dividir y mezclar.
	 * @param lo el índice de inicio a modificación.
	 * @param hi el índice del último elemento a modificación.
	 */
	private static void mergeSort(int[] arr, int lo, int hi){
		// Cuando ya esta ordenado el fragmento de lo hasta hi
		if(hi <= lo)
			return;

		// La mitad del corte del arreglo
		int mid = lo + (hi-lo) / 2;

		mergeSort(arr, lo, mid);
		mergeSort(arr, mid+1, hi);

		merge(arr, lo, mid, hi);
	}

	/**
	 * Mezcla dos arreglos, ordenando de menor a mayor.
	 * @param arr el arreglo con los elementos a modificar.
	 * @param lo el inicio de la primera mitad.
	 * @param mid el índice de la mitad del subarreglo.
	 * @param hi el índice del último elemento.
	 */
	private static void merge(int[] arr, int lo, int mid, int hi){
        //Subarreglos que contienen unicamente los elementos de la lista que vamos a usar.
        int Izquierdo[] = new int[mid - lo + 1];
        int Derecho[] = new int[hi - mid];
  
        for (int i = 0; i < Izquierdo.length; ++i) {
            Izquierdo[i] = arr[lo + i];
		}

        for (int j = 0; j < Derecho.length; ++j) {
            Derecho[j] = arr[mid + 1 + j];
		}
  
        int[] fusionada = merge(Izquierdo, Derecho);

		for (int i = 0; i < fusionada.length; i++) {
			arr[i + lo] = fusionada[i];
		}
	}

	//Recibe dos arreglos y regresa un arreglo. Los tres estarán ordenados.
	private static int[] merge(int[] arr1, int[] arr2) {
		int[] fusionado = new int[arr1.length + arr2.length];

		//Apuntadores a los tres diferentes arreglos que usamos
		int i = 0;
		int j = 0;
		int k = 0;
		//recorremos los dos subarreglos hasta que lleguemos al fin de alguno
		while (i < arr1.length && j < arr2.length) {
			
			if (arr1[i] <= arr2[j]) {
				fusionado[k++] = arr1[i++];
			} else {
				fusionado[k++] = arr2[j++];
			}
		}

		// si quedan elementos de arr1, guardamos todos en el fusionado.
        while (i < arr1.length) {
            fusionado[k++] = arr1[i++];
		}
     
        // Homólogo al bloque anterior
        while (j < arr2.length) {
            fusionado[k++] = arr2[j++];
		}

		return fusionado;
	}
}