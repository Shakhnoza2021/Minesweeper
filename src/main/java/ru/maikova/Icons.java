package ru.maikova;

import ru.maikova.field.Form;

import javax.swing.*;
import java.awt.*;

public class Icons {
    public static Icon flagIcon;
    public static Icon openIcon;
    public static Icon closedIcon;
    public static Icon minedIcon;

    static {
        ImageIcon iconFlag = new ImageIcon(Form.class.getResource("/icons/flag.png"));
        ImageIcon iconClosed = new ImageIcon(Form.class.getResource("/icons/closed.png"));
        ImageIcon iconMined = new ImageIcon(Form.class.getResource("/icons/mined.png"));
        ImageIcon iconOpened = new ImageIcon(Form.class.getResource("/icons/zero.png"));

        flagIcon = new ImageIcon(iconFlag.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        closedIcon = new ImageIcon(iconClosed.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        minedIcon = new ImageIcon(iconMined.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        openIcon = new ImageIcon(iconOpened.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
    }

    public static Icon getNumberIcon(int num) {
        ImageIcon iconNum = new ImageIcon(Form.class.getResource("/icons/num" + num + ".png"));
        Icon numIcon = new ImageIcon(iconNum.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        return numIcon;
    }
}
