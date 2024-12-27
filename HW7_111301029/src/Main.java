import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args) throws FileNotFoundException
	{
		KeywordHeap heap = new KeywordHeap();
	
		File file = new File("input.txt");
		Scanner scanner = new Scanner(file);
	
		while (scanner.hasNextLine())
		{
			String line = scanner.nextLine();
			Scanner lineScanner = new Scanner(line);
			if (!lineScanner.hasNext()) {
				lineScanner.close();
				continue;
			}
			String operation = lineScanner.next();
	
			switch (operation)
			{
			case "add":
				if (lineScanner.hasNext()) {
					String name = lineScanner.next();
					int count = lineScanner.nextInt();
					double weight = lineScanner.nextDouble();
					heap.add(new Keyword(name, count, weight));
				} else {
					System.out.println("InvalidOperation");
				}
				break;
	
			case "peek":
				heap.peek();
				break;
	
			case "removeMin":
				heap.removeMin();
				break;
	
			case "output":
				heap.output();
				break;
	
			default:
				System.out.println("InvalidOperation");
				break;
			}
			lineScanner.close();
		}
		scanner.close();
	}
	
}