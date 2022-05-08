package co.edu.univalle.vistaCasino;

import co.edu.univalle.sistema.Jugador;
import co.edu.univalle.sistema.Maquina;
import co.edu.univalle.sistema.Sistema;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import javax.swing.SwingUtilities;

/**
 *
 * @author Carlos Andres Hernandez Agudelo , Brian Sanchez
 */
public class VentanaCasino extends JFrame {

    //Declaracion de variables                    
    private Sistema juegoCasino = new Sistema();
    Random aleatorio = new Random();
    GridBagConstraints constraints = new GridBagConstraints();

    // Elementos para el lanzamiento de dados
    private JLabel lblTitulo;
    private JLabel lbldado1;
    private JLabel lbldado2;
    private JButton btnLanzar;
    private JTextField txtResultado;
    private Container contenedorPpal;

    private JLabel lblModoJuego;
    private JTextField txtModoJuego;
    private JLabel lblNombre1;
    private JTextField txtNombre1;
    private JLabel lblNombre2;
    private JTextField txtNombre2;
    private JLabel lblNumeroLanzamientos;
    private JTextField txtNumeroLanzamientos;
    private JLabel lblRondas;
    private JTextField txtRondas;
    private JButton btnSeguirResumen;
    private JButton btnReiniciarResumen;


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

    private void setConstraints(int x, int y, int hx, int hy){
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = hx;
        constraints.gridheight = hy;
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
        contenedorPpal = getContentPane();        
        contenedorPpal.setLayout(new GridBagLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Perpetua Titling MT", 0, 24)); // NOI18N
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        lblTitulo.setText("CASINO UNIVALLE");
        setConstraints(0,0,2,1);
        contenedorPpal.add(lblTitulo, constraints);

        lbldado1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/dado2.png"))); // NOI18N
        setConstraints(0,1,1,1);
        contenedorPpal.add(lbldado1, constraints);

        lbldado2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/dado4.png"))); // NOI18N
        setConstraints(1,1,1,1);
        contenedorPpal.add(lbldado2, constraints);

        txtResultado.setEditable(false);
        txtResultado.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        setConstraints(0,3,3,1);
        txtResultado.setBorder(javax.swing.BorderFactory.createEmptyBorder());       
        contenedorPpal.add(txtResultado, constraints);

        btnLanzar.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnLanzar.setText("Tira los dados");
        btnLanzar.setActionCommand("BTNLANZAR");
        btnLanzar.addActionListener(gestorEventos);
        setConstraints(0,4,2,1);
        contenedorPpal.add(btnLanzar, constraints);
    */
    }

    class EventosInternos implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "BTNLANZAR":
                    //Generar el tiro de los dados, usando numeros generados aleatoriamente
                    int dado1 = 1 + aleatorio.nextInt(6);
                    int dado2 = 1 + aleatorio.nextInt(6);  

                    //Generamos los iconos a partir de imagenes
                    Icon uno = new javax.swing.ImageIcon(getClass().getResource("/imagenes/dado1.png"));
                    Icon dos = new javax.swing.ImageIcon(getClass().getResource("/imagenes/dado2.png"));
                    Icon tres = new javax.swing.ImageIcon(getClass().getResource("/imagenes/dado3.png"));
                    Icon cuatro = new javax.swing.ImageIcon(getClass().getResource("/imagenes/dado4.png"));
                    Icon cinco = new javax.swing.ImageIcon(getClass().getResource("/imagenes/dado5.png"));
                    Icon seis = new javax.swing.ImageIcon(getClass().getResource("/imagenes/dado6.png"));

                    //Mostramos los dados al usuario
                    switch (dado1) {
                        case 1 -> lbldado1.setIcon(uno);
                        case 2 -> lbldado1.setIcon(dos);
                        case 3 -> lbldado1.setIcon(tres);
                        case 4 -> lbldado1.setIcon(cuatro);
                        case 5 -> lbldado1.setIcon(cinco);
                        case 6 -> lbldado1.setIcon(seis);
                        default -> {
                    }
                    }
                    
                    switch (dado2) {
                        case 1 -> lbldado2.setIcon(uno);
                        case 2 -> lbldado2.setIcon(dos);
                        case 3 -> lbldado2.setIcon(tres);
                        case 4 -> lbldado2.setIcon(cuatro);
                        case 5 -> lbldado2.setIcon(cinco);
                        case 6 -> lbldado2.setIcon(seis);
                        default -> {
                    }
                    }
                    
                    //Mostramos por texto el resultado del lanzamiento
                    int resultado = dado1+dado2;
                    txtResultado.setText(resultado+"");

                    break;

                default:
                    JOptionPane.showMessageDialog(null, "a");
                    break;
            }
        SwingUtilities.updateComponentTreeUI(contenedorPpal);
        }

    }
}
