package ru.maikova.gameprocess;

import ru.maikova.Icons;
import ru.maikova.field.Cell;
import ru.maikova.field.Form;

import javax.swing.*;
import java.awt.*;

public class GameProcess {
    private int closedCells;
    private boolean firstClick;
    private int gridX;
    private int gridY;
    private Cell[][] cells;

    public GameProcess(int gridX, int gridY) {
        this.gridX = gridX;
        this.gridY = gridY;
        cells = Form.getCells();
    }

    //инициализирует поле
    public void initField() {
        firstClick = false;
        closedCells = gridX * gridY;
        resetCells();
    }

    //сбрасывает значения ячеек
    private void resetCells() {
        Dimension buttonPreferredSize = new Dimension(30, 30);
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j].setMined(false);
                cells[i][j].setOpened(false);
                cells[i][j].setFlagged(false);
                cells[i][j].setNumberOfMinesAround(0);
                cells[i][j].setIcon(Icons.closedIcon);
                cells[i][j].setPreferredSize(buttonPreferredSize);
            }
        }
    }

    //открывает ячейку
    public void openCell(Cell cell) {
        if (!firstClick) {
            firstClick = true;
            setMines(Form.getMinesCount(), cell.getRow(), cell.getColumn());
            setCountOfMines();
        }
        if (!cell.isOpened() && !cell.isFlagged()) {
            if (closedCells == Form.getMinesCount() || cell.isMined()) {
                showGameOver();
            } else if (cell.getNumberOfMines() > 0) {
                cell.setIcon(Icons.getNumberIcon(cell.getNumberOfMines()));
                cell.setOpened(true);
                closedCells--;
            } else {
                cell.setIcon(Icons.openIcon);
                openSafeCells(cell.getRow(), cell.getColumn());
            }
            if (closedCells == Form.getMinesCount() || cell.isMined()) {
                showGameOver();
            }
        }
    }

    //устанавливает/снимает флаг
    public void setFlag(Cell cell) {
        if (!cell.isOpened()) {
            cell.setFlagged(!cell.isFlagged());
            if (cell.isFlagged()) {
                cell.setIcon(Icons.flagIcon);
            } else {
                cell.setIcon(Icons.closedIcon);
            }
        }
    }

    //открывает пустые ячейки
    private void openSafeCells(int x, int y) {
        if (x < 0 || y < 0 || x >= gridX || y >= gridY) {
            return;
        }
        if (cells[x][y].isOpened() || cells[x][y].isFlagged()) {
            return;
        }
        if (isCellNumber(cells[x][y])) {
            return;
        }
        cells[x][y].setOpened(true);
        closedCells--;
        cells[x][y].setIcon(Icons.openIcon);

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                openSafeCells(i, j);
            }
        }
    }

    //проверяет содержит ли ячейка информацию о минах
    private boolean isCellNumber(Cell cell) {
        if (cell.getNumberOfMines() > 0) {
            cell.setIcon(Icons.getNumberIcon(cell.getNumberOfMines()));
            cell.setOpened(true);
            closedCells--;
            return true;
        }
        return false;
    }

    //показываает результат игры и обновляет поле
    private void showGameOver() {
        if (closedCells != Form.getMinesCount()) {
            showMines();
            JOptionPane.showMessageDialog(null, "Вы проиграли :(", "Сообщение", JOptionPane.INFORMATION_MESSAGE);
            initField();
        } else {
            JOptionPane.showMessageDialog(null, "Вы выиграли :)", "Сообщение", JOptionPane.INFORMATION_MESSAGE);
            initField();
        }
    }

    //расставляет мины в случайном порядке
    private void setMines(int minesCount, int row, int column) {
        for (int i = 1; i <= minesCount; i++) {
            int x;
            int y;
            do {
                x = (int) (Math.random() * cells.length);
                y = (int) (Math.random() * cells[0].length);
            } while (cells[x][y].isMined() || x == row || y == column);
            cells[x][y].setNumberOfMinesAround(-1);
            cells[x][y].setMined(true);
        }
    }

    //показывает все ячейки с минами
    private void showMines() {
        for (Cell[] cellsMass : cells) {
            for (Cell cell : cellsMass) {
                if (cell.isMined()) {
                    cell.setIcon(Icons.minedIcon);
                }
            }
        }
    }

    //записывает в ячейку число мин вокруг
    private void setCountOfMines() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j].isMined()) continue;
                int minesAround = calcMinesAround(i, j);
                cells[i][j].setNumberOfMinesAround(minesAround);
            }
        }
    }

    //считывает количество мин вокруг текущей ячейки
    private int calcMinesAround(int x, int y) {
        int minesCount = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i < 0 || j < 0 || i >= gridX || j >= gridY) {
                    continue;
                }
                minesCount += cells[i][j].isMined() ? 1 : 0;
            }
        }
        return minesCount;
    }
}
