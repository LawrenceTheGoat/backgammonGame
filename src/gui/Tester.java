package gui;
import javax.swing.*;
public class Tester {
	public static void main ( String[] args )  {
	      MyFrame frame = new MyFrame();
	     // construct a MyFrame object
	      frame.setSize(500, 500 );                                             // set it to 150 wide by 100 high
	      frame.setVisible( true );                                                 // ask it to become visible
	      frame.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
	  }    
}
