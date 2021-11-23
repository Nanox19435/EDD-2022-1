package fciencias.edatos.Practica03;

/**
 * Excepeción lanzada cuando se selecciona una pared como inicio o fin del laberinto.
 */
public class InvalidBoxException extends Exception {
    public InvalidBoxException(String errorMessage) {
        super(errorMessage);
    }
}
