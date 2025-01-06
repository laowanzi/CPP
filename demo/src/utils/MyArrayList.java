package utils;

import java.util.Iterator;

public class MyArrayList<E> implements Iterable<E> {

    E[] array;
    private int size;

    public MyArrayList() {
        array = (E[]) new Object[8];
        size = 0;
    }
    public int getSize() {
        return size;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return array[index];
    }
    public void add(E e, int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        for (int i = size; i > index ; i--) {
            array[i] = array[i - 1];
        }
        array[index] = e;
        size++;
        if (size >= len()) {
            resize(size * 2);
        }
    }

    public void add(E e) {
        add(e, size);
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null;
        size--;
        if (size * 4 <= len()) {
            resize(size / 2);
        }
    }

    public void remove() {
        remove(size - 1);
    }

    private int len() {
        return array.length;
    }

    private void copy(E[] oldArray, E[] newArray) {
        if (size >= 0) System.arraycopy(oldArray, 0, newArray, 0, size);
    }

    private void resize(int capacity) {
        E[] old = array;
        array = (E[]) new Object[capacity];
        copy(old, array);
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<E> {

        private int wizPos;

        MyIterator() {
            wizPos = 0;
        }

        @Override
        public boolean hasNext() {
            return wizPos < size;
        }

        @Override
        public E next() {
            wizPos++;
            return array[wizPos - 1];
        }
    }

    public static void main(String[] args) {

    }
}
