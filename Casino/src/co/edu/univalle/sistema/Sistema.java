package co.edu.univalle.sistema;

import java.util.ArrayList;

/**
 *
 * @author
 *
 * Carlos Andres Hernandez Agudelo
 *
 * Brian Sanchez
 */
public class Sistema {

    private final String posiblesModoDeJuego[] = {"JugadorVSJuador", "JugadorVSMaquina"};
    private String modoDeJuego;
    private ArrayList<Jugador> jugadores = new ArrayList<>();
    private int numeroLanzamientosRonda;
    private int numeroRondas;

    public Sistema() {
        this.modoDeJuego = "";
        this.numeroLanzamientosRonda = 0;
        this.numeroRondas = 0;
    }

    public String getModoDeJuego() {
        return modoDeJuego;
    }

    public void setModoDeJuego(String modoDeJuego) {
        this.modoDeJuego = modoDeJuego;
    }

    public int getNumeroLanzamientosRonda() {
        return numeroLanzamientosRonda;
    }

    public void setNumeroLanzamientosRonda(int numeroLanzamientosRonda) {
        this.numeroLanzamientosRonda = numeroLanzamientosRonda;
    }

    public int getNumeroRondas() {
        return numeroRondas;
    }

    public void setNumeroRondas(int numeroRondas) {
        this.numeroRondas = numeroRondas;
    }
    
    public void agregarJugador(Jugador jugador){
        jugadores.add(jugador);
    }
    
    public void eliminarJugadorPorIndice(int indice){
        jugadores.remove(indice);
    }
    
    public void eliminarTodosLosJugadores(){
        jugadores.clear();
    }
    

}
