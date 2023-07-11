package ru.maikova.field;

import javax.swing.*;

public class Cell extends JButton {
    private int row;
    private int column;
    private boolean isOpened;
    private boolean isMined;
    private boolean isFlagged;
    private int numberOfMines;

    Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    public boolean isMined() {
        return isMined;
    }

    public void setMined(boolean mined) {
        isMined = mined;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    public int getNumberOfMines() {
        return numberOfMines;
    }

    public void setNumberOfMinesAround(int numberOfMines) {
        this.numberOfMines = numberOfMines;
    }
}