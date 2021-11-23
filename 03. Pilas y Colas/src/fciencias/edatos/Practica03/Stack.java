package fciencias.edatos.Practica03;

import java.util.EmptyStackException;

public class Stack<T> implements TDAStack<T> {
    private class Nodo {
        protected T elemento;
        protected Nodo siguiente;

        public Nodo(T e) {
            this.elemento = e;
        }
    }
    private Nodo head;

    public void clear() {
        head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public T pop() throws EmptyStackException {
        if (isEmpty()) throw new EmptyStackException();
        T returnable = head.elemento;
        this.head = head.siguiente;
        return returnable;
    }

    public void push(T e) {
        Nodo nuevo = new Nodo(e);
        nuevo.siguiente = head;
        head = nuevo;
    }

    public T top() throws EmptyStackException {
        if (isEmpty()) throw new EmptyStackException();
        return head.elemento;
    }

    @Override
    public String toString() {
        String str = "";
        Nodo pointer = head;
        while(pointer != null) {
            str += pointer.elemento.toString() + ", ";
            pointer = pointer.siguiente;
        }
        return str;
    }
}
