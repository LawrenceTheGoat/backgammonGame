/*Name: Lawrence Wang
 * Date: Jan 18
 * Title: Backgammon
 * Description: The class that contains the game
 */
package backgammon;
import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Backgammon extends JPanel implements ActionListener, MouseListener{
	private BufferedImage backgammonBoard;
	private BufferedImage redPiece;
	private BufferedImage bluePiece;
	private BufferedImage clickable;
	private BufferedImage option;
	private JButton roll;
	private JLabel yourTurn;
	private JLabel cpuTurn;	
	private JLabel cpuRoll;
	private JLabel youWin;
	private JLabel cpuWins;
	private JButton endTurn;
	private JButton showMoves;
	private JButton endCpuTurn;
	private int randomPiece;
	private int randomDie;
	private boolean playersTurn = false;
	private boolean rolled = false;
	private boolean movable = false;
	private static BufferedImage one;
	private static JLabel diceOne;
	private static JLabel diceOne2;
	private static JLabel diceOne3;
	private static JLabel diceOne4;
	private static BufferedImage two;
	private static JLabel diceTwo;
	private static JLabel diceTwo2;
	private static JLabel diceTwo3;
	private static JLabel diceTwo4;
	private static BufferedImage three;
	private static JLabel diceThree;
	private static JLabel diceThree2;
	private static JLabel diceThree3;
	private static JLabel diceThree4;
	private static BufferedImage four;
	private static JLabel diceFour;
	private static JLabel diceFour2;
	private static JLabel diceFour3;
	private static JLabel diceFour4;
	private static BufferedImage five;
	private static JLabel diceFive;
	private static JLabel diceFive2;
	private static JLabel diceFive3;
	private static JLabel diceFive4;
	private static BufferedImage six;
	private static JLabel diceSix;
	private static JLabel diceSix2;
	private static JLabel diceSix3;
	private static JLabel diceSix4;
	private static JLabel board;
	private static int[] dice = {0,0};
	private static JLabel[] clickables = new JLabel[15];//15 clickable circles
	private static int[][] spaces = new int[26][2];//24 triangles, 2nd dimension contains one element 
													//	which holds the current colour of pieces on it
													//and one element which holds the number of pieces
	private static int[] middleBar = new int[2];//blue and red middle bar for when pieces are knocked off the board
	private static JLabel[] pieces = new JLabel[30];//array of 30 pieces
	private static JLabel clickedPiece;//current clicked piece
	private static JLabel[] options = new JLabel[4];//array of 4 options, in reality i only need 2 but just in case
	private static int[] diceCombos = new int[4];

	
	Backgammon() {
		//SET THE DEFAULT PANEL THINGS
		this.addMouseListener(this);
		this.setLayout(null);
		this.setBackground(new Color(10,108,3));//pool table green
		
		
		gameSetup();//setup the game
		playerTurn();//start with playerturn

		
		}
	public void playerTurn() {
		playersTurn=true;//
		this.add(yourTurn);//add yourturn label
		this.add(roll);//add roll button
		drawClickables(); //add clickables onto screen
		redrawPieces(); 
	}
	public void computerTurn() {
		
		playersTurn = false;
		add(cpuTurn);//add "cpu turn" label
		rollDice();
		displayDice();
		add(cpuRoll);//add cpu roll button
		showMoves.setBounds(685,180,180,50);//add showmoves button
		add(showMoves);
	}
	public void drawClickables() {//draw clickables
		int clickableCounter = 0;
		for(int i=0;i<15;i++) {
			clickables[i].setBounds(0,0,0,0);//clear clickables
		}
		if(middleBar[0]!=0) {//if middlebar is not empty put a clickable there
			clickables[0].setBounds(307,255,40,40);
		}
		else {//else because if middlebar is not empty, no other pieces are allowed to be moved
			for(int i=0;i<24;i++) {//for all spaces
				if(spaces[i][0] ==1) {//if spaces colour = 1(red)
					if(i <= 11) {//if in bottom row
						clickables[clickableCounter].setBounds(570-47*i,400,40,40);
						add(clickables[clickableCounter]);
						clickableCounter++;
					}
					if(i>=12) {//if in top row
						clickables[clickableCounter].setBounds(53+47*(i-12),50,40,40);
						add(clickables[clickableCounter]);
						clickableCounter++;
					}
				}
			}
		}
	}
	public void gameSetup() {//called only once, to create the Array of pieces and fill the board with pieces
		yourTurn = new JLabel("YOUR TURN");//create and initialize yourturn label
		yourTurn.setFont(new Font("Verdana", Font.PLAIN, 28));
		yourTurn.setBounds(690,40,180,50);
		cpuTurn = new JLabel("CPU TURN");//create and initialize cputurn label
		cpuTurn.setFont(new Font("Verdana", Font.PLAIN, 28));
		cpuTurn.setBounds(700,40,180,50);
		cpuRoll = new JLabel("Computer Rolled: ");//create and initialize cpurolled label
		cpuRoll.setFont(new Font("Verdana",Font.PLAIN, 14));
		cpuRoll.setBounds(680,80,180,50);
		showMoves = new JButton("Show CPU Moves");//create and initialize showMoves button
		showMoves.addActionListener(this);
		showMoves.setBounds(685,180,180,50);
		roll = new JButton("Roll the dice!");//create and initialize roll button
		roll.addActionListener(this);
		roll.setBounds(685,120,180,50);
		endTurn = new JButton("END TURN");////create and initialize endturn button
		endTurn.addActionListener(this);
		endTurn.setBounds(685,180,180,50);
		endCpuTurn = new JButton("END CPU TURN");//create and initialize endcputurn button
		endCpuTurn.addActionListener(this);
		endCpuTurn.setBounds(685,180,180,50);
		youWin = new JLabel("YOU WIN"); //create and initialize youwin label
		youWin.setFont(new Font("Verdana", Font.PLAIN, 28));
		youWin.setBounds(690,40,180,50);
		cpuWins = new JLabel("CPU WINS");//create and initialize cpuwins label
		cpuWins.setFont(new Font("Verdana", Font.PLAIN, 28));
		cpuWins.setBounds(690,40,180,50);
		
		try {
			redPiece =ImageIO.read(new File("backgammonFiles/redPiece.png"));//load red piece file
		} catch (IOException e) {
		}
		try {
			bluePiece =ImageIO.read(new File("backgammonFiles/bluePiece.png"));//load blue piece file
		} catch (IOException e) {
		}
		try {
			clickable =ImageIO.read(new File("backgammonFiles/clickable.png"));//load clickable file
		} catch (IOException e) {
		}
		try {
			option =ImageIO.read(new File("backgammonFiles/option.png")); //load option file
		} catch (IOException e) {
		}
		for(int i = 0;i<15;i++) {
			pieces[i] = new JLabel(new ImageIcon(redPiece)); //initialize all red pieces
		}
		for(int i = 15;i<30;i++) {
			pieces[i] = new JLabel(new ImageIcon(bluePiece)); //initialize all blue pieces
		}
		for(int i = 0;i<15;i++) {
			clickables[i] = new JLabel(new ImageIcon(clickable)); //initialize all clickables
		}
		for(int i = 0;i<4;i++) {
			options[i] = new JLabel(new ImageIcon(option)); //initialize all options
		}
		//Format: spaces[x][y] X is the space number. there are 24 possible spaces.
		//Y is the colour or the number of pieces. if y is 0, the value is the colour.
		//if y is 1, the value is the number of pieces
		spaces[0][0] = 1;//first triangle is controlled by red
		spaces[0][1] = 2;//2 pieces on first triangle
		spaces[11][0] = 1;
		spaces[11][1] = 5;
		spaces[16][0] = 1;
		spaces[16][1] = 3;
		spaces[18][0] = 1;
		spaces[18][1] = 5;
		spaces[24][0] = 1;
		
		spaces[23][0] = 2;//last triangle controlled by blue(computer)
		spaces[23][1] = 2;
		spaces[12][0] = 2;
		spaces[12][1] = 5;
		spaces[7][0] = 2;
		spaces[7][1] = 3;
		spaces[5][0] = 2;
		spaces[5][1] = 5;
		spaces[25][0] = 2;
	}
	public void redrawPieces() {//redraw all pieces
		int bluePieceCount = 15;
		int redPieceCount = 0;
		for(int i=0;i<24;i++) {//through each triangle
			if(spaces[i][0] == 1) {//if space is red
				if(spaces[i][1] == 0) {//if there are no pieces on that space clear the colour
					spaces[i][0] = 0;//
				}
				for(int j=0;j<spaces[i][1];j++) {//for all pieces on that space
					if(i <= 11) {//bottom row
						pieces[redPieceCount].setBounds(570-47*i,400-30*j,40,40);
						add(pieces[redPieceCount]);
						redPieceCount++;
					}
					if(i>=12) {//top row
						pieces[redPieceCount].setBounds(53+47*(i-12),50+30*j,40,40);
						add(pieces[redPieceCount]);
						redPieceCount++;
					}
				}
			}
			if(spaces[i][0] == 2) {//if space is blue
				if(spaces[i][1] == 0) {
					spaces[i][0] = 0;
				}
				for(int j=0;j<spaces[i][1];j++) {//for all pieces on that space
					if(i <= 11) {//if bottom row
						pieces[bluePieceCount].setBounds(570-47*i,400-30*j,40,40);
						add(pieces[bluePieceCount]);
						bluePieceCount++;
					}
					if(i>=12) {//if top row
						if(bluePieceCount ==30) {
							bluePieceCount--;
						}
						pieces[bluePieceCount].setBounds(53+47*(i-12),50+30*j,40,40);
						add(pieces[bluePieceCount]);
						bluePieceCount++;
					}
				}
			}
		}
		int redCount= 0;
		for(int i=0;i<spaces[24][1];i++) {//if red piece has been moved off the baord
			pieces[redPieceCount].setBounds(630,50+redCount*19,40,40);
			add(pieces[redPieceCount]);
			redPieceCount++;
			redCount++;
		}
		int blueCount =0;
		for(int i=0;i<spaces[25][1];i++) {//if blue piece has been moved off the baord
			pieces[bluePieceCount].setBounds(630,400-blueCount*19,40,40);
			add(pieces[bluePieceCount]);
			bluePieceCount++;
			blueCount++;
		}
		if(redCount ==15) {//if 15 red pieces are off the board
			diceOne.setBounds(10000,10000,50,50);
			diceOne2.setBounds(10000,10000,50,50);
			diceOne3.setBounds(10000,10000,50,50);
			diceOne4.setBounds(10000,10000,50,50);
			diceTwo.setBounds(10000,10000,50,50);
			diceTwo2.setBounds(10000,10000,50,50);
			diceTwo3.setBounds(10000,10000,50,50);
			diceTwo4.setBounds(10000,10000,50,50);
			diceThree.setBounds(10000,10000,50,50);
			diceThree2.setBounds(10000,10000,50,50);
			diceThree3.setBounds(10000,10000,50,50);
			diceThree4.setBounds(10000,10000,50,50);
			diceFour.setBounds(10000,10000,50,50);
			diceFour2.setBounds(10000,10000,50,50);
			diceFour3.setBounds(10000,10000,50,50);
			diceFour4.setBounds(10000,10000,50,50);
			diceFive.setBounds(10000,10000,50,50);
			diceFive2.setBounds(10000,10000,50,50);
			diceFive3.setBounds(10000,10000,50,50);
			diceFive4.setBounds(10000,10000,50,50);
			diceSix.setBounds(10000,10000,50,50);
			diceSix2.setBounds(10000,10000,50,50);
			diceSix3.setBounds(10000,10000,50,50);
			diceSix4.setBounds(10000,10000,50,50);
			remove(yourTurn);
			remove(endTurn);
			add(youWin);
			MyGame.redraw();
		}
		if(blueCount ==15) {//if 15 blue pieces are off the board
			diceOne.setBounds(10000,10000,50,50);
			diceOne2.setBounds(10000,10000,50,50);
			diceOne3.setBounds(10000,10000,50,50);
			diceOne4.setBounds(10000,10000,50,50);
			diceTwo.setBounds(10000,10000,50,50);
			diceTwo2.setBounds(10000,10000,50,50);
			diceTwo3.setBounds(10000,10000,50,50);
			diceTwo4.setBounds(10000,10000,50,50);
			diceThree.setBounds(10000,10000,50,50);
			diceThree2.setBounds(10000,10000,50,50);
			diceThree3.setBounds(10000,10000,50,50);
			diceThree4.setBounds(10000,10000,50,50);
			diceFour.setBounds(10000,10000,50,50);
			diceFour2.setBounds(10000,10000,50,50);
			diceFour3.setBounds(10000,10000,50,50);
			diceFour4.setBounds(10000,10000,50,50);
			diceFive.setBounds(10000,10000,50,50);
			diceFive2.setBounds(10000,10000,50,50);
			diceFive3.setBounds(10000,10000,50,50);
			diceFive4.setBounds(10000,10000,50,50);
			diceSix.setBounds(10000,10000,50,50);
			diceSix2.setBounds(10000,10000,50,50);
			diceSix3.setBounds(10000,10000,50,50);
			diceSix4.setBounds(10000,10000,50,50);
			remove(cpuTurn);
			remove(endCpuTurn);
			add(cpuWins);
			MyGame.redraw();
		}
		for(int i=0;i<middleBar[0];i++) {//draw red pieces in middle bar
			pieces[redPieceCount].setBounds(307,255+17*i,40,40);
			add(pieces[redPieceCount]);
			redPieceCount++;
		}
		for(int i=0;i<middleBar[1];i++) {//draw blue pieces in middle bar
			pieces[bluePieceCount].setBounds(307,205-17*i,40,40);
			add(pieces[bluePieceCount]);
			bluePieceCount++;
		}
	}
	public void rollDice(){//roll 2 dice
		dice[0] = 0;
		dice[1] = 0;

		dice[0] = (int)(Math.random() * 6) + 1;
		dice[1] = (int)(Math.random() * 6) + 1;

}
	public void displayDice() {
		try { //import number one on dice
			one = ImageIO.read(new File("backgammonFiles/one.png"));
		} catch (IOException e1) {
		}
		try { //import number two on dice
			two = ImageIO.read(new File("backgammonFiles/two.png"));
		} catch (IOException e1) {
		}
		try { //import number three on dice
			three = ImageIO.read(new File("backgammonFiles/three.png"));
		} catch (IOException e1) {
		}
		try { //import number four on dice
			four = ImageIO.read(new File("backgammonFiles/four.png"));
		} catch (IOException e1) {
		}
		try { //import number five on dice
			five = ImageIO.read(new File("backgammonFiles/five.png"));
		} catch (IOException e1) {
		}
		try { //import number six on dice
			six = ImageIO.read(new File("backgammonFiles/six.png"));
		} catch (IOException e1) {
		}
		diceOne = new JLabel(new ImageIcon(one));
		diceOne2 = new JLabel(new ImageIcon(one));
		diceOne3 = new JLabel(new ImageIcon(one));
		diceOne4 = new JLabel(new ImageIcon(one));
		diceTwo = new JLabel(new ImageIcon(two));
		diceTwo2 = new JLabel(new ImageIcon(two));
		diceTwo3 = new JLabel(new ImageIcon(two));
		diceTwo4 = new JLabel(new ImageIcon(two));
		diceThree = new JLabel(new ImageIcon(three));
		diceThree2 = new JLabel(new ImageIcon(three));
		diceThree3 = new JLabel(new ImageIcon(three));
		diceThree4 = new JLabel(new ImageIcon(three));
		diceFour = new JLabel(new ImageIcon(four));
		diceFour2 = new JLabel(new ImageIcon(four));
		diceFour3 = new JLabel(new ImageIcon(four));
		diceFour4 = new JLabel(new ImageIcon(four));
		diceFive = new JLabel(new ImageIcon(five));
		diceFive2 = new JLabel(new ImageIcon(five));
		diceFive3 = new JLabel(new ImageIcon(five));
		diceFive4 = new JLabel(new ImageIcon(five));
		diceSix = new JLabel(new ImageIcon(six));
		diceSix2 = new JLabel(new ImageIcon(six));
		diceSix3 = new JLabel(new ImageIcon(six));
		diceSix4 = new JLabel(new ImageIcon(six));
		if(dice[0]==dice[1]) {//if dice are the same
			if(dice[0]==1){//if dice are both 1
				
				diceOne.setBounds(675, 120, 50, 50);
				this.add(diceOne);
				
				
				diceOne2.setBounds(725, 120, 50, 50);
				this.add(diceOne2);
				
				MyGame.redraw();
			}
			if(dice[0]==2){//if dice are both 2
				
				
				diceTwo.setBounds(675, 120, 50, 50);
				this.add(diceTwo);
				
				
				diceTwo2.setBounds(725, 120, 50, 50);
				this.add(diceTwo2);
				
				
				MyGame.redraw();
			}
			if(dice[0]==3) {//if dice are both 3
				
				
				diceThree.setBounds(675, 120, 50, 50);
				this.add(diceThree);
				
				
				diceThree2.setBounds(725, 120, 50, 50);
				this.add(diceThree2);
				
				

				MyGame.redraw();
			}
			if(dice[0]==4) {//if dice are both 4
				
				
				diceFour.setBounds(675, 120, 50, 50);
				this.add(diceFour);
				
				
				diceFour2.setBounds(725, 120, 50, 50);
				this.add(diceFour2);
				
				

				MyGame.redraw();
			}
			if(dice[0]==5) {//if dice are both 5
				
				
				diceFive.setBounds(675, 120, 50, 50);
				this.add(diceFive);
				
				
				diceFive2.setBounds(725, 120, 50, 50);
				this.add(diceFive2);
				

				MyGame.redraw();
			}
			if(dice[0]==6) {//if dice are both 6
				
				
				diceSix.setBounds(675, 120, 50, 50);
				this.add(diceSix);
				
				
				diceSix2.setBounds(725, 120, 50, 50);
				this.add(diceSix2);
				
				MyGame.redraw();
			}
			
		}

		else {//if dice arent the same
			for(int i = 0;i<2;i++) {//for each individual dice
				if(dice[i]==1) {//if that dice = 1
					
					try { //import number two on dice
						one = ImageIO.read(new File("backgammonFiles/one.png"));
					} catch (IOException e1) {
					}
					diceOne = new JLabel(new ImageIcon(one));
					diceOne.setBounds(675+50*i, 120, 50, 50);
					this.add(diceOne);
					
					MyGame.redraw();
				}
				if(dice[i]==2) {//if that dice = 2
					try { //import number two on dice
						two = ImageIO.read(new File("backgammonFiles/two.png"));
					} catch (IOException e1) {
					}
					diceTwo = new JLabel(new ImageIcon(two));
					diceTwo.setBounds(675+50*i, 120, 50, 50);
					this.add(diceTwo);
					MyGame.redraw();
				}
				if(dice[i]==3) {//if that dice = 3
					try { //import number three on dice
						three = ImageIO.read(new File("backgammonFiles/three.png"));
					} catch (IOException e1) {
					}
					diceThree = new JLabel(new ImageIcon(three));
					diceThree.setBounds(675+50*i, 120, 50, 50);
					this.add(diceThree);
					MyGame.redraw();
				}
				if(dice[i]==4) {//if that dice = 4
					try { //import number four on dice
						four = ImageIO.read(new File("backgammonFiles/four.png"));
					} catch (IOException e1) {
					}
					diceFour = new JLabel(new ImageIcon(four));
					diceFour.setBounds(675+50*i, 120, 50, 50);
					this.add(diceFour);
					MyGame.redraw();
				}
				if(dice[i]==5) {//if that dice = 5
					try { //import number five on dice
						five = ImageIO.read(new File("backgammonFiles/five.png"));
					} catch (IOException e1) {
					}
					diceFive = new JLabel(new ImageIcon(five));
					diceFive.setBounds(675+50*i, 120, 50, 50);
					this.add(diceFive);
					MyGame.redraw();
				}
				if(dice[i]==6) {//if that dice = 6
					try { //import number six on dice
						six = ImageIO.read(new File("backgammonFiles/six.png"));
					} catch (IOException e1) {
					}
					diceSix = new JLabel(new ImageIcon(six));
					diceSix.setBounds(675+50*i, 120, 50, 50);
					this.add(diceSix);
					MyGame.redraw();
				}

			}
		}
		}


	@Override
	public void actionPerformed(ActionEvent e){  
		if(e.getSource()==roll) {//if roll button pressed
			remove(roll);//remove roll
			rollDice();//roll and display the dice
			displayDice();
		}
		if(e.getSource()==endTurn) {//if end turn button pressed
			diceOne.setBounds(10000,10000,50,50);//remove all dice from visible screen
			diceOne2.setBounds(10000,10000,50,50);
			diceOne3.setBounds(10000,10000,50,50);
			diceOne4.setBounds(10000,10000,50,50);
			diceTwo.setBounds(10000,10000,50,50);
			diceTwo2.setBounds(10000,10000,50,50);
			diceTwo3.setBounds(10000,10000,50,50);
			diceTwo4.setBounds(10000,10000,50,50);
			diceThree.setBounds(10000,10000,50,50);
			diceThree2.setBounds(10000,10000,50,50);
			diceThree3.setBounds(10000,10000,50,50);
			diceThree4.setBounds(10000,10000,50,50);
			diceFour.setBounds(10000,10000,50,50);
			diceFour2.setBounds(10000,10000,50,50);
			diceFour3.setBounds(10000,10000,50,50);
			diceFour4.setBounds(10000,10000,50,50);
			diceFive.setBounds(10000,10000,50,50);
			diceFive2.setBounds(10000,10000,50,50);
			diceFive3.setBounds(10000,10000,50,50);
			diceFive4.setBounds(10000,10000,50,50);
			diceSix.setBounds(10000,10000,50,50);
			diceSix2.setBounds(10000,10000,50,50);
			diceSix3.setBounds(10000,10000,50,50);
			diceSix4.setBounds(10000,10000,50,50);
			for(int i=0;i<15;i++) {//remove all clickables
				clickables[i].setBounds(10000,10000,50,50);
			}
			remove(endTurn);//remove end turn and your turn
			remove(yourTurn);
			MyGame.redraw();
			computerTurn();
		}
		if(e.getSource()==endCpuTurn) {//if endCpuTurn button pressed
			diceOne.setBounds(10000,10000,50,50);//remove all dice
			diceOne2.setBounds(10000,10000,50,50);
			diceOne3.setBounds(10000,10000,50,50);
			diceOne4.setBounds(10000,10000,50,50);
			diceTwo.setBounds(10000,10000,50,50);
			diceTwo2.setBounds(10000,10000,50,50);
			diceTwo3.setBounds(10000,10000,50,50);
			diceTwo4.setBounds(10000,10000,50,50);
			diceThree.setBounds(10000,10000,50,50);
			diceThree2.setBounds(10000,10000,50,50);
			diceThree3.setBounds(10000,10000,50,50);
			diceThree4.setBounds(10000,10000,50,50);
			diceFour.setBounds(10000,10000,50,50);
			diceFour2.setBounds(10000,10000,50,50);
			diceFour3.setBounds(10000,10000,50,50);
			diceFour4.setBounds(10000,10000,50,50);
			diceFive.setBounds(10000,10000,50,50);
			diceFive2.setBounds(10000,10000,50,50);
			diceFive3.setBounds(10000,10000,50,50);
			diceFive4.setBounds(10000,10000,50,50);
			diceSix.setBounds(10000,10000,50,50);
			diceSix2.setBounds(10000,10000,50,50);
			diceSix3.setBounds(10000,10000,50,50);
			diceSix4.setBounds(10000,10000,50,50);
			remove(endCpuTurn);//remove cpus buttons and labels 
			remove(cpuRoll);
			remove(cpuTurn);
			MyGame.redraw();
			playerTurn();
		}
		if(e.getSource()==showMoves) {//if showmoves button pressed
			movable = true;//variable for if moves are even possible to prevent error.
			//if moves are not possible, and it enters the while loop on line 728, the dice will never become 0 and code will never end
			check:{//THIS CHECKS THAT THERE ARE POSSIBLE MOVES TO BE MADE BY THE COMPUTER.
			for(int i = 0;i<2;i++) {//check if movable is true.
				//check breaks when movable is true/false depending on what happpens
				if(middleBar[1]!=0) {//if there are pieces in the blue middle bar\
					//while there are pieces in the blue middle bar, they can only move pieces onto their first quadrant.
					if(middleBar[1] == 1) {//if there is only one piece in the blue middle bar
						if(dice[i]!=0&&spaces[24-dice[i]][0]==1&&spaces[24-dice[i]][1]==1) {//if the space that the cpu wants to go(24-the dice result[which has to be in the fourth quadrant]) to is red and has one piece on it, it's movable
							movable = true;//set movable true
							break check;
						}
						if(dice[i]!=0&&(spaces[24-dice[i]][0]==2||spaces[24-dice[i]][0]==0)){//if the space that the cpu wants to go(24-the dice result[which has to be in the fourth quadrant]) to is
							movable = true;//set movable true
							break check;
						}
						movable = false;//if either dice does not meet these conditions, the while loop will go on forever and code will break
					}
					if(middleBar[1]>1) {//if there is more than 1 piece in middle bar
						if(dice[0]==dice[1]) {//if dice are the same
							if(spaces[24-dice[0]][0] == 0||spaces[24-dice[0]][0] == 2||(spaces[24-dice[0]][0] == 1&&spaces[24-dice[0]][1] == 1)) {//if the desired space is either blue, neutral, or has 1 red piece on it
								movable = true;//set movable
								break check;
							}
							else {
								movable = false;//else its not movable, false
								redrawPieces();
								break check;
							}
						}
						else {//if dice are not equal
							for(int j=0;j<2;j++) {//for both dice
								if(spaces[24-dice[j]][0] == 0||spaces[24-dice[j]][0] == 2||(spaces[24-dice[j]][0] == 1&&spaces[24-dice[j]][1] == 1)) {//when desired space is movable
									middleBar[1]--;//subtract a piece from the middle bar
									if(spaces[24-dice[j]][0]==2||spaces[24-dice[j]][0]==0) {//if desired space was blue or neutral
										spaces[24-dice[j]][1]++;//add a piece onto that space
									}
									else {//if it was red with one piece on it
										middleBar[0]++;//add 1 to opposing middle bar
									}
									spaces[24-dice[j]][0] = 2;//set the desired space's colour to blue
									
								}
							}
							movable = false;//set movable to false because you already moved the pieces
							redrawPieces();
							break check;
						}
					}
					
				}
				else {//if there are no pieces in the middle bar
					boolean possible1 = false;
					boolean possible2 = false;
					for(int j=0;j<24;j++) {//for all spaces
						if(spaces[j][0]==2) {//if they contain a blue piece
							if(dice[0]!=0&&j-dice[0]>=0&&(spaces[j-dice[0]][0]==0||spaces[j-dice[0]][0]==2||spaces[j-dice[0]][1]==1&&spaces[j-dice[0]][0]==1)) {//check if 1st die works anywhere
								possible1 = true;
							}
							if(dice[1]!=0&&j-dice[1]>=0&&(spaces[j-dice[1]][0]==0||spaces[j-dice[1]][0]==2||spaces[j-dice[1]][1]==1&&spaces[j-dice[1]][0]==1)) {//check if 2nd die works anywhere
								possible2 = true;
							}
							if(possible1 == true &&possible2 == true) {//if both dice can be removed set movable and break
								movable = true;
								break check;
							}
							c:{//for checking if pieces are able to be moved off the board and scored
							for(int k=06;k<24;k++) {// if there are any pieces not in the bottom right quadrant
								if(spaces[k][0]==2) {
									break c;//break the c block
								}
							}
							
							if(dice[i]!=0&&j-dice[i]<0) {//if dice + any piece extends border, movable true and move pieces
								spaces[j][1]--;//remove 1 from the piece's original space
								if(spaces[j][1]==0) {//if original space becomes empty, set color to blank
									spaces[j][0]=0;
								}
								spaces[25][0]=2;
								spaces[25][1]++;//add 1 to the end bar
								dice[i]=0;//dice becomes cleared
								if(spaces[25][1]==15) {//if all 15 pieces are in end zone, break check
									movable = false;
									break check;
								}
								redrawPieces();
							}
							}
							redrawPieces();
						}
					}
					movable = false;
					if(dice[0]!=0 ||dice[1]!=0) {
						movable = true;
					}
				}
			}
			}
			MyGame.redraw();
			//above, we check if the pieces are movable. if they are indeed movable, run this loop
			while(movable==true&&(dice[0]!=0||dice[1]!=0)) {//while movable and dice aren't 0,
				randomPiece = 15+(int)(Math.random()*15);//find a random piece
				randomDie = (int)(Math.random()*2);//find a random die
				if(middleBar[1]!=0) {//if middlebar isnt empty
					
					if(dice[randomDie]!=0&&(spaces[24-dice[randomDie]][0]==0||spaces[24-dice[randomDie]][0]==2)) {//if the space that the random piece would move to is clear or blue
						middleBar[1]--;//remove 1 from middlebar
						spaces[24-dice[randomDie]][0]= 2;//
						spaces[24-dice[randomDie]][1]++;//add 1 to new bar
						dice[randomDie]=0;
					}
					if(dice[randomDie]!=0&&(spaces[24-dice[randomDie]][0]==1&&spaces[24-dice[randomDie]][1]==1) ){//if the space that the random piece would move to has 1 red piece
						middleBar[1]--;//remove 1 from middlebar
						middleBar[0]++;//add 1 to red middlebar
						spaces[24-dice[randomDie]][0]=2;
						dice[randomDie]=0;//clear dice
					}
					MyGame.redraw();
					redrawPieces();
				}
				else {//if middlebar is empty
					//below: if the piece is in the top row
					if(dice[randomDie]!=0&&(pieces[randomPiece].getY()==50||pieces[randomPiece].getY()==80||pieces[randomPiece].getY()==110||pieces[randomPiece].getY()==140||pieces[randomPiece].getY()==170||pieces[randomPiece].getY()==200||pieces[randomPiece].getY()==230||pieces[randomPiece].getY()==260||pieces[randomPiece].getY()==290||pieces[randomPiece].getY()==320)) {
						if(spaces[12+(pieces[randomPiece].getX()-53)/47-dice[randomDie]][0] == 2||spaces[12+(pieces[randomPiece].getX()-53)/47-dice[randomDie]][0] == 0) {//if random piece is moved a random number of spaces mentioned on a certain die and is moved onto a blue or neutral space
							spaces[12+(pieces[randomPiece].getX()-53)/47][1]--;//remove 1 piece from original spot
							spaces[12+(pieces[randomPiece].getX()-53)/47-dice[randomDie]][0]=2;
							spaces[12+(pieces[randomPiece].getX()-53)/47-dice[randomDie]][1]++;//add 1 to new spot
							
							if(spaces[12+(pieces[randomPiece].getX()-53)/47][1] == 0) {//if original spot is now empty, clear its color
								spaces[12+(pieces[randomPiece].getX()-53)/47][0]=0;
							}
							dice[randomDie]=0;//clear dice
						}
						else if(spaces[12+(pieces[randomPiece].getX()-53)/47-dice[randomDie]][0] == 1&&spaces[12+(pieces[randomPiece].getX()-53)/47-dice[randomDie]][1]==1) {//if random piece is moved a random number of spaces mentioned on a certain die and is moved onto a space with 1 red piece
							spaces[12+(pieces[randomPiece].getX()-53)/47][1]--;//remove 1 piece from original spot
							spaces[12+(pieces[randomPiece].getX()-53)/47-dice[randomDie]][0]=2; //set colour of new spot to red. piece amount does not change because the red piece that was there is now replaced by 1 blue piece
							middleBar[0]++;//add 1 to red middlebar
					
							if(spaces[12+(pieces[randomPiece].getX()-53)/47][1] == 0) {
								spaces[12+(pieces[randomPiece].getX()-53)/47][0]=0;
							}
							dice[randomDie]=0;
						}
						redrawPieces();
					}
					//below: if randompiece is in bottom row
					else if(pieces[randomPiece].getY()==400||pieces[randomPiece].getY()==370||pieces[randomPiece].getY()==340||pieces[randomPiece].getY()==310||pieces[randomPiece].getY()==280||pieces[randomPiece].getY()==250||pieces[randomPiece].getY()==220||pieces[randomPiece].getY()==190||pieces[randomPiece].getY()==160||pieces[randomPiece].getY()==130) {
						
						if((11-(pieces[randomPiece].getX()-53)/47-dice[randomDie]>=0)&&(spaces[11-(pieces[randomPiece].getX()-53)/47-dice[randomDie]][0] == 2||spaces[11-(pieces[randomPiece].getX()-53)/47-dice[randomDie]][0] == 0)) {//if random piece is moved a random number of spaces mentioned on a certain die and is moved onto a blue or neutral space
							spaces[11-(pieces[randomPiece].getX()-53)/47][1]--;
							spaces[11-(pieces[randomPiece].getX()-53)/47-dice[randomDie]][0]=2;   //all code here is the same as above except with spaces[11-] instead of spaces[12+]
							spaces[11-(pieces[randomPiece].getX()-53)/47-dice[randomDie]][1]++;
							
							if(spaces[11-(pieces[randomPiece].getX()-53)/47][1] == 0) {
								spaces[11-(pieces[randomPiece].getX()-53)/47][0]=0;
							}
							dice[randomDie]=0;
						}
						else if((11-(pieces[randomPiece].getX()-53)/47-dice[randomDie]>=0)&&spaces[11-(pieces[randomPiece].getX()-53)/47-dice[randomDie]][0] == 1&&spaces[11-(pieces[randomPiece].getX()-53)/47-dice[randomDie]][1] == 1){//if random piece is moved a random number of spaces mentioned on a certain die and is moved onto a space with 1 red piece
							spaces[11-(pieces[randomPiece].getX()-53)/47][1]--;
							spaces[11-(pieces[randomPiece].getX()-53)/47-dice[randomDie]][0]=2;
							middleBar[0]++;

							if(spaces[11-(pieces[randomPiece].getX()-53)/47][1] == 0) {
								spaces[11-(pieces[randomPiece].getX()-53)/47][0]=0;
							}
							dice[randomDie]=0;
						}
						
						
					}
					redrawPieces();
					MyGame.redraw();
				}
				
			}	
			
				remove(showMoves);
				add(endCpuTurn);
} 
}

	@Override
	public void paintComponent(Graphics g) {//paint the components
		super.paintComponent(g);
		try {//import board 
			backgammonBoard = ImageIO.read(new File("backgammonFiles/backgammon.png"));
		} catch (IOException e) {
		}
		g.drawImage(backgammonBoard,30, 40, 600, 412, null);//draw board
		g.drawRect(675, 40, 200, 412);//draw and colour sidebar
		g.setColor(Color.white);
		g.fillRect(675, 40, 200, 412);
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	public void showOptions(JLabel j) {//shows the options for where the player's piece could go

		for(int i=0;i<4;i++) {//clear all options off the screen
			options[i].setBounds(10000,10000,40,150);
		}
		
		
		if(middleBar[0]!= 0) {//if middlebar is occupied
			for(int i = 0;i<2;i++) {//for both dice
				if(dice[i]>0&&(spaces[dice[i]-1][0]==0||spaces[dice[i]-1][0]==1||(spaces[dice[i]-1][0]==2&&spaces[dice[i]-1][1]==1))&&dice[i]<=12) {//if the space indicated on the current dice is red, clear, or has only 1 blue piece
					options[i].setBounds(623-47*dice[i], 290,40,150);//add an option there
				}
				if(dice[i]>0&&(spaces[dice[i]-1][0]==0||spaces[dice[i]-1][0]==1||(spaces[dice[i]-1][0]==2&&spaces[dice[i]-1][1]==1))&&dice[i]>12) {//this part is arbitrary, from an old version of the code
					options[i].setBounds(53+47*(dice[i]-13), 50,40,150);
				}
			}
			
			MyGame.redraw();
			redrawPieces();
			if(options[0].getX()==10000&&options[1].getX()==10000&&options[2].getX()==10000&&options[3].getX()==10000) {//if no options are available, no moves can be made, and the player will have the option to end the turn
				add(endTurn);
			}
		}
		
		else{//if middlebar not occupied
	 		for(int i = 0;i<2;i++) {//for both dice
				if(dice[i] != 0 && j.getY() == 400 && j.getX()-47*dice[i] >=53) {//if clickedpiece is on the bottom row and the potential option is on the bottom row
					if(spaces[11-(((j.getX()-53)/47)-dice[i])][0] !=2 ||(spaces[11-(((j.getX()-53)/47)-dice[i])][0]==2&&spaces[11-(((j.getX()-53)/47)-dice[i])][1]==1)) {//if moving there is possible
						options[i].setBounds(j.getX()-47*dice[i], 290,40,150);//add an option there
						add(options[i]);
						MyGame.redraw();
				}}
				if(dice[i] != 0 && j.getY() == 400 && j.getX()-47*dice[i] <53&&(6+47*(dice[i]-((j.getX()-53)/47))<=570)) {//if clickedpiece is on the bottom row and the potential option is on the top row
					if(spaces[11-(((j.getX()-53)/47)-dice[i])][0] != 2||(spaces[11-(((j.getX()-53)/47)-dice[i])][0]==2&&spaces[11-(((j.getX()-53)/47)-dice[i])][1]==1)) {//if moving there is possible
						options[i].setBounds(6+47*(dice[i]-((j.getX()-53)/47)), 50, 40, 150);//add an option there
						add(options[i]);
						MyGame.redraw();
					}
				}
				if(dice[i] != 0 && j.getY() == 50&&dice[i]*47+j.getX()<=570) {//if clicked piece is on the top row
					if(spaces[12+(((j.getX()-53)/47)+dice[i])][0] !=2 ||(spaces[12+(((j.getX()-53)/47)+dice[i])][0]==2&&spaces[12+(((j.getX()-53)/47)+dice[i])][1]==1)){//if moving to the option's location is possible
						options[i].setBounds(j.getX()+47*dice[i],50,40,150);//add an option there
						add(options[i]);
						MyGame.redraw();
					}
				}
			}
		
		}
		thing:{//checks if pieces can be moved off the board if all pieces are in the top right quadrant
			for(int i=0;i<18;i++) {//for spaces 1-18, if any spaces are red, break
				if(spaces[i][0]==1) {
					break thing;
				}
			}
			
			
		for(int i=0;i<2;i++) {//for both dice
			if(12+(clickedPiece.getX()-53)/47+dice[i]>=24) {//if clickedpiece+dice goes past the edge of the board
				if(options[0].getX()== 10000) {//add an option to move it off the board
					options[0].setBounds(630, 50, 40, 150);
				}
				else if(options[1].getX()== 10000) {
					options[1].setBounds(630, 50, 40, 150);
				}
			}
		}
		}
		a:{//checks if no moves are possible
		if(middleBar[0]==0) {//if middlebar empty
			for(int i=0;i<2;i++) {//for both dice
				for(int j1=0;j1<24;j1++) {//for all spaces
					b:{
						for(int k=0;k<18;k++) {//for first 18 spaces
							if(spaces[j1][0]==1) {//if colour = red, break b
								break b;
							}
						}
						//if no pieces are there, break a
						break a;
					}
					if(dice[i]!=0&&(dice[i]!=0&&spaces[j1][0]==1&&((spaces[j1+dice[i]][0]==0||spaces[j1+dice[i]][0]==1)||(spaces[j1+dice[i]][0]==2&&spaces[j1+dice[i]][1]==1)))) {// if any piece at all works, break check
						break a;
					}
				}
			}
			add(endTurn);//add endturn if no moves are possible
			MyGame.redraw();
		}
		
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		// TODO Auto-generated method stub
		//for all clickables
		for(int i = 0; i<15;i++) {//if the mouse click is within the x and y coordinates of any of the clickables
			if(e.getX()>clickables[i].getX()&&
					e.getX()<clickables[i].getX()+40&&
					e.getY()>clickables[i].getY()&&
					e.getY()<clickables[i].getY()+40) {
				for(int j=0;j<15;j++) {//find the clicked piece
					if(pieces[j].getX()==clickables[i].getX()&&pieces[j].getY()==clickables[i].getY() && e.getX()>pieces[j].getX()&&
					e.getX()<pieces[j].getX()+40&&
					e.getY()>pieces[j].getY()&&
					e.getY()<pieces[j].getY()+40) {
						clickedPiece = pieces[j];
						
						
					}
					
				}
				showOptions(clickables[i]);//showoptions for that clickable
				
			}
		}
		for(int i =0;i<4;i++) {//when an option is clicked
			int spacesMoved = 0;//set variable "spacesmoved" to 0
			if(middleBar[0]!=0&&e.getX()>options[i].getX()&&//if middlebar is not occupied and click is within the boundaries of an option
					e.getX()<options[i].getX()+40&&
					e.getY()>options[i].getY()&&
					e.getY()<options[i].getY()+150) {
				if(options[i].getY()==290&&spaces[11-((options[i].getX()-53)/47)][0]==2&&(spaces[11-((options[i].getX()-53)/47)][1]==1)){//if in bottom row and  1 blue piece in desired space
					middleBar[0]--;//remove 1 from red middlebar
					middleBar[1]++;//add 1 to blue middlebar
					spaces[11-(options[i].getX()-53)/47][0]=1;
					spacesMoved = 12-(options[i].getX()-53)/47;//set spacesmoved
				}
				else if(options[i].getY()==290&&(spaces[11-((options[i].getX()-53)/47)][0]==1||spaces[11-((options[i].getX()-53)/47)][0]==0)) {// if desired space is red or clear
					middleBar[0]--;//remove 1 from middlebar
					
					spaces[11-(options[i].getX()-53)/47][0]=1;
					spaces[11-(options[i].getX()-53)/47][1]++;//add 1 to desired space
					spacesMoved = 12-(options[i].getX()-53)/47;//set spacesmoved
				}
				if(options[i].getY()==50&&(spaces[12+((options[i].getX()-53)/47)][0]==2&&spaces[12+((options[i].getX()-53)/47)][1]==1)) {//same as line 954 but for top row
					middleBar[0]--;
					middleBar[1]++;
					spaces[12+(options[i].getX()-53)/47][0] =1;
					spacesMoved = 13+(options[i].getX()-53)/47;
				}
				else if(options[i].getY()==50&&(spaces[12+((options[i].getX()-53)/47)][0]==0||spaces[12+((options[i].getX()-53)/47)][0]==1)) {//same as 960 but for top row
					middleBar[0]--;
					spaces[12+(options[i].getX()-53)/47][0] =1;
					spaces[12+(options[i].getX()-53)/47][1]++;
					spacesMoved = 13+(options[i].getX()-53)/47;
				}
				MyGame.redraw();
				if(dice[0] == dice[1]&&spacesMoved!=0) {//if any spaces have moved
					for(int k=1;k>-1;k--) {
						spacesMoved-=dice[k];//clear dice for the number of spaces moved

						dice[k] =0;
						if(spacesMoved == 0) {
							break;
						}
					}
				}
				
				if(dice[0]!=dice[1]&&spacesMoved!=0) {//
					for(int l=0;l<2;l++) {
						if(dice[l]==spacesMoved) {//clear dice for spaces moved again
							spacesMoved-=dice[l];
							dice[l] = 0;
							break;
						}
					}

				}
				for(int j=0;j<4;j++) {
					options[j].setBounds(10000,10000,40,150);//un-display all options
				}
				
				drawClickables();//redraw clickables
				redrawPieces();
				
				if(dice[0]==0&&dice[1]==0) {//if all dice are 0, end turn and clear dice
					add(endTurn);
					diceOne.setBounds(10000,10000,50,50);
					diceOne2.setBounds(10000,10000,50,50);
					diceOne3.setBounds(10000,10000,50,50);
					diceOne4.setBounds(10000,10000,50,50);
					diceTwo.setBounds(10000,10000,50,50);
					diceTwo2.setBounds(10000,10000,50,50);
					diceTwo3.setBounds(10000,10000,50,50);
					diceTwo4.setBounds(10000,10000,50,50);
					diceThree.setBounds(10000,10000,50,50);
					diceThree2.setBounds(10000,10000,50,50);
					diceThree3.setBounds(10000,10000,50,50);
					diceThree4.setBounds(10000,10000,50,50);
					diceFour.setBounds(10000,10000,50,50);
					diceFour2.setBounds(10000,10000,50,50);
					diceFour3.setBounds(10000,10000,50,50);
					diceFour4.setBounds(10000,10000,50,50);
					diceFive.setBounds(10000,10000,50,50);
					diceFive2.setBounds(10000,10000,50,50);
					diceFive3.setBounds(10000,10000,50,50);
					diceFive4.setBounds(10000,10000,50,50);
					diceSix.setBounds(10000,10000,50,50);
					diceSix2.setBounds(10000,10000,50,50);
					diceSix3.setBounds(10000,10000,50,50);
					diceSix4.setBounds(10000,10000,50,50);
					
					
				}
				MyGame.redraw();
			}
			else if(e.getX()>options[i].getX()&&//else if same thing but middlebar is empty
					e.getX()<options[i].getX()+40&&
					e.getY()>options[i].getY()&&
					e.getY()<options[i].getY()+150) {
				
				if(options[i].getY()==290&&spaces[11-((options[i].getX()-53)/47)][0]==2&&(spaces[11-((options[i].getX()-53)/47)][1]==1)){// basically same as line 954 except you remove 1 from the original space since you're no longer removing from the middlebar
					 middleBar[1]++;
					 spaces[11-((options[i].getX()-53)/47)][0] = 1;
					 spaces[11-((clickedPiece.getX()-53)/47)][1]--;
					 if(spaces[11-((clickedPiece.getX()-53)/47)][1]==0) {
							spaces[11-((clickedPiece.getX()-53)/47)][0] = 0;
						}
					 spacesMoved = (11-((options[i].getX()-53)/47))-(11-((clickedPiece.getX()-53)/47));
				}
				else if(options[i].getY()==290&&(spaces[11-((options[i].getX()-53)/47)][0]==1||spaces[11-((options[i].getX()-53)/47)][0]==0)) {//if selected option was bottom row and currently red or empty
					spaces[11-((options[i].getX()-53)/47)][0]=1;
					spaces[11-((options[i].getX()-53)/47)][1]++;
					if(clickedPiece.getY()==400) {
						spaces[11-((clickedPiece.getX()-53)/47)][1]--;
						if(spaces[11-((clickedPiece.getX()-53)/47)][1]==0) {
							spaces[11-((clickedPiece.getX()-53)/47)][0] = 0;
						}
						spacesMoved = (11-((options[i].getX()-53)/47))-(11-((clickedPiece.getX()-53)/47));
					}
				}
				if(options[i].getX()==650) {//if selected option was indicating to move pieces off the board
					spaces[24][1]++;//add 1 to tjhe off the board array
					spaces[12+(clickedPiece.getX()-53)/47][1]--;//remove 1 from original space
					if(spaces[12+((clickedPiece.getX()-53)/47)][1]==0) {
						spaces[12+((clickedPiece.getX()-53)/47)][0] = 0;
					}
				}
				if(options[i].getY()==50&&(spaces[12+((options[i].getX()-53)/47)][0]==2&&spaces[12+((options[i].getX()-53)/47)][1]==1)){//if top row and 1 blue piece
					spaces[12+((options[i].getX()-53)/47)][0]=1;//set colour to red
					middleBar[1]++;//add 1 to blue middlebar
					if(clickedPiece.getY()==400) {//if clickedpiece was bottom row
						spaces[11-((clickedPiece.getX()-53)/47)][1]--;//remove from original
						if(spaces[11-((clickedPiece.getX()-53)/47)][1]==0) {
							spaces[11-((clickedPiece.getX()-53)/47)][0] = 0;
						}
						spacesMoved = (12+((options[i].getX()-53)/47))-(11-((clickedPiece.getX()-53)/47));
					}
					if(clickedPiece.getY()==50) {//if clickedpiece was top ow
						spaces[12+((clickedPiece.getX())-53)/47][1]--;//same except spaces[12+ instead of spaces[11-
						if(spaces[12+((clickedPiece.getX()-53)/47)][1]==0) {
							spaces[12+((clickedPiece.getX()-53)/47)][0] = 0;
						}
						spacesMoved = (12+((options[i].getX()-53)/47))-(12+((clickedPiece.getX()-53)/47));
					}
				}
				else if(options[i].getY()==50&&(spaces[12+((options[i].getX()-53)/47)][0]==0||spaces[12+((options[i].getX()-53)/47)][0]==1)) {//if selected option was top row and red or clear
					spaces[12+((options[i].getX()-53)/47)][0]=1;
					spaces[12+((options[i].getX()-53)/47)][1]++;//add 1 to desired space
					if(clickedPiece.getY()==400) {//exact same principle as line 1074 and 1081
						spaces[11-((clickedPiece.getX()-53)/47)][1]--;
						if(spaces[11-((clickedPiece.getX()-53)/47)][1]==0) {
							spaces[11-((clickedPiece.getX()-53)/47)][0] = 0;
						}
						spacesMoved = (12+((options[i].getX()-53)/47))-(11-((clickedPiece.getX()-53)/47));
					}
					if(clickedPiece.getY()==50) {
						spaces[12+((clickedPiece.getX())-53)/47][1]--;
						if(spaces[12+((clickedPiece.getX()-53)/47)][1]==0) {
							spaces[12+((clickedPiece.getX()-53)/47)][0] = 0;
						}
						spacesMoved = (12+((options[i].getX()-53)/47))-(12+((clickedPiece.getX()-53)/47));
					}
				}
				if(dice[0] == dice[1]&&spacesMoved!=0) {//if dice are equal and spacesmoved arent 0
					for(int k=0;k<2;k++) {//clear dice and spacesmoved
						spacesMoved-=dice[k];

						dice[k] =0;
						if(spacesMoved <= 0) {
							break;
						}
					}
				}
				cleardice:{//checks for which dice to clear depending on the spaces moved
				if(dice[0]!=dice[1]&&spacesMoved!=0) {
					for(int l=0;l<2;l++) {
						if(dice[l]==spacesMoved) {//if either dice is identical to the spaces moved, clear that die and spacesmoved
							spacesMoved-=dice[l];
							dice[l] = 0;
							break cleardice;
						}
					}
					if(dice[0]<=dice[1]) {
						dice[1] = 0;
						break cleardice;
					}
					if(dice[1]<=dice[0]) {
						dice[0] = 0;
						break cleardice;
					}
				}
				}
				for(int j=0;j<4;j++) {
					options[j].setBounds(10000,10000,40,150);//un-display all options
				}
				
				drawClickables();
				redrawPieces();
				
				if(dice[0]==0&&dice[1]==0) {
					add(endTurn);
					diceOne.setBounds(10000,10000,50,50);
					diceOne2.setBounds(10000,10000,50,50);
					diceOne3.setBounds(10000,10000,50,50);
					diceOne4.setBounds(10000,10000,50,50);
					diceTwo.setBounds(10000,10000,50,50);
					diceTwo2.setBounds(10000,10000,50,50);
					diceTwo3.setBounds(10000,10000,50,50);
					diceTwo4.setBounds(10000,10000,50,50);
					diceThree.setBounds(10000,10000,50,50);
					diceThree2.setBounds(10000,10000,50,50);
					diceThree3.setBounds(10000,10000,50,50);
					diceThree4.setBounds(10000,10000,50,50);
					diceFour.setBounds(10000,10000,50,50);
					diceFour2.setBounds(10000,10000,50,50);
					diceFour3.setBounds(10000,10000,50,50);
					diceFour4.setBounds(10000,10000,50,50);
					diceFive.setBounds(10000,10000,50,50);
					diceFive2.setBounds(10000,10000,50,50);
					diceFive3.setBounds(10000,10000,50,50);
					diceFive4.setBounds(10000,10000,50,50);
					diceSix.setBounds(10000,10000,50,50);
					diceSix2.setBounds(10000,10000,50,50);
					diceSix3.setBounds(10000,10000,50,50);
					diceSix4.setBounds(10000,10000,50,50);
					
					
				}
				MyGame.redraw();
				
				
			}
			
		}
		
		
	
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}