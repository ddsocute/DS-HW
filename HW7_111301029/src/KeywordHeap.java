import java.util.PriorityQueue;

public class KeywordHeap
{
	private PriorityQueue<Keyword> heap;

	public KeywordHeap()
	{
		this.heap = new PriorityQueue<Keyword>(10, new KeywordComparator());
	}

	public void add(Keyword k)
	{
		heap.offer(k);
	}

	public void peek()
	{
        if (heap.isEmpty()) {
            System.out.println("InvalidOperation");
        } else {
            System.out.println(heap.peek().toString());
        }
	}

	public void removeMin()
	{
		// YOUR TURN
		// 2. remove minimal element and print it
        if (heap.isEmpty()) {
            System.out.println("InvalidOperation");
        } else {
            Keyword min = heap.poll();
            System.out.println(min.toString());
        }
	}

	public void output()
	{
		// YOUR TURN
		// 3. print the output in order and remove all element
		if (heap.isEmpty()) {
            System.out.println("InvalidOperation");
        } else {
            StringBuilder sb = new StringBuilder();
            while (!heap.isEmpty()) {
                Keyword k = heap.poll();
                sb.append(k.toString()).append(" ");
            }
            System.out.println(sb.toString().trim());
        }
	}
}