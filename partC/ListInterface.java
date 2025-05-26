package erg3;

import erg3.EmptyListException;

public interface ListInterface<T> {
    /**
     * Inserts the data at the front of the list
     *
     * @param data the inserted data
     */
    void insertAtFront(T data);


    /**
     * Returns and removes the data from the list head
     *
     * @return the data contained in the list head
     * @throws EmptyListException if the list is empty
     */
    T removeFromFront() throws EmptyListException;


    /**
     * Determine whether list is empty
     *
     * @return true if list is empty
     */
    boolean isEmpty();
}
