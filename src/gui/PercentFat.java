package gui;
import java.awt.* ;
import java.awt.event.*;
import javax.swing.*;
   
public class PercentFat extends JFrame implements ActionListener{
     JLabel title     = new JLabel("Percent of Calories from Fat");
     JLabel fatLabel  = new JLabel("Enter grams of fat:   ");
     JLabel calLabel  = new JLabel("Enter total calories: ");
     JLabel perLabel  = new JLabel("Percent calories from fat: ");
    
     JTextField inFat  = new JTextField( 7 );
     JTextField inCal  = new JTextField( 7 );
     JTextField outPer = new JTextField( 7 );
    
     JButton    doit   = new JButton("Do It!");
     JButton nah = new JButton("dont");
    
     int calories ;            // input: total calories per serving
     int fatGrams ;            // input: grams of fat per serving
     double percent;           // result: percent of calories from fat
    
     public PercentFat(){                   //constructor
           setTitle( "Calories from Fat" );
           Container c = getContentPane();                                 
           c.setLayout( new FlowLayout() );
   
           c.add( title );  
           c.add( fatLabel );  
           c.add( inFat );    
           c.add( calLabel );  
           c.add( inCal );    
           c.add( perLabel );   
           c.add( outPer );   
           outPer.setEditable( false );    
   
           c.add( doit );     
           c.add(nah);
           nah.addActionListener( this );
           doit.addActionListener( this );
           setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );  
   }
             // The application
  public void calcPercent( )    {
            percent = ( (fatGrams * 9.0) / calories ) * 100.0 ;
  }

  public void actionPerformed( ActionEvent evt) {
           String userIn ;
            userIn    = inFat.getText()  ;
            fatGrams  = Integer.parseInt( userIn ) ;
   
            userIn    = inCal.getText()  ;
            calories  = Integer.parseInt( userIn ) ;
           
            calcPercent() ;
   
            outPer.setText( (percent+"       ").substring(0,6) );
                        repaint();   
}
  public static void main ( String[] args ){
          PercentFat fatApp  = new PercentFat() ;
           fatApp.setSize( 280, 200 );     
           fatApp.setVisible( true );         
      }   // end of main
}     // end of class    
