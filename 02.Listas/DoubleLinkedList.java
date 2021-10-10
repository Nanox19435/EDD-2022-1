import java.util.Iterator;

class DoubleLinkedList<T> implements TDAList<T> {

    public Nodo primero;
    public Nodo ultimo;
    public int length;

    private class Nodo {
        public T elemento;
        public Nodo anterior;
        public Nodo siguiente;

        public Nodo(T elemento) {
            this.elemento = elemento;
        }
    }


    public static void main(String[] args) {
        DoubleLinkedList<Integer> lista = new DoubleLinkedList<Integer>();
        lista.push(99); lista.push(89);
        System.out.println(lista);
    }

    private class Iterador implements Iterator<T> {
        private Nodo actual;

        public Iterador(DoubleLinkedList<T> lista) {
            actual = lista.primero;
        }

        @Override
        public T next() {
            Nodo guardado = this.actual;
            this.actual = actual.siguiente;

            return guardado.elemento;
        }

        @Override 
        public boolean hasNext() {
            return actual != null;
        }
    }

    public void push(T elemento) {
        Nodo nuevo = new Nodo(elemento);

        if (this.length++ == 0) {
            this.primero = nuevo;
            this.ultimo = nuevo;
            return;
        }

        nuevo.anterior = this.ultimo;
        this.ultimo.siguiente = nuevo;

        this.ultimo = nuevo;
    }

    @Override
    public Iterator<T> listIterador() {
        return new Iterador(this);
    }

    @Override
    public TDAList<T> cut(boolean side) {
        return null;
    }

    @Override
    public void revert() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int size() {
        return this.length;
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

    @Override
    public String toString() {
        String str = "[";
        Iterator<T> iterador = this.listIterador();
        while (iterador.hasNext()) {
            str += iterador.next() +", ";
        }
        str += "]";
        return str;
    }
}