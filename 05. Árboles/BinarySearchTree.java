import org.w3c.dom.Node;

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
           return actual;
        
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
        Nodo nodo = retrieve(this.root, k);
        if (nodo == null) 
            return null;
        
        T elemento = nodo.elemento;

        delete(nodo);

        return elemento;
    }

    private void delete(Nodo borrar) {
        //System.out.println(borrar.elemento);
        if (borrar.izquierdo == null) {
            if (borrar.derecho == null) {
                // Caso nodo es una hoja.
                if (borrar.progenitor.izquierdo == borrar) {
                    //Nodo es hijo izquierdo de su padre
                    borrar.progenitor.izquierdo = null;
                } else {
                    //Nodo es hijo derecho de su padre
                    borrar.progenitor.derecho = null;
                }
            } else {
                // Caso nodo tiene un solo hijo: el hijo izquierdo.
                if (borrar.progenitor.izquierdo == borrar) {
                    //Nodo es hijo izquierdo de su padre
                    borrar.progenitor.izquierdo = borrar.izquierdo;
                } else {
                    //Nodo es hijo derecho de su padre
                    borrar.progenitor.derecho = borrar.izquierdo;
                }
            }
        } else {
            if (borrar.derecho == null) { 
                // Caso nodo tiene un solo hijo: el hijo derecho.
                if (borrar.progenitor.izquierdo == borrar) {
                    //Nodo es hijo izquierdo de su padre
                    borrar.progenitor.izquierdo = borrar.izquierdo;
                } else {
                    //Nodo es hijo derecho de su padre
                    borrar.progenitor.derecho = borrar.izquierdo;
                }
            } else {
                // Caso nodo tiene dos hijos
                Nodo maxmin = borrar.izquierdo;
                while (maxmin.derecho != null) 
                    maxmin = maxmin.derecho;
                
                borrar.elemento = maxmin.elemento;
                borrar.clave = maxmin.clave;

                delete(maxmin);
            }
        }
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
        System.out.print("(");
        preorden(root);
        System.out.print(")\n");
    }

    private void preorden(Nodo actual) {
        if (actual == null) return;

        System.out.print("(key: " + actual.clave +", "+ "valor: " + actual.elemento + "), ");

        postorden(actual.izquierdo);

        postorden(actual.derecho);
    }

    @Override
    public void inorden() {
        System.out.print("(");
        inorden(root);
        System.out.print(")\n");
    }

    private void inorden(Nodo actual) {
        if (actual == null) return;

        postorden(actual.izquierdo);

        System.out.print("(key: " + actual.clave +", "+ "valor: " + actual.elemento + "), ");

        postorden(actual.derecho);
    }

    @Override
    public void postorden() {
        System.out.print("(");
        postorden(root);
        System.out.print(")\n");
    }

    private void postorden(Nodo actual) {
        if (actual == null) return;

        postorden(actual.izquierdo);

        postorden(actual.derecho);

        System.out.print("(key: " + actual.clave +", "+ "valor: " + actual.elemento + ")");
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }
}
