/** Pong.java plays the classic pong game
 * player1 plays using left and right arrow keys
 * palyer2 plays using 'a' and 'd' keys
 * First to 10 wins
 */
package com.gleneagle.cs.pong;
import javax.swing.*; // JFrame, JPanel, ...

import java.awt.*; // BorderLayout, Color, ...
import java.awt.event.*; // ActionEvent, ...

public class Pong extends JPanel implements ActionListener, KeyListener {


	private static final long serialVersionUID = 1L;
	
	static public final int width = 500;
	static public final int height = 600;
	public Paddle player1;
	public Paddle player2;
	public boolean a;
	public boolean d;
	public boolean right;
	public boolean left;
	public Puck puck;
	private Timer timer;
	public int scoreLimit = 10;

	public static void main(String[] args) {
		/*
		 * Dimension panelSize = new Dimension(width, height); Pong pong = new
		 * Pong(); pong.setPreferredSize(panelSize); pong.setVisible(true);
		 * 
		 * JFrame playScreen = new JFrame(); playScreen.add(pong);
		 * playScreen.addKeyListener(pong);
		 * 
		 * playScreen.pack(); playScreen.setLocationRelativeTo(null);
		 * playScreen.setVisible(true); playScreen.setResizable(false);
		 * playScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 */

		JFrame playScreen = new JFrame("Pong");
		playScreen.setSize(width, height + 30);
		playScreen.setResizable(false);
		playScreen.setLocationRelativeTo(null);
		playScreen.setBackground(Color.white);
		playScreen.setVisible(true);
		playScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Pong pong = new Pong();
		playScreen.add(pong);
		playScreen.addKeyListener(pong);

		pong.showHomeDialg("Play");
	}

	public Pong() {
		this.setVisible(true);

		player1 = new Paddle(this, 1);
		player2 = new Paddle(this, 2);
		puck = new Puck(this);
	}

	public void start() {
		if (timer == null) {
			timer = new Timer(20, this);
		}

		timer.start();
	}

	public void stop() {
		if (timer != null) {
			timer.stop();
		}

		player1 = new Paddle(this, 1);
		player2 = new Paddle(this, 2);
		puck = new Puck(this);
		a = false;
		d = false;
		right = false;
		left = false;

		repaint();
	}

	private void update() {
		if (player1.score >= scoreLimit || player2.score >= scoreLimit) {
			showHomeDialg("Replay");
		} else {
			if (left) {
				player1.move(true);
			}
			if (right) {
				player1.move(false);
			}
			if (a) {
				player2.move(true);
			}
			if (d) {
				player2.move(false);
			}
			puck.update(player1, player2);
		}

	}

	public void display(Graphics graphics) {
		Dimension size = getSize();

		graphics.setColor(Color.WHITE);
		graphics.fillRect((size.width - width) / 2, 0, width, height);

		player1.display(graphics);
		player2.display(graphics);
		puck.display(graphics);
	}

	void showHomeDialg(String playButtonLabel) {
		stop();

		JFrame playScreen = (JFrame) SwingUtilities.getWindowAncestor(this);
		;
		JDialog homeDialog = new JDialog(playScreen, true);
		homeDialog.setLayout(new GridLayout(2, 1, 10, 10));
		homeDialog.setSize(300, 200);
		homeDialog.setLocationRelativeTo(playScreen);
		JButton replayButton = new JButton(playButtonLabel);
		replayButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				homeDialog.dispose();
				start();
			}
		});
		homeDialog.add(replayButton);
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		homeDialog.add(exitButton);
		homeDialog.setUndecorated(true);
		homeDialog.setVisible(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		this.display(g);
	}

	@Override
	public void keyPressed(KeyEvent event) {
		int id = event.getKeyCode();
		if (id == KeyEvent.VK_A) {
			a = true;
		} else if (id == KeyEvent.VK_D) {
			d = true;
		} else if (id == KeyEvent.VK_RIGHT) {
			right = true;
		} else if (id == KeyEvent.VK_LEFT) {
			left = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent event) {
		int id = event.getKeyCode();
		if (id == KeyEvent.VK_A) {
			a = false;
		} else if (id == KeyEvent.VK_D) {
			d = false;
		} else if (id == KeyEvent.VK_RIGHT) {
			right = false;
		} else if (id == KeyEvent.VK_LEFT) {
			left = false;
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		update();
		repaint();

	}

}
