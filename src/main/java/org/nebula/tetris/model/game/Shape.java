package org.nebula.tetris.model.game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public enum Shape {
    T(0x04e0, "/org/nebula/tetris/images/T.png"),
    S(0x4620, "/org/nebula/tetris/images/S.png"),
    Z(0x2640, "/org/nebula/tetris/images/Z.png"),
    L(0x6220, "/org/nebula/tetris/images/L.png"),
    J(0x6440, "/org/nebula/tetris/images/J.png"),
    O(0x0660, "/org/nebula/tetris/images/O.png"),
    I(0x0f00, "/org/nebula/tetris/images/I.png");

    private final int hex;
    private BufferedImage image;

    Shape(int hex, String image) {
        this.hex = hex;
        try {
            this.image = ImageIO.read(getClass().getResource(image));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getHex() {
        return hex;
    }

    public BufferedImage getImage() {
        return image;
    }

    public static Shape random() {
        return values()[new Random().nextInt(values().length)];
//        return values()[(int) (Math.random() * values().length)];
    }
}
