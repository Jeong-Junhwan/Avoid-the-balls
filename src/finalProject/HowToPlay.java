package finalProject;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;

public class HowToPlay extends JDialog {

	private JPanel contentPanel;
	private JLabel lblHowToPlay;
	private JPanel buttonPane;
	private JButton buttonBack;

	/**
	 * Create the dialog.
	 */

	// show guide using image
	public HowToPlay() {
		contentPanel = new JPanel();
		setTitle("How To Play");
		setModal(true);
		setAlwaysOnTop(true);
		setBounds(100, 100, 691, 426);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(204, 255, 153));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			lblHowToPlay = new JLabel("");
			// attach image to label to show
			lblHowToPlay.setIcon(new ImageIcon(HowToPlay.class.getResource("/images/howToPlay.png")));
			contentPanel.add(lblHowToPlay, BorderLayout.CENTER);
		}
		{
			buttonPane = new JPanel();
			buttonPane.setBackground(new Color(204, 255, 153));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				buttonBack = new JButton("Back");
				buttonBack.setFont(new Font("Arial", Font.BOLD, 12));
				buttonBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// click back, close the dialog
						dispose();
					}
				});
				buttonBack.setActionCommand("OK");
				buttonPane.add(buttonBack);
				getRootPane().setDefaultButton(buttonBack);
			}
		}
	}

}
