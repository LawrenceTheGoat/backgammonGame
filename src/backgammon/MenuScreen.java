/*Name: Lawrence Wang
 * Date: Jan 18
 * Title: Menu Screen
 * Description: Menu Screen for Backgammon game
 */
package backgammon;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;


public class MenuScreen extends JPanel implements ActionListener{
	private JButton newGame;
	private JButton back;
	
	
	public MenuScreen() {
		setLayout(null);
		setBackground(new Color(10,108,3));
		
		newGame = new JButton("New game");
		newGame.setBounds(350,100,200,30);
		this.add(newGame);
		
		
		back = new JButton("Back");
		back.setBounds(350,140, 200,30);
		this.add(back);
		
		newGame.addActionListener(this);
		back.addActionListener(this);
		
		

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==newGame) 
			MyGame.nextSlide();
		if(e.getSource()==back)
			MyGame.previousSlide();
		
		
	}

	
}
