package co.edu.univalle.vistaCasino;

import co.edu.univalle.sistema.Jugador;
import co.edu.univalle.sistema.Maquina;
import co.edu.univalle.sistema.Sistema;
import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.SwingUtilities;

/**
 * Laboratorio N.1: primer miniproyecto. Archivo: Inicio.java Autores (Grupo 01 POE): -Brayan Andrés
 * Sánchez Lozano <brayan.andres.sanchez@correounivalle.edu.co>
 * -Carlos Andrés Hernandez Agudelo <carlos.hernandez.agudelo@correounivalle.edu.co>
 * Fecha creación: 07-05-2022 Fecha última modificación: 10-02-2022 Docente: -Luis Romo
 * <luis.romo@correounivalle.edu.co>
 */
public class Inicio extends JFrame {

    private final Sistema juegoCasino = new Sistema();

    //Declaracion de variables                    
    Random aleatorio = new Random();
    GridBagConstraints constraints = new GridBagConstraints();
    EventosInternos gestorEventos = new EventosInternos();

    //Timer para iniciar la contabilización
    private Timer tiempo;

    //Componentes de la ventana de bienvenida al juego
    private JLabel lblTitulo;
    private JLabel lblLogo;
    private JButton btnEmpezar;
    private Container contenedorPpal;

    //Componentes de la ventana para escoger el modo de juego
    private JPanel panelCentro;
    private JPanel panelImagenesI;
    private JPanel panelImagenesD;
    private JPanel panelTitulo;
    private JPanel panelBotonesI;
    private JPanel panelBotonesD;
    private JLabel lblMensaje;
    private JLabel lblImagenUs1;
    private JLabel lblImagenUs2;
    private JLabel lblImagenUs3;
    private JLabel lblMaquina;
    private JButton btnDosPersonas;
    private JButton btnUnaPersona;

    //Componentes dispuestos para ingresar la información requerida para continuar con el juego
    private JTextField txtCampo;
    private JTextField txtCampo2;
    private JButton btnSeguir;
    private JLabel lblMensaje2;
    private JPanel panelCampos;
    private JPanel panelnombres;

    //Componentes para el lanzamiento de dados
    private JLabel lbldado1;
    private JLabel lbldado2;
    private JButton btnLanzar;
    private JTextField txtResultado;
    private JPanel flowPanel;

    //Componentes con los que se brinda un resumen de la selección de modo de juego
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

    //Componentes con los que se brindan diversos datos en el desarrollo del juego
    private JLabel lblLanzamientos;
    private JTextField txtLanzamientos;
    private JLabel lblLanzamientosJ1;
    private JTextField txtLanzamientosJ1;
    private JLabel lblLanzamientosJ2;
    private JTextField txtLanzamientosJ2;
    private JLabel lblLanzamientosEmpate;
    private JTextField txtLanzamientosEmpate;
    private JLabel lblGanadorParcial;
    private JTextField txtGanadorParcial;
    private JLabel lblSumatoriaJ1;
    private JTextField txtSumatoriaJ1;
    private JLabel lblSumatoriaJ2;
    private JTextField txtSumatoriaJ2;
    private JLabel lblTiempoRonda;
    private JTextField txtTiempoRonda;
    private JLabel lblTurnoActual;
    private JTextField txtTurnoActual;
    private JButton btnJugarDeNuevo;
    private JButton btnSalir;

    //Variables necesarias para realizar acciones con la lógica del programa
    private int empates;
    private int sumatoriaJ1;
    private int sumatoriaJ2;
    private int resultado;
    private boolean tirohecho = false;

    //Función que facilita el uso de los constraints para el GridBagLayout
    private void setConstraints(int x, int y, int hx, int hy) {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = hx;
        constraints.gridheight = hy;
    }

    //Creación de la ventana para el casino
    public Inicio() {
        inicializarVentana();
    }

    //Características iniciales de la ventana
    private void inicializarVentana() {
        BufferedImage img;
        try {
            img = ImageIO.read(getClass().getResource("/imagenes/fondodados.png"));

            setLayout(new BorderLayout());
            setContentPane(new JLabel(new ImageIcon(img)));
            Dimension dim = new Dimension(img.getWidth(), img.getHeight());
            setSize(dim);
            setMinimumSize(dim);
            setMaximumSize(dim);
            setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JFrame.setDefaultLookAndFeelDecorated(true);
        setLocationRelativeTo(null);
        setTitle("Casino Univalle");
        setSize(900, 500);
        setLocation(100, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cerrar al salir
        setVisible(true);
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
        inicializarComponentes();
        t = new Timer(5, acciones);
        SwingUtilities.updateComponentTreeUI(contenedorPpal);
    }

    //SwingUtilities.updateComponentTreeUI(contenedorPpal); es utilizado para forzar al contenedor a actualizarse (Y así mostrar cambios)
    //Timer utilizado para contabilizar el tiempo de las rondas
    private Timer t;
    private int h, m, s, cs;
    private final ActionListener acciones = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            cs++;
            if (cs == 60) {
                cs = 0;
                ++s;
                cronometro(m, s);
            }
            if (s == 60) {
                s = 0;
                ++m;
                cronometro(m, s);
            }
            if (m == 60) {
                m = 0;
                ++h;
            }
        }
    };

    //Inicializamos los componentes para la ventana Inicio
    public void inicializarComponentes() {
        contenedorPpal = getContentPane();
        contenedorPpal.setLayout(new GridBagLayout());

        JLabel lblTemp1 = new JLabel("   ");
        JLabel lblTemp2 = new JLabel("  ");
        JLabel lblTemp3 = new JLabel("  ");
        JLabel lblTemp4 = new JLabel("  ");
        JLabel lblTemp5 = new JLabel("  ");

//        lblTitulo = new JLabel();
//        lblTitulo.setText("BIENVENIDOS");
//        lblTitulo.setFont(new java.awt.Font("Perpetua Titling MT", 0, 24)); // NOI18N
//        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo = new JLabel();
        Icon logo = new javax.swing.ImageIcon(getClass().getResource("/imagenes/mensaje.png"));
        lblLogo.setIcon(logo);

        btnEmpezar = new JButton();
        btnEmpezar.setActionCommand("EMPEZAR");
        btnEmpezar.addActionListener(gestorEventos);
        btnEmpezar.setFont(new java.awt.Font("Perpetua Titling MT", 0, 18)); // NOI18N
        btnEmpezar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnEmpezar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/unlock_norm.png"))); // NOI18N
        btnEmpezar.setAutoscrolls(true);
        btnEmpezar.setBorder(null);
        btnEmpezar.setBorderPainted(false);
        btnEmpezar.setContentAreaFilled(false);
        btnEmpezar.setFocusPainted(false);
        btnEmpezar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/unlock_press.png"))); // NOI18N
        btnEmpezar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/unlock_roll.png"))); // NOI18N

        JPanel panel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setOpaque(false);

        // Put constraints on different buttons
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.gridheight = 1;
//        gbc.fill = GridBagConstraints.CENTER;
//        panel.add(lblTitulo, gbc);
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        gbc.gridwidth = 3;
//        gbc.fill = GridBagConstraints.CENTER;
//        panel.add(lblTemp3, gbc);
//
//        gbc.gridx = 0;
//        gbc.gridy = 2;
//        gbc.gridwidth = 3;
//        gbc.fill = GridBagConstraints.CENTER;
//        panel.add(lblTemp4, gbc);
//
//        gbc.gridx = 0;
//        gbc.gridy = 3;
//        gbc.gridwidth = 3;
//        gbc.fill = GridBagConstraints.CENTER;
//        panel.add(lblTemp5, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.CENTER;
        panel.add(lblLogo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.CENTER;
        panel.add(lblTemp1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.CENTER;
        panel.add(lblTemp2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridwidth = 3;
        panel.add(btnEmpezar, gbc);

        setConstraints(0, 0, 1, 0);
        contenedorPpal.add(panel, constraints);
    }

    private void limpiarVentana() {
        contenedorPpal.removeAll();
        SwingUtilities.updateComponentTreeUI(contenedorPpal);
    }

    class EventosInternos implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "EMPEZAR":
                    limpiarVentana();
                    inicializarModoDeJuego();
                    break;
                case "DOS_JUGADORES":
                    limpiarVentana();
                    juegoCasino.setModoDeJuego("Jugador VS Jugador");
                    inicializarLanzamientoPorRonda();
                    break;
                case "UN_JUGADOR":
                    limpiarVentana();
                    juegoCasino.setModoDeJuego("Jugador VS Maquina");
                    inicializarLanzamientoPorRonda();
                    break;
                case "BOTON_SEGUIR_LANZAMIENTO":
                    String numeroDeLanzamientos = txtCampo.getText();

                    if (numeroDeLanzamientos == null || "".equals(numeroDeLanzamientos) || "0".equals(numeroDeLanzamientos)) {
                        JOptionPane.showMessageDialog(Inicio.this, "Ingresa un valor valido", "Casino Univalle", JOptionPane.WARNING_MESSAGE);
                    } else {
                        boolean estado = true;

                        int lanzamientos = 0;
                        try {
                            lanzamientos = Integer.parseInt(numeroDeLanzamientos);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(Inicio.this, "Ingresa un valor valido", "Casino Univalle", JOptionPane.WARNING_MESSAGE);
                            estado = false;
                        }
                        if (estado) {
                            juegoCasino.setNumeroLanzamientosRonda(lanzamientos);
                            juegoCasino.setNumeroLanzamientosCopia(lanzamientos);
                            limpiarVentana();
                            inicializarNombres();
                            break;
                        }
                    }
                    break;
                case "BOTON_SEGUIR_NOMBRES":
                    if ("Jugador VS Jugador".equals(juegoCasino.getModoDeJuego())) {
                        String nombre1 = txtCampo.getText();
                        String nombre2 = txtCampo2.getText();
                        if (nombre1 == null || "".equals(nombre1) || nombre2 == null || "".equals(nombre2) || nombre1.equals(nombre2)) {
                            JOptionPane.showMessageDialog(Inicio.this, "Ingresa nombres validos", "Casino Univalle", JOptionPane.WARNING_MESSAGE);
                        } else {
                            juegoCasino.agregarJugador(nombre1);
                            juegoCasino.agregarJugador(nombre2);
                            limpiarVentana();
                            resumen();
                            break;
                        }
                    } else if ("Jugador VS Maquina".equals(juegoCasino.getModoDeJuego())) {
                        String nombre1 = txtCampo.getText();
                        if (nombre1 == null || "".equals(nombre1)) {
                            JOptionPane.showMessageDialog(Inicio.this, "Ingresa nombres validos", "Casino Univalle", JOptionPane.WARNING_MESSAGE);

                        } else {
                            juegoCasino.agregarJugador(nombre1);
                            juegoCasino.agregarMaquina();

                            //INICIALIZAR TIEMPO EN GENERAL
                            limpiarVentana();
                            resumen();
                            break;
                        }
                    } else {
                        JOptionPane.showMessageDialog(Inicio.this, "Hubo problemas en los datos, disculpe", "Casino Univalle", JOptionPane.WARNING_MESSAGE);
                        System.exit(0);
                    }
                    break;
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
                    resultado = dado1 + dado2;
                    txtResultado.setText(resultado + "");
                    SwingUtilities.updateComponentTreeUI(contenedorPpal);
                    btnLanzar.setEnabled(false);
                    btnSeguir.setEnabled(true);
                    tirohecho = true;

                    break;

                case "LIMPIAR":
                    limpiarVentana();
                    break;

                case "LIMPIAR+CREAR":
                    limpiarVentana();
                    crearInterfazJuego();
                    primeraRonda();
                    break;

                case "PASAR-TURNO":
                    txtResultado.setText("");
                    SwingUtilities.updateComponentTreeUI(contenedorPpal);
                    boolean J1 = juegoCasino.obtenerJugador(0).isDebeLanzar();
                    boolean J2 = juegoCasino.obtenerJugador(1).isDebeLanzar();
                    System.out.println(juegoCasino.getModoDeJuego());
                    System.out.println("J2" + juegoCasino.obtenerJugador(1).isDebeLanzar());
                    System.out.println("J1" + juegoCasino.obtenerJugador(0).isDebeLanzar());

                    if ("Jugador VS Maquina".equals(juegoCasino.getModoDeJuego()) && J1 == true && tirohecho == true) {
                        pasoDeTurno(J1, J2);
                    } else if ("Jugador VS Jugador".equals(juegoCasino.getModoDeJuego())) {
                        pasoDeTurno(J1, J2);
                    }
                    btnLanzar.setEnabled(true);
                    btnSeguir.setEnabled(false);
                    break;
                case "LIMPIAR_RESUMEN":
                    limpiarVentana();
                    inicializarModoDeJuego();
                    break;
                case "REINICIAR_FIN_JUEGO":
                    juegoCasino.setModoDeJuego("");
                    juegoCasino.getJugadores().clear();
                    juegoCasino.setNumeroLanzamientosRonda(0);
                    System.out.println(juegoCasino.getNumeroLanzamientosCopia());
                    juegoCasino.setTiempoDeJuego(0);
                    juegoCasino.setLanzamientosEmpatados(0);
                    juegoCasino.setNumeroLanzamientoActual(0);
                    juegoCasino.setEnJuego(false);
                    h = 0;
                    m = 0;
                    s = 0;
                    cs = 0;
                    empates = 0;
                    sumatoriaJ1 = 0;
                    sumatoriaJ2 = 0;
                    resultado = 0;
                    limpiarVentana();
                    inicializarModoDeJuego();
                case "JUGAR_DE_NUEVO":
                    juegoCasino.setNumeroLanzamientosRonda(juegoCasino.getNumeroLanzamientosCopia());
                    juegoCasino.setTiempoDeJuego(0);
                    juegoCasino.setLanzamientosEmpatados(0);
                    juegoCasino.setNumeroLanzamientoActual(0);
                    juegoCasino.setEnJuego(true);
                    juegoCasino.setNumeroLanzamientosCopia(0);
                    juegoCasino.obtenerJugador(0).setDebeLanzar(false);
                    juegoCasino.obtenerJugador(1).setDebeLanzar(false);
                    h = 0;
                    m = 0;
                    s = 0;
                    cs = 0;
                    empates = 0;
                    sumatoriaJ1 = 0;
                    sumatoriaJ2 = 0;
                    resultado = 0;
                    limpiarVentana();
                    crearInterfazJuego();
                    primeraRonda();
                    break;
                case "SALIR_FIN_JUEGO":
                    JOptionPane.showMessageDialog(Inicio.this, "Hasta la proxima", "Casino Univalle", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);

                    break;
                default:
                    JOptionPane.showMessageDialog(Inicio.this, "Hubo problemas en los datos, disculpe", "Casino Univalle", JOptionPane.WARNING_MESSAGE);

                    System.exit(0);
                    break;
            }
        }
    }

    public void ganadorParcial(int J1, int J2) {
        if (J1 > J2) {
            txtGanadorParcial.setText(juegoCasino.obtenerJugador(0).getNombre());
        } else if (J1 < J2) {
            txtGanadorParcial.setText(juegoCasino.obtenerJugador(1).getNombre());
        } else {
            txtGanadorParcial.setText("Ninguno");
        }
        SwingUtilities.updateComponentTreeUI(contenedorPpal);
    }

    public void deshabilitarMouse() {
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                cursorImg, new Point(0, 0), "blank cursor");
        getContentPane().setCursor(blankCursor);
    }

    public void habilitarMouse() {
        getContentPane().setCursor(Cursor.getDefaultCursor());
    }

    public void finDelJuego() {
        t.stop();
        juegoCasino.setEnJuego(false);
        juegoCasino.obtenerJugador(0).setDebeLanzar(false);
        juegoCasino.obtenerJugador(1).setDebeLanzar(false);

        btnLanzar.setEnabled(false);
        btnSeguir.setEnabled(false);
        int delay = 1500;
        Timer timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarVentana();
                SwingUtilities.updateComponentTreeUI(contenedorPpal);
                if (!"Ninguno".equals(txtGanadorParcial.getText())) {
                    JOptionPane.showMessageDialog(Inicio.this, "¡Felicidades " + txtGanadorParcial.getText() + ", ganaste!", "Casino Univalle", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(Inicio.this, "¡Fue un gran duelo!, quedaron empatados.", "Casino Univalle", JOptionPane.WARNING_MESSAGE);
                }
                lblGanadorParcial.setText("Jugador Vencedor: ");
                setConstraints(0, 1, 2, 1);
                contenedorPpal.add(lblGanadorParcial, constraints);
                setConstraints(2, 1, 2, 1);
                contenedorPpal.add(txtGanadorParcial, constraints);

                btnJugarDeNuevo = new JButton();
                btnSalir = new JButton();

                lblLanzamientosJ1.setFont(new java.awt.Font("Century Gothic", 0, 18));
                lblLanzamientosJ1.setText("# de Lanzamientos Jugador 1: ");
                setConstraints(0, 2, 1, 1);
                contenedorPpal.add(lblLanzamientosJ1, constraints);

                txtLanzamientosJ1.setEditable(false);
                txtLanzamientosJ1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
                setConstraints(1, 2, 1, 1);
                txtLanzamientosJ1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
                contenedorPpal.add(txtLanzamientosJ1, constraints);

                lblLanzamientosJ2.setFont(new java.awt.Font("Century Gothic", 0, 18));
                lblLanzamientosJ2.setText("# de Lanzamientos Jugador 2: ");
                setConstraints(0, 3, 1, 1);
                contenedorPpal.add(lblLanzamientosJ2, constraints);

                txtLanzamientosJ2.setEditable(false);
                txtLanzamientosJ2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
                setConstraints(1, 3, 1, 1);
                txtLanzamientosJ2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
                contenedorPpal.add(txtLanzamientosJ2, constraints);

                lblLanzamientosEmpate.setFont(new java.awt.Font("Century Gothic", 0, 18));
                lblLanzamientosEmpate.setText("# de Lanzamientos con Empate: ");
                setConstraints(0, 4, 1, 1);
                contenedorPpal.add(lblLanzamientosEmpate, constraints);

                txtLanzamientosEmpate.setEditable(false);
                txtLanzamientosEmpate.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
                setConstraints(1, 4, 1, 1);
                txtLanzamientosEmpate.setBorder(javax.swing.BorderFactory.createEmptyBorder());
                contenedorPpal.add(txtLanzamientosEmpate, constraints);

                lblSumatoriaJ1.setFont(new java.awt.Font("Century Gothic", 0, 18));
                lblSumatoriaJ1.setText("Sumatoria Jugador 1: ");
                setConstraints(2, 2, 1, 1);
                contenedorPpal.add(lblSumatoriaJ1, constraints);

                txtSumatoriaJ1.setEditable(false);
                txtSumatoriaJ1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
                setConstraints(3, 2, 1, 1);
                txtSumatoriaJ1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
                contenedorPpal.add(txtSumatoriaJ1, constraints);

                lblSumatoriaJ2.setFont(new java.awt.Font("Century Gothic", 0, 18));
                lblSumatoriaJ2.setText("Sumatoria Jugador 2: ");
                setConstraints(2, 3, 1, 1);
                contenedorPpal.add(lblSumatoriaJ2, constraints);

                txtSumatoriaJ2.setEditable(false);
                txtSumatoriaJ2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
                setConstraints(3, 3, 1, 1);
                txtSumatoriaJ2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
                contenedorPpal.add(txtSumatoriaJ2, constraints);

                lblTiempoRonda.setFont(new java.awt.Font("Century Gothic", 0, 18));
                lblTiempoRonda.setText("Tiempo Ronda: ");
                setConstraints(2, 4, 1, 1);
                contenedorPpal.add(lblTiempoRonda, constraints);

                txtTiempoRonda.setEditable(false);
                txtTiempoRonda.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
                setConstraints(3, 4, 1, 1);
                txtTiempoRonda.setText(txtTiempoRonda.getText());
                txtTiempoRonda.setBorder(javax.swing.BorderFactory.createEmptyBorder());
                contenedorPpal.add(txtTiempoRonda, constraints);

                /*lblTiempoJ1.setFont(new java.awt.Font("Century Gothic", 0, 18));
                lblTiempoJ1.setText("Tiempo Jugador 1: ");
                setConstraints(4, 4, 1, 1);
                contenedorPpal.add(lblTiempoJ1, constraints);

                txtTiempoJ1.setEditable(false);
                txtTiempoJ1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
                setConstraints(5, 4, 1, 1);
                txtTiempoJ1.setText(juegoCasino.obtenerJugador(0).getTiempoDeJuego() + " segundos");
                txtTiempoJ1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
                contenedorPpal.add(txtTiempoJ1, constraints);
                
                lblTiempoJ2.setFont(new java.awt.Font("Century Gothic", 0, 18));
                lblTiempoJ2.setText("Tiempo Jugador 2: ");
                setConstraints(5, 4, 1, 1);
                contenedorPpal.add(lblTiempoJ1, constraints);

                txtTiempoJ2.setEditable(false);
                txtTiempoJ2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
                setConstraints(6, 4, 1, 1);
                txtTiempoJ2.setText(juegoCasino.obtenerJugador(1).getTiempoDeJuego() + " segundos");
                txtTiempoJ2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
                contenedorPpal.add(txtTiempoJ1, constraints);
                 */
                flowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 10));
                flowPanel.setOpaque(false);
                flowPanel.setBorder(BorderFactory.createEmptyBorder());
                setConstraints(0, 5, 4, 1);
                contenedorPpal.add(flowPanel, constraints);

                btnJugarDeNuevo.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
                // btnJugarDeNuevo.setText("Jugar de Nuevo");
                btnJugarDeNuevo.setActionCommand("JUGAR_DE_NUEVO");
                btnJugarDeNuevo.addActionListener(gestorEventos);
                btnReiniciarResumen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/nuevo_norm.png"))); // NOI18N
                btnReiniciarResumen.setAutoscrolls(true);
                btnReiniciarResumen.setBorder(null);
                btnReiniciarResumen.setBorderPainted(false);
                btnReiniciarResumen.setContentAreaFilled(false);
                btnReiniciarResumen.setFocusPainted(false);
                btnReiniciarResumen.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/nuevo_press.png"))); // NOI18N
                btnReiniciarResumen.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/nuevo_roll.png"))); // NOI18N
                setConstraints(0, 6, 1, 1);
                contenedorPpal.add(btnJugarDeNuevo, constraints);

                btnReiniciarResumen.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
                // btnReiniciarResumen.setText("Reiniciar Casino");
                btnReiniciarResumen.setActionCommand("REINICIAR_FIN_JUEGO"); //FALTA CREAR ESTE COMANDO
                btnReiniciarResumen.addActionListener(gestorEventos);
                btnJugarDeNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/vista_prev_norm.png"))); // NOI18N
                btnJugarDeNuevo.setAutoscrolls(true);
                btnJugarDeNuevo.setBorder(null);
                btnJugarDeNuevo.setBorderPainted(false);
                btnJugarDeNuevo.setContentAreaFilled(false);
                btnJugarDeNuevo.setFocusPainted(false);
                btnJugarDeNuevo.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/vista_prev_press.png"))); // NOI18N
                btnJugarDeNuevo.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/vista_prev_roll.png"))); // NOI18N
                setConstraints(1, 6, 1, 1);
                contenedorPpal.add(btnReiniciarResumen, constraints);

                btnSalir.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
                //btnSalir.setText("Finalizar");
                btnSalir.setActionCommand("SALIR_FIN_JUEGO"); //FALTA CREAR ESTE COMANDO
                btnSalir.addActionListener(gestorEventos);
                btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/salir_norm.png"))); // NOI18N
                btnSalir.setAutoscrolls(true);
                btnSalir.setBorder(null);
                btnSalir.setBorderPainted(false);
                btnSalir.setContentAreaFilled(false);
                btnSalir.setFocusPainted(false);
                btnSalir.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/salir_press.png"))); // NOI18N
                btnSalir.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/salir_roll.png"))); // NOI18N
                setConstraints(2, 6, 1, 1);
                contenedorPpal.add(btnSalir, constraints);

                SwingUtilities.updateComponentTreeUI(contenedorPpal);
            }
        });
        timer.setRepeats(false);
        timer.start();

    }

    public void tiroDeMaquina() {
        int delay = 1500;
        Timer timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                btnLanzar.setEnabled(true);
                btnLanzar.doClick();
                pasoDeTurno(false, true);
            }
        });
        timer.setRepeats(false);
        timer.start();

        int delay2 = 3000;
        Timer timer2 = new Timer(delay2, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tirohecho = false;
                btnSeguir.doClick();
                btnLanzar.setEnabled(true);
                btnSeguir.setEnabled(false);
            }
        });
        timer2.setRepeats(false);
        timer2.start();
    }

    public void pasoDeTurno(boolean J1, boolean J2) {
        int lanzamientosRestantes = juegoCasino.getNumeroLanzamientosRonda();
        int lanzamientosRealizadosJ1 = juegoCasino.obtenerJugador(0).getLanzamientosHechos();
        int lanzamientosRealizadosJ2 = juegoCasino.obtenerJugador(1).getLanzamientosHechos();
        int puntajeJ1 = juegoCasino.obtenerJugador(0).getPuntajeLanzamientoActual();
        int puntajeJ2 = juegoCasino.obtenerJugador(1).getPuntajeLanzamientoActual();

        if (J1 == true) {
            puntajeJ1 = resultado;
            lanzamientosRealizadosJ1 += 1;
            juegoCasino.obtenerJugador(0).setLanzamientosHechos(lanzamientosRealizadosJ1);
            txtLanzamientosJ1.setText(lanzamientosRealizadosJ1 + "");
            juegoCasino.obtenerJugador(0).setPuntajeLanzamientoActual(puntajeJ1);
            sumatoriaJ1 += puntajeJ1;
            txtSumatoriaJ1.setText(sumatoriaJ1 + "");
            juegoCasino.obtenerJugador(0).setDebeLanzar(false);
            juegoCasino.obtenerJugador(1).setDebeLanzar(true);
            txtTurnoActual.setText(juegoCasino.obtenerJugador(1).getNombre());
            SwingUtilities.updateComponentTreeUI(contenedorPpal);
            ganadorParcial(sumatoriaJ1, sumatoriaJ2);
            if (juegoCasino.getModoDeJuego() == "Jugador VS Maquina") {
                tiroDeMaquina();
                deshabilitarMouse();
            }
        } else {
            SwingUtilities.updateComponentTreeUI(contenedorPpal);
            puntajeJ2 = resultado;
            if (puntajeJ1 == puntajeJ2) {
                lanzamientosRealizadosJ1 -= 1;
                juegoCasino.obtenerJugador(0).setLanzamientosHechos(lanzamientosRealizadosJ1);
                txtLanzamientosJ1.setText(lanzamientosRealizadosJ1 + "");
                sumatoriaJ1 -= puntajeJ1;
                txtSumatoriaJ1.setText(sumatoriaJ1 + "");
                juegoCasino.obtenerJugador(0).setDebeLanzar(true);
                juegoCasino.obtenerJugador(1).setDebeLanzar(false);
                txtTurnoActual.setText(juegoCasino.obtenerJugador(0).getNombre());
                SwingUtilities.updateComponentTreeUI(contenedorPpal);
                empates += 1;
                txtLanzamientosEmpate.setText(empates + "");
                if (lanzamientosRealizadosJ1 != 0) {
                    JOptionPane.showMessageDialog(Inicio.this, "¡Empate! Se repetirán los lanzamientos", "Casino Univalle", JOptionPane.WARNING_MESSAGE);
                }
                ganadorParcial(sumatoriaJ1, sumatoriaJ2);
                habilitarMouse();
            } else {
                lanzamientosRestantes -= 1;
                juegoCasino.setNumeroLanzamientosRonda(lanzamientosRestantes);
                txtLanzamientos.setText(lanzamientosRestantes + "");
                lanzamientosRealizadosJ2 += 1;
                juegoCasino.obtenerJugador(1).setLanzamientosHechos(lanzamientosRealizadosJ2);
                txtLanzamientosJ2.setText(lanzamientosRealizadosJ2 + "");
                juegoCasino.obtenerJugador(1).setPuntajeLanzamientoActual(puntajeJ2);
                sumatoriaJ2 += puntajeJ2;
                txtSumatoriaJ2.setText(sumatoriaJ2 + "");
                txtTurnoActual.setText(juegoCasino.obtenerJugador(0).getNombre());
                SwingUtilities.updateComponentTreeUI(contenedorPpal);
                ganadorParcial(sumatoriaJ1, sumatoriaJ2);
                juegoCasino.obtenerJugador(1).setDebeLanzar(false);
                juegoCasino.obtenerJugador(0).setDebeLanzar(true);
                habilitarMouse();
                if (lanzamientosRestantes == 0) {
                    finDelJuego();
                }
            }
        }

    }

    public void resumen() {

        contenedorPpal = getContentPane();
        contenedorPpal.setLayout(new GridBagLayout());

        lblModoJuego = new javax.swing.JLabel();
        lblModoJuego.setText("Modo de Juego: ");
        lblModoJuego.setFont(new java.awt.Font("Arial Narrow", 0, 19)); // NOI18N
        lblModoJuego.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblNombre1 = new javax.swing.JLabel();
        lblNombre1.setText("Nombre Jugador 1: ");
        lblNombre1.setFont(new java.awt.Font("Arial Narrow", 0, 19)); // NOI18N
        lblNombre1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblNombre2 = new javax.swing.JLabel();
        if (juegoCasino.obtenerJugador(1) instanceof Maquina) {
            lblNombre2.setText("Nombre de la Maquina: ");
        } else if (juegoCasino.obtenerJugador(1) instanceof Jugador) {
            lblNombre2.setText("Nombre del Jugador 2: ");
        } else {
            lblNombre2.setText("Desconocido: ");
        }
        lblNombre2.setFont(new java.awt.Font("Arial Narrow", 0, 19)); // NOI18N
        lblNombre2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblNumeroLanzamientos = new javax.swing.JLabel();
        lblNumeroLanzamientos.setText("Numero de lanzamientos por ronda: ");
        lblNumeroLanzamientos.setFont(new java.awt.Font("Arial Narrow", 0, 19)); // NOI18N
        lblNumeroLanzamientos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblRondas = new javax.swing.JLabel();
        lblRondas.setText("Numero rondas: ");
        lblRondas.setFont(new java.awt.Font("Arial Narrow", 0, 19)); // NOI18N
        lblRondas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        txtModoJuego = new javax.swing.JTextField();
        txtModoJuego.setFont(new java.awt.Font("Arial Narrow", 0, 19)); // NOI18N
        txtModoJuego.setText(juegoCasino.getModoDeJuego());
        txtModoJuego.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtModoJuego.setOpaque(false);
        txtModoJuego.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        txtModoJuego.setDisabledTextColor(Color.BLACK);
        txtModoJuego.setEnabled(false);

        SwingUtilities.updateComponentTreeUI(contenedorPpal);

        txtNombre1 = new javax.swing.JTextField();
        txtNombre1.setEnabled(false);
        txtNombre1.setText(juegoCasino.obtenerJugador(0).getNombre());
        txtNombre1.setFont(new java.awt.Font("Arial Narrow", 0, 19)); // NOI18N
        txtNombre1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNombre1.setOpaque(false);
        txtNombre1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        txtNombre1.setDisabledTextColor(Color.BLACK);

        txtNombre2 = new javax.swing.JTextField();
        txtNombre2.setEnabled(false);
        txtNombre2.setText(juegoCasino.obtenerJugador(1).getNombre());
        txtNombre2.setFont(new java.awt.Font("Arial Narrow", 0, 19)); // NOI18N
        txtNombre2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNombre2.setOpaque(false);
        txtNombre2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        txtNombre2.setDisabledTextColor(Color.BLACK);

        txtNumeroLanzamientos = new javax.swing.JTextField();
        txtNumeroLanzamientos.setEnabled(false);
        txtNumeroLanzamientos.setText(juegoCasino.getNumeroLanzamientosRonda() + "");
        txtNumeroLanzamientos.setFont(new java.awt.Font("Arial Narrow", 0, 19)); // NOI18N
        txtNumeroLanzamientos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNumeroLanzamientos.setOpaque(false);
        txtNumeroLanzamientos.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        txtNumeroLanzamientos.setDisabledTextColor(Color.BLACK);

        txtRondas = new javax.swing.JTextField();
        txtRondas.setEnabled(false);
        txtRondas.setText(juegoCasino.getNumeroRondas() + "");
        txtRondas.setFont(new java.awt.Font("Arial Narrow", 0, 19)); // NOI18N
        txtRondas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRondas.setOpaque(false);
        txtRondas.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        txtRondas.setDisabledTextColor(Color.BLACK);

        btnSeguirResumen = new javax.swing.JButton();
        btnSeguirResumen.setFont(new java.awt.Font("Perpetua Titling MT", 0, 18)); // NOI18N
        btnSeguirResumen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        //btnSeguirResumen.setText("Seguir");
        btnSeguirResumen.setActionCommand("LIMPIAR+CREAR");
        btnSeguirResumen.addActionListener(gestorEventos);
        btnSeguirResumen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/acep_norm.png"))); // NOI18N
        btnSeguirResumen.setAutoscrolls(true);
        btnSeguirResumen.setBorder(null);
        btnSeguirResumen.setBorderPainted(false);
        btnSeguirResumen.setContentAreaFilled(false);
        btnSeguirResumen.setFocusPainted(false);
        btnSeguirResumen.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/acep_press.png"))); // NOI18N
        btnSeguirResumen.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/acep_roll.png"))); // NOI18N

        btnReiniciarResumen = new javax.swing.JButton();
        btnReiniciarResumen.setFont(new java.awt.Font("Perpetua Titling MT", 0, 18)); // NOI18N
        btnReiniciarResumen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        //btnReiniciarResumen.setText("Reiniciar");
        btnReiniciarResumen.setActionCommand("LIMPIAR_RESUMEN");
        btnReiniciarResumen.addActionListener(gestorEventos);
        btnReiniciarResumen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/limpiar_norm.png"))); // NOI18N
        btnReiniciarResumen.setAutoscrolls(true);
        btnReiniciarResumen.setBorder(null);
        btnReiniciarResumen.setBorderPainted(false);
        btnReiniciarResumen.setContentAreaFilled(false);
        btnReiniciarResumen.setFocusPainted(false);
        btnReiniciarResumen.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/limpiar_press.png"))); // NOI18N
        btnReiniciarResumen.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/limpiar_roll.png"))); // NOI18N

        panelCentro = new JPanel();
        panelCentro.setLayout(new GridLayout(5, 1)); // Vamos a agrupar paneles dentro de un "grid"
        panelCentro.setOpaque(false);

        lblTitulo = new JLabel();
        lblTitulo.setText("CASINO UNIVALLE");
        lblTitulo.setFont(new java.awt.Font("Perpetua Titling MT", 0, 24)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        panelTitulo = new JPanel();
        panelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelTitulo.add(lblTitulo);
        panelTitulo.setOpaque(false);

        panelnombres = new JPanel();
        panelnombres.setLayout(new GridLayout(5, 2, 50, 0));
        panelnombres.add(lblModoJuego);
        panelnombres.add(txtModoJuego);
        panelnombres.add(lblNombre1);
        panelnombres.add(txtNombre1);
        panelnombres.add(lblNombre2);
        panelnombres.add(txtNombre2);
        panelnombres.add(lblNumeroLanzamientos);
        panelnombres.add(txtNumeroLanzamientos);
        panelnombres.add(lblRondas);
        panelnombres.add(txtRondas);
        panelnombres.setOpaque(false);

        panelBotonesI = new JPanel();
        panelBotonesI.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelBotonesI.add(btnSeguirResumen);
        panelBotonesI.add(btnReiniciarResumen);
        panelBotonesI.setOpaque(false);

        panelCentro.add(panelTitulo);
        panelCentro.add(panelnombres);
        panelCentro.add(panelBotonesI);
        panelCentro.setOpaque(false);

        contenedorPpal.add(panelCentro);

    }

    public void crearInterfazJuego() {
        t.start();
        juegoCasino.setEnJuego(true);

        tiempo = new Timer(1000, null);
        tiempo.start();

        tiempo.addActionListener((ActionEvent e) -> {
            inicializarTiempoGenral();
            inicializarTiempoJugador1();
            inicializarTiempoJugador2();
        });

        lblTitulo = new JLabel();
        btnLanzar = new JButton();
        lbldado1 = new JLabel();
        lbldado2 = new JLabel();
        txtResultado = new JTextField();

        lblLanzamientos = new JLabel();
        txtLanzamientos = new JTextField();
        lblLanzamientosJ1 = new JLabel();
        txtLanzamientosJ1 = new JTextField();
        lblLanzamientosJ2 = new JLabel();
        txtLanzamientosJ2 = new JTextField();
        lblLanzamientosEmpate = new JLabel();
        txtLanzamientosEmpate = new JTextField();
        lblGanadorParcial = new JLabel();
        txtGanadorParcial = new JTextField();
        lblSumatoriaJ1 = new JLabel();
        txtSumatoriaJ1 = new JTextField();
        lblSumatoriaJ2 = new JLabel();
        txtSumatoriaJ2 = new JTextField();
        lblTiempoRonda = new JLabel();
        txtTiempoRonda = new JTextField();
        lblTurnoActual = new JLabel();
        txtTurnoActual = new JTextField();
        btnSeguir = new JButton();

        contenedorPpal = getContentPane();
        contenedorPpal.setLayout(new GridBagLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblLanzamientos.setFont(new java.awt.Font("Century Gothic", 0, 18));
        lblLanzamientos.setText("# de Lanzamientos: ");
        setConstraints(0, 0, 1, 1);
        contenedorPpal.add(lblLanzamientos, constraints);

        txtLanzamientos.setEditable(false);
        txtLanzamientos.setOpaque(false);
        txtLanzamientos.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        setConstraints(1, 0, 1, 1);
        txtLanzamientos.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contenedorPpal.add(txtLanzamientos, constraints);

        lblLanzamientosJ1.setFont(new java.awt.Font("Century Gothic", 0, 18));
        lblLanzamientosJ1.setText("# de Lanzamientos Jugador 1: ");
        setConstraints(0, 1, 1, 1);
        contenedorPpal.add(lblLanzamientosJ1, constraints);

        txtLanzamientosJ1.setEditable(false);
        txtLanzamientosJ1.setOpaque(false);
        txtLanzamientosJ1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        setConstraints(1, 1, 1, 1);
        txtLanzamientosJ1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contenedorPpal.add(txtLanzamientosJ1, constraints);

        lblLanzamientosJ2.setFont(new java.awt.Font("Century Gothic", 0, 18));
        lblLanzamientosJ2.setText("# de Lanzamientos Jugador 2: ");
        setConstraints(0, 2, 1, 1);
        contenedorPpal.add(lblLanzamientosJ2, constraints);

        txtLanzamientosJ2.setEditable(false);
        txtLanzamientosJ2.setOpaque(false);
        txtLanzamientosJ2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        setConstraints(1, 2, 1, 1);
        txtLanzamientosJ2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contenedorPpal.add(txtLanzamientosJ2, constraints);

        lblLanzamientosEmpate.setFont(new java.awt.Font("Century Gothic", 0, 18));
        lblLanzamientosEmpate.setText("# de Lanzamientos con Empate: ");
        setConstraints(0, 3, 1, 1);
        contenedorPpal.add(lblLanzamientosEmpate, constraints);

        txtLanzamientosEmpate.setEditable(false);
        txtLanzamientosEmpate.setOpaque(false);
        txtLanzamientosEmpate.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        setConstraints(1, 3, 1, 1);
        txtLanzamientosEmpate.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contenedorPpal.add(txtLanzamientosEmpate, constraints);

        lblGanadorParcial.setFont(new java.awt.Font("Century Gothic", 0, 18));
        lblGanadorParcial.setText("Jugador Parcialmente Ganador: ");
        setConstraints(2, 0, 1, 1);
        contenedorPpal.add(lblGanadorParcial, constraints);

        txtGanadorParcial.setEditable(false);
        txtGanadorParcial.setOpaque(false);
        txtGanadorParcial.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        setConstraints(3, 0, 1, 1);
        txtGanadorParcial.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contenedorPpal.add(txtGanadorParcial, constraints);

        lblSumatoriaJ1.setFont(new java.awt.Font("Century Gothic", 0, 18));
        lblSumatoriaJ1.setText("Sumatoria Jugador 1: ");
        setConstraints(2, 1, 1, 1);
        contenedorPpal.add(lblSumatoriaJ1, constraints);

        txtSumatoriaJ1.setEditable(false);
        txtSumatoriaJ1.setOpaque(false);
        txtSumatoriaJ1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        setConstraints(3, 1, 1, 1);
        txtSumatoriaJ1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contenedorPpal.add(txtSumatoriaJ1, constraints);

        lblSumatoriaJ2.setFont(new java.awt.Font("Century Gothic", 0, 18));
        lblSumatoriaJ2.setText("Sumatoria Jugador 2: ");
        setConstraints(2, 2, 1, 1);
        contenedorPpal.add(lblSumatoriaJ2, constraints);

        txtSumatoriaJ2.setEditable(false);
        txtSumatoriaJ2.setOpaque(false);
        txtSumatoriaJ2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        setConstraints(3, 2, 1, 1);
        txtSumatoriaJ2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contenedorPpal.add(txtSumatoriaJ2, constraints);

        lblTiempoRonda.setFont(new java.awt.Font("Century Gothic", 0, 18));
        lblTiempoRonda.setText("Tiempo Ronda: ");
        setConstraints(2, 3, 1, 1);
        contenedorPpal.add(lblTiempoRonda, constraints);

        txtTiempoRonda.setEditable(false);
        txtTiempoRonda.setOpaque(false);
        txtTiempoRonda.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtTiempoRonda.setText(juegoCasino.getTiempoDeJuego() + "");
        setConstraints(3, 3, 1, 1);
        txtTiempoRonda.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contenedorPpal.add(txtTiempoRonda, constraints);

        flowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 10));
        flowPanel.setBorder(BorderFactory.createEmptyBorder());
        flowPanel.setOpaque(false);
        setConstraints(0, 4, 4, 1);
        contenedorPpal.add(flowPanel, constraints);

        //Espacio de los dados
        constraints.fill = GridBagConstraints.NONE;
        lblTurnoActual.setFont(new java.awt.Font("Century Gothic", 0, 18));
        lblTurnoActual.setText("Jugador a Realizar el Lanzamiento: ");
        setConstraints(0, 5, 2, 1);
        contenedorPpal.add(lblTurnoActual, constraints);

        txtTurnoActual.setEditable(false);
        txtTurnoActual.setOpaque(false);
        txtTurnoActual.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        setConstraints(1, 5, 2, 1);
        txtTurnoActual.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contenedorPpal.add(txtTurnoActual, constraints);

        /*
        lblTitulo.setFont(new java.awt.Font("Perpetua Titling MT", 0, 24)); // NOI18N
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        lblTitulo.setText("CASINO UNIVALLE");
        setConstraints(0,0,2,1);
        contenedorPpal.add(lblTitulo, constraints); */
        lbldado1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/dado2.png"))); // NOI18N
        setConstraints(0, 6, 2, 1);
        contenedorPpal.add(lbldado1, constraints);

        lbldado2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/dado4.png"))); // NOI18N
        setConstraints(1, 6, 2, 1);
        contenedorPpal.add(lbldado2, constraints);

        txtResultado.setEditable(false);
        txtResultado.setOpaque(false);
        txtResultado.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        setConstraints(1, 7, 1, 1);
        txtResultado.setBorder(BorderFactory.createEmptyBorder());
        contenedorPpal.add(txtResultado, constraints);

        btnLanzar.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        //btnLanzar.setText("Tira los dados");
        btnLanzar.setActionCommand("BTNLANZAR");
        btnLanzar.addActionListener(gestorEventos);
        btnLanzar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/upd_norm.png"))); // NOI18N
        btnLanzar.setAutoscrolls(true);
        btnLanzar.setBorder(null);
        btnLanzar.setBorderPainted(false);
        btnLanzar.setContentAreaFilled(false);
        btnLanzar.setFocusPainted(false);
        btnLanzar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/upd_norm.png"))); // NOI18N
        btnLanzar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/upd_norm.png"))); // NOI18N
        setConstraints(1, 8, 1, 1);
        contenedorPpal.add(btnLanzar, constraints);

        btnSeguir.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        //btnSeguir.setText("Seguir");
        btnSeguir.setActionCommand("PASAR-TURNO");
        btnSeguir.addActionListener(gestorEventos);
        btnSeguir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/acep_norm.png"))); // NOI18N
        btnSeguir.setAutoscrolls(true);
        btnSeguir.setBorder(null);
        btnSeguir.setBorderPainted(false);
        btnSeguir.setContentAreaFilled(false);
        btnSeguir.setFocusPainted(false);
        btnSeguir.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/acep_press.png"))); // NOI18N
        btnSeguir.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/acep_roll.png"))); // NOI18N
        setConstraints(3, 9, 1, 1);
        contenedorPpal.add(btnSeguir, constraints);

        SwingUtilities.updateComponentTreeUI(contenedorPpal);

    }

    private void inicializarNombres() {

        contenedorPpal = getContentPane();
        contenedorPpal.setLayout(new GridBagLayout());

        panelCentro = new JPanel();
        panelCentro.setLayout(new GridLayout(5, 1)); // Vamos a agrupar paneles dentro de un "grid"

        lblTitulo = new JLabel();
        lblTitulo.setText("CASINO UNIVALLE");
        lblTitulo.setFont(new java.awt.Font("Perpetua Titling MT", 0, 24)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblMensaje = new JLabel();
        lblMensaje.setText("Nombre del Jugador #1 ");
        lblMensaje.setFont(new java.awt.Font("Arial Narrow", 0, 19)); // NOI18N
        lblMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        txtCampo = new JTextField(10);
        txtCampo.setFont(new java.awt.Font("Arial Narrow", 0, 19)); // NOI18N
        txtCampo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnSeguir = new JButton();
        btnSeguir.setFont(new java.awt.Font("Perpetua Titling MT", 0, 18)); // NOI18N
        btnSeguir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        //btnSeguir.setText("Seguir");
        btnSeguir.setActionCommand("BOTON_SEGUIR_NOMBRES");
        btnSeguir.addActionListener(gestorEventos);
        btnSeguir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/acep_norm.png"))); // NOI18N
        btnSeguir.setAutoscrolls(true);
        btnSeguir.setBorder(null);
        btnSeguir.setBorderPainted(false);
        btnSeguir.setContentAreaFilled(false);
        btnSeguir.setFocusPainted(false);
        btnSeguir.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/acep_press.png"))); // NOI18N
        btnSeguir.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/acep_roll.png"))); // NOI18N

        panelTitulo = new JPanel();
        panelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelTitulo.add(lblTitulo);
        panelTitulo.setOpaque(false);

        if ("Jugador VS Jugador".equals(juegoCasino.getModoDeJuego())) {
            lblMensaje2 = new JLabel();
            lblMensaje2.setText("Nombre del Jugador #2 ");
            lblMensaje2.setFont(new java.awt.Font("Arial Narrow", 0, 19)); // NOI18N
            lblMensaje2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

            txtCampo2 = new JTextField(10);
            txtCampo2.setFont(new java.awt.Font("Arial Narrow", 0, 19)); // NOI18N
            txtCampo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            panelnombres = new JPanel();
            panelnombres.setLayout(new GridLayout(2, 2, 50, 0));
            panelnombres.add(lblMensaje);
            panelnombres.add(txtCampo);
            panelnombres.add(lblMensaje2);
            panelnombres.add(txtCampo2);
            panelnombres.setOpaque(false);
        } else {
            panelnombres = new JPanel();
            panelnombres.setLayout(new GridLayout(2, 2, 50, 0));
            panelnombres.add(lblMensaje);
            panelnombres.add(txtCampo);
            panelnombres.setOpaque(false);
        }

        panelBotonesI = new JPanel();
        panelBotonesI.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelBotonesI.add(btnSeguir);
        panelBotonesI.setOpaque(false);

        panelCentro.add(panelTitulo);
        panelCentro.add(panelnombres);
        panelCentro.add(panelBotonesI);
        panelCentro.setOpaque(false);

        contenedorPpal.add(panelCentro);

    }

    public void primeraRonda() {
        txtLanzamientos.setText(juegoCasino.getNumeroLanzamientosRonda() + "");
        txtLanzamientosJ1.setText(0 + "");
        txtLanzamientosJ2.setText(0 + "");
        txtLanzamientosEmpate.setText(0 + "");
        txtGanadorParcial.setText("Ninguno");
        txtSumatoriaJ1.setText(0 + "");
        txtSumatoriaJ2.setText(0 + "");
        txtTiempoRonda.setText(0 + "");
        txtTurnoActual.setText(juegoCasino.obtenerJugador(0).getNombre());
        juegoCasino.obtenerJugador(0).setDebeLanzar(true);
        btnSeguir.setEnabled(false);
        juegoCasino.obtenerJugador(0).setPuntajeLanzamientoActual(0);
        juegoCasino.obtenerJugador(1).setPuntajeLanzamientoActual(0);
    }

    private void inicializarLanzamientoPorRonda() {

        contenedorPpal = getContentPane();
        contenedorPpal.setLayout(new GridBagLayout());

        panelCentro = new JPanel();
        panelCentro.setLayout(new GridLayout(5, 1)); // Vamos a agrupar paneles dentro de un "grid"

        lblTitulo = new JLabel();
        lblTitulo.setText("CASINO UNIVALLE");
        lblTitulo.setFont(new java.awt.Font("Perpetua Titling MT", 0, 24)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblMensaje = new JLabel();
        lblMensaje.setText("Digite el numero de lanzamientos por ronda");
        lblMensaje.setFont(new java.awt.Font("Arial Narrow", 0, 19)); // NOI18N
        lblMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        txtCampo = new JTextField(10);
        txtCampo.setFont(new java.awt.Font("Arial Narrow", 0, 19)); // NOI18N
        txtCampo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnSeguir = new JButton();
        btnSeguir.setFont(new java.awt.Font("Perpetua Titling MT", 0, 18)); // NOI18N
        btnSeguir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//        btnSeguir.setText("Seguir");
        btnSeguir.setEnabled(true);
        btnSeguir.setActionCommand("BOTON_SEGUIR_LANZAMIENTO");
        btnSeguir.addActionListener(gestorEventos);
        btnSeguir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/acep_norm.png"))); // NOI18N
        btnSeguir.setAutoscrolls(true);
        btnSeguir.setBorder(null);
        btnSeguir.setBorderPainted(false);
        btnSeguir.setContentAreaFilled(false);
        btnSeguir.setFocusPainted(false);
        btnSeguir.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/acep_press.png"))); // NOI18N
        btnSeguir.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/acep_roll.png"))); // NOI18N

        panelTitulo = new JPanel();
        panelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelTitulo.add(lblTitulo);
        panelTitulo.setOpaque(false);

        panelCampos = new JPanel();
        panelCampos.setLayout(new GridLayout(2, 2, 50, 0));
        panelCampos.add(lblMensaje);
        panelCampos.add(txtCampo);
        panelCampos.setOpaque(false);

        panelBotonesI = new JPanel();
        panelBotonesI.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelBotonesI.add(btnSeguir);
        panelBotonesI.setOpaque(false);

        panelCentro.add(panelTitulo);
        panelCentro.add(panelCampos);
        panelCentro.add(panelBotonesI);
        panelCentro.setOpaque(false);

        contenedorPpal.add(panelCentro);

    }

    private void inicializarModoDeJuego() {

        contenedorPpal = getContentPane();
        contenedorPpal.setLayout(new GridBagLayout());

        panelCentro = new JPanel();
        panelCentro.setLayout(new GridLayout(5, 1)); // Vamos a agrupar paneles dentro de un "grid"

        lblTitulo = new JLabel();
        lblTitulo.setText("CASINO UNIVALLE");
        lblTitulo.setFont(new java.awt.Font("Perpetua Titling MT", 0, 24)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblImagenUs1 = new JLabel();
        lblImagenUs1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/user.png")));

        lblImagenUs2 = new JLabel();
        lblImagenUs2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/user.png")));

        lblImagenUs3 = new JLabel();
        lblImagenUs3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/user.png")));

        lblMaquina = new JLabel();
        lblMaquina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/machine.png")));

        btnDosPersonas = new JButton();
        btnDosPersonas.setText("Dos Jugadores");
        btnDosPersonas.setActionCommand("DOS_JUGADORES");
        btnDosPersonas.addActionListener(gestorEventos);
        btnDosPersonas.setFont(new java.awt.Font("Perpetua Titling MT", 0, 24)); // NOI18N
        btnDosPersonas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDosPersonas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/nuevo_norm.png"))); // NOI18N
        btnDosPersonas.setAutoscrolls(true);
        btnDosPersonas.setBorder(null);
        btnDosPersonas.setBorderPainted(false);
        btnDosPersonas.setContentAreaFilled(false);
        btnDosPersonas.setFocusPainted(false);
        btnDosPersonas.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/nuevo_press.png"))); // NOI18N
        btnDosPersonas.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/nuevo_roll.png"))); // NOI18N

        btnUnaPersona = new JButton();
        btnUnaPersona.setText("Un Jugador");
        btnUnaPersona.setActionCommand("UN_JUGADOR");
        btnUnaPersona.addActionListener(gestorEventos);
        btnUnaPersona.setFont(new java.awt.Font("Perpetua Titling MT", 0, 24)); // NOI18N
        btnUnaPersona.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnUnaPersona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/nuevo_norm.png"))); // NOI18N
        btnUnaPersona.setAutoscrolls(true);
        btnUnaPersona.setBorder(null);
        btnUnaPersona.setBorderPainted(false);
        btnUnaPersona.setContentAreaFilled(false);
        btnUnaPersona.setFocusPainted(false);
        btnUnaPersona.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/nuevo_press.png"))); // NOI18N
        btnUnaPersona.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/nuevo_roll.png"))); // NOI18N

        panelTitulo = new JPanel();
        panelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelTitulo.add(lblTitulo);
        panelTitulo.setOpaque(false);

        panelImagenesI = new JPanel();
        panelImagenesI.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelImagenesI.add(lblImagenUs1);
        panelImagenesI.add(lblImagenUs2);
        panelImagenesI.setOpaque(false);

        panelBotonesI = new JPanel();
        panelBotonesI.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelBotonesI.add(btnDosPersonas);
        panelBotonesI.setOpaque(false);

        panelImagenesD = new JPanel();
        panelImagenesD.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelImagenesD.add(lblImagenUs3);
        panelImagenesD.add(lblMaquina);
        panelImagenesD.setOpaque(false);

        panelBotonesD = new JPanel();
        panelBotonesD.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelBotonesD.add(btnUnaPersona);
        panelBotonesD.setOpaque(false);

//        panelBotones = new JPanel();
//        panelBotones.setLayout(new BorderLayout());
//        panelBotones.add(btnDosPersonas, BorderLayout.WEST);
//        panelBotones.add(btnUnaPersona, BorderLayout.EAST);
        panelCentro.add(panelTitulo);
        panelCentro.add(panelImagenesI);
        panelCentro.add(panelBotonesI);
        panelCentro.add(panelImagenesD);
        panelCentro.add(panelBotonesD);
        panelCentro.setOpaque(false);

        contenedorPpal.add(panelCentro);

    }

    private void inicializarTiempoJugador2() {
        if (!juegoCasino.getJugadores().isEmpty()) {
            if (juegoCasino.obtenerJugador(1).isDebeLanzar()) {
                juegoCasino.obtenerJugador(1).setTiempoDeJuego(juegoCasino.obtenerJugador(1).getTiempoDeJuego() + 1);
            }
        }
    }

    private void inicializarTiempoJugador1() {
        if (!juegoCasino.getJugadores().isEmpty()) {
            if (juegoCasino.obtenerJugador(0).isDebeLanzar()) {
                juegoCasino.obtenerJugador(0).setTiempoDeJuego(juegoCasino.obtenerJugador(0).getTiempoDeJuego() + 1);
            }
        }
    }

    private void inicializarTiempoGenral() {
        if (juegoCasino.isEnJuego()) {
            juegoCasino.setTiempoDeJuego(juegoCasino.getTiempoDeJuego() + 1);
        }
    }

    private void cronometro(int m, int s) {
        SwingUtilities.updateComponentTreeUI(contenedorPpal);
        txtTiempoRonda.setText(m + " min, " + s + " segs");
        SwingUtilities.updateComponentTreeUI(contenedorPpal);
    }

}
