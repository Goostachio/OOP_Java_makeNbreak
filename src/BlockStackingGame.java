import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class BlockStackingGame extends Application {

    // Game variables
    private List<Block> blocks = new ArrayList<>(); // List to store blocks
    private int blockWidth = 50;
    private int blockHeight = 50;
    private int baseY = 400;
    private int score = 0;
    private boolean gameOver = false;
    private long startTime;
    private int timeLimit = 60; // 60 seconds time limit

    @Override
    public void start(Stage primaryStage) {
        // Setup Canvas
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Initial Block
        blocks.add(new Block(375, baseY));
        startTime = System.currentTimeMillis(); // Start timer

        // Scene and Group
        Group root = new Group(canvas);
        Scene scene = new Scene(root);

        // Key Controls
        scene.setOnKeyPressed(event -> {
            if (!gameOver) {
                Block current = blocks.get(blocks.size() - 1);
                if (event.getCode() == KeyCode.LEFT) {
                    current.moveLeft();
                } else if (event.getCode() == KeyCode.RIGHT) {
                    current.moveRight();
                } else if (event.getCode() == KeyCode.SPACE) {
                    addBlock();
                }
            }
        });

        // Animation Loop
        new Thread(() -> {
            while (true) {
                draw(gc);
                try {
                    Thread.sleep(16); // Approx 60 FPS
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // Setup Stage
        primaryStage.setTitle("2D Block Stacking Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Add a new block to the stack
    private void addBlock() {
        Block current = blocks.get(blocks.size() - 1);
        if (blocks.size() > 1) {
            Block previous = blocks.get(blocks.size() - 2);
            if (!current.isAligned(previous)) {
                gameOver = true;
                return;
            }
        }
        blocks.add(new Block(375, baseY - blocks.size() * blockHeight));
        score++;
    }

    // Draw game elements
    private void draw(GraphicsContext gc) {
        gc.setFill(Color.LIGHTBLUE);
        gc.fillRect(0, 0, 800, 600);

        // Draw blocks
        for (Block block : blocks) {
            block.draw(gc);
        }

        // Draw score
        gc.setFill(Color.BLACK);
        gc.setFont(new Font(20));
        gc.fillText("Score: " + score, 10, 20);

        // Draw timer
        long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
        long remainingTime = timeLimit - elapsedTime;
        gc.fillText("Time Left: " + remainingTime + "s", 10, 50);

        if (gameOver || remainingTime <= 0) {
            gc.setFont(new Font(40));
            gc.fillText("Game Over!", 300, 300);
            gameOver = true;
        }
    }

    // Block class
    private class Block {
        int x, y;
        Block(int x, int y) {
            this.x = x;
            this.y = y;
        }

        void moveLeft() {
            if (x > 0) x -= 10;
        }

        void moveRight() {
            if (x < 800 - blockWidth) x += 10;
        }

        boolean isAligned(Block below) {
            return Math.abs(this.x - below.x) < blockWidth / 2; // Check alignment
        }

        void draw(GraphicsContext gc) {
            gc.setFill(Color.hsb(Math.random() * 360, 0.8, 0.8));
            gc.fillRect(x, y, blockWidth, blockHeight);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

