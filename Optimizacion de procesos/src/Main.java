import java.util.*;

    public class Main{

    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1, 5, 6, 8, 3};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // Optimización 1: Selección del pivote como mediana de tres elementos
            int pivot = medianOfThree(arr, low, high);
            int pi = partition(arr, low, high, pivot);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high, int pivot) {
        int pivotValue = arr[pivot];
        swap(arr, pivot, high); // Mover el pivote al final
        int i = low;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivotValue) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, high); // Mover el pivote de nuevo a su posición correcta
        return i;
    }

    public static int medianOfThree(int[] arr, int low, int high) {
        int mid = low + (high - low) / 2;
        if (arr[low] > arr[mid]) {
            swap(arr, low, mid);
        }
        if (arr[low] > arr[high]) {
            swap(arr, low, high);
        }
        if (arr[mid] > arr[high]) {
            swap(arr, mid, high);
        }
        return mid;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
