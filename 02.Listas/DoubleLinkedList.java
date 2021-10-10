import java.util.Iterator;

class DoubleLinkedList<T> implements TDAList<T> {

    public Nodo primero;
    public Nodo ultimo;
    public int length;

    private class Nodo {
        public T elemento;
        public Nodo anterior;
        public Nodo siguiente;
    }


    public static void main(String[] args) {
        System.out.println("Hola Mundo!");
    }

    private class Iterador implements Iterator {
        private Nodo actual;

        public Iterador(DoubleLinkedList lista) {
            actual = lista.primero;
        }

        @Override
        public T next() {
            this.actual = actual.siguiente;

            return this.actual.anterior.elemento;
        }

        @Override 
        public boolean hasNext() {
            return actual.siguiente != null;
        }
    }

    @Override
    public Iterator listIterador() {
        return new Iterador(this);
    }

    @Override
    public TDAList cut(boolean side) {
        return null;
    }

    @Override
    public void revert() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public T remove(int i) throws IndexOutOfBoundsException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isEmpty() {
        return this.primero == null;
    }

    @Override
    public T get(int i) throws IndexOutOfBoundsException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean contains(T e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void clear() {
        this.primero = null;
        this.ultimo = null;
        this.length = 0;
    }

    @Override
    public void add(int i, T e) throws IndexOutOfBoundsException {
        // TODO Auto-generated method stub
        
    }


}