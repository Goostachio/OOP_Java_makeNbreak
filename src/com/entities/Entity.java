package com.entities;
import com.commons.Coordinate;
import com.commons.Globals;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;


public class Entity extends Pane {
    protected Coordinate position;
    protected boolean interactable;
    protected Canvas canvas;
    protected double width, height;

    public Entity() {
        this.width = Globals.DEFAULT_WIDTH;
        this.height = Globals.DEFAULT_HEIGHT;
        this.canvas = new Canvas(width, height);
        this.setPrefWidth(width);
        this.setPrefHeight(height);
        this.position = new Coordinate(0, 0);
        this.interactable = false;
    }

    public Entity(Coordinate position, boolean interactable) {
        this();
        this.position = position;
        this.interactable = interactable;
    }

    public Entity(Coordinate position, boolean interactable, double width, double height) {
        this.position = position;
        this.interactable = interactable;
        this.width = width;
        this.height = height;
        this.canvas = new Canvas(width, height);
        this.setPrefWidth(width);
        this.setPrefHeight(height);
        this.setLayoutX(position.x);
        this.setLayoutY(position.y);
    }

    public Coordinate getPosition() {
        return this.position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }

    public boolean isInteractable() {
        return this.interactable;
    }

    public void setInteractable(boolean interactable) {
        this.interactable = interactable;
    }
}
