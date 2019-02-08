package com.leonardo.gui;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private final int FRAME_WIDTH = 300;
    private final int FRAME_HEIGHT = 310;
    private final int PANEL_WIDHT = 260;
    private final int PANEL_HEIGHT = 260;

    private GamePanel gamePanel;
    private JLabel scoreLabel;

    public GameFrame() throws HeadlessException {
        this.setTitle("Snake");
        this.setBounds(100, 100, FRAME_WIDTH, FRAME_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.BLACK);

        scoreLabel = new JLabel("0000");
        scoreLabel.setBounds(0, 0, 70, 20);
        scoreLabel.setForeground(Color.WHITE);
        getContentPane().add(scoreLabel);

        gamePanel = new GamePanel(PANEL_WIDHT, PANEL_HEIGHT);
        gamePanel.setBounds(20, 20, PANEL_WIDHT, PANEL_HEIGHT);
        this.add(gamePanel);

        this.setVisible(true);
    }
}
