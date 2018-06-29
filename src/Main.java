/*
 * AUTHOR: Sebastin Justeeson
 * EMAILID: justeeson.1@osu.edu
 */



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

	public static void main(String[] args) {
		String in = "input.txt";
		String out = "output.txt";
		
		String line = null;
		int i = 0, j = 0;
		int loop_check = 0;
		int min_check = 0;
		int print_check = 0;
		int end_of_mst_check = 0;
		int min_i = 0, min_j = 0;
		int min_edge = 0;
		int nodes, mst_edges = 0;
		//Try to read in the input file
		try{
			FileReader fileReader = new FileReader(in);
			BufferedReader buffReader = new BufferedReader(fileReader);
			line = buffReader.readLine();
			//Split the first line of the input file
			String[] parts = line.split(" ");
			nodes = Integer.parseInt(parts[0]);
			//Declare arrays to hold the graph and tree
            int [][] graph = new int[nodes+1][nodes+1];
            int [][] mst = new int[nodes+1][nodes+1];
            int [] visited = new int[nodes+1];
			i = 1;
			while((line = buffReader.readLine()) != null){
				parts = line.split(" ");
				for(j = 0; j < parts.length; j++){
					//Storing the edge weights between the nodes in an array
					if(j % 2 == 0){
						graph[i][Integer.parseInt(parts[j])] = Integer.parseInt(parts[j+1]);		
					}
				}
				//Increase node count by 1 when reading a new line
				i++;
			}
			buffReader.close();
			//The loop will continue until there are no more edges to add
			while(end_of_mst_check == 0){
				min_edge = 0;
				end_of_mst_check = 1;
				min_check = 0;
				loop_check = 0;
				for(i = 0; i < nodes+1; i++){
					for(j = 0; j < nodes+1; j++){
						if(graph[i][j] != 0){
							if((graph[i][j] <= min_edge) || min_edge == 0){
								end_of_mst_check = 0;
								min_edge = graph[i][j];	
								min_i = i;
								min_j = j;	
								min_check = 1;
							}
						}
					}
				}
				if(min_check == 1){
				graph[min_i][min_j] = 0;
				
				//Check if the edge forms a loop
				for(i = 0; i < nodes+1; i++){
					if(mst[i][min_i]!=0 && mst[min_i][i]!=0 && mst[i][min_j]!=0 && mst[i][min_j]!=0){
						loop_check =  1;
					}
					
				}
				
				if(loop_check == 0){
					//Check if both nodes have already been visited
					if(!((visited[min_i] == 1) && (visited[min_j] == 1)))
						mst_edges++;
				mst[min_i][min_j] = min_edge;
				visited[min_i] = 1;
				visited[min_j] = 1;
				}
				}
			}
			//Declare a new printwriter stream to write output to a text file
			PrintWriter writer = new PrintWriter(out);
			writer.println(nodes+" "+mst_edges );
			
			
			for(i = 0; i < nodes+1; i++){
				print_check = 0;
				for(j = 0; j < nodes+1; j++){
				if(mst[i][j]!=0){
					writer.print(j+" "+mst[i][j]+" ");
					print_check = 1;
				}
				}
				if(print_check == 1){
				writer.println("");
				}
			}
			writer.close();
		}
		catch(FileNotFoundException ex) {
			ex.printStackTrace();    
		}
		catch(IOException ex) {
			ex.printStackTrace();    
		}
	}
}
