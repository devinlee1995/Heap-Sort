import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class main {
	public static void main (String [] args) {
		int count = 1; 
		if (args.length < 2) {
			throw new IllegalArgumentException("Not enough arguments!"); 
		}
		try {
			String input_file_name = args[0]; 
			Scanner input_file = new Scanner(new File(input_file_name));
			
			String output_file_name = args[1]; 
			File output_file = new File(output_file_name); 
		
			while (input_file.hasNext()) { 
				input_file.next(); 
				count++;
			}
			System.out.println(count); 
			Heap_Sort heap = new Heap_Sort(count); 
			heap.buildHeap(input_file_name, output_file_name);
			heap.deleteHeap(output_file_name);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	
	}
}
