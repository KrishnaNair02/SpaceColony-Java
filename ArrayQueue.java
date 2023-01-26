// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions
// of those who do.
// -- Krishna Nair (krishnanair)
package spacecolonies;

import queue.QueueInterface;
import queue.EmptyQueueException;

/**
 * Array based queue that will hold Person objects
 * @author Krishna Nair (krishnanair)
 * @version 11.9.2021
 * @param <T>
 *          type that the queue will hold
 */
public class ArrayQueue<T> implements QueueInterface<T> {

    private T[] queue;
    /**
     * max capacity of the queue
     */
    public static final int DEFAULT_CAPACITY = 20;
    private int size;
    private int enqueueIndex;
    private int dequeueIndex;
    
    /**
     * new ArrayQueue object
     * @param capacity
     *          how many items the arrayqueue can hold
     */
    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity)
    {
        queue = (T[]) new Object[capacity + 1];
        dequeueIndex = 0;
        enqueueIndex = capacity;
        size = 0;
    }
    
    /**
     * clears the arrayqueue
     */
    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        size = 0;
        dequeueIndex = 0;
        enqueueIndex = DEFAULT_CAPACITY;
        queue = (T[]) new Object[DEFAULT_CAPACITY + 1];
    }

    /**
     * removes the front entry from the queue
     */
    @Override
    public T dequeue() {
        if (isEmpty())
        {
            throw new EmptyQueueException();
        }
        T front = getFront();
        queue[dequeueIndex] = null;
        dequeueIndex = incrementIndex(dequeueIndex);
        size--;
        return front;
    }


    /**
     * adds an entry to the back of the queue
     * @param anEntry
     *          entry being added to queue
     */
    @Override
    public void enqueue(T anEntry) {
        ensureCapacity();
        enqueueIndex = incrementIndex(enqueueIndex);
        queue[enqueueIndex] = anEntry;
        size++;
    }

    /**
     * returns the front entry in the queue
     */
    @Override
    public T getFront() 
    {
        if (isEmpty())
        {
            throw new EmptyQueueException();
        }
        return queue[dequeueIndex];
    }

    /**
     * returns true or false if the queue is empty or not
     * @return true or false if the queue is empty or not
     */
    @Override
    public boolean isEmpty() {
        return (size == 0);
    }
    
    /**
     * returns the number of entries in a queue
     * @return the number of entries in a queue
     */
    public int getSize()
    {
        return size;
    }
    
    /**
     * returns the length of the queue
     * @return the length of the queue
     */
    public int getLengthOfUnderlyingArray()
    {
        return queue.length;
    }
    
    /**
     * expands the capacity if the array becomes full
     */
    @SuppressWarnings("unchecked")
    private void ensureCapacity()
    {
        if (isFull()) 
        {
            T[] oldQueue = queue;
            int oldSize = oldQueue.length;
            int newSize = ((oldSize - 1) * 2) + 1;
            queue = (T[]) new Object[newSize];
            
            int j = dequeueIndex;
            for (int i = 0; i < oldSize - 1; i++)
            {
                queue[i] = oldQueue[j];
                j = (j + 1) % oldSize;
            }
            
            dequeueIndex = 0;
            enqueueIndex = oldSize - 2;
        }
    }
    
    /**
     * increments the index of a given index (accounts for circular array)
     * @param index
     *          index number to be incremented
     * @return the index after being incremented
     */
    private int incrementIndex(int index)
    {
        return ((index + 1) % queue.length);
    }
    
    /**
     * returns true or false if the array is full or not
     * @return true or false if the array is full or not
     */
    private boolean isFull()
    {
        return ((enqueueIndex + 2) % queue.length == dequeueIndex);
    }

    /**
     * tests if one queue is equal to another
     * @return true or false if equal or not
     * @param obj
     *          object that this is being compared to
     */
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (this.getClass().equals(obj.getClass()))
        {
            ArrayQueue<T> other = (ArrayQueue<T>) obj;
            for (int i = 0; i < size; i++)
            {
                T myElement = queue[(dequeueIndex + i) % queue.length];
                T otherElement = other.queue[(other.dequeueIndex + i) 
                                             % queue.length];
                if (!myElement.equals(otherElement))
                {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    /**
     * converts the queue to an array
     * @return the queue in array form
     */
    public Object[] toArray() 
    {
        if (isEmpty())
        {
            throw new EmptyQueueException();
        }
        int j = dequeueIndex;
        Object[] copy = new Object[size];
        for (int i = 0; i < size; i++)
        {
            copy[i] = queue[i];
            j = incrementIndex(j);
        }
        return copy;
    }
    
    /**
     * returns the queue as a string
     * @return the queue in string form
     */
    @SuppressWarnings("unchecked")
    public String toString()
    {
        StringBuilder string = new StringBuilder();
        if (isEmpty())
        {
            return "[]";
        }
        T[] stringForm = (T[]) toArray();
        string.append("[");
        for (int i = 0; i < stringForm.length; i++)
        {
            string.append(stringForm[i].toString());
            if (i != stringForm.length - 1)
            {
                string.append(", ");
            }
        }
        string.append("]");
        return string.toString();
    }
}
