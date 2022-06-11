package finalProject;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class GameBoard extends JFrame {

	private JPanel contentPane;
	private GamePanel panel;

	// use swing Timer for check game is end or not.
	private Timer gameOverCheck;
	private int currentScore;

	/**
	 * Create the frame.
	 */
	public GameBoard(int highScore) {
		setTitle("Avoid Balls");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 824, 841);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		// Almost everything is implemented in GamePanel.java
		panel = new GamePanel();
		panel.setFocusable(true);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		gameOverCheck = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// if panel said game is over
				if (panel.isGameOver()) {

					currentScore = (int) panel.getScore();
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								// send high score and current score data to show them.
								GameOver dialog = new GameOver(Math.max(highScore, currentScore), currentScore);
								dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								dialog.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});

					// close the frame and stop the timer
					dispose();
					gameOverCheck.stop();
				}

			}
		});
		// checking start
		gameOverCheck.start();

	}

}
