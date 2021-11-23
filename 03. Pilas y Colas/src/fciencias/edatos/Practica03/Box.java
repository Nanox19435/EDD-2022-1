package fciencias.edatos.Practica03;

public class Box {
    private boolean wall;
    private boolean visited = false;
    public Queue<Integer> neighbors;

    public Box(boolean wall) {
        this.wall = wall;
        neighbors = new Queue<Integer>();
    }

    public boolean isWall() {
        return this.wall;
    }

    public boolean isVisited() {
        return this.visited;
    }

    public void visit() {
        this.visited = true;
    }

    //retira el estado de visita. Se usa para que el resultado final, al
    //imprimir el laberinto, muestre lo que se ha y lo que no se ha visitado.
    public void desvisitar() {
        this.visited = false;
    }
}
