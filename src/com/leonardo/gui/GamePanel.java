package com.leonardo.gui;

import com.leonardo.GameManager.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements KeyListener {
    private GameManager gameManager;

    GamePanel(int width, int height) {
        this.setOpaque(true);
        this.setBackground(new Color(51, 51, 51));
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocus();

        gameManager = new GameManager(width, height, this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics bufferedGraphics = bufferedImage.getGraphics();

        gameManager.renderSnake(bufferedGraphics);
        gameManager.renderFruit(bufferedGraphics);

        g.drawImage(bufferedImage, 0, 0, this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                gameManager.moveSnakeUp();
                break;
            case KeyEvent.VK_DOWN:
                gameManager.moveSnakeDown();
                break;
            case KeyEvent.VK_RIGHT:
                gameManager.moveSnakeRight();
                break;
            case KeyEvent.VK_LEFT:
                gameManager.moveSnakeLeft();
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }
}
