/**
 * Interface for implementing queue methods
 * @author Dante Smith
 *
 * @param <T> represents the Type of the elements of the list
 */
public interface QueueADT<T> {
	
	public boolean isEmpty();
	
	public int queueSize();
	
	public void	enqueue(T newObject);
	
	public void clear();
	
	public T peek();
	
	public T deal();
}
