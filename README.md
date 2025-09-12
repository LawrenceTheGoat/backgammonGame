This is the classic board game backgammon. I made it using purely Java in the winter of 2023. It was fun piecing together concepts into each component of the game, such as using nested arrays to store the number of pieces and color of those pieces on each space, and implementing User Experience (UX) features like the green boxes (options) that display the possible locations that the piece that the user just clicked can be moved to. Identifying the source of certain bugs was a challenge, and it was quite tedious trying to get them out of the way. There are probably still some bugs that have yet to be fixed.

# HOW TO RUN: 

## VSCODE:
Clone this repo --> set directory to backgammonGame --> paste "javac -d bin src/backgammon/*.java" in terminal --> paste "java -cp bin backgammon.MyGame" in terminal

# Backgammon Rules

## Objective:
Move all your checkers into your home board and then bear them off before your opponent does.

## Setup:
Each player starts with 15 checkers placed on the board in a standard formation.

## Turns:
Players take turns rolling two dice and moving their checkers accordingly.

## Movement:

Each die represents a separate move.

You can move one checker the total of both dice or two checkers individually according to each die roll.

Checkers move forward to lower-numbered points (from 24 to 1 for one player, and vice versa for the other).

## Valid Moves:

A checker can only move to an open point (a point that is not occupied by two or more opposing checkers).

If a point has only one opposing checker, you can “hit” it, sending that checker to the bar.

## Hitting and Entering from the Bar:

When a checker is hit, it is placed on the bar.

Before making any other move, a player must enter all checkers from the bar onto the opponent’s home board by rolling dice corresponding to an open point.

## Bearing Off:

Once all your checkers are in your home board, you can start bearing them off the board by rolling dice corresponding to the points they occupy.

If no checker is on the exact point rolled, you must make a legal move using a checker on a higher-numbered point.

## Winning:
The first player to bear off all 15 checkers wins.
