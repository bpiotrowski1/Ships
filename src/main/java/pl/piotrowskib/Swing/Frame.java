package pl.piotrowskib.Swing;

import pl.piotrowskib.Board.Board;

import javax.swing.*;

public class Frame extends JFrame {

    public Frame(Board board) {
        JPanel panel = new Map(board);
        add(panel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


}
