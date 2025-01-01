package com.entities;

import com.commons.Coordinate;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class Card extends Entity {
    private Color[][] cells;
    private int number;

    public Card(Color[][] cells, Coordinate position, int cardNumber) {
        super(position, true);
        this.cells = cells;
        this.number = cardNumber;
    }

    public Card(Color[][] cells, Coordinate position) {
        super(position, true);
        this.cells = cells;
        this.number = 0;
    }

    public Card(Color[][] cells, Coordinate position, double width, double height) {
        super(position, true, width, height);
        this.cells = cells;
        this.number = 0;
    }

    public void draw() {
        GraphicsContext gc = this.canvas.getGraphicsContext2D();

        // set color the background

        Rectangle playground = new Rectangle(0, 0, this.width, this.height);
        Color color = Color.rgb(255, 255, 191); // Fully opaque
        playground.setFill(color);
        double cornerRadius = 20; // Max possible rounding
        playground.setArcWidth(cornerRadius);
        playground.setArcHeight(cornerRadius);

        // Set the border (stroke) color and width
        playground.setStroke(Color.DARKBLUE);
        playground.setStrokeWidth(this.width * 0.01);
        this.getChildren().add(playground);

        Rectangle line = new Rectangle(this.width * 0.008, this.height * 0.9, this.width * 0.983, this.height * 0.01);
        color = Color.rgb(255,230,87);
        line.setFill(color);
        this.getChildren().add(line);

        double tmp = Math.min(width, height);
        double gridSize = tmp * 0.8;
        Grid grid = new Grid(this.cells, new Coordinate((int) ((this.width - gridSize) * 0.49), (int) (this.height * 0.9 - gridSize)), gridSize, gridSize);
        grid.draw();
        this.getChildren().add(grid);

        gc.setFill(Color.RED); // Set the text color to black
        gc.setFont(new Font("Arial", tmp * 0.15)); // Set the font style and size
        // string to text

        gc.fillText(Integer.toString(3), this.width * 0.05, this.height * 0.15); // Draw the number
        gc.fillText(Integer.toString(3), this.width * 0.87, this.height * 0.15); // Draw the number


        this.getChildren().add(canvas);
    }
}
