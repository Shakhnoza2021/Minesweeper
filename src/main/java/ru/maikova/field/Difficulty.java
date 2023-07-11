package ru.maikova.field;

import javax.swing.*;
import java.awt.*;

public class Difficulty extends JFrame {
    public Difficulty() {
        super("Выберите уровень игры");
        chooseLvl();
    }

    private void chooseLvl() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel content = new JPanel();

        JButton btnEasy = new JButton("Новичок");
        JButton btnNormal = new JButton("Любитель");
        JButton btnHard = new JButton("Профессионал");

        btnEasy.setPreferredSize(new Dimension(140, 30));
        btnNormal.setPreferredSize(new Dimension(140, 30));
        btnHard.setPreferredSize(new Dimension(140, 30));

        btnEasy.addActionListener(e -> {
            setVisible(false);
            new Form(9, 9, 10, Difficulty.this);
        });

        btnNormal.addActionListener(e -> {
            setVisible(false);
            new Form(16, 16, 30, Difficulty.this);
        });

        btnHard.addActionListener(e -> {
            setVisible(false);
            new Form(16, 30, 60, Difficulty.this);
        });

        content.add(btnEasy);
        content.add(btnNormal);
        content.add(btnHard);

        setContentPane(content);
        setSize(260, 150);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
