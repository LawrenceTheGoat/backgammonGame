/* Name: Lawrence
 * Date: Dec 20
 * Title: GUIQ2
 * Description: The button assignment. It's the second question in the layout assignment
 * except when you click the buttons in the grid the icon on it changes. This can happen up to twice.
 */

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUIQ2 extends JFrame implements ActionListener{    
   BufferedImage image1;
   Icon icon1;
   BufferedImage image2;
   Icon icon2;
   BufferedImage image3;
   Icon icon3;
   JButton[] list = new JButton[100];
	GUIQ2(){    
		try {
			image1 = ImageIO.read(new File("one.png"));//import image, create imageicon//to lawrence: add backgammonfiles to the beginning if you ever revisit this
		} catch (IOException e) {
		}
	    icon1 = new ImageIcon(image1);
	   
	    try {
			image2 = ImageIO.read(new File("two.png"));//import image, create imageicon
		} catch (IOException e) {
		}
	    icon2 = new ImageIcon(image2);
	   
	    try {
			image3 = ImageIO.read(new File("three.png"));//import image, create imageicon
		} catch (IOException e) {
		}
	    icon3 = new ImageIcon(image3);
	    
		JPanel upperPart = new JPanel();
		JLabel jlabel = new JLabel("My new game...."); //create upper part with button and label
	    upperPart.add(new JButton(icon1));
		upperPart.add(jlabel);
	    
		JPanel buttonPanel = new JPanel();//create a panel for the grid of buttons
	    for(int i = 0;i<100;i++) {
	    list[i] = new JButton(icon1);
	    list[i].addActionListener(this);
	    }
	    for(int i = 0;i<100;i++) {
		    buttonPanel.add(list[i]);
		    
		}
	    buttonPanel.setLayout(new GridLayout(10,10,1,1));  //make buttonpanel a gridlayout
	    
	    
	    JPanel lowerPart = new JPanel();//create the lower panel with buttons
	    lowerPart.setLayout(new GridLayout(1,3));
	    lowerPart.setPreferredSize(new Dimension(675, 50));
	    lowerPart.add(new JButton("aaaa"));
	    lowerPart.add(new JButton("bbbb"));
	    lowerPart.add(new JButton("cccc"));
	    this.add(upperPart);
	    this.add(buttonPanel);
	    this.add(lowerPart);
	}    
	public static void main(String[]args) {//frame creation and customization
		GUIQ2 m = new GUIQ2();
		m.setLayout(new FlowLayout());
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m.setResizable(false);
		m.setTitle("My New Game");
		m.pack();
		m.setSize(675,800);
		m.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton b = (JButton)e.getSource();
		if(b.getIcon()==icon1) {
			b.setIcon(icon2);
		}
		else {
			b.setIcon(icon3);
		}
		
	}
}    
