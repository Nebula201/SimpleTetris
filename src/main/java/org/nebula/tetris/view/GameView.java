package org.nebula.tetris.view;

import org.nebula.tetris.Constants;
import org.nebula.tetris.model.game.Cell;
import org.nebula.tetris.model.game.Tetromino;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class GameView extends JPanel {
    private Cell[][] wall;
    private Set<Cell> cellSet;

    public GameView(int rows, int cols) {
        this.cellSet = new HashSet<>(200);
        this.wall = new Cell[rows][cols];
    }

    public GameView() {
        this(Constants.ROWS, Constants.COLS);
    }

    public boolean couldRight(Tetromino tetromino) {
        Objects.requireNonNull(tetromino);

        Cell[] cells = tetromino.getCells();
        return Arrays.stream(cells).noneMatch(cell ->
                cell.getCol() + 1 >= Constants.COLS || wall[cell.getRow()][cell.getCol() + 1] != null);
    }

    public boolean couldLeft(Tetromino tetromino) {
        Objects.requireNonNull(tetromino);

        Cell[] cells = tetromino.getCells();
        return Arrays.stream(cells).noneMatch(cell ->
                cell.getCol() - 1 < 0 || wall[cell.getRow()][cell.getCol() - 1] != null);
    }

    public boolean couldDown(Tetromino tetromino) {
        Objects.requireNonNull(tetromino);

        Cell[] cells = tetromino.getCells();
        return Arrays.stream(cells).noneMatch(cell ->
                cell.getCol() + 1 >= Constants.COLS || wall[cell.getRow()][cell.getCol() + 1] != null);
    }

    private boolean couldRemove(int row) {
        return Arrays.stream(wall[row]).allMatch(Objects::nonNull);
    }

    public void add(Tetromino tetromino) {
        Objects.requireNonNull(tetromino);

        Cell[] cells = tetromino.getCells();
        cellSet.addAll(Arrays.asList(cells));
        Arrays.stream(cells).forEach(cell -> wall[cell.getRow()][cell.getCol()] = cell);
        repaint();
    }

//    @Override
//    protected void printComponent(Graphics g) {
//        super.printComponent(g);
//
//        cellSet.forEach(cell -> {
//            int xSize = cell.getCol() + Constants.CELL_SIZE;
//            int ySize = cell.getRow() + Constants.CELL_SIZE;
//
//            System.out.println("Draw " + cell + "," + g.drawImage(cell.getImage(), xSize, ySize, this));
//        });
//    }

    @Override
    public void paint(Graphics g) {
        paintWall(g);
    }


    private void paintWall(Graphics g) {
        cellSet.forEach(cell -> {
            int xSize = cell.getCol() * Constants.CELL_SIZE;
            int ySize = cell.getRow() * Constants.CELL_SIZE;
//            g.drawImage(cell.getImage(), xSize, ySize, this);
//            System.out.println("Draw " + cell);
            System.out.println(xSize+" "+ySize);
            System.out.println("Draw " + cell + "," + g.drawImage(cell.getImage(), xSize, ySize, this));
        });
    }
}
