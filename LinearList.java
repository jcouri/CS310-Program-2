package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class LinearList<E extends Comparable<E>> implements LinearListADT<E>{
	private Node<E> head, tail;
	private int currentSize;
	private long modCounter;
	
	class Node<T> {
		T data;
		Node<T> next;
		Node<T> prev;
		Node(T obj) {
			data = obj;
			next = null;
			prev = null;
		}
	}
	
	public LinearList() {
		head = tail = null;
		currentSize = 0;
		modCounter = 0;
	}
	
	public boolean addFirst(E obj) {                 
		Node <E> newNode = new Node<E>(obj);
		if (head == null) head = tail = newNode;
		else {
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
		}
		currentSize++;
		modCounter++;
		return true;
	}

	public boolean addLast(E obj) {                  
		Node <E> newNode = new Node<E>(obj);
		if (head == null) head = tail = newNode;
		else {
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
		}
		currentSize++;
		modCounter++;
		return true;
	}

	public E removeFirst() {
		if (head == null) return null;       
		E tmp = head.data;
		if (currentSize == 1){
			head = tail = null;
			currentSize--;
			modCounter++;
			return tmp;
		}
		head = head.next;
		head.prev = null;
		if (head == null) tail = null;
		currentSize--;
		modCounter++;
		return tmp;
	}

	public E removeLast() {
		if (tail == null) return null;       
		E tmp = tail.data;
		if (currentSize == 1){
			head = tail = null;
			currentSize--;
			modCounter++;
			return tmp;
		}
		tail = tail.prev;
		tail.next = null;
		if (tail == null) head = null;
		currentSize--;
		modCounter++;
		return tmp;
	}

	public E remove(E obj) {
		Node <E> current = head;
		while (current != null && obj.compareTo(current.data) != 0) 
			current = current.next;
		if (current == null) return null;
		if(currentSize == 1){
			head = tail = null;           
			currentSize--;
			modCounter++;
			return current.data;
		}
		if (current == head) return removeFirst();
		if (current == tail) return removeLast();
		current.prev.next = current.next;
		current.next.prev = current.prev;
		currentSize--;
		modCounter++;
		return current.data;
	}

	public E peekFirst() {
		if (head == null) return null;
		return head.data;
	}
	
	public E peekLast() {
		if (head == null) return null;
		return tail.data;
	}

	public boolean contains(E obj) {
		if (find(obj) != null) return true;
		return false;
	}

	public E find(E obj) {
		Node <E> curr = head;
		E tmp = null;
		while (curr != null && obj.compareTo(curr.data) != 0) {
			curr.prev = curr;
			curr = curr.next;
		}
		if (curr == null) return null;
		tmp = curr.data;
		return tmp;
	}

	public void clear() {      
		head = tail = null;
		currentSize = 0;
		modCounter = 0;
	}

	public boolean isEmpty() {                
		if (currentSize == 0) return true;
		return false;
	}

	public boolean isFull() {                	
		return false;
	}

	public int size() {                       
		return currentSize;
	}

	public Iterator<E> iterator() {
		class IteratorHelper implements Iterator<E> {
			private Node<E> iterPtr;
			private long modCheck;
			public IteratorHelper() {
				modCheck = modCounter;
				iterPtr = head;
			}
			public boolean hasNext() {
				if(modCheck != modCounter)
					throw new ConcurrentModificationException();
				return iterPtr != null;
			}
			public E next() {
				if(!hasNext()) throw new NoSuchElementException();
				E tmp = iterPtr.data;
				iterPtr = iterPtr.next;
				return tmp;
			}
			public void remove() {
				
			}
		}	
		return new IteratorHelper();
	}
		

}
