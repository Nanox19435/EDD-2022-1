public class BinarySearchTree<K extends Comparable, T> implements TDABinarySearchTree<K, T> {

    private class Nodo {
        public K clave;
        public T elemento;

        public Nodo progenitor;
        public Nodo izquierdo;
        public Nodo derecho;

        public Nodo(K clave, T elemento) {
            this.clave = clave;
            this.elemento = elemento;

            progenitor = null;
            izquierdo = null;
            derecho = null;
        }
    }

    private Nodo root;

    @Override
    public T retrieve(K k) {
        Nodo nodo = retrieve(root, k);
        if (nodo == null) 
            return null;
        return nodo.elemento;
    }

    private Nodo retrieve(Nodo actual, K k) {
        if (actual == null) 
            return null;

        if (k.compareTo(actual.clave) == 0) 
           return null;
        
        if (k.compareTo(actual.clave) < 0)
            return retrieve(actual.izquierdo, k);
        else 
            return retrieve(actual.derecho, k);
            
    }

    @Override
    public void insert(T e, K k) {
        if (isEmpty()) {
            root = new Nodo(k, e);
        } else {
            insertar(root, e, k);
        }  
    }

    

    private void insertar(Nodo actual, T e, K k) {
        //Añade al subarbol izquierdo.
        if (k.compareTo(actual.clave) <= 0) {
            if (actual.izquierdo == null) {
                Nodo n = new Nodo(k, e);
                n.progenitor = actual;
                actual.izquierdo = n;
            } else {
                insertar(actual.izquierdo, e, k);
            }
        } else { //Añade al subarbol derecho.
            if (actual.derecho == null) {
                Nodo n = new Nodo(k, e);
                n.progenitor = actual;
                actual.derecho = n;
            } else {
                insertar(actual.derecho, e, k);
            }
        }
    }

    @Override
    public T delete(K k) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T findMin() {
        if (isEmpty()) 
            return null;
        
        Nodo nodo = root;

        while (nodo.izquierdo != null) {
            nodo = nodo.izquierdo;
        }
        
        return nodo.elemento;
    }

    @Override
    public T findMax() {
        if (isEmpty()) 
            return null;
        
        Nodo nodo = root;

        while (nodo.derecho != null) {
            nodo = nodo.derecho;
        }
        
        return nodo.elemento;
    }

    @Override
    public void preorden() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void inorden() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void postorden() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }
    
}
