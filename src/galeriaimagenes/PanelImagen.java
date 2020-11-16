package galeriaimagenes;

import javax.swing.*;
import java.awt.*;
@SuppressWarnings("serial")

/**
 *
 * @author Luis Barca
 */
public class PanelImagen extends JPanel {
    // Atributos
    // Objeto Casilla para visualizar imágenes en el PanelImagen
    private final Casilla casilla;

    /**
     * Constructor que al iniciar el programa coloca la imagen logoTaller.jpg
     * en el panelImagen
     */
    public PanelImagen() {
        // Creamos una especie de casilla grande
        this.casilla = new Casilla(450, 450);
        // Le añadimos la foto de logoTaller.jpg y establecemos un layout
        this.casilla.actualizarCasillaGrande("C:/Users/luisb/IdeaProjects/TallerEvaluable2/imagenes/logoTaller.jpg");
        this.setLayout(new GridLayout(1, 1));
        // Añadimos la casilla al panel de imagen
        this.add(casilla);

        this.setVisible(true);
    }

    /**
     * Método que actualiza la imágen del Panel Imagen mediante un ImageIcon 
     * pasado por parámetro
     * @param img - ImageIcon
     */
    public void actualizarImagen(ImageIcon img) {
        // Si la imágen no es vacía, visualiza la imágen en el PanelImagen
        if(!img.getDescription().equals("C:/Users/luisb/IdeaProjects/TallerEvaluable2/imagenes/imagenVacia.jpg")) {
            casilla.actualizarCasillaGrande(img.getDescription());
        }
    }
}
