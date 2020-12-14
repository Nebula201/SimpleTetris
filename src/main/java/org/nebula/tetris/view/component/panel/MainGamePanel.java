package org.nebula.tetris.view.component.panel;

import org.nebula.tetris.Constants;
import org.nebula.tetris.model.game.Cell;
import org.nebula.tetris.model.game.Tetromino;

import javax.swing.*;
import java.util.*;

public class MainGamePanel extends JPanel {
    private final int COLS;
    private final int ROWS;
    private Set<Cell> cellSet;
    private Tetromino curr;


    public MainGamePanel(int rows, int cols) {
        this.ROWS = rows;
        this.COLS = cols;
        this.cellSet = new HashSet<>(rows * cols);
    }

    public MainGamePanel() {
        this(Constants.ROWS, Constants.COLS);
    }

    public Tetromino getCurr() {
        return curr;
    }

    public void setCurr(Tetromino curr) {
        this.curr = curr;
    }

    public boolean couldRight() throws CloneNotSupportedException {
        Objects.requireNonNull(curr);
        Tetromino tmp = (Tetromino) curr.clone();
        tmp.right();
        return Arrays.stream(tmp.getCells()).noneMatch(cell ->
                cellSet.contains(cell) || cell.getCol() > COLS);
    }

    public boolean couldLeft() throws CloneNotSupportedException {
        Objects.requireNonNull(curr);
        Tetromino tmp = (Tetromino) curr.clone();
        tmp.left();
        return Arrays.stream(tmp.getCells()).noneMatch(cell ->
                cellSet.contains(cell) || cell.getCol() < COLS);
    }

    public boolean couldDown() throws CloneNotSupportedException {
        Objects.requireNonNull(curr);
        Tetromino tmp = (Tetromino) curr.clone();
        tmp.down();
        return Arrays.stream(tmp.getCells()).noneMatch(cell ->
                cellSet.contains(cell) || cell.getRow() > ROWS);
    }

    private void store() {
        cellSet.addAll(Arrays.asList(curr.getCells()));
    }
}
