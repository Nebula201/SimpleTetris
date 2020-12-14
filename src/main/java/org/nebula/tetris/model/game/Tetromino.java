package org.nebula.tetris.model.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Tetromino implements Cloneable {
    private Cell[] cells;

    public Tetromino(Shape shape) {
        cells = new Cell[4];
        String binary = String.format("%16s", Integer.toBinaryString(shape.getHex())).replace(" ", "0");
        int flag = 0;

        for (int i = 0; i < 4; i++) {
            int index = binary.indexOf("1", flag);
            int cRow = (int) Math.floor((double) index / 4);
            int cCol = index % 4;
            cells[i] = new Cell(cRow, cCol, shape.getImage());
            flag = index + 1;
        }
    }

    public Cell[] getCells() {
        return cells;
    }


    public void turn() {

    }

    public void down() {
        Arrays.stream(cells).forEach(Cell::down);
    }

    public void left() {
        Arrays.stream(cells).forEach(Cell::left);
    }

    public void right() {
        Arrays.stream(cells).forEach(Cell::right);
    }

    public List<Integer> getRows() {
        List<Integer> lines = new ArrayList<>();
        for (Cell cell : cells) {
            lines.add(cell.getRow());
        }
        return lines;
    }

    @Override
    public String toString() {
        return "Tetromino{" +
                "cells=" + Arrays.toString(cells) +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
