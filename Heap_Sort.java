import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;





public class Heap_Sort {

	int [] d;
	int size; //indicates max size of array and amount of elements +1 
	
	public Heap_Sort(int x) {
		d = new int[x]; 
		size =x; 
	}
	
	void buildHeap(String input_file_name, String output_file_name) {
		try {
			Scanner input_file = new Scanner(new File(input_file_name));
			File output_file = new File(output_file_name); 
			FileWriter fWriter = new FileWriter(output_file_name, true);
			PrintWriter print_to_outputfile = new PrintWriter(fWriter); 
			
			print_to_outputfile.println("Build Heap: "); 
			
			int num;
			while (input_file.hasNext()) { 
				num = input_file.nextInt();
				print_to_outputfile.print("Insert: " + num + "          "  );
				insert_one_data_item(num); 
				printHeap_first_ten(print_to_outputfile); 
			}
			print_to_outputfile.println("\n" + "Final Heap: ");
			printHeap_full(print_to_outputfile); 
			print_to_outputfile.println("\n"); 
			print_to_outputfile.close(); 
		} 	
		catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	void insert_one_data_item(int x) {
		if (heap_full()) {
			System.out.println("Full!"); 
		}
		else {
			bubbleUp(x); 
		}
	}
	
	boolean heap_full() {
		if (size == d[0]) {
			return true;
		}
		return false; 
	}
	
	void bubbleUp(int x) {
		d[0]++; //increments d[0] or the count (which is the index of the last element)
	    d[d[0]] = x; //puts new data item in last place in array (as last leaf)
	    
	    int parent = d[0]/2; //parent = index of the parent
	    int child = d[0]; //child = index of the child (leaf)
	    
	    while (d[parent] > d[child] && child != 1) { //swaps parent with child if parent value > child value
	        int temp = d[child];
	        d[child] = d[parent];
	        d[parent] = temp;
	        
	        child = child/2;
	        parent = parent/2;
	    }
	}
	
	boolean heap_empty(){ //tests if heap is empty
	    if (d[0] == 0) {
	        return true;
	    }
	    else return false;
	}
	
	void deleteHeap (String output_file_name) {
		File output_file = new File (output_file_name); 
		FileWriter fWriter;
		try {
			fWriter = new FileWriter(output_file_name, true);
			PrintWriter print_to_outputfile = new PrintWriter(fWriter); 
			
			print_to_outputfile.println("Build Heap: ");
			
			while (!heap_empty()) {
				print_to_outputfile.print("delete: " + deleteRoot() + "          " );
				printHeap_first_ten(print_to_outputfile); 
			}
			
			print_to_outputfile.println("\n"+ "Final Heap: ");
			printHeap_full(print_to_outputfile);
			print_to_outputfile.close(); 
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	int deleteRoot() {
		if (heap_empty()) {
			System.out.println("No!! Empty!");
		}
		else {
			return bubbleDown2(); 
		}
		return 0; 
	}
	
	int bubbleDown2 () {
		int root = d[1];
	    d[1] = d[d[0]]; //swaps with last leaf
	    d[0]--; //decrements
	    
	    int parent = 1;
	    int left_child_index = parent*2;
	    int right_child_index = (parent*2)+1;
	    int min_child; //index of lower child value
	    
	    if (d[0] == 2) { //when down to the last two elements and swaps if the value of the root > value of last child
	        if (d[left_child_index] < d[right_child_index]) {
	            min_child = left_child_index;
	        }
	        else {
	            min_child = right_child_index;
	        }
	        if (d[parent] > d[min_child]) {
	            int temp = d[parent];
	            d[parent] = d[min_child];
	            d[min_child] = temp;
	        }
	    }
	   
	    while (left_child_index <= d[0] && right_child_index <= d[0]) { //swaps until last leaf put at root is at the right place
	        if (d[parent] > d[left_child_index] || d[parent] > d[right_child_index]) { //checks to see if parent is more than children
	        	//gets the minimum child from left and right child
	        	if (d[left_child_index] < d[right_child_index]) {
	        		min_child = left_child_index;
	        	}
	        	else {
	            min_child = right_child_index;
	        	}	
	        	//swaps the minimum child with parent 
	        	int temp = d[parent];
	        	d[parent] = d[min_child];
	        	d[min_child] = temp;
	        	//parent index is now the min_child's index in order to check its children and so on
	        	parent = min_child;
		        left_child_index = min_child*2;
		        right_child_index = (min_child*2)+1;
	        }   
	       else { //the loop stops/breaks when the parent is no longer more than both children
	    	   break; 
	        }   
	    }
	    return root; 
	}
	
	void printHeap_first_ten(PrintWriter pWriter) {			
			int i = 1; 
			while (i<= d[0] && i<=10) {
				pWriter.print(d[i] + " "); 
				i++; 
			}
			pWriter.println(); 
	}
	
	void printHeap_full(PrintWriter pWriter) {			
		for (int i = 1; i <= d[0]; i++) {
			pWriter.print(d[i] + " "); 
		}
		pWriter.println(); 
	}

}