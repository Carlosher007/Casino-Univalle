package co.edu.univalle.sistema;

/**
 *
 * @author
 *
 * Carlos Andres Hernandez Agudelo
 *
 * Brian Sanchez
 */
public class Juego {

    private Sistema sistemaCasino = new Sistema();
    private int tiempoDeJuego;

    public Juego() {
        this.tiempoDeJuego = 0;
    }

    public int getTiempoDeJuego() {
        return tiempoDeJuego;
    }

    public void setTiempoDeJuego(int tiempoDeJuego) {
        this.tiempoDeJuego = tiempoDeJuego;
    }

    public void crearJugador(String nombre) {
        Jugador jugador = new Jugador(nombre);
        sistemaCasino.agregarJugador(jugador);
    }

    public void crearMaquina() {
        Jugador maquina = new Maquina();
        maquina.generarNombreAleatorio();
        sistemaCasino.agregarJugador(maquina);
    }

    public void iniciar() {
        //I don't know what it does
    }

    public int lanzarDado(Jugador jugador) {   
        return 0;
    }
    
    public Jugador determinarGanadorJuego(){
        return null;
    }
    
    public Jugador determinarGanadorLanzamiento(){
        return null;
    }
    
    

}
