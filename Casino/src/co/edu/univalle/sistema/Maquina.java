package co.edu.univalle.sistema;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author
 *
 * Carlos Andres Hernandez Agudelo
 *
 * Brian Sanchez
 */
public class Maquina extends Jugador {

    ArrayList<String> posiblesNombres = new ArrayList<>(Arrays.asList("Aczino", "Chuty","Teorema","Gazir", "MetaLinguistica","VallesT", "Skone",
            "Jaze", "MuMaKSO"));
    Random aleatorio = new Random();

    public Maquina() {
        this.nombre = "";
        this.debeLanzar = false;
        this.esPersona = false;
        this.tiempoDeJuego = 0;
        this.sumaResultado = 0;
        this.lanzamientosGanados = 0;
        this.rondasGanadas = 0;
        this.puntajeLanzamientoActual = 0;
    }
    
    
    @Override
    public void generarNombreAleatorio() {
        int indice = (aleatorio.nextInt(posiblesNombres.size() - 1));
        this.nombre = posiblesNombres.get(indice);
    }

}
