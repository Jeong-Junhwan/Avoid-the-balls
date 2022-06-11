package finalProject;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class GameOver extends JDialog implements Serializable {
	private JLabel lblGameOver;
	private JPanel panelScore;
	private JPanel panelButton;
	private JButton btnBackToBeginning;
	private JButton btnTryAgain;
	private JLabel lblHighScore;
	private JLabel lblScore1;
	private JLabel lblScore2;
	private JLabel lblCurrentScore;
	private ArrayList<Integer> rank;

	/**
	 * Create the dialog.
	 */

	// get high score and current score from game board
	public GameOver(int highScore, int currentScore) {
		setAlwaysOnTop(true);
		setModal(true);
		setTitle("Game Over");
		setBounds(100, 100, 450, 239);

		lblGameOver = new JLabel("");
		lblGameOver.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameOver.setIcon(new ImageIcon(GameOver.class.getResource("/images/gameOver.png")));
		getContentPane().add(lblGameOver, BorderLayout.NORTH);

		panelScore = new JPanel();
		getContentPane().add(panelScore, BorderLayout.CENTER);
		panelScore.setLayout(new GridLayout(0, 2, 0, 0));

		lblScore1 = new JLabel("High Score: ");
		lblScore1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblScore1.setFont(new Font("Arial", Font.BOLD, 20));
		panelScore.add(lblScore1);

		// show high score
		lblHighScore = new JLabel("" + highScore);
		lblHighScore.setFont(new Font("Arial", Font.BOLD, 20));
		panelScore.add(lblHighScore);

		lblScore2 = new JLabel("Current Score: ");
		lblScore2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblScore2.setFont(new Font("Arial", Font.BOLD, 20));
		panelScore.add(lblScore2);

		// show current score
		lblCurrentScore = new JLabel("" + currentScore);
		lblCurrentScore.setFont(new Font("Arial", Font.BOLD, 20));
		panelScore.add(lblCurrentScore);

		panelButton = new JPanel();
		getContentPane().add(panelButton, BorderLayout.SOUTH);

		btnBackToBeginning = new JButton("Back to beginning");
		btnBackToBeginning.setFont(new Font("Arial", Font.BOLD, 12));
		btnBackToBeginning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// open the main menu
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Main frame = new Main();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

				// close the jDialog
				dispose();
			}
		});
		panelButton.add(btnBackToBeginning);

		btnTryAgain = new JButton("Try Again");
		btnTryAgain.setFont(new Font("Arial", Font.BOLD, 12));
		btnTryAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// open the game board
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							GameBoard frame = new GameBoard(highScore);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

				// close the jDialog
				dispose();
			}
		});
		panelButton.add(btnTryAgain);

		// save the score to the .txt file for score board and future playing
		try {
			// get project path
			String filePath = new File("").getAbsolutePath();
			// use buffer reader, read rank.txt file
			BufferedReader br = new BufferedReader(new FileReader(filePath + "/src/scores/rank.txt"));

			// add current score and every score in rank.txt to ArrayList
			rank = new ArrayList<Integer>();
			rank.add(currentScore);

			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				rank.add(Integer.parseInt(currentLine));
			}
			br.close();

			// sorting for showing top 5 scores and remove last
			Collections.sort(rank, Collections.reverseOrder());
			rank.remove(5);

			// write rank.txt file using buffer writer
			BufferedWriter bw = new BufferedWriter(new FileWriter(filePath + "/src/scores/rank.txt"));

			for (int score : rank) {
				bw.write(score + System.lineSeparator());
			}

			bw.flush();
			bw.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
