/* Name: Lawrence
 * Date: Dec 18
 * Title: GUIQ3
 * Description: Question 3 of the gui exercises. A label at the top,
 * A dealer label with five cards,
 * A player label with five cards,
 * and three adjacent buttons at the bottom
 */

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUIQ3 extends JFrame{
	BufferedImage image;
	ImageIcon icon;
	public GUIQ3() {
		try {
			image = ImageIO.read(new File("card.png"));//import image, create imageicon
		} catch (IOException e) {
		}
	    icon = new ImageIcon(image);
	    
		this.setLayout(new FlowLayout());
		JLabel top = new JLabel("Win: 0 Lost: 0 Tie: 0 Welcome!");//create top label
		JPanel dealer = new JPanel();
		dealer.add(new JLabel("Dealer"));//create dealer panel
		dealer.add(new JLabel(icon));
		dealer.add(new JLabel(icon));
		dealer.add(new JLabel(icon));
		dealer.add(new JLabel(icon));
		dealer.add(new JLabel(icon));
		
		JPanel player = new JPanel();
		player.add(new JLabel("Player"));//create player panel
		player.add(new JLabel(icon));
		player.add(new JLabel(icon));
		player.add(new JLabel(icon));
		player.add(new JLabel(icon));
		player.add(new JLabel(icon));
		
		JPanel bot = new JPanel();//create bottom panel with buttons
		bot.setLayout(new GridLayout(1,3));
		bot.add(new JButton("Deal"));
	    bot.add(new JButton("Hit"));
	    bot.add(new JButton("Stand"));
		
	    this.add(top);//add panels to the frame
		this.add(dealer);
		this.add(player);
		this.add(bot);
	}
	public static void main(String[]args) {//frame creation and customization
		GUIQ3 m = new GUIQ3();
		m.setLayout(new FlowLayout());
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m.setResizable(false);
		m.setTitle("");
		m.pack();
		m.setSize(600,500);
		m.setVisible(true);
	}
}
