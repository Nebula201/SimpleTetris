package org.nebula.tetris.model.game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Cell {
    private int row;
    private int col;
    private BufferedImage image;

    public Cell(int row, int col, BufferedImage image) {
        this.row = row;
        this.col = col;
        this.image = image;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Image getImage() {
        return image;
    }

    public void down() {
        row++;
    }

    public void left() {
        col--;
    }

    public void right() {
        col++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return row == cell.row &&
                col == cell.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public String toString() {
        return "Cell(" + row + ", " + col + ')';
    }
}
