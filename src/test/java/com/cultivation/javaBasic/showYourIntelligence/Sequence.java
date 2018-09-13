package com.cultivation.javaBasic.showYourIntelligence;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Sequence implements Iterable<Integer> {
    private final Integer start;
    private final Integer end;

    public Sequence(Integer start, Integer end) {
        if (start >= end) {
            throw new IllegalArgumentException("Start must be smaller than End.");
        }
        this.start = start;
        this.end = end;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new SequenceIterator(start, end);
    }
}

class SequenceIterator implements Iterator<Integer> {

    // TODO: You can add additional fields or methods if you want.
    // <--start
    private Integer[] squene;
    private Integer curPosition = -1;
    // --end-->

    SequenceIterator(Integer start, Integer end) {
        // TODO: please implements the following code to pass the test
        // <--start
        squene = new Integer[end - start];
        for (int index = start, i = 0; index < end; index++, i++) {
            squene[i] = index;
        }
        // --end-->
    }

    @Override
    public boolean hasNext() {
        // TODO: please implements the following code to pass the test
        // <--start
        return curPosition + 1 < squene.length;
        // --end-->
    }

    @Override
    public Integer next() {
        // TODO: please implements the following code to pass the test
        // <--start
        if (!hasNext()) throw new NoSuchElementException();
        curPosition = curPosition + 1;
        return squene[curPosition];
        // --end-->
    }
}
