/*Name: Lawrence Wang
 * Date: Jan 18
 * Title: MyGame
 * Description: Main class
 */
package backgammon;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;

public class MyGame extends JFrame {
	static Container window;
	static TitleScreen ttl = new TitleScreen();
	static MenuScreen menu = new MenuScreen();
	static CardLayout crd = new CardLayout();
	public static Backgammon backgammon;
	
	
	public MyGame(){
		window = getContentPane();
		backgammon = new Backgammon();
		window.setLayout(crd);
		window.add(ttl);
		window.add(menu);
		window.add(backgammon);
		
	}
	
	public static void nextSlide() {
		crd.next(window);
	}
	public static void previousSlide() {
		crd.previous(window);
	}
	public static void redraw() {
		window.invalidate();
		window.validate();
		window.repaint();
	}
	public static void main(String[] args) {
		MyGame m = new MyGame();
		m.setPreferredSize(new Dimension(900,520));
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m.setResizable(true);
		m.setTitle("Backgammon");
		m.pack();
		m.setLocationRelativeTo(null);
		m.setVisible(true);
	}
}
