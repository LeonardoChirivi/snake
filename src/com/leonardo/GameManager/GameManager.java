package com.leonardo.GameManager;

import com.leonardo.gui.GamePanel;
import com.leonardo.snake.Snake;

import java.awt.*;
import java.util.stream.Collectors;

public class GameManager {
    private final Rectangle FRUIT = new Rectangle(0, 0, 10, 10);
    private final Color FRUIT_COLOR = Color.RED;
    private final int PANEL_WIDTH;
    private final double PANEL_HEIGHT;

    private Snake snake;
    private GamePanel gamePanel;
    private boolean running;

    public GameManager(int panelWidth, int panelHeight, GamePanel gamePanel) {
        this.PANEL_WIDTH = panelWidth;
        this.PANEL_HEIGHT = panelHeight;
        this.gamePanel = gamePanel;

        snake = new Snake();
        newRandomFruitLocation();
        running = true;

        GameThread gameThread = new GameThread(this);
        gameThread.startGame();
    }

    void gameLoop() {
        checkCollision();
        updateSnakePosition();
        draw();
    }

    private void checkCollision() {
        if (snakeOutOfBounds() || snakeCollideItself())
            running = false;
        else if (snakeCollideWithFruit()) {
            extendSnake();
            newRandomFruitLocation();
        }
    }

    private void updateSnakePosition() {
        snake.move();
    }

    private void draw() {
        gamePanel.repaint();
    }

    public void renderSnake(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(snake.getColor());
        for (Rectangle tile : snake.getBody())
            graphics2D.fillRect(tile.x, tile.y, tile.width, tile.height);
    }

    public void renderFruit(Graphics bufferedGraphics) {
        bufferedGraphics.setColor(FRUIT_COLOR);
        bufferedGraphics.fillRect(FRUIT.x, FRUIT.y, 10, 10);
    }

    public void moveSnakeUp() {
        snake.setUpDirection();
    }

    public void moveSnakeDown() {
        snake.setDownDirection();
    }

    public void moveSnakeRight() {
        snake.setRightDirection();
    }

    public void moveSnakeLeft() {
        snake.setLeftDirection();
    }

    boolean isRunning() {
        return running;
    }

    private void extendSnake() {
        snake.extend();
    }

    private void newRandomFruitLocation() {
        FRUIT.x = (int)(Math.round(Math.random() * (PANEL_WIDTH / 10 - 1))) * 10;
        FRUIT.y = (int)(Math.round(Math.random() * (PANEL_HEIGHT / 10 - 1))) * 10;
    }

    private boolean snakeCollideWithFruit() {
        Rectangle head = snake.getHead();
        return (head.x == FRUIT.x) && (head.y == FRUIT.y);
    }

    private boolean snakeCollideItself() {
        Rectangle head = snake.getHead();
        for (Rectangle tile : snake.getBody().stream().skip(1).collect(Collectors.toList())) {
            if (tile.x == head.x && tile.y == head.y)
                return true;
        }
        return false;
    }

    private boolean snakeOutOfBounds() {
        Rectangle head = snake.getHead();
        return head.x < 0 ||
                head.x > PANEL_WIDTH ||
                head.y < 0 ||
                head.y > PANEL_HEIGHT;
    }
}
