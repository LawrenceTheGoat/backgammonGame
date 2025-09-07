package gui;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import backgammon.Main;

public class ImageChangesWhenButtonClicked extends JFrame implements ActionListener{
	public ImageChangesWhenButtonClicked() {
		JButton but = new JButton();
		this.add(but);
	}
	public static void main(String[]args) {
		ImageChangesWhenButtonClicked m = new ImageChangesWhenButtonClicked();
		m.setPreferredSize(new Dimension(550,630));
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m.setResizable(true);
		m.setTitle("Backgammon");
		m.pack();
		m.setLocationRelativeTo(null);
		m.setVisible(true);

	}
}
