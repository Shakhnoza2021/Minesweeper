package ru.maikova.field;

import ru.maikova.gameprocess.GameProcess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Form extends JFrame {
    private static int mines;
    private int gridX;
    private int gridY;
    private static Cell[][] cells;
    private final JFrame parentFrame;
    private GameProcess gameProcess;

    Form(int gridX, int gridY, int mines, JFrame parentFrame) {
        this.parentFrame = parentFrame;
        this.gridX = gridX;
        this.gridY = gridY;
        Form.mines = mines;
        initForm();
    }

    //инициализация формы
    private void initForm() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                parentFrame.setVisible(true);
            }
        });
        setLayout(new BorderLayout());

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));

        JMenu jMenu = new JMenu("File");
        jMenu.add(exitItem);

        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(jMenu);

        JPanel centerPanel = new JPanel();
        JPanel eastPanel = new JPanel();
        JPanel westPanel = new JPanel();
        JPanel northPanel = new JPanel();
        JPanel southPanel = new JPanel();

        initCenterPanel(centerPanel);
        eastPanel.setPreferredSize(new Dimension(20, 20));
        westPanel.setPreferredSize(new Dimension(20, 20));
        northPanel.setPreferredSize(new Dimension(20, 20));
        southPanel.setPreferredSize(new Dimension(20, 20));

        setJMenuBar(jMenuBar);
        add(northPanel, BorderLayout.NORTH);
        add(southPanel, BorderLayout.SOUTH);
        add(westPanel, BorderLayout.WEST);
        add(eastPanel, BorderLayout.EAST);
        add(centerPanel, BorderLayout.CENTER);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //инициализация игрового поля
    private void initCenterPanel(JPanel centerPanel) {
        centerPanel.setLayout(new GridLayout(gridX, gridY));
        cells = new Cell[gridX][gridY];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
        gameProcess = new GameProcess(gridX, gridY);
        gameProcess.initField();
        addListenerToCells();

        for (Cell[] cell : cells) {
            for (Cell value : cell) {
                centerPanel.add(value);
            }
        }
    }

    private void addListenerToCells() {
        for (Cell[] cell : cells) {
            for (Cell value : cell) {
                value.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            gameProcess.openCell((Cell) e.getSource());
                        }
                        if (e.getButton() == MouseEvent.BUTTON3) {
                            gameProcess.setFlag((Cell) e.getSource());
                        }
                    }
                });
            }
        }
    }

    public static Cell[][] getCells() {
        return cells;
    }

    public static int getMinesCount() {
        return mines;
    }
}
