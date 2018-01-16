package com.gleneagle.cs.pong;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Puck {
	public int x;
	public int y;
	public final int width = 20;
	public final int height = 20;
	private Pong pong;
	public int componentX;
	public int componentY;
	public Random random;

	public Puck(Pong pong) {
		this.pong = pong;
		this.random = new Random();

		spawn();
	}

	public void update(Paddle paddle1, Paddle paddle2) {
		int speed = 5;

		this.x += componentX * speed;
		this.y += componentY * speed;

		if (this.x + width - componentX > Pong.width || this.x + componentX < 0) {
			if (this.componentX < 0) {
				this.x = 0;
				this.componentX = 2;
			} else {
				this.componentX = -2;
				this.x = Pong.width - width;
			}

		}
		if (checkCollision(paddle1) == 1) {
			this.componentX = -random.nextInt(4);
			this.componentY = -3;

			if (componentX == 0) {
				componentX = 1;
			}
		} else if (checkCollision(paddle2) == 1) {
			this.componentX = -random.nextInt(4);
			this.componentY = 3;

			if (componentX == 0) {
				componentX = 1;
			}
		}

		if (checkCollision(paddle1) == 2) {
			paddle1.score++;
			spawn();
		} else if (checkCollision(paddle2) == 2) {
			paddle2.score++;
			spawn();
		}
	}

	public int checkCollision(Paddle paddle) {
		if (this.y < paddle.y + paddle.height && this.y + height > paddle.y
				&& this.x < paddle.x + paddle.width
				&& this.x + width > paddle.x) {
			return 1; // bounce
		} else if ((paddle.y > this.y && paddle.paddleNumber == 2)
				|| (paddle.y < this.y - height && paddle.paddleNumber == 1)) {
			return 2; // score
		} else {
			return 0;
		}
	}

	public void spawn() {
		this.x = Pong.width / 2 - this.width / 2;
		this.y = Pong.height / 2 - this.height / 2;
		this.componentX = 0;
		this.componentY = 1;
	}

	public void display(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillOval(x, y, width, height);
	}

}
