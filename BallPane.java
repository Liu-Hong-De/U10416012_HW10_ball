//U10416012 劉宏德

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class BallPane extends Pane {
	public final double radius = 20;
	private double x = radius, y = radius;
	private double dx = 1, dy = 1;
	private Circle circle = new Circle(x, y, radius, new Color(Math.random(),Math.random(), Math.random(), 0.8));
	private Timeline animation;						//use random to change the ball color
	
	public BallPane() {
		getChildren().add(circle);	// Place a ball into this pane
		// Create an animation for moving the ball
		animation = new Timeline(new KeyFrame(Duration.millis(50), e -> moveBall()));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();	// Start animation
	}
	
	public void play() {
		animation.play();
	}
	
	public void pause() {
		animation.pause();
		Color color = new Color(Math.random(), Math.random(), Math.random(), 0.8);
		circle.setFill(color);	//change the ball color when user enter mouse
	}
	
	public void increaseSpeed() {
		animation.setRate(animation.getRate() + 0.1);
	}
	
	public void decreaseSpeed() {
		animation.setRate(animation.getRate() > 0 ? animation.getRate() - 0.1 : 0);
	}
	
	public DoubleProperty rateProperty() {
		return animation.rateProperty();
	}
	
	protected void moveBall() {
		// Checkboundaries
		if(x < radius || x > getWidth() - radius) {
			dx *= -1;	// Change ball move direction
		}
		if(y < radius || y > getHeight() - radius) {
			dy *= -1;
		}
		
		// Adjust ball position
		x += dx;
		y += dy;
		circle.setCenterX(x);
		circle.setCenterY(y);
	}
}
