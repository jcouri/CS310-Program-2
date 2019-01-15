/*
Program #2
Joseph Couri
cssc0883
 */
package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class Queue <E extends Comparable<E>> implements Iterable<E>{
	
	private LinearListADT<E> list;
	
	public Queue() {
		list = new LinearList<E>();
	}
	
	
    public void enqueue(E obj) {
    	list.addLast(obj);
    }
       
    public E dequeue() {
    	return list.removeFirst();
    }
    
    public int size() {
    	return list.size();
    }
    
    public boolean isEmpty() {
    	return list.isEmpty();
    }
      
    public E peek() {
    	return list.peekFirst();
    }
       
    public boolean contains(E obj) {
    	return list.contains(obj);
    }
     
    public void makeEmpty() {
    	list.clear();
    }
    
    public boolean remove(E obj) {
    	if (list.remove(obj) == null) return false;
    	return true;
    }
     
    public Iterator<E> iterator() {
    	return list.iterator();
    }
}
	
