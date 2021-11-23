package fciencias.edatos.Practica03;

import java.util.EmptyStackException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Representación de un laberinto. 
 */
public class Maze {
    private Box[][] data;
    private Box inicio;
    private Box fin;
    private Box actual;
    private int[] coordsActual;

    private boolean solved = false;
    private Stack<Integer> camino;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--------------Resolvedor de laberinto----------------");
        System.out.println("Elija una opción:");
        System.out.println("    Pulse 1 para resolver un laberinto.");
        System.out.println("    Pulse cualquier otra cosa para salir.");

        //System.out.println(new Maze(ArrayReader.readMatrix("Laberintos/LaberintoB.txt")));
        
        try {
            if (scanner.nextInt() == 1) {
                Box[][] data;
                while (true) {
                    System.out.println("Introduzca el nombre del archivo donde se encuentra el Laberinto");
                    data = ArrayReader.readMatrix(scanner.next());

                    if (data != null) break;
                    
                }
                Maze maze = new Maze(data);
                
                while (true) {
                    try {
                        int[] init = new int[2];
                        System.out.println("Introduzca la primera coordenada de la casilla de inicio");
                        init[1] = scanner.nextInt();
                        System.out.println("Introduzca la segunda coordenada de la casilla de inicio");
                        init[0] = scanner.nextInt();

                        maze.setInicio(init[0], init[1]);

                        maze.inicio = maze.getBox(init);
                        break;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Las coordenadas dadas se encuentran fuera del rango del laberinto. vuelva a intentarlo.");
                    } catch (InputMismatchException e){
                        System.out.println("Por favor, introduzca un número.");
                    } catch (InvalidBoxException e) {
                        System.out.println("La casilla seleccionada es una pared, no puede ser el inicio el laberinto");
                    }
                }
                while (true) {
                    try {
                        int[] end = new int[2];
                        System.out.println("Introduzca la primera coordenada de la casilla final");
                        end[1] = scanner.nextInt();
                        System.out.println("Introduzca la segunda coordenada de la casilla final");
                        end[0] = scanner.nextInt();

                        maze.setFin(end[0], end[1]);

                        maze.fin = maze.getBox(end);
                        break;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Las coordenadas dadas se encuentran fuera del rango del laberinto. vuelva a intentarlo.");
                    } catch (InputMismatchException e){
                        System.out.println("Por favor, introduzca un número.");
                    } catch (InvalidBoxException e) {
                        System.out.println("La casilla seleccionada es una pared, no puede ser el final el laberinto");
                    }
                }
                
                maze.solve();
                if (maze.isSolution()) {
                    System.out.println(maze);
                } else {
                    System.out.println("El laberinto dado no tiene solución.");
                }

            }
        } catch (InputMismatchException e) {}
    }

    public Maze(Box[][] data) {
        this.data = data;

        //Inicia el proceso de construcción del laberinto.
        for (int i = 0; i < data.length; i++) {
            Box[] renglón = data[i];
            for (int j = 0; j < data[i].length; j++) {
                if (renglón[j] == null) {
                    //Añadimos las paredes
                    renglón[j] = new Box(true);
                }
            }
        }

        //Una vez todas las casillas están listas, podemos llenar los datos de sus vecindades
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (!data[i][j].isWall()) {
                    Box b = data[i][j];

                    try {
                        if (!data[i-1][j].isWall())  b.neighbors.enqueue(1);
                    } catch (ArrayIndexOutOfBoundsException e) {}
                    try {
                        if (!data[i+1][j].isWall())  b.neighbors.enqueue(3);
                    } catch (ArrayIndexOutOfBoundsException e) {}
                    try {
                        if (!data[i][j+1].isWall())  b.neighbors.enqueue(2);
                    } catch (ArrayIndexOutOfBoundsException e) {}
                    try {
                        if (!data[i][j-1].isWall())  b.neighbors.enqueue(4);
                    } catch (ArrayIndexOutOfBoundsException e) {}
                    //Atrapamos las excepciones que surgen aquí, y no es necesario hacer nada.
                    //Pues la excepción ocurre en la condición del if, así que, no se mete ningún valor
                    //a la fila.
                }
            }
        }
    }

    public void setInicio(int i, int j) throws IndexOutOfBoundsException, InvalidBoxException {
        Box seleccionado = data[i][j];
        if (seleccionado.isWall()) throw new InvalidBoxException(""); 

        coordsActual = new int[] {i, j};
        this.inicio = seleccionado;
    }

    public void setFin(int i, int j) throws IndexOutOfBoundsException, InvalidBoxException {
        Box seleccionado = data[i][j];
        if (seleccionado.isWall()) throw new InvalidBoxException("");

        this.fin = seleccionado;
    }

    public boolean isSolution() {
        return solved;
    }

    public boolean isExtensible() {
        return !actual.neighbors.isEmpty();
    }

    //Método auxiliar, que, dado un par de coordenadas, regresa la celda en esa posición.
    private Box getBox(int[] coords) {
        return data[coords[0]][coords[1]];
    }

    public void extend() {
        int siguiente = actual.neighbors.dequeue();
        int i = coordsActual[0];
        int j = coordsActual[1];
        camino.push(siguiente);
        switch (siguiente) {
            case 1: //Arriba
                if (data[i-1][j].isVisited()) {
                    camino.pop();
                    break;
                }
                actual = data[--i][j];
                coordsActual[0]--;
                break;
            case 2: //Derecha
                if (data[i][j+1].isVisited()) {
                    camino.pop();
                    break;
                }
                actual = data[i][++j];
                coordsActual[1]++;
                break;
            case 3: //Abajo
                if (data[i+1][j].isVisited()) {
                    camino.pop();
                    break;
                }
                actual = data[++i][j];
                coordsActual[0]++;
                break;
            case 4: //Izquierda
                if (data[i][j-1].isVisited()) {
                    camino.pop();
                    break;
                }
                actual = data[i][--j];
                coordsActual[1]--;
                break;
            default: //Según yo esto nunca debería pasar.
                System.out.println("Si estás viendo esto, entonces algo salió inconcebiblemente mal");
                break;
        }
        actual.visit();
    }

    //Toma la dirección que le pases como inversa a la que va a usar.
    //Se creó para poder ayudar a retroceder en el camino.
    private int[] getCoordsInv(int[] coords, int direcciónInversa) {
        //Las clonamos para que sean cosas diferentes.
        int[] nuevas = coords.clone();
        switch (direcciónInversa) {
            
            case 3:
                nuevas[0] -= 1;
                return nuevas;
            case 4:
                nuevas[1] += 1;
                return nuevas;
            case 1:
                nuevas[0] += 1;
                return nuevas;
            case 2:
                nuevas[1] -= 1;
                return nuevas;
            default:
                return null;
        }
    }

    public void solve() {
        this.actual = inicio;
        this.camino = new Stack<Integer>();
        while (actual != fin) {
            if (isExtensible()) {
                extend();
                //Esto ocurre cuando llegamos a un callejón sin salida.
                if (actual.neighbors.size() < 1) {
                    while (actual.neighbors.size() < 1) {
                        actual.desvisitar();
                        int[] coords;
                        try {
                            coords = getCoordsInv(coordsActual, camino.pop());
                        } catch (EmptyStackException e) {
                            //Esto solo ocurre cuando no hay solución, por lo que podemos terminar aquí :D
                            return;
                        }
                         
                        actual = getBox(coords);
                        coordsActual = coords;
                    }
                }
            }
        }
        solved = true;
    }

    @Override
    public String toString() {
        String str = "";
        for (Box[] boxes : data) {
            for (Box box : boxes) {
                if (box.isWall())
                    str += "@@@@";
                else
                    if (box == inicio) str += "INIT";
                    else if (box == fin) str += "EXIT";
                    else {
                        if (box.isVisited()) str += "████";
                        else str += "    ";
                    }
            }
            str += "\n";
        }
        return str;
    }
}
