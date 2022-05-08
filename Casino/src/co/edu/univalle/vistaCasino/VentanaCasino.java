package co.edu.univalle.vistaCasino;

import co.edu.univalle.sistema.Jugador;
import co.edu.univalle.sistema.Maquina;
import co.edu.univalle.sistema.Sistema;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author Carlos Andres Hernandez Agudelo , Brian Sanchez
 */
public class VentanaCasino extends JFrame {

    //Declaracion de variables                    
    private Sistema juegoCasino = new Sistema();
    Random aleatorio = new Random();

    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lbldado1;
    private javax.swing.JLabel lbldado2;
    private javax.swing.JButton btnLanzar;
    private javax.swing.JTextField txtResultado;
    private Container contenedorPpal;

    private javax.swing.JLabel lblModoJuego;
    private javax.swing.JTextField txtModoJuego;
    private javax.swing.JLabel lblNombre1;
    private javax.swing.JTextField txtNombre1;
    private javax.swing.JLabel lblNombre2;
    private javax.swing.JTextField txtNombre2;
    private javax.swing.JLabel lblNumeroLanzamientos;
    private javax.swing.JTextField txtNumeroLanzamientos;
    private javax.swing.JLabel lblRondas;
    private javax.swing.JTextField txtRondas;
    private javax.swing.JButton btnSeguirResumen;
    private javax.swing.JButton btnReiniciarResumen;

    /**
     * Creamos una ventana para casino
     */
    public VentanaCasino() {
        inicializarVentana();
    }

    private void inicializarVentana() {

        inicializarComponentes();

        setLocationRelativeTo(null);
        setTitle("Ser ludopata es mi pasion"); //Título del JFrame
        setSize(600, 500); //Dimensiones del JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cerrar al salir
        setVisible(true); //Mostrar JFrame
    }

    public void elegirModoDeJuego() {
        /**
         * ELEGIR MODO DE JUEGO
         */

        Object modoDeJuego = JOptionPane.showInputDialog(VentanaCasino.this, "Seleccione un modo de Juego", "Casino Univalle", JOptionPane.QUESTION_MESSAGE, null, juegoCasino.obtenerPosibleModoDeJuego(), juegoCasino.obtenerPosibleModoDeJuego(1));

        boolean estado = true;

        while (estado) {
            if (modoDeJuego == null) {
                JOptionPane.showMessageDialog(VentanaCasino.this, "Elige una opcion");
                modoDeJuego = JOptionPane.showInputDialog(null, "Seleccione un modo de Juego", "Casino Univalle", JOptionPane.QUESTION_MESSAGE, null, juegoCasino.obtenerPosibleModoDeJuego(), juegoCasino.obtenerPosibleModoDeJuego(1));
            } else {
                //JOptionPane.showMessageDialog(VentanaCasino.this, "Se ha seleccionado el modo de juego >> " + modoDeJuego);
                juegoCasino.setModoDeJuego(modoDeJuego.toString());
                estado = false;
            }
        }

    }

    public void elegirNumeroDeLanzamientos() {

        String numeroDeLanzamientos = JOptionPane.showInputDialog("Ingrese el numero de lanzamientos por ronda: ");

        while (true) {
            if (numeroDeLanzamientos == null || "".equals(numeroDeLanzamientos)) {
                JOptionPane.showMessageDialog(VentanaCasino.this, "Ingresa un valor valido", "Casino Univalle", JOptionPane.INFORMATION_MESSAGE);
                numeroDeLanzamientos = JOptionPane.showInputDialog("Ingrese el numero de lanzamientos por ronda: ");

            } else {
                boolean estado = true;

                int lanzamientos = 0;
                try {
                    lanzamientos = Integer.parseInt(numeroDeLanzamientos);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(VentanaCasino.this, "Ingresa un valor valido", "Casino Univalle", JOptionPane.INFORMATION_MESSAGE);
                    numeroDeLanzamientos = JOptionPane.showInputDialog("Ingrese el numero de lanzamientos por ronda: ");

                    estado = false;
                }
                if (estado) {
                    //JOptionPane.showMessageDialog(VentanaCasino.this, "Se agrego correctamente el numero " + lanzamientos + " a los lanzamientos por ronda", "CasinoUnivalle", JOptionPane.INFORMATION_MESSAGE);
                    juegoCasino.setNumeroLanzamientosRonda(lanzamientos);
                    break;
                }
            }
        }

    }

    public void elegirNumeroDeRondas() {

        String numeroDeRondas = JOptionPane.showInputDialog("Ingrese el numero de rondas: ");

        while (true) {
            if (numeroDeRondas == null || "".equals(numeroDeRondas)) {
                JOptionPane.showMessageDialog(VentanaCasino.this, "Ingresa un valor valido", "Casino Univalle", JOptionPane.INFORMATION_MESSAGE);
                numeroDeRondas = JOptionPane.showInputDialog("Ingrese el numero de rondas: ");

            } else {
                boolean estado = true;

                int rondas = 0;
                try {
                    rondas = Integer.parseInt(numeroDeRondas);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(VentanaCasino.this, "Ingresa un valor valido", "Casino Univalle", JOptionPane.INFORMATION_MESSAGE);
                    numeroDeRondas = JOptionPane.showInputDialog("Ingrese el numero de rondas: ");

                    estado = false;
                }
                if (estado) {
                    //  JOptionPane.showMessageDialog(VentanaCasino.this, "Se agrego correctamente el numero " + rondas + " al total de rondas", "CasinoUnivalle", JOptionPane.INFORMATION_MESSAGE);
                    juegoCasino.setNumeroRondas(rondas);
                    break;
                }
            }
        }

    }

    public void digitarNombres() {
        if ("Jugador VS Jugador".equals(juegoCasino.getModoDeJuego())) {
            for (int i = 0; i < 2; i++) {
                String nombre = JOptionPane.showInputDialog("Ingrese el nombre del jugador #" + (i + 1) + " : ");
                while (true) {
                    if (nombre == null || "".equals(nombre)) {
                        JOptionPane.showMessageDialog(VentanaCasino.this, "Ingresa un nombre");
                        nombre = JOptionPane.showInputDialog("Ingrese el nombre del jugador #" + (i + 1) + " : ");
                    } else {
                        juegoCasino.agregarJugador(nombre);
                        break;
                    }
                }
            }
        } else if ("Jugador VS Maquina".equals(juegoCasino.getModoDeJuego())) {
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre del jugador: ");
            while (true) {
                if (nombre == null || "".equals(nombre)) {
                    JOptionPane.showMessageDialog(VentanaCasino.this, "Ingresa un nombre");
                    nombre = JOptionPane.showInputDialog("Ingrese el nombre del jugador: ");
                } else {
                    juegoCasino.agregarJugador(nombre);
                    juegoCasino.agregarMaquina();
                    break;
                }
            }
        } else {
            JOptionPane.showMessageDialog(VentanaCasino.this, "Hubo Problemas", "Casino Univalle", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    public void resumen() {

        lblModoJuego = new javax.swing.JLabel();
        lblModoJuego.setText("Modo de Juego: ");

        lblNombre1 = new javax.swing.JLabel();
        lblNombre1.setText("Nombre Jugador 1: ");

        lblNombre2 = new javax.swing.JLabel();
        if (juegoCasino.obtenerJugador(1) instanceof Maquina) {
            lblNombre2.setText("Nombre de la Maquina: ");
        } else if (juegoCasino.obtenerJugador(1) instanceof Jugador) {
            lblNombre2.setText("Nombre del Jugador 2: ");
        } else {
            lblNombre2.setText("Desconocido");

        }

        lblNumeroLanzamientos = new javax.swing.JLabel();
        lblNumeroLanzamientos.setText("Numero de lanzamientos por ronda: ");

        lblRondas = new javax.swing.JLabel();
        lblRondas.setText("Numero rondas: ");

        txtModoJuego = new javax.swing.JTextField();
        txtModoJuego.setEnabled(false);
        txtModoJuego.setText(juegoCasino.getModoDeJuego());

        txtNombre1 = new javax.swing.JTextField();
        txtNombre1.setEnabled(false);
        txtNombre1.setText(juegoCasino.obtenerJugador(0).getNombre());

        txtNombre2 = new javax.swing.JTextField();
        txtNombre2.setEnabled(false);
        txtNombre2.setText(juegoCasino.obtenerJugador(1).getNombre());

        txtNumeroLanzamientos = new javax.swing.JTextField();
        txtNumeroLanzamientos.setEnabled(false);
        txtNumeroLanzamientos.setText(juegoCasino.getNumeroLanzamientosRonda() + "");

        txtRondas = new javax.swing.JTextField();
        txtRondas.setEnabled(false);
        txtRondas.setText(juegoCasino.getNumeroRondas() + "");

        btnSeguirResumen = new javax.swing.JButton();
        btnSeguirResumen.setText("Seguir");
        btnReiniciarResumen = new javax.swing.JButton();
        btnReiniciarResumen.setText("Seguir");


        contenedorPpal = getContentPane();
        contenedorPpal.setLayout(new GridLayout(4, 2));

        contenedorPpal.add(lblModoJuego);
        contenedorPpal.add(txtModoJuego);
        contenedorPpal.add(lblNombre1);
        contenedorPpal.add(txtNombre1);
        contenedorPpal.add(lblNombre2);
        contenedorPpal.add(txtNombre2);
        contenedorPpal.add(lblNumeroLanzamientos);
        contenedorPpal.add(txtNumeroLanzamientos);
        contenedorPpal.add(lblRondas);
        contenedorPpal.add(txtRondas);
        contenedorPpal.add(btnSeguirResumen);
        contenedorPpal.add(btnReiniciarResumen);


    }

    public void inicializarComponentes() {

        EventosInternos gestorEventos = new EventosInternos();

        elegirModoDeJuego();
        elegirNumeroDeLanzamientos();
        elegirNumeroDeRondas();
        digitarNombres();
        resumen();

        lblTitulo = new javax.swing.JLabel();
        btnLanzar = new javax.swing.JButton();
        lbldado1 = new javax.swing.JLabel();
        lbldado2 = new javax.swing.JLabel();
        txtResultado = new javax.swing.JTextField();

        /*
        lblTitulo.setFont(new java.awt.Font("Perpetua Titling MT", 0, 24)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("CASINO UNVIALLE");

        btnLanzar.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnLanzar.setText("Tira los dados");
        btnLanzar.setActionCommand("BTNLANZAR");
        btnLanzar.addActionListener(gestorEventos);

        lbldado1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/dado2.png"))); // NOI18N

        lbldado2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/dado4.png"))); // NOI18N

        txtResultado.setEditable(false);
        txtResultado.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtResultado.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        contenedorPpal = getContentPane();
        contenedorPpal.setLayout(new GridLayout(4, 2));

        contenedorPpal.add(lblTitulo);
        contenedorPpal.add(lbldado1);
        contenedorPpal.add(lbldado2);
        contenedorPpal.add(txtResultado);
        contenedorPpal.add(btnLanzar);
         */
    }

    class EventosInternos implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "BTNLANZAR":
                    //Generar el tiro de los dados, usando numeros generados aleatoriamente
                    int dado1 = aleatorio.nextInt(7);
                    int dado2 = aleatorio.nextInt(7);

                    //Generamos los iconos a partir de imagenes
                    Icon uno = new javax.swing.ImageIcon(getClass().getResource("/imagenes/dado1.png"));
                    Icon dos = new javax.swing.ImageIcon(getClass().getResource("/imagenes/dado2.png"));
                    Icon tres = new javax.swing.ImageIcon(getClass().getResource("/imagenes/dado3.png"));
                    Icon cuatro = new javax.swing.ImageIcon(getClass().getResource("/imagenes/dado4.png"));
                    Icon cinco = new javax.swing.ImageIcon(getClass().getResource("/imagenes/dado5.png"));
                    Icon seis = new javax.swing.ImageIcon(getClass().getResource("/imagenes/dado6.png"));

                    //Mostramos los dados al usuario
                    switch (dado1) {
                        case 1 ->
                            lbldado1.setIcon(uno);
                        case 2 ->
                            lbldado1.setIcon(dos);
                        case 3 ->
                            lbldado1.setIcon(tres);
                        case 4 ->
                            lbldado1.setIcon(cuatro);
                        case 5 ->
                            lbldado1.setIcon(cinco);
                        case 6 ->
                            lbldado1.setIcon(seis);
                        default -> {
                        }
                    }

                    switch (dado2) {
                        case 1 ->
                            lbldado2.setIcon(uno);
                        case 2 ->
                            lbldado2.setIcon(dos);
                        case 3 ->
                            lbldado2.setIcon(tres);
                        case 4 ->
                            lbldado2.setIcon(cuatro);
                        case 5 ->
                            lbldado2.setIcon(cinco);
                        case 6 ->
                            lbldado2.setIcon(seis);
                        default -> {
                        }
                    }

                    //Mostramos por texto el resultado del lanzamiento
                    int resultado = dado1 + dado2;
                    txtResultado.setText(resultado + "");

                    break;

                default:
                    JOptionPane.showMessageDialog(null, "a");
                    break;
            }
        }

    }
}
