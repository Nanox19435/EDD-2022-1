package fciencias.edatos.Practica02;
import java.util.Iterator;
import java.util.Scanner;

/**
* Metodos genericos para ListasTDA.
* @author Carlos Cruz Rangel, Juan Carlos Zenteno Pompa.
* @version 3.0 Octubre 2021.
* @since Estructuras de datos 2022-1. Prática 2.
*/

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
        


        // ACTIVIDAD 2.12, PRACTICA 2.
        // Iniciamos una demostracion con un programa en terminal del uso del mismo.

        Scanner entradaOpcion = new Scanner(System.in); //Para detectar la opcion del usuario
        Scanner scanEntradaListaString = new Scanner(System.in); //Entrada tipo string del usuario que quiere editar
        Scanner scanEntradaListaInt = new Scanner(System.in); //Entrada numerica del usuario
        Scanner scanEntradaListaBool = new Scanner(System.in); //Entrada booleana del usuario
        
        boolean salir = false; //variable para ayudarnos a salir del programa en la terminal
        int opcion; //Variable que nos ayuda a guardar la opcion del usuario
    
        String cadena1; // variable para scanner que acepta strings
        int posicion; // variable para scanner que acepta ints
        boolean eleccion; //variable para scanner que hacepta booleanos

        //Creacion de una lista de string basados en una lista generica.
        DoubleLinkedList<String> listaString = new DoubleLinkedList <String>();

            while(!salir){
                // Opciones para el usuario
                System.out.println("║█║▌║█║▌│║▌█║▌║-------- PRACTICA 2: LISTAS --------║█║▌║█║▌│║▌█║▌║");
                System.out.println("1   Agrega Cadenas a la lista");
                System.out.println("2   Elimina una cadena a la lista");
                System.out.println("3   Limpia la lista");
                System.out.println("4   Verifica si ya hay un elemento en la lista");
                System.out.println("5   Obtener elementos de la lista");
                System.out.println("6   Verifica si la lista esta vacia");
                System.out.println("7   Obtener la longitud de la lista");
                System.out.println("8   Obtener la reversa de la lista");
                System.out.println("9   Corta la lista");
                System.out.println("10  Mostar lista");
                System.out.println("11  SALIR");


                //Enviamos mensaje al usuario para que ingrase una entrada numerica para empezar una ejecucion del programa           
                System.out.println("ESCRIBE EL NUMERO DE LA OPCION QUE DESEAS AQUI ABAJITO");
                //Guardamos la opcion del usuario.
                opcion = entradaOpcion.nextInt();

                //Uso de switch para la ejecucion por casos para opciones del programa.
                switch (opcion) {


                        //Primera opcion del usuario, aqui agregara cadenas a su lista.
                        case 1:
                          System.out.println("Has seleccionado la opcion 1, Agrega cadenas a la lista  "+" \n¿QUE CADENA QUIERES AGREGAR?");
                          cadena1 = scanEntradaListaString.nextLine();
                          System.out.println("Que posicion");
                          posicion =scanEntradaListaInt.nextInt();
                          listaString.add(posicion,cadena1);
                          System.out.println("La cadena añadida fue -"+cadena1+"-. Los elementos de la lista son: "+listaString.toString());

                          break;


                        //Segunda opcion del usuario, aqui eliminara cadenas de la lista.
                        case 2:
                          System.out.println("Has seleccionado la opcion 2, Elimina cadenas a la lista  "+
                                            "\n EN QUE POSICION SE ENCUENTRA LA CADENA QUE QUIERES REMOVER?");
                          posicion =scanEntradaListaInt.nextInt();
                          listaString.remove(posicion);
                          System.out.println("La cadena en la posicion "+posicion+" fue eliminada"+
                                             "\n Los elementos de la lista son: "+listaString.toString());
                          break;

                        //Tercera opcion del usuario, aqui se eliminan todo el contenido de la lista.
                        case 3:
                          System.out.println("Has seleccionado la opcion 3"+
                                            "\n SE ESTA LIMPIANDO LA LISTA!");
                          listaString.clear();
                          System.out.println("La lista esta completamente limpia"+
                                             "\n Los elementos de la lista son: "+listaString.toString());
                          break;

                        //Cuarta opcion del usuario, aqui se busca un elemento en la lista.
                        case 4:
                          System.out.println("Has seleccionado la opcion 4, Verifiquemos si ya hay un elemento en la lista"+
                                            "\n ESCRIBE LA CADENA PARA PODER VERIFICAR SI YA ESTA EN LA LISTA");
                          
                          cadena1 = scanEntradaListaString.nextLine();
                          listaString.contains(cadena1);

                          //condicional if para dar un mejor uso a la entrada del usuari y devolver lo que busca.
                          if(listaString.contains(cadena1)==true){
                            System.out.println("LA CADENA FUE ENCONTRADA EN LA LISTA");
                            }else{
                                System.out.println("Lo siento, no la encontre. \nRevisa si la escribiste bien y vulve a intentarlo");
                            }
                          break;

                        //Quinta opcion del usuario, aqui se le entrega el elemento buscado por su posicion al usuario.
                        case 5:
                          System.out.println("Has seleccionado la opcion 5,  "+
                                            "\n EN QUE POSICION SE ENCUENTRA LA CADENA QUE QUIERES OBTENER?");
                          posicion =scanEntradaListaInt.nextInt();
                          System.out.println("La cadena en la posicion "+posicion+" es -"+listaString.get(posicion)+"-");
                          break;

                        //Sexta opcion del usuario, Se le dice al usuario si la lista esta vacia.
                        case 6:
                          System.out.println("Has seleccionado la opcion 6, "+" veamos si la lista esta vacia..."+
                                            "\n \n"+listaString.isEmpty());
                          break;
                        
                        //Septima opcion del usuario, aqui obtiene la longitud de la lista el usuario.
                        case 7:
                          System.out.println("Has seleccionado la opcion 7."+" veamos, la longitud de la lista es..."+listaString.size());
                          
                          break;

                        //Octaba opcion del usuario, sirve para invertir la lista.
                        case 8:
                          System.out.println("Has seleccionado la opcion 8, "+" veamos la reversa de tu lista...");
                          listaString.revert();
                          System.out.println(listaString.toString());
                          break;
                        
                        //Novena opcion, se biparticiona la lista y se le entrega al usuario la parte de su eleccion.
                        case 9:
                          System.out.println("Has seleccionado la opcion 9 "+"Esta es tu lista"+"\n"+listaString.toString()+
                                            "\n ...VOY A CORTAR LA LISTA A LA MITAD..."+
                                            "\n QUE LADO TE GUSTARIA TENER?"+
                                            "\n Escribe false si gustas la parte izquierda \n Escribe true si quieres la parte derecha.");
                          eleccion = scanEntradaListaBool.nextBoolean();
                          System.out.println("aqui esta el pedazo que pediste "+ listaString.cut(eleccion));
                          break;

                        //Decima opcion, Se muestra la lista.
                        case 10:
                          System.out.println("Has seleccionado la opcion 10, "+" Te muestro tu lista..."+
                                            "\n \n"+listaString.toString());
                          break;

                        //Decima primer opcion del usuario, esta opcion fue creada para que el usuario salga del programa.
                        case 11:
                          salir = true;
                          break;

                        //Este es un caso por si el usuario da como entrada algo incorrecto.
                      default:
                          System.out.println("Las opciones son numericas, introduce un numero entre 1 y 11 segun lo que quieras hacer");
  
              }

            }

        //Termina programa de demostracion en terminal de los metodos genericos de la practica.
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
