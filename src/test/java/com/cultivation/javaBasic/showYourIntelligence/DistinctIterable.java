package com.cultivation.javaBasic.showYourIntelligence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class DistinctIterable<T> implements Iterable<T> {
    private Iterable<T> iterable;

    public DistinctIterable(Iterable<T> iterable) {
        this.iterable = iterable;
    }

    @Override
    public Iterator<T> iterator() {
        return new DistinctIterator<>(iterable.iterator());
    }

    public List<T> toList() {
        ArrayList<T> result = new ArrayList<>();
        for (Iterator<T> iterator = this.iterator(); iterator.hasNext(); ) {
            T t = iterator.next();
            result.add(t);
        }
        return result;
    }
}

class DistinctIterator<E> implements Iterator<E> {
    // TODO: Implement the class to pass the test. Note that you cannot put all items into memory or you will fail.
    // <--start
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final Iterator<E> iterator;

    private List<E> containsList;

    DistinctIterator(Iterator<E> iterator) {
        containsList = new ArrayList<>();
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        if (!iterator.hasNext()) return false;
        E curElem = null;
        boolean hasFound = false;
        while (iterator.hasNext()) {
            curElem = iterator.next();
            if (containsList.size() == 0 || !containsList.contains(curElem)) {
                containsList.add(curElem);
                hasFound = true;
                break;
            }
        }
        return hasFound;
    }

    @Override
    public E next() {
        if (!iterator.hasNext()) throw new NoSuchElementException();
        return iterator.next();
    }
    // --end->
}