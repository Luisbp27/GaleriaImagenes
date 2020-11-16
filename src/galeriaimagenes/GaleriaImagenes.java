package galeriaimagenes;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
@SuppressWarnings("serial")

/**
 *
 * @author Luis Barca
 */
public class GaleriaImagenes extends JFrame {
    // Atributos
    // Variables de tipo entero para gestionar los tamaños de la ventana y los
    // diferentes paneles
    private static final int ALTURAVENTANA = 560;
    private static final int ANCHURAVENTANA = 710;
    private static final int ALTURAICONOS = 450;
    private static final int ANCHURAICONOS = (4 * 52) + (3 * 14);
    private static final int ALTURAIMAGEN = 450;
    private static final int ANCHURAIMAGEN = 450;
    private static final int ALTURABOTONES = 40;
    private static final int ANCHURABOTONES = 700;
    private static final int ALTURATEXTO = 35;
    private static final int ANCHURATEXTO = 700;
    // Variable ImageIcon para visualizar la imagen correspondiente
    private ImageIcon imagen;

    public GaleriaImagenes() {
        super("Visualizador de imágenes - Taller II - Programación II - Curso 2019/20");
        // Establece las medidas de la ventana
        this.setSize(ANCHURAVENTANA, ALTURAVENTANA);
        this.setLocationRelativeTo(null);

        // Añadimos los paneles al JFrame
        PanelIconos panelIconos = new PanelIconos();
        PanelImagen panelImagen = new PanelImagen();
        AreaBotones areaBotones = new AreaBotones();
        PanelTexto panelTexto = new PanelTexto();

        // Establece las medidas y la localización de los paneles
        panelIconos.setBounds(0, 0, ANCHURAICONOS, ALTURAICONOS);
        panelImagen.setBounds(ANCHURAICONOS, 0, ANCHURAIMAGEN, ALTURAIMAGEN);
        areaBotones.setBounds(0, 450, ANCHURABOTONES, ALTURABOTONES);
        panelTexto.setBounds(0, 490, ANCHURATEXTO, ALTURATEXTO);

        // No establece ningún layout predeterminado
        this.getContentPane().setLayout(null);
        // Añade el contenido a los paneles
        this.getContentPane().add(panelIconos);
        this.getContentPane().add(areaBotones);
        this.getContentPane().add(panelImagen);
        this.getContentPane().add(panelTexto);


        // Le pasamos el Panel de Iconos, el texto del Panel de Texto, el Panel
        // de Imagen al Panel de los Botones para que este pueda modificarlos
        areaBotones.setPanelIconos(panelIconos);
        areaBotones.setPanelTexto(panelTexto);
        areaBotones.setPanelImagen(panelImagen);
        // Le pasamos el Panel de Texto al Panel de Iconos para que este pueda
        // modificarlo
        panelIconos.setPanelTexto(panelTexto);

        // Este escuchador nos permite saber cuando se clica una casilla y
        // efectua la acción correspondiente
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                // Condicional que no permite intereactuar con el mouse hasta que 
                // el usuario introduzca la librería de imágenes
                if(areaBotones.isIconosMarcables()) {     
                    // Hay que modificar los valores recogidos por el mouse
                    // para que se seleccione correctamente el icono
                    if ((e.getX() < ANCHURAICONOS + 7) && (e.getY() < ALTURAICONOS + 30)) {
                        // Calculamos las coordenadas
                        int x = (e.getX() - 7) / 62;
                        int y = (e.getY() - 30) / 63;
                       
                        // Creamos la imagen, la marcamos y la actualizamos en el
                        // Panel de Imagen
                        imagen = panelIconos.marcar(x, y);
                        panelImagen.actualizarImagen(imagen);
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        // Prohibe que la ventana sea redimensionada
        this.setResizable(false);
        
        // Añadimos una confirmación para salir de la ventana
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(GaleriaImagenes.this,  
                        "¿Estas seguro de que quieres cerrar la ventana?", "Cerrar ventana",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
                {
                    System.exit(0);
                }
            }
        });
    }

    /**
     * Método main del programa
     * @param args
     */
    public static void main(String[] args) {
        // Este try catch permite que independientemente del sistema operativo 
        // donde se ejecute el programa, su visualización sea correcta
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        // Creamos una ventana TallerEvaluable2 y la hacemos visible
        new GaleriaImagenes().setVisible(true);
    }
}