import java.util.Iterator;
import java.util.function.LongToIntFunction;

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
        lista.push(99); lista.push(89); lista.revert();
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

    // Anexa al final de la lista el elemento dado.
    public void push(T elemento) {
        Nodo nuevo = new Nodo(elemento);

        if (this.isEmpty()) {
            this.primero = nuevo;
            this.ultimo = nuevo;
            return;
        }

        nuevo.anterior = this.ultimo;
        this.ultimo.siguiente = nuevo;

        this.ultimo = nuevo;
    }

    //Anexa al inicio de la lista el elemento dado.
    public void append(T elemento) {
        Nodo nuevo = new Nodo(elemento);

        if (this.isEmpty()) {
            this.primero = nuevo;
            this.ultimo = nuevo;
            return;
        }

        nuevo.siguiente = this.primero;
        this.primero.anterior = nuevo;

        this.primero = nuevo;
    }

    //Regresa el elemento al inicio de la lista y lo elimina de la misma
    public T pop() {
        //Caso lista vacía, se regresa null.
        if (this.isEmpty()) 
            return null;
        T guardado = this.primero.elemento;
        //Caso lista de un único elemento. Se vacía la lista.
        if (this.length == 1) {
            this.clear();
        } else {
            primero = primero.siguiente;
            primero.anterior = null;
        }

        length--;
        return guardado;
    }

    @Override
    public Iterator<T> listIterador() {
        return new Iterador(this);
    }

    @Override
    public TDAList<T> cut(boolean side) {
        // TODO 
        return null;
    }

    @Override
    public void revert() {
        Iterador iterador = (Iterador)this.listIterador();
        DoubleLinkedList<T> revertida = new DoubleLinkedList<T>();

        while (iterador.hasNext()) 
            revertida.append(iterador.next());
        
        this.primero = revertida.primero;
        this.ultimo = revertida.ultimo;
        //Esto desperdicia mucha memoria. Estoy (casi) seguro de que hay un mejor método, pero mi crush me está hablando y no soy bueno asignando prioridades.
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
        if (this.length < i+1 || i < 0) throw new IndexOutOfBoundsException();
        if (0 == i) {this.append(e); return;}
        if (this.length == i) {this.push(e); return;}
        // TODO implementar la anexión por el lado que esté más cercano al índice.
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