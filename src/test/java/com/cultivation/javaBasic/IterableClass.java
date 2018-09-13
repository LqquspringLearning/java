package com.cultivation.javaBasic;

import java.util.Iterator;
import java.util.function.Consumer;

public class IterableClass implements Iterable<Long> {

    private Iterator<Long> iterator;

    public IterableClass() {
        iterator = new IteratorClass();
    }

    @Override
    public Iterator<Long> iterator() {
        return iterator;
    }


    @Override
    public void forEach(Consumer<? super Long> action) {
        if(iterator.hasNext())
        action.accept(iterator().next());
    }

}

class IteratorClass implements Iterator<Long> {

    long curPointer = 1;

    @Override
    public boolean hasNext() {
        return curPointer < 16;
    }

    @Override
    public Long next() {
        return curPointer++;
    }
}
