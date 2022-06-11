package finalProject;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class Main extends JFrame {

	private JPanel contentPane;
	private JButton btnGameStart;
	private JButton btnScoreBoard;
	private JButton btnHowToPlay;
	private JLabel lblCharacter;
	private JLabel lblAvoidBalls;
	private JLabel lblPurpleBall;
	private JLabel lblRedBall;
	private JLabel lblBlueBall;
	private JLabel lblBlackBall;
	private JLabel lblYellowBall;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		/*
		 * This program is written with WindowBuilder.
		 * 
		 * 
		 * All the abbreviation in the fields below
		 * 
		 * lbl: label
		 * btn: button
		 * gbl: grid bag layout 
		 * gbc: grid bag constraints
		 * 
		 */
		setTitle("Avoid Balls");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 863, 676);
		contentPane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(new ImageIcon(getClass().getResource("/images/mainBackground.jpg")).getImage(), 0, 0, null);
			}
			
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{234, 35, 248, 0, 234, 0};
		gbl_contentPane.rowHeights = new int[]{172, 220, 0, 236, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lblAvoidBalls = new JLabel("Avoid Balls!");
		lblAvoidBalls.setForeground(Color.RED);
		lblAvoidBalls.setFont(new Font("Arial", Font.BOLD, 40));
		GridBagConstraints gbc_lblAvoidBalls = new GridBagConstraints();
		gbc_lblAvoidBalls.insets = new Insets(0, 0, 5, 5);
		gbc_lblAvoidBalls.gridx = 2;
		gbc_lblAvoidBalls.gridy = 0;
		contentPane.add(lblAvoidBalls, gbc_lblAvoidBalls);
		
		lblCharacter = new JLabel("");
		lblCharacter.setIcon(new ImageIcon(Main.class.getResource("/images/player_big.png")));
		GridBagConstraints gbc_lblCharacter = new GridBagConstraints();
		gbc_lblCharacter.insets = new Insets(0, 0, 5, 5);
		gbc_lblCharacter.gridx = 2;
		gbc_lblCharacter.gridy = 1;
		contentPane.add(lblCharacter, gbc_lblCharacter);
		
		btnHowToPlay = new JButton("How To Play");
		btnHowToPlay.setBorder(new LineBorder(new Color(0, 128, 0), 4, true));
		btnHowToPlay.setOpaque(false);
		btnHowToPlay.setContentAreaFilled(false);
		btnHowToPlay.setForeground(Color.BLACK);
		btnHowToPlay.setFont(new Font("Arial", Font.BOLD, 30));
		btnHowToPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// open how to play jDialog
				try {
					HowToPlay dialog = new HowToPlay();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception error) {
					error.printStackTrace();
				}
			}
		});
		
		btnGameStart = new JButton("Game Start");
		btnGameStart.setBorder(new LineBorder(new Color(0, 128, 0), 4, true));
		btnGameStart.setOpaque(false);
		btnGameStart.setContentAreaFilled(false);
		btnGameStart.setBackground(new Color(255, 255, 255));
		btnGameStart.setFont(new Font("Arial", Font.BOLD, 30));
		btnGameStart.setForeground(Color.BLACK);
		btnGameStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// start the game!
				// open GameBoard (JFrame)
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							GameBoard frame = new GameBoard(0);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
				// close the main window
				dispose();
			}
		});
		
		lblBlackBall = new JLabel("");
		lblBlackBall.setIcon(new ImageIcon(Main.class.getResource("/images/circleBlack_big.png")));
		GridBagConstraints gbc_lblBlackBall = new GridBagConstraints();
		gbc_lblBlackBall.insets = new Insets(0, 0, 5, 5);
		gbc_lblBlackBall.gridx = 0;
		gbc_lblBlackBall.gridy = 2;
		contentPane.add(lblBlackBall, gbc_lblBlackBall);
		
		lblBlueBall = new JLabel("");
		lblBlueBall.setIcon(new ImageIcon(Main.class.getResource("/images/circleBlue_big.png")));
		GridBagConstraints gbc_lblBlueBall = new GridBagConstraints();
		gbc_lblBlueBall.insets = new Insets(0, 0, 5, 5);
		gbc_lblBlueBall.gridx = 1;
		gbc_lblBlueBall.gridy = 2;
		contentPane.add(lblBlueBall, gbc_lblBlueBall);
		
		lblPurpleBall = new JLabel("");
		lblPurpleBall.setIcon(new ImageIcon(Main.class.getResource("/images/circlePurple_big.png")));
		GridBagConstraints gbc_lblPurpleBall = new GridBagConstraints();
		gbc_lblPurpleBall.insets = new Insets(0, 0, 5, 5);
		gbc_lblPurpleBall.gridx = 2;
		gbc_lblPurpleBall.gridy = 2;
		contentPane.add(lblPurpleBall, gbc_lblPurpleBall);
		
		lblRedBall = new JLabel("");
		lblRedBall.setIcon(new ImageIcon(Main.class.getResource("/images/circleRed_big.png")));
		GridBagConstraints gbc_lblRedBall = new GridBagConstraints();
		gbc_lblRedBall.insets = new Insets(0, 0, 5, 5);
		gbc_lblRedBall.gridx = 3;
		gbc_lblRedBall.gridy = 2;
		contentPane.add(lblRedBall, gbc_lblRedBall);
		
		lblYellowBall = new JLabel("");
		lblYellowBall.setIcon(new ImageIcon(Main.class.getResource("/images/circleYellow_big.png")));
		GridBagConstraints gbc_lblYellowBall = new GridBagConstraints();
		gbc_lblYellowBall.insets = new Insets(0, 0, 5, 0);
		gbc_lblYellowBall.gridx = 4;
		gbc_lblYellowBall.gridy = 2;
		contentPane.add(lblYellowBall, gbc_lblYellowBall);
		GridBagConstraints gbc_btnGameStart = new GridBagConstraints();
		gbc_btnGameStart.ipady = 20;
		gbc_btnGameStart.ipadx = 20;
		gbc_btnGameStart.insets = new Insets(0, 0, 0, 5);
		gbc_btnGameStart.gridx = 0;
		gbc_btnGameStart.gridy = 3;
		contentPane.add(btnGameStart, gbc_btnGameStart);
		GridBagConstraints gbc_btnHowToPlay = new GridBagConstraints();
		gbc_btnHowToPlay.ipady = 20;
		gbc_btnHowToPlay.ipadx = 20;
		gbc_btnHowToPlay.insets = new Insets(0, 0, 0, 5);
		gbc_btnHowToPlay.gridx = 2;
		gbc_btnHowToPlay.gridy = 3;
		contentPane.add(btnHowToPlay, gbc_btnHowToPlay);
		
		btnScoreBoard = new JButton("Score Board");
		btnScoreBoard.setBorder(new LineBorder(new Color(0, 128, 0), 4, true));
		btnScoreBoard.setOpaque(false);
		btnScoreBoard.setContentAreaFilled(false);
		btnScoreBoard.setForeground(Color.BLACK);
		btnScoreBoard.setFont(new Font("Arial", Font.BOLD, 30));
		btnScoreBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// open score board jDialog
				try {
					ScoreBoard dialog = new ScoreBoard();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception error) {
					error.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnScoreBoard = new GridBagConstraints();
		gbc_btnScoreBoard.ipady = 20;
		gbc_btnScoreBoard.ipadx = 20;
		gbc_btnScoreBoard.gridx = 4;
		gbc_btnScoreBoard.gridy = 3;
		contentPane.add(btnScoreBoard, gbc_btnScoreBoard);
	}

}
