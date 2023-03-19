package ohm.softa.a02;

import java.util.Iterator;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListImpl implements SimpleList, Iterable {

    private Element head;
    @Override
    public void add(Object o) {
        if (head == null) {
            head = new Element();
            head.item = o;
        } else {
            Element e = new Element();
            e.item = o;
            e.next = head;
            head = e;
        }
    }

    @Override
    public int size() {
        int size = 0;
        Element current = head;
        while (current != null) {
            size++;
            current = current.next;
        }

        return size;
    }

    @Override
    public SimpleList filter(SimpleFilter filter) {
        SimpleList list = new SimpleListImpl();

        for (Object o : this) {
            if(filter.include(o)) {
                list.add(o);
            }
        }

        return list;
    }

    @Override
    public Iterator iterator() {
        return new SimpleIteratorImpl(head);
    }


    private static class Element {
        private Object item;
        private Element next;

    }

    private class SimpleIteratorImpl implements Iterator{

        Element current;

        SimpleIteratorImpl(Element head) {
            current = head;
        }
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Object next() {
            Object o = current.item;
            current = current.next;
            return o;
        }
    }

}
