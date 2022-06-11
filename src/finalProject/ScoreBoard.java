package finalProject;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.ImageIcon;

public class ScoreBoard extends JDialog implements Serializable {
	private JLabel lblRank1;
	private JLabel lblRank2;
	private JLabel lblRank3;
	private JLabel lblRank4;
	private JLabel lblRank5;
	private JPanel panelScore;
	private JPanel buttonPane;
	private JButton btnBack;

	private ArrayList<String> rank;

	private final JPanel contentPanel = new JPanel();
	private JPanel panelMedal;
	private JLabel lblGoldMedal;
	private JLabel lblSilverMedal;
	private JLabel lblBronzeMedal;
	private JLabel lblNumber4;
	private JLabel lblNumber5;

	/**
	 * Create the dialog.
	 */
	public ScoreBoard() {
		setTitle("Score Board");
		setModal(true);
		setAlwaysOnTop(true);
		setBounds(100, 100, 263, 496);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(204, 255, 153));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		// read score files from .txt file
		try {
			String filePath = new File("").getAbsolutePath(); // get project path

			BufferedReader brRank = new BufferedReader(new FileReader(filePath + "/src/scores/rank.txt"));

			rank = new ArrayList<String>();

			String currentLine;
			while ((currentLine = brRank.readLine()) != null) {
				rank.add(currentLine);
			}
			brRank.close();

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		contentPanel.setLayout(new GridLayout(1, 2, 0, 0));
		{
			
			// make new panel and show medal
			panelMedal = new JPanel();
			panelMedal.setBackground(new Color(204, 255, 153));
			contentPanel.add(panelMedal);
			panelMedal.setLayout(new GridLayout(5, 1, 0, 0));
			{
				lblGoldMedal = new JLabel("");
				lblGoldMedal.setOpaque(true);
				lblGoldMedal.setBackground(new Color(204, 255, 153));
				lblGoldMedal.setHorizontalAlignment(SwingConstants.CENTER);
				lblGoldMedal.setIcon(new ImageIcon(ScoreBoard.class.getResource("/images/medalGold.png")));
				panelMedal.add(lblGoldMedal);
			}
			{
				lblSilverMedal = new JLabel("");
				lblSilverMedal.setOpaque(true);
				lblSilverMedal.setBackground(new Color(204, 255, 153));
				lblSilverMedal.setHorizontalAlignment(SwingConstants.CENTER);
				lblSilverMedal.setIcon(new ImageIcon(ScoreBoard.class.getResource("/images/medalSilver.png")));
				panelMedal.add(lblSilverMedal);
			}
			{
				lblBronzeMedal = new JLabel("");
				lblBronzeMedal.setOpaque(true);
				lblBronzeMedal.setBackground(new Color(204, 255, 153));
				lblBronzeMedal.setHorizontalAlignment(SwingConstants.CENTER);
				lblBronzeMedal.setIcon(new ImageIcon(ScoreBoard.class.getResource("/images/medalBronze.png")));
				panelMedal.add(lblBronzeMedal);
			}
			{
				lblNumber4 = new JLabel("");
				lblNumber4.setOpaque(true);
				lblNumber4.setBackground(new Color(204, 255, 153));
				lblNumber4.setHorizontalAlignment(SwingConstants.CENTER);
				lblNumber4.setIcon(new ImageIcon(ScoreBoard.class.getResource("/images/number4.png")));
				panelMedal.add(lblNumber4);
			}
			{
				lblNumber5 = new JLabel("");
				lblNumber5.setOpaque(true);
				lblNumber5.setBackground(new Color(204, 255, 153));
				lblNumber5.setHorizontalAlignment(SwingConstants.CENTER);
				lblNumber5.setIcon(new ImageIcon(ScoreBoard.class.getResource("/images/number5.png")));
				panelMedal.add(lblNumber5);
			}
		}

		{
			// make new panel and show score
			panelScore = new JPanel();
			panelScore.setBackground(new Color(204, 255, 153));
			panelScore.setBorder(null);
			contentPanel.add(panelScore);
			panelScore.setLayout(new GridLayout(5, 1, 0, 0));
			{
				lblRank1 = new JLabel("");
				lblRank1.setOpaque(true);

				lblRank1.setBackground(new Color(204, 255, 153));
				lblRank1.setHorizontalAlignment(SwingConstants.CENTER);
				lblRank1.setFont(new Font("Arial", Font.BOLD, 25));
				lblRank1.setText(rank.get(0));
				panelScore.add(lblRank1);
			}
			{
				lblRank2 = new JLabel("");
				lblRank2.setOpaque(true);
				lblRank2.setBackground(new Color(204, 255, 153));
				lblRank2.setHorizontalAlignment(SwingConstants.CENTER);
				lblRank2.setFont(new Font("Arial", Font.BOLD, 25));
				lblRank2.setText(rank.get(1));
				panelScore.add(lblRank2);
			}
			{
				lblRank3 = new JLabel("");
				lblRank3.setOpaque(true);
				lblRank3.setBackground(new Color(204, 255, 153));
				lblRank3.setHorizontalAlignment(SwingConstants.CENTER);
				lblRank3.setFont(new Font("Arial", Font.BOLD, 25));
				lblRank3.setText(rank.get(2));
				panelScore.add(lblRank3);
			}
			{
				lblRank4 = new JLabel("");
				lblRank4.setOpaque(true);
				lblRank4.setBackground(new Color(204, 255, 153));
				lblRank4.setHorizontalAlignment(SwingConstants.CENTER);
				lblRank4.setFont(new Font("Arial", Font.BOLD, 25));
				lblRank4.setText(rank.get(3));
				panelScore.add(lblRank4);
			}
			{
				lblRank5 = new JLabel("");
				lblRank5.setOpaque(true);
				lblRank5.setBackground(new Color(204, 255, 153));
				lblRank5.setHorizontalAlignment(SwingConstants.CENTER);
				lblRank5.setFont(new Font("Arial", Font.BOLD, 25));
				lblRank5.setText(rank.get(4));
				panelScore.add(lblRank5);
			}
		}

		{
			buttonPane = new JPanel();
			buttonPane.setBackground(new Color(204, 255, 153));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnBack = new JButton("Back");
				btnBack.setFont(new Font("Arial", Font.BOLD, 12));
				btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// close the dialog when it's clicked
						dispose();
					}
				});
				btnBack.setActionCommand("Cancel");
				buttonPane.add(btnBack);
			}
		}

	}

}
