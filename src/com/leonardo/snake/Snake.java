package com.leonardo.snake;

import java.awt.*;
import java.util.LinkedList;

public class Snake {
    private final int SNAKE_SPEED = 10;
    private final int TILE_DIMENSION = 10;
    private final Color COLOR = Color.GREEN;

    private LinkedList<Rectangle> body;
    private Directions direction;
    private boolean hasEaten;

    public Snake() {
        direction = Directions.RIGHT;
        hasEaten = false;
        body = new LinkedList<>();
        body.add(new Rectangle(50, 20, TILE_DIMENSION, TILE_DIMENSION));
        body.add(new Rectangle(40, 20, TILE_DIMENSION, TILE_DIMENSION));
        body.add(new Rectangle(30, 20, TILE_DIMENSION, TILE_DIMENSION));
        body.add(new Rectangle(20, 20, TILE_DIMENSION, TILE_DIMENSION));
        body.add(new Rectangle(10, 20, TILE_DIMENSION, TILE_DIMENSION));
    }

    public LinkedList<Rectangle> getBody() {
        return body;
    }

    public void setUpDirection() {
        if (direction != Directions.DOWN)
            direction = Directions.UP;
    }

    public void setDownDirection() {
        if (direction != Directions.UP)
            direction = Directions.DOWN;
    }

    public void setRightDirection() {
        if (direction != Directions.LEFT)
            direction = Directions.RIGHT;
    }

    public void setLeftDirection() {
        if (direction != Directions.RIGHT)
            direction = Directions.LEFT;
    }

    public void move() {
        Rectangle newHead = hasEaten ?
                (Rectangle) body.peekLast().clone() :
                body.removeLast();
        hasEaten = false;
        newHead.x = body.peek().x;
        newHead.y = body.peek().y;

        switch (direction) {
            case UP:
                newHead.y -= SNAKE_SPEED;
                break;
            case DOWN:
                newHead.y += SNAKE_SPEED;
                break;
            case RIGHT:
                newHead.x += SNAKE_SPEED;
                break;
            case LEFT:
                newHead.x -= SNAKE_SPEED;
                break;
        }

        body.addFirst(newHead);
    }

    public Rectangle getHead() {
        return body.peek();
    }

    public void extend() {
        hasEaten = true;
    }

    public Color getColor() {
        return COLOR;
    }
}
