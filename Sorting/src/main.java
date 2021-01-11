
public class Main {

	public static void main(String[] args) {
	
		Integer[] integerArray = {10, 20, 3, 1, 8, 3, 12 };
		String [] stringArray =  {"selection", "quick", "insertion", "merge"}; 
		
		//Integers
		System.out.println("Unsorted");		
		printArray(integerArray);
		
		ArraySort.quickSort(integerArray, (x,y) -> Integer.compare(x, y));	
		
		System.out.println("Sorted");
		printArray(integerArray);
		
		//Strings
		System.out.println("Unsorted");		
		printArray(stringArray);
		
		ArraySort.quickSort(stringArray, (x,y) -> x.compareTo(y));
		System.out.println("Sorted");
		printArray(stringArray);
		
		
	}
	
	private static <E> void printArray(E[] arr) {
		
		for(int i = 0; i < arr.length; i++) {
			System.out.print("[" + arr[i] + "] ");
		}
		System.out.println();
	}

}
