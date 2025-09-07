/*Name: Lawrence Wang
 * Date: Jan 18
 * Title: Title Screen
 * Description: Title Screen 
 */
package backgammon;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class TitleScreen extends JPanel implements ActionListener{
	private JButton startGame;
	private BufferedImage screen;
	private Icon screenIcon;
	public TitleScreen() {
		try {
			screen = ImageIO.read(new File("backgammonFiles/titlescreen.png"));
		} catch (IOException e) {
		}
		
		screenIcon = new ImageIcon(screen);
		
		startGame = new JButton(screenIcon);
		startGame.setPreferredSize(new Dimension(screenIcon.getIconWidth(),screenIcon.getIconHeight()));
		this.add(startGame);
		startGame.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		MyGame.nextSlide();
		
	}

	
}
