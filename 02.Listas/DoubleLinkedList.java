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

//Los métodos agregados en esta sección no fueron pedidos. Sin embargo, nos pareces métodos INDISPENSABLES para cualquier lista, y por ende, aquí están.
    // Anexa al final de la lista el elemento dado.
    public void push(T elemento) {
        Nodo nuevo = new Nodo(elemento);

        this.length++;
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

        this.length++;
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

    //Regresa el elemento al final de la lista y lo elimina de la misma
    public T pop_back() {
        //Caso lista vacía, se regresa null.
        if (this.isEmpty()) 
            return null;
        T guardado = this.ultimo.elemento;
        //Caso lista de un único elemento. Se vacía la lista.
        if (this.length == 1) {
            this.clear();
        } else {
            ultimo = ultimo.anterior;
            ultimo.siguiente = null;
        }

        length--;
        return guardado;
    }
//Terminan los métodos agregados por conveniencia.


    @Override
    public Iterator<T> listIterador() {
        return new Iterador(this);
    }

    @Override
    public TDAList<T> cut(boolean side) {
        DoubleLinkedList<T> cercenada = new DoubleLinkedList<T>();
        
        if (side) {
            Nodo apuntador = this.ultimo;
            for (int i = 0; i < this.length/2; i++) {
                cercenada.append(apuntador.elemento);
                apuntador = apuntador.anterior;
            }
        } else {
            Nodo apuntador = this.primero;
            for (int i = 0; i < this.length/2; i++) {
                cercenada.push(apuntador.elemento);
                apuntador = apuntador.siguiente;
            }    
        }

        return cercenada;
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
        if (length <= i || i < 0) throw new IndexOutOfBoundsException();

        //Casos donde trabajamos con los extremos de la lista.
        if (i == 0) return this.pop();
        if (i == this.length-1) return this.pop_back();

        
        int delta = length - i;
        this.length--;
        //usamos la delta anterior para encontrar que extremo de la lista está más cerca del índice.
        if (i <= delta) { //en este caso i está más cerca de la cabeza de la lista.
            Nodo apuntador = this.primero;
            for (int j = 0; j < i; j++) apuntador = apuntador.siguiente;
            apuntador.anterior.siguiente = apuntador.siguiente;
            apuntador.siguiente.anterior = apuntador.anterior;
            return apuntador.elemento;
        } else { //en este caso i está más cerca de la cola de la lista.
            Nodo apuntador = this.ultimo;
            for (int j = 0; j < i; j++) apuntador = apuntador.anterior;
            apuntador.anterior.siguiente = apuntador.siguiente;
            apuntador.siguiente.anterior = apuntador.anterior;
            return apuntador.elemento;
        }
    }

    @Override
    public boolean isEmpty() {
        return this.primero == null && this.ultimo == null;
    }

    @Override
    public T get(int i) throws IndexOutOfBoundsException {
        if (length <= i || i < 0) throw new IndexOutOfBoundsException();
        int delta = length - i;
        //usamos la delta anterior para encontrar que extremo de la lista está más cerca del índice.
        if (i <= delta) { //en este caso i está más cerca de la cabeza de la lista.
            Nodo apuntador = this.primero;
            for (int j = 0; j < i; j++) apuntador = apuntador.siguiente;
            return apuntador.elemento;
        } else { //en este caso i está más cerca de la cola de la lista.
            Nodo apuntador = this.ultimo;
            for (int j = 0; j < i; j++) apuntador = apuntador.anterior;
            return apuntador.elemento;
        }
    }

    @Override
    public boolean contains(T e) {
        int counter = (this.length + (this.length%2))/2;
        //apuntadores
        Nodo frontal = this.primero;
        Nodo trasero = this.ultimo;
        System.out.println(counter);
        for (int i = 0; i < counter; i++) {
            //Revisamos en la parte trasera y frontal a la vez
            if (e.equals(frontal.elemento)) return true;
            if (e.equals(trasero.elemento)) return true;
            //Avanzamos ambos apuntadores.
            frontal = frontal.siguiente;
            trasero = trasero.anterior;
        }

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
        if (this.length == i) {this.push(e); return;}
        if (this.length <= i || i < 0) throw new IndexOutOfBoundsException();
        if (0 == i) {this.append(e); return;}
        
        int delta = length - i;
        // Usamos la delta anterior para encontrar que extremo de la lista está más cerca del índice.
        if (i <= delta) { //en este caso i está más cerca de la cabeza de la lista.
            Nodo apuntador = this.primero;
            for (int j = 0; j < i; j++) apuntador = apuntador.siguiente;
            Nodo nuevo = new Nodo(e);
            // El nodo nuevo va a sustituir en el orden de la lista al nodo apuntador, por ende, 
            // tiene a este como siguiente, y sustituye a su anterior.
            nuevo.anterior = apuntador.anterior;
            nuevo.siguiente = apuntador;

            nuevo.anterior.siguiente = nuevo;
            apuntador.anterior = nuevo;

        } else { //en este caso i está más cerca de la cola de la lista.
            Nodo apuntador = this.ultimo;
            for (int j = 0; j < delta - 1; j++) apuntador = apuntador.anterior;
            Nodo nuevo = new Nodo(e);
            //Ésto es homólogo al caso anterior.
            nuevo.anterior = apuntador.anterior;
            nuevo.siguiente = apuntador;

            nuevo.anterior.siguiente = nuevo;
            apuntador.anterior = nuevo;
        }
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