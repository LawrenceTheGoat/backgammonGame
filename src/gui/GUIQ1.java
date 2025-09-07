/* Name: Lawrence
 * Date: Dec 18
 * Title: GUIQ1
 * Description: Question 1 of the gui exercises. 5 buttons in a column
 */

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import backgammon.Main;

public class GUIQ1 extends JPanel{
	public GUIQ1() {
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.add(new JButton("A"));
		this.add(new JButton("B"));
		this.add(new JButton("C"));
		this.add(new JButton("D"));
		this.add(new JButton("E"));
	}
	public static void main(String[]args) {//frame creation and customization
		JFrame m = new JFrame();
		m.add(new GUIQ1());
		m.setPreferredSize(new Dimension(300,200));
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m.setResizable(false);
		m.setTitle("Buttons");
		m.pack();
		m.setVisible(true);
	}
}
