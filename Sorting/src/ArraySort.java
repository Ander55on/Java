
import java.util.Comparator;

public class ArraySort {
	
	/** 
	 * Sorts the given array using the mergesort algorithm
	 * @param arr the array to be sorted
	 * @param comp The comparator to be used for the sorting
	 * */
	@SuppressWarnings("unchecked")
	public static <E> void mergeSort(E[] arr, Comparator<E> comp) {
		E[] tmpArr = (E[]) new Object[arr.length];
		mergeSort(arr, tmpArr, comp, 0, arr.length - 1);
	}
	
	
	private static <E> void mergeSort(E[] arr, E[] tmpArr, Comparator<E> comp, int first, int last) {
		
		if(first < last) {
			int mid = first + (last - first) / 2;
			//Sort first half
			mergeSort(arr, tmpArr ,comp, first, mid);
			//Sort second half
			mergeSort(arr, tmpArr, comp, mid + 1, last);
			//Merge the two halves
			merge(arr, tmpArr, comp, first, mid + 1, last);
		}
	}
	
	private static <E> void merge(E[] arr, E[] tmpArr, Comparator<E> comp, int leftPos, int rightPos, int rightEnd) {
		
		int leftEnd = rightPos - 1;
		int index = leftPos;
		int leftStart = leftPos;
		int size = rightEnd - leftStart + 1;
		
		while(leftPos <= leftEnd && rightPos <= rightEnd) {
			if(comp.compare(arr[leftPos], arr[rightPos]) <= 0) {
				tmpArr[index] = arr[leftPos];
				leftPos++;
			} else {
				tmpArr[index] = arr[rightPos];
				rightPos++;
			}
			index++;
		}
		
		//Copy if any elements left in left array
		System.arraycopy(arr, leftPos, tmpArr, index, leftEnd - leftPos + 1);
		//Copy if any elements left in right array
		System.arraycopy(arr, rightPos, tmpArr, index, rightEnd - rightPos + 1);
		//Copy over the sorted elements from temp
		System.arraycopy(tmpArr, leftStart, arr, leftStart, size);
				
	}
	
	public static <E> void quickSort(E[] arr, Comparator<E> comp) {
		quickSort(arr, comp, 0, arr.length-1);
	}
	
	private static <E> void quickSort(E[] arr, Comparator<E> comp, int first, int last) {
		if(first < last) {
			E pivot = getMedian(arr, first, last, comp);
			int pivotIndex = partition(arr, comp, first, last, pivot);
			quickSort(arr, comp, first, pivotIndex - 1);
			quickSort(arr, comp, pivotIndex + 1, last);
		}
	}


	private static <E> int partition(E[] arr, Comparator<E> comp, int left, int right, E pivot) {
		//p points on the last nbr that is less than the pivot
		int p = left - 1;
		
		//i keeps track of the current element
		for(int i = left; i < right; i++) {
			if(comp.compare(arr[i], arr[right]) < 0) {
				p++;
				swap(arr, p, i);
			}
		}
		swap(arr, p + 1, right);
		
		return p + 1;
	}
	
	private static <E> E getMedian(E[]arr, int first, int last, Comparator<E> comp) {
		int mid = first + (last - first) / 2;
		
		if(comp.compare(arr[first], arr[mid]) > 0) {
			swap(arr, first, last);
		}
		
		if(comp.compare(arr[first], arr[last]) > 0) {
			swap(arr, first, last);
		}
		
		if(comp.compare(arr[mid], arr[last]) > 0) {
			swap(arr, mid, last);
		}
		
		swap(arr, mid, last);
		return arr[last];
	}
	
	private static <E> void swap(E[] arr, int left, int right) {
		E temp = arr[left];
		arr[left] = arr[right];
		arr[right] = temp;
	}

}
