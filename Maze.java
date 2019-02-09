import java.util.Random;
import java.util.*;
public class Maze {
	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of rows:");
		int num_rows = sc.nextInt();
		System.out.println("Enter the number of columns:");
		int num_columns = sc.nextInt();
		
		while(num_rows == 1 && num_columns == 1) {
			System.out.println("The number of rows and columns both cannot be equal to 1 simultaneously");
			System.out.println("Please enter the number of rows:");
			num_rows = sc.nextInt();
			System.out.println("Please enter the number of columns:");
			num_columns = sc.nextInt();
		}
		
		while(num_rows <1 || num_columns<1) {
			System.out.println("Please enter positive number of rows and columns:(>0)");
			System.out.println("Please enter the number of rows:");
			num_rows = sc.nextInt();
			System.out.println("Please enter the number of columns:");
			num_columns = sc.nextInt();
		}
		
		int count = 1;
		int first = 0;
		int second = 0;
		int num_walls = 5;
		int wall = 0;
		
		int prod_rows_columns = num_rows*num_columns;
		DisjSets ds = new DisjSets( prod_rows_columns );
		
		// Create two boolean arrays: one for vertical lines and another for horizontal
		boolean [] vertical_lines = new boolean[prod_rows_columns];
		boolean [] horizontal_lines = new boolean[prod_rows_columns];
		for(int i = 0; i < prod_rows_columns;i++)
		{
			vertical_lines[i] = true;
		}
		for(int i = 0; i < prod_rows_columns;i++)
		{
			horizontal_lines[i] = true;
		}
		while(count < prod_rows_columns) {
			wall = new Random().nextInt(num_walls);
			first = new Random().nextInt(prod_rows_columns);
			
			
			if(wall == 0) 
			{
				if(first%num_columns == 0)
				{
					continue;
				}
				second = first -1;
			}
			
			else if(wall == 1) 
			{
				if(first < num_columns) 
				{
					continue;
				}
				second = first-num_columns;
			}
			
			else if(wall == 2) 
			{
				if((first+1)%num_columns == 0)
				{
					continue;
				}
				second = first +1;
			}
			
			else
			{
				if((prod_rows_columns - num_columns) <= first) 
				{
					continue;
				}
				second = first + num_columns;
			}
			
			if(ds.find(first) != ds.find(second))
			{
				ds.union(ds.find(first), ds.find(second));
				if(wall == 0 || wall == 2)
				{
					if(first >= second) {
						vertical_lines[first] = false;
					}
					else {
						vertical_lines[second] = false;
					}
				}
				else 
				{
					if(first <= second) {
						horizontal_lines[first] = false;
					}
					else {
						horizontal_lines[second] = false;
					}
				}
				count++;
			}
		}
		
		for(int i = 0; i < num_columns; i++) {
			if(i == 0) 
			{
				System.out.print("   ");
			}
			else 
			{
				System.out.print("_ ");
			}				
		}
		System.out.println();
		for(int i = 0; i < prod_rows_columns; i++) {
			if(i != 0 && vertical_lines[i] == true)
			{
				System.out.print("|");
			}
			
			else
			{
				System.out.print(" ");
			}
			
			if(i+1 < prod_rows_columns && horizontal_lines[i] == true)
			{
				System.out.print("_");
			}
			
			else
			{
				System.out.print(" ");
			}
			
            if( i+1 < prod_rows_columns && i%num_columns == (num_columns - 1)) 
            {
            	System.out.println("|");
            }
		}
		System.out.println();
		System.out.println();
		System.out.println("The maze has been printed with "+num_rows+" rows and "+num_columns+" columns");		
	}
}