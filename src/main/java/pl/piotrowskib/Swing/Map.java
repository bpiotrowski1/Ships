package pl.piotrowskib.Swing;

import pl.piotrowskib.Board.Board;
import pl.piotrowskib.Ships.NoShip;

import javax.swing.*;
import java.awt.*;

import static pl.piotrowskib.Statics.Constants.*;

public class Map extends JPanel {
    private Board board;

    public Map(Board board) {
        this.board = board;
        setPreferredSize(new Dimension(400, 400));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D) graphics;

        for (int i = 1; i < SIZE_Y - 1; i++) {
            for (int j = 1; j < SIZE_X - 1; j++) {
                g2d.drawRect(i * WIELKOSC, j * WIELKOSC, WIELKOSC, WIELKOSC);
                if(board.getShip(board.convertToCords(j,i)).getClass() != NoShip.class) {
                    g2d.setBackground(Color.BLACK);
                    g2d.fillRect(i * WIELKOSC, j * WIELKOSC, WIELKOSC, WIELKOSC);
                }
            }
        }
    }
}
