package com.gleneagle.cs.pong;

import java.awt.*;

public class Paddle {
	public int paddleNumber;
	public int x;
	public int y;
	public int width = 100;
	public int height = 20;
	public int score;

	public Paddle(Pong pong, int paddleNumber) {
		this.paddleNumber = paddleNumber;

		if (paddleNumber == 1) {
			this.y = Pong.height - height;
		}

		if (paddleNumber == 2) {
			this.y = 0;
		}

		this.x = Pong.width / 2 - this.width / 2;

	}

	public void display(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(x, y, width, height);
	}

	public void move(boolean left) {
		int distance = 10;
		if (left) {
			if (x - distance > 0) {
				x -= distance;
			} else {
				x = 0;
			}
		} else {
			if (Pong.width >= x + width + distance) {
				x += distance;
			} else {
				x = Pong.width - width;
			}
		}
	}

}
