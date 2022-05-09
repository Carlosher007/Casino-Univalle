package co.edu.univalle.vistaCasino;

import co.edu.univalle.sistema.Jugador;
import co.edu.univalle.sistema.Maquina;
import co.edu.univalle.sistema.Sistema;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TimerTask;
import javax.swing.*;
import javax.swing.SwingUtilities;

/**
 *
 * @author Carlos Andres Hernandez Agudelo , Brayan Sanchez
 */
public class Inicio extends JFrame {

    private Sistema juegoCasino = new Sistema();
    //Declaracion de variables                    
    Random aleatorio = new Random();
    GridBagConstraints constraints = new GridBagConstraints();
    EventosInternos gestorEventos = new EventosInternos();

    //tiempo
    private Timer tiempo;

    /**
     * INICIO
     */
    private JLabel lblTitulo;
    private JLabel lblLogo;
    private JButton btnEmpezar;
    private Container contenedorPpal;

    /**
     * MODO DE JUEGO
     */
    private JLabel lblMensaje;
    private JLabel lblImagenUs1;
    private JLabel lblImagenUs2;
    private JLabel lblMaquina;
    private JButton btnDosPersonas;
    private JButton btnUnaPersona;

    /**
     * LANZAMIENTOS, RONDA Y NOMBRES
     */
    private JTextField txtCampo;
    private JTextField txtCampo2;
    private JButton btnSeguir;
    private JLabel lblMensaje2;

    // Elementos para el lanzamiento de dados y el resumen
    private JLabel lbldado1;
    private JLabel lbldado2;
    private JButton btnLanzar;
    private JTextField txtResultado;
    private JPanel flowPanel;

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
    private JLabel lblTiempoJ1;
    private JTextField txtTiempoJ1;
    private JLabel lblTiempoJ2;
    private JTextField txtTiempoJ2;
    private JLabel lblTurnoActual;
    private JTextField txtTurnoActual;

    private JButton btnJugarDeNuevo;
    private JButton btnSalir;

    private int empates;
    private int sumatoriaJ1;
    private int sumatoriaJ2;
    private int resultado;
    private boolean tirohecho = false;

    private void setConstraints(int x, int y, int hx, int hy) {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = hx;
        constraints.gridheight = hy;
    }

    /**
     * Creamos una ventana para casino
     */
    public Inicio() {
        inicializarVentana();
    }

    private void inicializarVentana() {
        inicializarComponentes();
        setLocationRelativeTo(null);
        setTitle("Casino Univalle"); //Título del JFrame
        setSize(900, 500); //Dimensiones del JFrame
        setLocation(100, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cerrar al salir
        setVisible(true); //Mostrar JFrame
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
    }

    public void inicializarComponentes() {

        /**
         * Inicializar para la ventana Inicio
         */
//        EventosInternos gestorEventos = new EventosInternos();
        lblTitulo = new JLabel();
        lblLogo = new JLabel();
        btnEmpezar = new JButton();

        contenedorPpal = getContentPane();
        contenedorPpal.setLayout(new GridBagLayout());

        lblTitulo.setText("CASINO UNIVALLE");
        setConstraints(0, 0, 2, 1);
        contenedorPpal.add(lblTitulo, constraints);

        Icon logo = new javax.swing.ImageIcon(getClass().getResource("/imagenes/icono.png"));
        lblLogo.setIcon(logo);
        setConstraints(0, 1, 2, 2);
        contenedorPpal.add(lblLogo, constraints);

        btnEmpezar.setText("Empezar");
        btnEmpezar.setActionCommand("EMPEZAR");
        btnEmpezar.addActionListener(gestorEventos);
        setConstraints(10, 20, 1, 0);
        contenedorPpal.add(btnEmpezar, constraints);

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
                        if (nombre1 == null || "".equals(nombre1) || nombre2 == null || "".equals(nombre2)) {
                            JOptionPane.showMessageDialog(Inicio.this, "Ingresa nombres validos", "Casino Univalle", JOptionPane.WARNING_MESSAGE);
                        } else {
                            juegoCasino.agregarJugador(nombre1);
                            juegoCasino.agregarJugador(nombre2);

                            limpiarVentana();
                            resumen();
                            //INICIALIZAR TIEMPO EN GENERAL

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
                    if (juegoCasino.getModoDeJuego() == "Jugador VS Maquina" && J1 == true && tirohecho == true) {
                        pasoDeTurno(J1, J2);
                    } else if (juegoCasino.getModoDeJuego() == "Jugador VS Jugador") {
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
                    juegoCasino.setNumeroLanzamientosRonda(juegoCasino.getNumeroLanzamientosCopia());
                    juegoCasino.setTiempoDeJuego(0);
                    juegoCasino.setLanzamientosEmpatados(0);
                    juegoCasino.setNumeroLanzamientoActual(0);
                    juegoCasino.setEnJuego(false);
                    limpiarVentana();
                    inicializarModoDeJuego();
                case "JUGAR_DE_NUEVO":
                    juegoCasino.setNumeroLanzamientosRonda(0);
                    juegoCasino.setTiempoDeJuego(0);
                    juegoCasino.setLanzamientosEmpatados(0);
                    juegoCasino.setNumeroLanzamientoActual(0);
                    juegoCasino.setEnJuego(true);
                    juegoCasino.setNumeroLanzamientosCopia(0);
                    juegoCasino.obtenerJugador(0).setDebeLanzar(false);
                    juegoCasino.obtenerJugador(0).setDebeLanzar(true);
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

    public void finDelJuego() {

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
                JOptionPane.showMessageDialog(Inicio.this, "¡Felicidades " + txtGanadorParcial.getText() + ", ganaste!", "Casino Univalle", JOptionPane.WARNING_MESSAGE);
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
                txtTiempoRonda.setText(juegoCasino.getTiempoDeJuego() + " segundos");
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
                flowPanel.setBorder(BorderFactory.createEmptyBorder());
                setConstraints(0, 5, 4, 1);
                contenedorPpal.add(flowPanel, constraints);

                btnJugarDeNuevo.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
                btnJugarDeNuevo.setText("Jugar de Nuevo");
                btnJugarDeNuevo.setActionCommand("JUGAR_DE_NUEVO");
                btnJugarDeNuevo.addActionListener(gestorEventos);
                setConstraints(0, 6, 1, 1);
                contenedorPpal.add(btnJugarDeNuevo, constraints);

                btnReiniciarResumen.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
                btnReiniciarResumen.setText("Reiniciar Casino");
                btnReiniciarResumen.setActionCommand("REINICIAR_FIN_JUEGO"); //FALTA CREAR ESTE COMANDO
                btnReiniciarResumen.addActionListener(gestorEventos);
                setConstraints(1, 6, 1, 1);
                contenedorPpal.add(btnReiniciarResumen, constraints);

                btnSalir.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
                btnSalir.setText("Finalizar");
                btnSalir.setActionCommand("SALIR_FIN_JUEGO"); //FALTA CREAR ESTE COMANDO
                btnSalir.addActionListener(gestorEventos);
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
            txtTiempoRonda.setText(juegoCasino.getTiempoDeJuego() + "");
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
            }
        } else {
            txtTiempoRonda.setText(juegoCasino.getTiempoDeJuego() + "");
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
                JOptionPane.showMessageDialog(Inicio.this, "¡Empate! Se repetirán los lanzamientos", "Casino Univalle", JOptionPane.WARNING_MESSAGE);
                ganadorParcial(sumatoriaJ1, sumatoriaJ2);
            } else {
                txtTiempoRonda.setText(juegoCasino.getTiempoDeJuego() + "");
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
                if (lanzamientosRestantes == 0) {
                    finDelJuego();
                }
            }
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
        btnSeguirResumen.setActionCommand("LIMPIAR+CREAR");
        btnSeguirResumen.addActionListener(gestorEventos);
        btnReiniciarResumen = new javax.swing.JButton();
        btnReiniciarResumen.setText("Reiniciar");
        btnReiniciarResumen.setActionCommand("LIMPIAR_RESUMEN");
        btnReiniciarResumen.addActionListener(gestorEventos);

        contenedorPpal = getContentPane();
        contenedorPpal.setLayout(new GridBagLayout());

        constraints.fill = GridBagConstraints.BOTH;
        setConstraints(0, 1, 1, 1);
        contenedorPpal.add(lblModoJuego, constraints);
        setConstraints(1, 1, 1, 1);
        contenedorPpal.add(txtModoJuego, constraints);
        setConstraints(0, 2, 1, 1);
        contenedorPpal.add(lblNombre1, constraints);
        setConstraints(1, 2, 1, 1);
        contenedorPpal.add(txtNombre1, constraints);
        setConstraints(0, 3, 1, 1);
        contenedorPpal.add(lblNombre2, constraints);
        setConstraints(1, 3, 1, 1);
        contenedorPpal.add(txtNombre2, constraints);
        setConstraints(0, 4, 1, 1);
        contenedorPpal.add(lblNumeroLanzamientos, constraints);
        setConstraints(1, 4, 1, 1);
        contenedorPpal.add(txtNumeroLanzamientos, constraints);
        setConstraints(0, 5, 1, 1);
        contenedorPpal.add(lblRondas, constraints);
        setConstraints(1, 5, 1, 1);
        contenedorPpal.add(txtRondas, constraints);
        setConstraints(0, 6, 1, 1);
        contenedorPpal.add(btnSeguirResumen, constraints);
        setConstraints(1, 6, 1, 1);
        contenedorPpal.add(btnReiniciarResumen, constraints);

    }

    public void crearInterfazJuego() {

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
        txtLanzamientos.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        setConstraints(1, 0, 1, 1);
        txtLanzamientos.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contenedorPpal.add(txtLanzamientos, constraints);

        lblLanzamientosJ1.setFont(new java.awt.Font("Century Gothic", 0, 18));
        lblLanzamientosJ1.setText("# de Lanzamientos Jugador 1: ");
        setConstraints(0, 1, 1, 1);
        contenedorPpal.add(lblLanzamientosJ1, constraints);

        txtLanzamientosJ1.setEditable(false);
        txtLanzamientosJ1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        setConstraints(1, 1, 1, 1);
        txtLanzamientosJ1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contenedorPpal.add(txtLanzamientosJ1, constraints);

        lblLanzamientosJ2.setFont(new java.awt.Font("Century Gothic", 0, 18));
        lblLanzamientosJ2.setText("# de Lanzamientos Jugador 2: ");
        setConstraints(0, 2, 1, 1);
        contenedorPpal.add(lblLanzamientosJ2, constraints);

        txtLanzamientosJ2.setEditable(false);
        txtLanzamientosJ2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        setConstraints(1, 2, 1, 1);
        txtLanzamientosJ2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contenedorPpal.add(txtLanzamientosJ2, constraints);

        lblLanzamientosEmpate.setFont(new java.awt.Font("Century Gothic", 0, 18));
        lblLanzamientosEmpate.setText("# de Lanzamientos con Empate: ");
        setConstraints(0, 3, 1, 1);
        contenedorPpal.add(lblLanzamientosEmpate, constraints);

        txtLanzamientosEmpate.setEditable(false);
        txtLanzamientosEmpate.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        setConstraints(1, 3, 1, 1);
        txtLanzamientosEmpate.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contenedorPpal.add(txtLanzamientosEmpate, constraints);

        lblGanadorParcial.setFont(new java.awt.Font("Century Gothic", 0, 18));
        lblGanadorParcial.setText("Jugador Parcialmente Ganador: ");
        setConstraints(2, 0, 1, 1);
        contenedorPpal.add(lblGanadorParcial, constraints);

        txtGanadorParcial.setEditable(false);
        txtGanadorParcial.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        setConstraints(3, 0, 1, 1);
        txtGanadorParcial.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contenedorPpal.add(txtGanadorParcial, constraints);

        lblSumatoriaJ1.setFont(new java.awt.Font("Century Gothic", 0, 18));
        lblSumatoriaJ1.setText("Sumatoria Jugador 1: ");
        setConstraints(2, 1, 1, 1);
        contenedorPpal.add(lblSumatoriaJ1, constraints);

        txtSumatoriaJ1.setEditable(false);
        txtSumatoriaJ1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        setConstraints(3, 1, 1, 1);
        txtSumatoriaJ1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contenedorPpal.add(txtSumatoriaJ1, constraints);

        lblSumatoriaJ2.setFont(new java.awt.Font("Century Gothic", 0, 18));
        lblSumatoriaJ2.setText("Sumatoria Jugador 2: ");
        setConstraints(2, 2, 1, 1);
        contenedorPpal.add(lblSumatoriaJ2, constraints);

        txtSumatoriaJ2.setEditable(false);
        txtSumatoriaJ2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        setConstraints(3, 2, 1, 1);
        txtSumatoriaJ2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contenedorPpal.add(txtSumatoriaJ2, constraints);

        lblTiempoRonda.setFont(new java.awt.Font("Century Gothic", 0, 18));
        lblTiempoRonda.setText("Tiempo Ronda: ");
        setConstraints(2, 3, 1, 1);
        contenedorPpal.add(lblTiempoRonda, constraints);

        txtTiempoRonda.setEditable(false);
        txtTiempoRonda.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtTiempoRonda.setText(juegoCasino.getTiempoDeJuego() + "");
        setConstraints(3, 3, 1, 1);
        txtTiempoRonda.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contenedorPpal.add(txtTiempoRonda, constraints);

        flowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 10));
        flowPanel.setBorder(BorderFactory.createEmptyBorder());
        setConstraints(0, 4, 4, 1);
        contenedorPpal.add(flowPanel, constraints);

        //Espacio de los dados
        constraints.fill = GridBagConstraints.NONE;
        lblTurnoActual.setFont(new java.awt.Font("Century Gothic", 0, 18));
        lblTurnoActual.setText("Jugador a Realizar el Lanzamiento: ");
        setConstraints(0, 5, 2, 1);
        contenedorPpal.add(lblTurnoActual, constraints);

        txtTurnoActual.setEditable(false);
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
        txtResultado.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        setConstraints(1, 7, 1, 1);
        txtResultado.setBorder(BorderFactory.createEmptyBorder());
        contenedorPpal.add(txtResultado, constraints);

        btnLanzar.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnLanzar.setText("Tira los dados");
        btnLanzar.setActionCommand("BTNLANZAR");
        btnLanzar.addActionListener(gestorEventos);
        setConstraints(1, 8, 1, 1);
        contenedorPpal.add(btnLanzar, constraints);

        btnSeguir.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnSeguir.setText("Seguir");
        btnSeguir.setActionCommand("PASAR-TURNO");
        btnSeguir.addActionListener(gestorEventos);
        setConstraints(3, 9, 1, 1);
        contenedorPpal.add(btnSeguir, constraints);
    }

    private void inicializarNombres() {

        contenedorPpal = getContentPane();
        contenedorPpal.setLayout(new GridBagLayout());

        lblTitulo = new JLabel();
        lblTitulo.setText("CASINO UNIVALLE");
        setConstraints(0, 0, 1, 1);
        contenedorPpal.add(lblTitulo, constraints);

        lblMensaje = new JLabel();
        lblMensaje.setText("Nombre del Jugador 1");
        setConstraints(2, 2, 2, 2);
        contenedorPpal.add(lblMensaje, constraints);

        txtCampo = new JTextField(10);
        setConstraints(3, 4, 9, 5);
        contenedorPpal.add(txtCampo, constraints);

        if ("Jugador VS Jugador".equals(juegoCasino.getModoDeJuego())) {
            lblMensaje2 = new JLabel();
            lblMensaje2.setText("Nombre del jugador 2");
            setConstraints(6, 6, 2, 2);
            contenedorPpal.add(lblMensaje2, constraints);
            txtCampo2 = new JTextField(10);
            setConstraints(9, 10, 9, 5);
            contenedorPpal.add(txtCampo2, constraints);
        }

        btnSeguir = new JButton();
        btnSeguir.setText("Seguir");
        btnSeguir.setActionCommand("BOTON_SEGUIR_NOMBRES");
        btnSeguir.addActionListener(gestorEventos);
        setConstraints(10, 15, 4, 4);
        contenedorPpal.add(btnSeguir, constraints);
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

        lblTitulo = new JLabel();
        lblTitulo.setText("CASINO UNIVALLE");
        setConstraints(0, 0, 1, 1);
        contenedorPpal.add(lblTitulo, constraints);

        lblMensaje = new JLabel();
        lblMensaje.setText("Digite el numero de lanzamientos por ronda, una vez...");
        setConstraints(2, 2, 2, 2);
        contenedorPpal.add(lblMensaje, constraints);

        txtCampo = new JTextField(10);
        setConstraints(5, 4, 9, 5);
        contenedorPpal.add(txtCampo, constraints);

        btnSeguir = new JButton();
        btnSeguir.setText("Seguir");
        setConstraints(7, 9, 2, 2);
        btnSeguir.setEnabled(true);
        contenedorPpal.add(btnSeguir, constraints);

        btnSeguir.setActionCommand("BOTON_SEGUIR_LANZAMIENTO");
        btnSeguir.addActionListener(gestorEventos);

    }

    private void inicializarModoDeJuego() {

        contenedorPpal = getContentPane();
        contenedorPpal.setLayout(new GridBagLayout());

        lblTitulo = new JLabel();
        lblTitulo.setText("CASINO UNIVALLE");
        setConstraints(0, 0, 1, 1);
        contenedorPpal.add(lblTitulo, constraints);

        lblImagenUs1 = new JLabel();
        lblImagenUs1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/user.png")));
        setConstraints(2, 2, 2, 2);
        contenedorPpal.add(lblImagenUs1, constraints);

        lblImagenUs2 = new JLabel();
        lblImagenUs2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/user.png")));
        setConstraints(5, 3, 2, 2);
        contenedorPpal.add(lblImagenUs2, constraints);

        lblMaquina = new JLabel();
        lblMaquina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/machine.png")));
        setConstraints(4, 4, 2, 2);
        contenedorPpal.add(lblMaquina, constraints);

        btnDosPersonas = new JButton();
        btnDosPersonas.setText("Dos Jugadores");
        btnDosPersonas.setActionCommand("DOS_JUGADORES");
        btnDosPersonas.addActionListener(gestorEventos);
        setConstraints(5, 7, 2, 2);
        contenedorPpal.add(btnDosPersonas, constraints);

        btnUnaPersona = new JButton();
        btnUnaPersona.setText("Un Jugador");
        btnUnaPersona.setActionCommand("UN_JUGADOR");
        btnUnaPersona.addActionListener(gestorEventos);
        setConstraints(5, 9, 2, 2);
        contenedorPpal.add(btnUnaPersona, constraints);
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

}
