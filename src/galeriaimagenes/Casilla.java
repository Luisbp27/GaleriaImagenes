package galeriaimagenes;

import javax.swing.*;
import java.awt.*;
@SuppressWarnings("serial")

/**
 *
 * @author Luis Barca
 */
public class Casilla extends JLabel {
    // Atributos
    // Declaración de un ImageIcon para asignar una imágen al objeto Casilla
    private ImageIcon imagen;

    /**
     * Constructor que directamente visualiza la imagen vacía en la casilla
     */
    public Casilla() {
        actualizarCasilla("imagenes/imagenVacia.jpg");
    }

    /**
     * Constructor que nos permite crear una casilla con la imágen y la posición
     * que queramos
     * @param x - Coordenada X
     * @param y - Coordenada Y
     */
    public Casilla(int x, int y) {}

    /**
     * Método que permite actualizar la imágen de la casilla
     * @param s - String con la ruta de la imágen a actualizar
     */
    public void actualizarCasilla(String s) {
        imagen = new ImageIcon(s);
        this.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(52, 52, Image.SCALE_DEFAULT)));
    }

    /**
     * Método que permite actualizar la imágen de la casilla grande
     * @param s - String con la ruta de la imágen a actualizar
     */
    public void actualizarCasillaGrande(String s) {
        imagen = new ImageIcon(s);
        this.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(450, 450, Image.SCALE_DEFAULT)));
    }

    /**
     * Método que devuelve el valor del atributo imagen
     * @return
     */
    public ImageIcon getImagen() {
        return imagen;
    }
}
