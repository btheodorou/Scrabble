/*
*Brandon Theodorou
*P6 - Big Coding Project
*Scrabble (Adapted Rules)
*Tested with a practice game and a shorter game (by removing some tiles from
the bag). Works as long as the coordinate entry format is followed by players

*Note on the Code:
The submitted code works, but it accesses a file (a word bank) saved on a 
computer (lines 264-266) in order to create a "dictionary" to judge if words 
are viable/legal. As such, it will not run on a different computer unless that 
file is downloaded and those lines of code are updated. If you want to run the 
code, changing the “realWord” method (line 272) to always return true and 
changing the “createDictionary” method call at the start of the main method 
(line 819) to just creating a blank list will return all words as true, allowing 
everything to be played as a valid word and the game to progress.
*/


package scrabble;

import java.awt.Font;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Collections;
import javax.swing.JTextArea;

public class Scrabble 
{
    public static int[][] createMultipliers()
    {
        int[][] scorer = new int[15][15];
        
        /*
        1 = Double Letter
        2 = Double Word
        3 = Triple Letter
        4 = Triple Word
        */
        scorer[0][3] = 1;
        scorer[0][11] = 1;
        scorer[2][6] = 1;
        scorer[2][8] = 1;
        scorer[3][0] = 1;
        scorer[3][7] = 1;
        scorer[3][14] = 1;
        scorer[6][2] = 1;
        scorer[6][6] = 1;
        scorer[6][8] = 1;
        scorer[6][12] = 1;
        scorer[7][3] = 1;
        scorer[7][11] = 1;
        scorer[8][2] = 1;
        scorer[8][6] = 1;
        scorer[8][8] = 1;
        scorer[8][12] = 1;
        scorer[11][0] = 1;
        scorer[11][7] = 1;
        scorer[11][14] = 1;
        scorer[12][6] = 1;
        scorer[12][8] = 1;
        scorer[14][3] = 1;
        scorer[14][11] = 1; 
        scorer[1][1] = 2;
        scorer[2][2] = 2;
        scorer[3][3] = 2;
        scorer[4][4] = 2;
        scorer[4][10] = 2;
        scorer[3][11] = 2;
        scorer[2][12] = 2;
        scorer[1][13] = 2;
        scorer[7][7] = 2;
        scorer[13][1] = 2;
        scorer[12][2] = 2;
        scorer[11][3] = 2;
        scorer[10][4] = 2;
        scorer[10][10] = 2;
        scorer[11][11] = 2;
        scorer[12][12] = 2;
        scorer[13][13] = 2;
        scorer[1][5] = 3;
        scorer[1][9] = 3;
        scorer[5][1] = 3;
        scorer[5][5] = 3;
        scorer[5][9] = 3;
        scorer[5][13] = 3;
        scorer[9][1] = 3;
        scorer[9][5] = 3;
        scorer[9][9] = 3;
        scorer[9][13] = 3;
        scorer[13][5] = 3;
        scorer[13][9] = 3;
        scorer[14][3] = 3;
        scorer[14][11] = 3;
        scorer[0][0] = 4;
        scorer[0][7] = 4;
        scorer[0][14] = 4;
        scorer[7][0] = 4;
        scorer[7][14] = 4;
        scorer[14][0] = 4;
        scorer[14][7] = 4;
        scorer[14][14] = 4;
        
        return scorer;
    }
    
    public static int coinFlip(String player1, String player2)
    {
        Random flipper = new Random();
        int firstTurn = 0;
        int coinValue = flipper.nextInt(2);
        String coinFlip = JOptionPane.showInputDialog("We will flip a coin to "
                + "decide who goes first. " + player1 + ", Heads or Tails? "
                + "(h/t)");
        String coinResponse = "";
        if(coinValue == 0)
        {
            coinResponse += "It was heads. ";
            if(coinFlip.equalsIgnoreCase("h"))
            {    
                coinResponse += (player1 + " will go first.");
                firstTurn = 1;
            }
            else
            {
                coinResponse += (player2 + " will go first.");
                firstTurn = 2;
            }
        }
        else
        {
            coinResponse += "It was tails. ";
            if(coinFlip.equalsIgnoreCase("t") == true)
            {
                coinResponse += (player1 + " will go first.");
                firstTurn = 1;
            }
            else
            {
                coinResponse += (player2 + " will go first.");
                firstTurn = 2;
            }
        }
        JOptionPane.showMessageDialog(null, coinResponse);
        
        return firstTurn;
    }
    
    public static ArrayList<String> createBag()
    {
        ArrayList<String> tiles = new ArrayList();
        
        tiles.add ("A");
        tiles.add ("A");
        tiles.add ("A");
        tiles.add ("A");
        tiles.add ("A");
        tiles.add ("A");
        tiles.add ("A");
        tiles.add ("A");
        tiles.add ("A"); //Nine A Tiles
        tiles.add ("B");
        tiles.add ("B"); // Two B Tiles
        tiles.add ("C");
        tiles.add ("C"); //Two C Tiles
        tiles.add ("D");
        tiles.add ("D");
        tiles.add ("D");
        tiles.add ("D"); //Four D Tiles
        tiles.add ("E");
        tiles.add ("E");
        tiles.add ("E");
        tiles.add ("E");
        tiles.add ("E");
        tiles.add ("E");
        tiles.add ("E");
        tiles.add ("E");
        tiles.add ("E");
        tiles.add ("E");
        tiles.add ("E");
        tiles.add ("E"); //Twelve E Tiles
        tiles.add ("F");
        tiles.add ("F"); //Two F Tiles
        tiles.add ("G");
        tiles.add ("G");
        tiles.add ("G");//Three G Tiles
        tiles.add ("H");
        tiles.add ("H"); // Two H Tiles
        tiles.add ("I");
        tiles.add ("I");
        tiles.add ("I");
        tiles.add ("I");
        tiles.add ("I");
        tiles.add ("I");
        tiles.add ("I");
        tiles.add ("I");
        tiles.add ("I"); // Nine I Tiles
        tiles.add ("J"); // One J Tile
        tiles.add ("K"); //One K Tile
        tiles.add ("L"); 
        tiles.add ("L"); 
        tiles.add ("L"); 
        tiles.add ("L"); // Four L Tiles
        tiles.add ("M"); 
        tiles.add ("M"); //Two M Tiles
        tiles.add ("N");
        tiles.add ("N");
        tiles.add ("N");
        tiles.add ("N");
        tiles.add ("N");
        tiles.add ("N"); // Six N Tiles
        tiles.add ("O");
        tiles.add ("O");
        tiles.add ("O");
        tiles.add ("O");
        tiles.add ("O");
        tiles.add ("O");
        tiles.add ("O");
        tiles.add ("O"); //Eight O Tiles
        tiles.add ("P");
        tiles.add ("P"); // Two P Tiles
        tiles.add ("Q"); // One Q Tile
        tiles.add ("R");
        tiles.add ("R");
        tiles.add ("R");
        tiles.add ("R");
        tiles.add ("R");
        tiles.add ("R"); // Six R Tiles
        tiles.add ("S");
        tiles.add ("S");
        tiles.add ("S");
        tiles.add ("S"); //Four S Tiles
        tiles.add ("T");
        tiles.add ("T");
        tiles.add ("T");
        tiles.add ("T");
        tiles.add ("T");
        tiles.add ("T"); // Six T Tiles
        tiles.add ("U");
        tiles.add ("U");
        tiles.add ("U");
        tiles.add ("U"); //Four U Tiles
        tiles.add ("V"); 
        tiles.add ("V"); // Two V Tiles
        tiles.add ("W"); 
        tiles.add ("W"); //Two W Tiles
        tiles.add ("X"); //One X Tile
        tiles.add ("Y");
        tiles.add ("Y"); // Two Y Tiles
        tiles.add ("Z"); // One Z Tile
        
        return tiles;
    }
    
    public static List<String> createDictionary() throws IOException
    {
        List<String> list = Files.readAllLines(Paths.get
                ("/Users/brandontheodorou/Downloads/WWFDict.txt"), 
                StandardCharsets.UTF_8); //utf tells how it is encoded
        
        return list;
    }
    
    public static boolean realWord(String wordPlayed, List<String> list)
    { 
        wordPlayed = wordPlayed.toLowerCase();
        int index = Collections.binarySearch (list, wordPlayed);
        
        if (index >= 0)
            return true;  
        else 
            return false;
    }
    
    public static String printBoard(String[][] board, int[][] multipliers)
    {
        DecimalFormat df = new DecimalFormat("00");
        
        String output = "     00  01  02  03  04  05  "
                + "06  07  08  09  10  11  12  13  14\n";
        
        for(int r = 0; r < board.length; r++)
        {
            output += (df.format(r) + "||");
            for(int c = 0; c < board.length; c++)
            {
                if(board[r][c] != null)
                    output += (" " + board[r][c] + " |");
                else if(multipliers[r][c] > 0)
                    output += " " + multipliers[r][c]+ " |";
                else
                    output += "   |";  
            }
            output += "|\n";
            
        }
        
        output += "Key: 1 = DL, 2 = DW, 3 = TL, 4 = TW\n\n";
        
        return output;
    }
    
    public static String printHand(String[] hand)
    {
        String output = "Your Hand Is ||";
        for(int i = 0; i < hand.length; i++)
        {
            output += " " + hand[i] + " |";
        }
        output += "|\n\n";
        
        return output;
    }
    
    public static boolean validPlacement(int row, int col, String word, 
            int direction, String[] hand, String[][] board)
    {
        boolean valid = true;
        int intersection = 0; 
        
        if(direction == 1)
        {
            if((col + word.length() - 1) >= board.length)
            {
                valid = false;
                return valid;
            }
            
            if((col != 0) && (col + word.length() - 1) != 14)
            { 
                if((board[row][col - 1] != null) ||
                        (board[row][col + word.length()] != null))
                {
                    valid = false;
                    return valid;
                }
            }
            
            for(int i = 0; i < word.length(); i++)
            {
                if((word.substring(i, i+1)).equalsIgnoreCase
                    (board[row][col + i]))
                {
                    intersection++;
                }
                
                else
                {
                    if(board[row][col + i] != null)
                    {
                        valid = false;
                        return valid;
                    }
                    
                    else
                    {
                        int n = 0;
                        boolean inHand = false;
                        boolean endHand = false;
                        while(endHand == false)
                        {
                            if(hand[n].equalsIgnoreCase(word.substring(i, i+1)))
                            {
                                inHand = true;
                                hand[n] = "0";
                                endHand = true;
                            }

                            if(n == (hand.length - 1))
                            {
                                endHand = true;
                            }
                            n++;   
                        } 

                        if(inHand == false)
                        {
                            valid = false;
                            return valid;
                        }
                    }
                }    
            }
        }
        
        else if(direction == 2)
        {
            if((row + word.length() - 1) >= board.length)
            {
                valid = false;
                return valid;
            }
            
            if((row != 0) && ((row + word.length() - 1) != 14))
            { 
                if((board[row - 1][col] != null) ||
                        (board[row + word.length()][col] != null))
                {
                    valid = false;
                    return valid;
                }
            }
            
            for(int i = 0; i < word.length(); i++)
            {
                if((word.substring(i, i+1)).equalsIgnoreCase
                    (board[row + i][col]))
                {
                    intersection++;
                }
                
                else
                {
                    if(board[row + i][col] != null)
                    {
                        valid = false;
                        return valid;
                    }
                    
                    else
                    {
                        int n = 0;
                        boolean inHand = false;
                        boolean endHand = false;
                        while(endHand == false)
                        {
                            if(hand[n].equalsIgnoreCase(word.substring(i, i+1)))
                            {
                                inHand = true;
                                hand[n] = "0";
                                endHand = true;
                            }

                            if(n == (hand.length - 1))
                            {
                                endHand = true;
                            }
                            n++;   
                        } 

                        if(inHand == false)
                        {
                            valid = false;
                            return valid;
                        }
                    }
                }    
            }
        }
        
        if(intersection == 0)
        {
            valid = false;
        }
        
        return valid;
    }

    public static boolean validFirstMove(int row, int col, String word,
            int direction, String[] hand, String[][] board)
    {
        boolean valid = true; 
        
        for(int i = 0; i < word.length(); i++)
        {
            int n = 0;
            boolean inHand = false;
            boolean endHand = false;
            while(endHand == false)
            {
                if(hand[n].equalsIgnoreCase(word.substring(i, i+1)))
                {
                    inHand = true;
                    hand[n] = "0";
                    endHand = true;
                }

                if(n == (hand.length - 1))
                {
                    endHand = true;
                }
                n++;   
            } 

            if(inHand == false)
            {
                valid = false;
                return valid;
            }
        }
        
        if(direction == 1)
        { 
            if(row != 7)
            {
                valid = false;
                return valid;
            }
            
            else if(!((7 >= col) && (7 <= (col + word.length()))))
            {
                valid = false;
                return valid;
            }
        }
        
        else if(direction == 2)
        {    
            if(col != 7)
            {
                valid = false;
                return valid;
            }
            
            else if(!((7 >= row) && (7 <= (row + word.length()))))
            {
                valid = false;
                return valid;
            }
        }
        
        return valid;
    }
    
    public static int scoreTurn(int row, int col, String word, int direction,
            String[][] board, int[][] multipliers)
    {
        int turnScore = 0;
        int wordScore = 1;
        int letterScore = 0;           
        
        if(direction == 1)
        {  
            for(int i = 0; i < word.length(); i++)
            {
                if(word.substring(i, i+1).equalsIgnoreCase("A"))
                    letterScore = 1;
                else if(word.substring(i, i+1).equalsIgnoreCase("B"))
                    letterScore = 3;
                else if(word.substring(i, i+1).equalsIgnoreCase("C"))
                    letterScore = 3;
                else if(word.substring(i, i+1).equalsIgnoreCase("D"))
                    letterScore = 2;
                else if(word.substring(i, i+1).equalsIgnoreCase("E"))
                    letterScore = 1;
                else if(word.substring(i, i+1).equalsIgnoreCase("F"))
                    letterScore = 4;
                else if(word.substring(i, i+1).equalsIgnoreCase("G"))
                    letterScore = 2;
                else if(word.substring(i, i+1).equalsIgnoreCase("H"))
                    letterScore = 4;
                else if(word.substring(i, i+1).equalsIgnoreCase("I"))
                    letterScore = 1;
                else if(word.substring(i, i+1).equalsIgnoreCase("J"))
                    letterScore = 8;
                else if(word.substring(i, i+1).equalsIgnoreCase("K"))
                    letterScore = 5;
                else if(word.substring(i, i+1).equalsIgnoreCase("L"))
                    letterScore = 1;
                else if(word.substring(i, i+1).equalsIgnoreCase("M"))
                    letterScore = 3;
                else if(word.substring(i, i+1).equalsIgnoreCase("N"))
                    letterScore = 1;
                else if(word.substring(i, i+1).equalsIgnoreCase("O"))
                    letterScore = 1;
                else if(word.substring(i, i+1).equalsIgnoreCase("P"))
                    letterScore = 3;
                else if(word.substring(i, i+1).equalsIgnoreCase("Q"))
                    letterScore = 10;
                else if(word.substring(i, i+1).equalsIgnoreCase("R"))
                    letterScore = 1;
                else if(word.substring(i, i+1).equalsIgnoreCase("S"))
                    letterScore = 1;
                else if(word.substring(i, i+1).equalsIgnoreCase("T"))
                    letterScore = 1;
                else if(word.substring(i, i+1).equalsIgnoreCase("U"))
                    letterScore = 1;
                else if(word.substring(i, i+1).equalsIgnoreCase("V"))
                    letterScore = 4;
                else if(word.substring(i, i+1).equalsIgnoreCase("W"))
                    letterScore = 4;
                else if(word.substring(i, i+1).equalsIgnoreCase("X"))
                    letterScore = 8;
                else if(word.substring(i, i+1).equalsIgnoreCase("Y"))
                    letterScore = 4;
                else
                    letterScore = 10;
                
                if(((word.substring(i, i+1)).equalsIgnoreCase
                    (board[row][col + i])))
                {
                    turnScore += letterScore;
                }
                else
                {
                    if(multipliers[row][col + i] == 1)
                    {
                        turnScore += (2 * letterScore);
                    }
                    else if(multipliers[row][col + i] == 2)
                    {
                        wordScore *= 2;
                        turnScore += letterScore;
                    }
                    else if(multipliers[row][col + i] == 3)
                    {
                        turnScore += (3 * letterScore);
                    }
                    else if(multipliers[row][col + i] == 4)
                    {
                        wordScore *= 3;
                        turnScore += letterScore;
                    }
                    else
                    {
                        turnScore += letterScore;
                    }   
                }    
            }
        }
        else if(direction == 2)
        {  
            for(int i = 0; i < word.length(); i++)
            {
                if(word.substring(i, i+1).equalsIgnoreCase("A"))
                    letterScore = 1;
                else if(word.substring(i, i+1).equalsIgnoreCase("B"))
                    letterScore = 3;
                else if(word.substring(i, i+1).equalsIgnoreCase("C"))
                    letterScore = 3;
                else if(word.substring(i, i+1).equalsIgnoreCase("D"))
                    letterScore = 2;
                else if(word.substring(i, i+1).equalsIgnoreCase("E"))
                    letterScore = 1;
                else if(word.substring(i, i+1).equalsIgnoreCase("F"))
                    letterScore = 4;
                else if(word.substring(i, i+1).equalsIgnoreCase("G"))
                    letterScore = 2;
                else if(word.substring(i, i+1).equalsIgnoreCase("H"))
                    letterScore = 4;
                else if(word.substring(i, i+1).equalsIgnoreCase("I"))
                    letterScore = 1;
                else if(word.substring(i, i+1).equalsIgnoreCase("J"))
                    letterScore = 8;
                else if(word.substring(i, i+1).equalsIgnoreCase("K"))
                    letterScore = 5;
                else if(word.substring(i, i+1).equalsIgnoreCase("L"))
                    letterScore = 1;
                else if(word.substring(i, i+1).equalsIgnoreCase("M"))
                    letterScore = 3;
                else if(word.substring(i, i+1).equalsIgnoreCase("N"))
                    letterScore = 1;
                else if(word.substring(i, i+1).equalsIgnoreCase("O"))
                    letterScore = 1;
                else if(word.substring(i, i+1).equalsIgnoreCase("P"))
                    letterScore = 3;
                else if(word.substring(i, i+1).equalsIgnoreCase("Q"))
                    letterScore = 10;
                else if(word.substring(i, i+1).equalsIgnoreCase("R"))
                    letterScore = 1;
                else if(word.substring(i, i+1).equalsIgnoreCase("S"))
                    letterScore = 1;
                else if(word.substring(i, i+1).equalsIgnoreCase("T"))
                    letterScore = 1;
                else if(word.substring(i, i+1).equalsIgnoreCase("U"))
                    letterScore = 1;
                else if(word.substring(i, i+1).equalsIgnoreCase("V"))
                    letterScore = 4;
                else if(word.substring(i, i+1).equalsIgnoreCase("W"))
                    letterScore = 4;
                else if(word.substring(i, i+1).equalsIgnoreCase("X"))
                    letterScore = 8;
                else if(word.substring(i, i+1).equalsIgnoreCase("Y"))
                    letterScore = 4;
                else
                    letterScore = 10;
                
                if(((word.substring(i, i+1)).equalsIgnoreCase
                    (board[row + i][col])))
                {
                    turnScore += letterScore;
                }
                else
                {
                    if(multipliers[row + i][col] == 1)
                    {
                        turnScore += (2 * letterScore);
                    }
                    else if(multipliers[row + i][col] == 2)
                    {
                        wordScore *= 2;
                        turnScore += letterScore;
                    }
                    else if(multipliers[row + i][col] == 3)
                    {
                        turnScore += (3 * letterScore);
                    }
                    else if(multipliers[row + i][col] == 4)
                    {
                        wordScore *= 3;
                        turnScore += letterScore;
                    }
                    else
                    {
                        turnScore += letterScore;
                    }   
                }    
            }
        }        
        turnScore *= wordScore;
        return turnScore;
    }
    
    public static boolean gameOver(ArrayList<String> bag, String[] hand)
    {
        boolean done = false;
        boolean handEmpty = true;
        
        if(bag.size() == 0)
        {
            for(int i = 0; i < hand.length; i++)
            {
                if(hand[i] != " ")
                {
                    handEmpty = false;
                }
            }
            
            if(handEmpty == true)
            {
                done = true;
            }
        }
        
        return done;
    }

    public static int scoreHand(String[] hand)
    {
        int handScore = 0;
        
        for(int i = 0; i < hand.length; i++)
        {
            if(hand[i].equalsIgnoreCase("A"))
                handScore += 1;
            else if(hand[i].equalsIgnoreCase("B"))
                handScore += 3;
            else if(hand[i].equalsIgnoreCase("C"))
                handScore += 3;
            else if(hand[i].equalsIgnoreCase("D"))
                handScore += 2;
            else if(hand[i].equalsIgnoreCase("E"))
                handScore += 1;
            else if(hand[i].equalsIgnoreCase("F"))
                handScore += 4;
            else if(hand[i].equalsIgnoreCase("G"))
                handScore += 2;
            else if(hand[i].equalsIgnoreCase("H"))
                handScore += 4;
            else if(hand[i].equalsIgnoreCase("I"))
                handScore += 1;
            else if(hand[i].equalsIgnoreCase("J"))
                handScore += 8;
            else if(hand[i].equalsIgnoreCase("K"))
                handScore += 5;
            else if(hand[i].equalsIgnoreCase("L"))
                handScore += 1;
            else if(hand[i].equalsIgnoreCase("M"))
                handScore += 3;
            else if(hand[i].equalsIgnoreCase("N"))
                handScore += 1;
            else if(hand[i].equalsIgnoreCase("O"))
                handScore += 1;
            else if(hand[i].equalsIgnoreCase("P"))
                handScore += 3;
            else if(hand[i].equalsIgnoreCase("Q"))
                handScore += 10;
            else if(hand[i].equalsIgnoreCase("R"))
                handScore += 1;
            else if(hand[i].equalsIgnoreCase("S"))
                handScore += 1;
            else if(hand[i].equalsIgnoreCase("T"))
                handScore += 1;
            else if(hand[i].equalsIgnoreCase("U"))
                handScore += 1;
            else if(hand[i].equalsIgnoreCase("V"))
                handScore += 4;
            else if(hand[i].equalsIgnoreCase("W"))
                handScore += 4;
            else if(hand[i].equalsIgnoreCase("X"))
                handScore += 8;
            else if(hand[i].equalsIgnoreCase("Y"))
                handScore += 4;
            else if(hand[i].equalsIgnoreCase("Z"))
                handScore += 10;
        }
        
        return handScore;
    }
    
    public static void main(String[] args) throws IOException 
    {        
        JTextArea printer = new JTextArea(25, 25);
        printer.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        Random rando = new Random();
        String[][] board = new String[15][15];
        int[][] multipliers = createMultipliers();
        String[] hand1 = new String[7];
        String[] hand2 = new String[7];
        ArrayList<String> letters = createBag();
        List<String> dictionary = createDictionary();
        int round = 1;
        int score1 = 0;
        int score2 = 0;
        int direction = 0;
        int turnScore = 0;
        int winner = 0;
        
        //initializing the two hands
        for(int i = 0; i < hand1.length; i++)
        {
            int choice = rando.nextInt(letters.size());
            hand1[i] = letters.get(choice);
            letters.remove(choice);
        }
        for(int i = 0; i < hand2.length; i++)
        {
            int choice = rando.nextInt(letters.size());
            hand2[i] = letters.get(choice);
            letters.remove(choice);
        }
        
        JOptionPane.showMessageDialog(null, "Adapted Scrabble Rules: \n-Same "
                + "process as normal scrabble\n-The first word must touch the "
                + "center space\n-There is no exchanging of tiles from the bag"
                + " on one's turn\n-You can only make one word per turn\n-This "
                + "word must intersect with (use) at least one letter on the "
                + "board\n-There are no 'bingos' for using all your tiles\n"
                + "-Follow instructions to play words");
        
        String player1 = JOptionPane.showInputDialog
                ("Player 1, what is your name?");
        String player2 = JOptionPane.showInputDialog
                ("Player 2, what is your name?");
        
        int firstTurn = coinFlip(player1, player2);

        if(firstTurn == 1)
        {
            String turnOutput = ("Turn " + round + ":\n" + player1 + ": " + 
                    score1 + "   " + player2 + ": " + score2 + "\nLetters "
                    + "Remaining: " + letters.size() + "\n\n" + printBoard
                    (board, multipliers) + printHand(hand1));

            //deciding word
            printer.setText(turnOutput + player1 + ", what word would you like "
                    + "to play?");
            String newWord = JOptionPane.showInputDialog(null, printer);
            boolean wordEntry = false;
            while(wordEntry == false)
            {
                if(newWord.startsWith(" "))
                {
                    printer.setText(turnOutput + "Please enter the word "
                            + "without a space at the front");
                    newWord = JOptionPane.showInputDialog(null, printer);
                }
                else if(newWord == null || newWord.equals(""))
                {
                    printer.setText(turnOutput + "Please enter a word");
                    newWord = JOptionPane.showInputDialog(null, printer);
                }
                else
                {
                    wordEntry = true;
                }
            }
            
            //deciding starting coordinates
            boolean coor = false;
            boolean format = false;
            int row = 0;
            int col = 0;
            printer.setText(turnOutput + "Where will the first letter of " + 
                    newWord + " be played? (Print row #,column #)");
            String coordinates = JOptionPane.showInputDialog(null, printer);
            int comma = coordinates.indexOf(",");
            while(coor == false)
            {
                if(comma < 1 || (coordinates.indexOf(" ") >= 0))
                {
                    printer.setText(turnOutput + "Invalid entry format. Please "
                            + "type coordinates of first letter using the "
                            + "format row #,column #");
                    coordinates = JOptionPane.showInputDialog(null, printer);
                    comma = coordinates.indexOf(",");
                }
                else
                {
                    format = true;
                }
                if(format == true)
                {
                    row = Integer.parseInt(coordinates.split(",")[0]);
                    col = Integer.parseInt(coordinates.split(",")[1]);
                    if(row < 0 || row > 14 || col < 0 || col > 14)
                    {
                        printer.setText(turnOutput + "Invalid starting point. "
                                + "Please enter a location on the board using "
                                + "the format row #,column #)");
                        coordinates = JOptionPane.showInputDialog
                                (null, printer);
                        comma = coordinates.indexOf(",");
                        format = false;
                    }
                    else
                    {
                        coor = true;
                    }
                }
            }

            //deciding direction    
            printer.setText(turnOutput + "Do you want to play '" + newWord + 
                    "' starting at row " + row + " amd column " + col + 
                    " down or to the right? (d/r)");
            String rOrD = JOptionPane.showInputDialog(null, printer);
            while(!((rOrD.equalsIgnoreCase("r")) || rOrD.equalsIgnoreCase("d")))
            {
                printer.setText(turnOutput + "Invalid direction. Enter 'r' "
                        + "for right or 'd' for down");
                rOrD = JOptionPane.showInputDialog(null, printer);
            }
            if(rOrD.equalsIgnoreCase("r"))
                direction = 1;
            else
                direction = 2;

            //checking the dictionary and tile placement
            String[] hand = hand1.clone();
            boolean legalWord = realWord(newWord, dictionary);
            boolean validPlay = validFirstMove
                    (row, col, newWord, direction, hand, board);

            //player1 turn
            if(legalWord == false)
            {
                printer.setText(turnOutput + "Not a valid word. "
                        + "You lose your turn. Please pass the computer to "
                        + player2 + ".");
                JOptionPane.showMessageDialog(null, printer);
            }
            else if(validPlay == false)
            {
                printer.setText(turnOutput + "Not a valid tile placement. "
                        + "You lose your turn. Please pass the computer to "
                        + player2 + ".");
                JOptionPane.showMessageDialog(null, printer);            
            }

            //completing a valid turn
            else
            {
                turnScore = scoreTurn
                        (row, col, newWord, direction, board, multipliers);
                score1 += turnScore;
                if(direction == 1)
                {  
                    for(int i = 0; i < newWord.length(); i++)
                    {
                        if(!((newWord.substring(i, i+1)).equalsIgnoreCase
                            (board[row][col + i])))
                        {
                            int n = 0;
                            boolean endSearch = false;
                            while(endSearch == false)
                            {

                                if(newWord.substring(i, i+1).equalsIgnoreCase
                                        (hand1[n]))
                                {
                                    board[row][col + i] = hand1[n];
                                    hand1[n] = " ";
                                    endSearch = true;
                                }
                                n++;
                            }
                        }    
                    }
                }
                else if(direction == 2)
                {  
                    for(int i = 0; i < newWord.length(); i++)
                    {
                        if(!((newWord.substring(i, i+1)).equalsIgnoreCase
                            (board[row + i][col])))
                        {
                            int n = 0;
                            boolean endSearch = false;
                            while(endSearch == false)
                            {
                                if(hand1[n].equalsIgnoreCase
                                        (newWord.substring(i, i+1)))
                                {
                                    board[row + i][col] = hand1[n];
                                    hand1[n] = " ";
                                    endSearch = true;
                                }
                                n++;
                            }
                        }    
                    }
                }

                //draw new tiles
                for(int i = 0; i < hand1.length; i++)
                {
                    if(letters.size() > 0)
                    {
                        if(hand1[i].equals(" "))
                        {
                            int choice = rando.nextInt(letters.size());
                            hand1[i] = letters.get(choice);
                            letters.remove(choice);
                        }
                    }
                }

                //summarize turn and pass computer
                printer.setText("Turn " + round + ":\n" + player1 + ": " + 
                        score1 + "   " + player2 + ": " + score2 + "\nLetters "
                        + "Remaining: " + letters.size() + "\n\n" + printBoard
                        (board, multipliers) + printHand(hand1) + "You scored " 
                        + turnScore + " points by" + " playing '" + newWord + 
                        "' Please pass the computer to " + player2 + ".");
                JOptionPane.showMessageDialog(null, printer);
            }

            round++;
            
            
            
            //setting the basic game data for player2
            turnOutput = ("Turn " + round + ":\n" + player1 + ": " + score1 + 
                    "   " + player2 + ": " + score2 + "\nLetters Remaining: " + 
                    letters.size() + "\n\n" + printBoard(board, multipliers) + 
                    printHand(hand2));

            //deciding word
            printer.setText(turnOutput + player2 + ", what word would you like"
                    + " to play?");
            newWord = JOptionPane.showInputDialog(null, printer);
            wordEntry = false;
            while(wordEntry == false)
            {
                if(newWord.startsWith(" "))
                {
                    printer.setText(turnOutput + "Please enter the word "
                            + "without a space at the front");
                    newWord = JOptionPane.showInputDialog(null, printer);
                }
                else if(newWord == null || newWord.equals(""))
                {
                    printer.setText(turnOutput + "Please enter a word");
                    newWord = JOptionPane.showInputDialog(null, printer);
                }
                else
                {
                    wordEntry = true;
                }
            }

            //deciding starting coordinates
            coor = false;
            format = false;
            row = 0;
            col = 0;
            printer.setText(turnOutput + "Where will the first letter of " + 
                    newWord + " be played? (Print row #,column #)");
            coordinates = JOptionPane.showInputDialog(null, printer);
            comma = coordinates.indexOf(",");
            while(coor == false)
            {
                if(comma < 1 || (coordinates.indexOf(" ") >= 0))
                {
                    printer.setText(turnOutput + "Invalid entry format. Please "
                            + "type coordinates of first letter using the "
                            + "format row #,column #");
                    coordinates = JOptionPane.showInputDialog(null, printer);
                    comma = coordinates.indexOf(",");
                }
                else
                {
                    format = true;
                }
                if(format == true)
                {
                    row = Integer.parseInt(coordinates.split(",")[0]);
                    col = Integer.parseInt(coordinates.split(",")[1]);
                    if(row < 0 || row > 14 || col < 0 || col > 14)
                    {
                        printer.setText(turnOutput + "Invalid starting point. "
                                + "Please enter a location on the board using "
                                + "the format row #,column #)");
                        coordinates = JOptionPane.showInputDialog
                                (null, printer);
                        comma = coordinates.indexOf(",");
                        format = false;
                    }
                    else
                    {
                        coor = true;
                    }
                }
            }

            //deciding direction    
            printer.setText(turnOutput + "Do you want to play '" + newWord + 
                    "' starting at row " + row + " amd column " + col + 
                    " down or to the right? (d/r)");
            rOrD = JOptionPane.showInputDialog(null, printer);
            while(!((rOrD.equalsIgnoreCase("r")) || rOrD.equalsIgnoreCase("d")))
            {
                printer.setText(turnOutput + "Invalid direction. Enter 'r' "
                        + "for right or 'd' for down");
                rOrD = JOptionPane.showInputDialog(null, printer);
            }
            if(rOrD.equalsIgnoreCase("r"))
                direction = 1;
            else
                direction = 2;

            //checking the dictionary and tile placement
            hand = hand2.clone();
            legalWord = realWord(newWord, dictionary);
            validPlay = validPlacement
                    (row, col, newWord, direction, hand, board);       

            //player2 turn
            if(legalWord == false)
            {
                printer.setText(turnOutput + "Not a valid word. "
                        + "You lose your turn. Please pass the computer to "
                        + player1 + ".");
                JOptionPane.showMessageDialog(null, printer);
            }
            else if(validPlay == false)
            {
                printer.setText(turnOutput + "Not a valid tile placement. "
                        + "You lose your turn. Please pass the computer to "
                        + player1 + ".");
                JOptionPane.showMessageDialog(null, printer);            
            }

            //completing a valid turn
            else
            {
                turnScore = scoreTurn
                        (row, col, newWord, direction, board, multipliers);
                score2 += turnScore;
                if(direction == 1)
                {  
                    for(int i = 0; i < newWord.length(); i++)
                    {
                        if(!((newWord.substring(i, i+1)).equalsIgnoreCase
                            (board[row][col + i])))
                        {
                            int n = 0;
                            boolean endSearch = false;
                            while(endSearch == false)
                            {

                                if(newWord.substring(i, i+1).equalsIgnoreCase
                                        (hand2[n]))
                                {
                                    board[row][col + i] = hand2[n];
                                    hand2[n] = " ";
                                    endSearch = true;
                                }
                                n++;
                            }
                        }    
                    }
                }
                else if(direction == 2)
                {  
                    for(int i = 0; i < newWord.length(); i++)
                    {
                        if(!((newWord.substring(i, i+1)).equalsIgnoreCase
                            (board[row + i][col])))
                        {
                            int n = 0;
                            boolean endSearch = false;
                            while(endSearch == false)
                            {
                                if(hand2[n].equalsIgnoreCase
                                        (newWord.substring(i, i+1)))
                                {
                                    board[row + i][col] = hand2[n];
                                    hand2[n] = " ";
                                    endSearch = true;
                                }
                                n++;
                            }
                        }    
                    }
                }

                //draw new tiles
                for(int i = 0; i < hand2.length; i++)
                {
                    if(letters.size() > 0)
                    {
                        if(hand2[i].equals(" "))
                        {
                            int choice = rando.nextInt(letters.size());
                            hand2[i] = letters.get(choice);
                            letters.remove(choice);
                        }
                    }
                }

                //summarize turn and pass computer
                printer.setText("Turn " + round + ":\n" + player1 + ": " + 
                        score1 + "   " + player2 + ": " + score2 + "\nLetters "
                        + "Remaining: " + letters.size() + "\n\n" + printBoard
                        (board, multipliers) + printHand(hand2) + "You scored " 
                        + turnScore + " points by" + " playing '" + newWord + 
                        "' Please pass the computer to " + player1 + ".");
                JOptionPane.showMessageDialog(null, printer);
            }

            round++;   
        }
        else
        {
        //setting the basic game data for player2
            String turnOutput = ("Turn " + round + ":\n" + player1 + ": " + 
                    score1 + "   " + player2 + ": " + score2 + "\nLetters "
                    + "Remaining: " + letters.size() + "\n\n" + printBoard
                    (board, multipliers) + printHand(hand2));

            //deciding word
            printer.setText(turnOutput + player2 + ", what word would you like "
                    + "to play?");
            String newWord = JOptionPane.showInputDialog(null, printer);
            boolean wordEntry = false;
            while(wordEntry == false)
            {
                if(newWord.startsWith(" "))
                {
                    printer.setText(turnOutput + "Please enter the word "
                            + "without a space at the front");
                    newWord = JOptionPane.showInputDialog(null, printer);
                }
                else if(newWord == null || newWord.equals(""))
                {
                    printer.setText(turnOutput + "Please enter a word");
                    newWord = JOptionPane.showInputDialog(null, printer);
                }
                else
                {
                    wordEntry = true;
                }
            }
            
            //deciding starting coordinates
            boolean coor = false;
            boolean format = false;
            int row = 0;
            int col = 0;
            printer.setText(turnOutput + "Where will the first letter of " + 
                    newWord + " be played? (Print row #,column #)");
            String coordinates = JOptionPane.showInputDialog(null, printer);
            int comma = coordinates.indexOf(",");
            while(coor == false)
            {
                if(comma < 1 || (coordinates.indexOf(" ") >= 0))
                {
                    printer.setText(turnOutput + "Invalid entry format. Please "
                            + "type coordinates of first letter using the "
                            + "format row #,column #");
                    coordinates = JOptionPane.showInputDialog(null, printer);
                    comma = coordinates.indexOf(",");
                }
                else
                {
                    format = true;
                }
                if(format == true)
                {
                    row = Integer.parseInt(coordinates.split(",")[0]);
                    col = Integer.parseInt(coordinates.split(",")[1]);
                    if(row < 0 || row > 14 || col < 0 || col > 14)
                    {
                        printer.setText(turnOutput + "Invalid starting point. "
                                + "Please enter a location on the board using "
                                + "the format row #,column #)");
                        coordinates = JOptionPane.showInputDialog
                                (null, printer);
                        comma = coordinates.indexOf(",");
                        format = false;
                    }
                    else
                    {
                        coor = true;
                    }
                }
            }

            //deciding direction    
            printer.setText(turnOutput + "Do you want to play '" + newWord + 
                    "' " + "starting at row " + row + " amd column " + col + 
                    " down or" + " to the right? (d/r)");
            String rOrD = JOptionPane.showInputDialog(null, printer);
            while(!((rOrD.equalsIgnoreCase("r")) || rOrD.equalsIgnoreCase("d")))
            {
                printer.setText(turnOutput + "Invalid direction. Enter 'r' "
                        + "for right or 'd' for down");
                rOrD = JOptionPane.showInputDialog(null, printer);
            }
            if(rOrD.equalsIgnoreCase("r"))
                direction = 1;
            else
                direction = 2;

            //checking the dictionary and tile placement
            String [] hand = hand2.clone();
            boolean legalWord = realWord(newWord, dictionary);
            boolean validPlay = validFirstMove
                    (row, col, newWord, direction, hand, board);       

            //player2 turn
            if(legalWord == false)
            {
                printer.setText(turnOutput + "Not a valid word. "
                        + "You lose your turn. Please pass the computer to "
                        + player1 + ".");
                JOptionPane.showMessageDialog(null, printer);
            }
            else if(validPlay == false)
            {
                printer.setText(turnOutput + "Not a valid tile placement. "
                        + "You lose your turn. Please pass the computer to "
                        + player1 + ".");
                JOptionPane.showMessageDialog(null, printer);            
            }

            //completing a valid turn
            else
            {
                turnScore = scoreTurn
                        (row, col, newWord, direction, board, multipliers);
                score2 += turnScore;
                if(direction == 1)
                {  
                    for(int i = 0; i < newWord.length(); i++)
                    {
                        if(!((newWord.substring(i, i+1)).equalsIgnoreCase
                            (board[row][col + i])))
                        {
                            int n = 0;
                            boolean endSearch = false;
                            while(endSearch == false)
                            {

                                if(newWord.substring(i, i+1).equalsIgnoreCase
                                        (hand2[n]))
                                {
                                    board[row][col + i] = hand2[n];
                                    hand2[n] = " ";
                                    endSearch = true;
                                }
                                n++;
                            }
                        }    
                    }
                }
                else if(direction == 2)
                {  
                    for(int i = 0; i < newWord.length(); i++)
                    {
                        if(!((newWord.substring(i, i+1)).equalsIgnoreCase
                            (board[row + i][col])))
                        {
                            int n = 0;
                            boolean endSearch = false;
                            while(endSearch == false)
                            {
                                if(hand2[n].equalsIgnoreCase
                                        (newWord.substring(i, i+1)))
                                {
                                    board[row + i][col] = hand2[n];
                                    hand2[n] = " ";
                                    endSearch = true;
                                }
                                n++;
                            }
                        }    
                    }
                }

                //draw new tiles
                for(int i = 0; i < hand2.length; i++)
                {
                    if(letters.size() > 0)
                    {
                        if(hand2[i].equals(" "))
                        {
                            int choice = rando.nextInt(letters.size());
                            hand2[i] = letters.get(choice);
                            letters.remove(choice);
                        }
                    }
                }

                //summarize turn and pass computer
                printer.setText("Turn " + round + ":\n" + player1 + ": " + 
                        score1 + "   " + player2 + ": " + score2 + "\nLetters "
                        + "Remaining: " + letters.size() + "\n\n" + printBoard
                        (board, multipliers) + printHand(hand2) + "You scored "
                        + turnScore + " points by" + " playing '" + newWord + 
                        "' Please pass the computer to " + player1 + ".");
                JOptionPane.showMessageDialog(null, printer);
            }
            if(gameOver(letters, hand2) == true)
            {
                winner = 2;
            }

            round++;
        }
        
        while(winner == 0)
        {
            //setting the basic game data for player1
            String turnOutput = ("Turn " + round + ":\n" + player1 + ": " + 
                    score1 + "   " + player2 + ": " + score2 + "\nLetters "
                    + "Remaining: " + letters.size() + "\n\n" + printBoard
                    (board, multipliers) + printHand(hand1));

            //deciding word
            printer.setText(turnOutput + player1 + ", what word would you like "
                    + "to play?");
            String newWord = JOptionPane.showInputDialog(null, printer);
            boolean wordEntry = false;
            while(wordEntry == false)
            {
                if(newWord.startsWith(" "))
                {
                    printer.setText(turnOutput + "Please enter the word "
                            + "without a space at the front");
                    newWord = JOptionPane.showInputDialog(null, printer);
                }
                else if(newWord == null || newWord.equals(""))
                {
                    printer.setText(turnOutput + "Please enter a word");
                    newWord = JOptionPane.showInputDialog(null, printer);
                }
                else
                {
                    wordEntry = true;
                }
            }
            
            //deciding starting coordinates
            boolean coor = false;
            boolean format = false;
            int row = 0;
            int col = 0;
            printer.setText(turnOutput + "Where will the first letter of " + 
                    newWord + " be played? (Print row #,column #)");
            String coordinates = JOptionPane.showInputDialog(null, printer);
            int comma = coordinates.indexOf(",");
            while(coor == false)
            {
                if(comma < 1 || (coordinates.indexOf(" ") >= 0))
                {
                    printer.setText(turnOutput + "Invalid entry format. Please "
                            + "type coordinates of first letter using the "
                            + "format row #,column #");
                    coordinates = JOptionPane.showInputDialog(null, printer);
                    comma = coordinates.indexOf(",");
                }
                else
                {
                    format = true;
                }
                if(format == true)
                {
                    row = Integer.parseInt(coordinates.split(",")[0]);
                    col = Integer.parseInt(coordinates.split(",")[1]);
                    if(row < 0 || row > 14 || col < 0 || col > 14)
                    {
                        printer.setText(turnOutput + "Invalid starting point. "
                                + "Please enter a location on the board using "
                                + "the format row #,column #)");
                        coordinates = JOptionPane.showInputDialog
                                (null, printer);
                        comma = coordinates.indexOf(",");
                        format = false;
                    }
                    else
                    {
                        coor = true;
                    }
                }
            }

            //deciding direction    
            printer.setText(turnOutput + "Do you want to play '" + newWord + 
                    "' starting at row " + row + " amd column " + col + " down "
                    + "or to the right? (d/r)");
            String rOrD = JOptionPane.showInputDialog(null, printer);
            while(!((rOrD.equalsIgnoreCase("r")) || rOrD.equalsIgnoreCase("d")))
            {
                printer.setText(turnOutput + "Invalid direction. Enter 'r' "
                        + "for right or 'd' for down");
                rOrD = JOptionPane.showInputDialog(null, printer);
            }
            if(rOrD.equalsIgnoreCase("r"))
                direction = 1;
            else
                direction = 2;

            //checking the dictionary and tile placement
            String[] hand = hand1.clone();
            boolean legalWord = realWord(newWord, dictionary);
            boolean validPlay = validPlacement
                    (row, col, newWord, direction, hand, board);

            //player1 turn
            if(legalWord == false)
            {
                printer.setText(turnOutput + "Not a valid word. "
                        + "You lose your turn. Please pass the computer to "
                        + player2 + ".");
                JOptionPane.showMessageDialog(null, printer);
            }
            else if(validPlay == false)
            {
                printer.setText(turnOutput + "Not a valid tile placement. "
                        + "You lose your turn. Please pass the computer to "
                        + player2 + ".");
                JOptionPane.showMessageDialog(null, printer);            
            }

            //completing a valid turn
            else
            {
                turnScore = scoreTurn
                        (row, col, newWord, direction, board, multipliers);
                score1 += turnScore;
                if(direction == 1)
                {  
                    for(int i = 0; i < newWord.length(); i++)
                    {
                        if(!((newWord.substring(i, i+1)).equalsIgnoreCase
                            (board[row][col + i])))
                        {
                            int n = 0;
                            boolean endSearch = false;
                            while(endSearch == false)
                            {

                                if(newWord.substring(i, i+1).equalsIgnoreCase
                                        (hand1[n]))
                                {
                                    board[row][col + i] = hand1[n];
                                    hand1[n] = " ";
                                    endSearch = true;
                                }
                                n++;
                            }
                        }    
                    }
                }
                else if(direction == 2)
                {  
                    for(int i = 0; i < newWord.length(); i++)
                    {
                        if(!((newWord.substring(i, i+1)).equalsIgnoreCase
                            (board[row + i][col])))
                        {
                            int n = 0;
                            boolean endSearch = false;
                            while(endSearch == false)
                            {
                                if(hand1[n].equalsIgnoreCase
                                        (newWord.substring(i, i+1)))
                                {
                                    board[row + i][col] = hand1[n];
                                    hand1[n] = " ";
                                    endSearch = true;
                                }
                                n++;
                            }
                        }    
                    }
                }

                //draw new tiles
                for(int i = 0; i < hand1.length; i++)
                {
                    if(letters.size() > 0)
                    {
                        if(hand1[i].equals(" "))
                        {
                            int choice = rando.nextInt(letters.size());
                            hand1[i] = letters.get(choice);
                            letters.remove(choice);
                        }
                    }
                }

                //summarize turn and pass computer
                printer.setText("Turn " + round + ":\n" + player1 + ": " + 
                        score1 + "   " + player2 + ": " + score2 + "\nLetters "
                        + "Remaining: " + letters.size() + "\n\n" + printBoard
                        (board, multipliers) + printHand(hand1) + "You scored " 
                        + turnScore + " points by playing '" + newWord + 
                        "' Please pass the computer to " + player2 + ".");
                JOptionPane.showMessageDialog(null, printer);
            }

            if(gameOver(letters, hand1) == true)
            {
                winner = 1;
            }

            round++;
                
                
            
                
            //only plays player2 if player1 didn't win
            if(winner == 0)
            {
                //setting the basic game data for player2
                turnOutput = ("Turn " + round + ":\n" + player1 + ": " + score1 
                        + "   " + player2 + ": " + score2 + "\nLetters "
                        + "Remaining: " + letters.size() + "\n\n" + printBoard
                        (board, multipliers) + printHand(hand2));

                //deciding word
                printer.setText(turnOutput + player2 + ", what word would you "
                        + "like to play?");
                newWord = JOptionPane.showInputDialog(null, printer);
                wordEntry = false;
                while(wordEntry == false)
                {
                    if(newWord.startsWith(" "))
                    {
                        printer.setText(turnOutput + "Please enter the word "
                                + "without a space at the front");
                        newWord = JOptionPane.showInputDialog(null, printer);
                    }
                    else if(newWord == null || newWord.equals(""))
                    {
                        printer.setText(turnOutput + "Please enter a word");
                        newWord = JOptionPane.showInputDialog(null, printer);
                    }
                    else
                    {
                        wordEntry = true;
                    }
                }
                
                //deciding starting coordinates
                coor = false;
                format = false;
                row = 0;
                col = 0;
                printer.setText(turnOutput + "Where will the first letter of " +
                        newWord + " be played? (Print row #,column #)");
                coordinates = JOptionPane.showInputDialog(null, printer);
                comma = coordinates.indexOf(",");
                while(coor == false)
                {
                    if(comma < 1 || (coordinates.indexOf(" ") >= 0))
                    {
                        printer.setText(turnOutput + "Invalid entry format. "
                                + "Please type coordinates of first letter "
                                + "using the format row #,column #");
                        coordinates = JOptionPane.showInputDialog
                                (null, printer);
                        comma = coordinates.indexOf(",");
                    }
                    else
                    {
                        format = true;
                    }
                    if(format == true)
                    {
                        row = Integer.parseInt(coordinates.split(",")[0]);
                        col = Integer.parseInt(coordinates.split(",")[1]);
                        if(row < 0 || row > 14 || col < 0 || col > 14)
                        {
                            printer.setText(turnOutput + "Invalid starting "
                                    + "point. Please enter a location on the "
                                    + "board using the format row #,column #)");
                            coordinates = JOptionPane.showInputDialog
                                    (null, printer);
                            comma = coordinates.indexOf(",");
                            format = false;
                        }
                        else
                        {
                            coor = true;
                        }
                    }
                }

                //deciding direction    
                printer.setText(turnOutput + "Do you want to play '" + newWord +
                        "' starting at row " + row + " amd column " + col + 
                        " down or to the right? (d/r)");
                rOrD = JOptionPane.showInputDialog(null, printer);
                while(!((rOrD.equalsIgnoreCase("r")) || rOrD.equalsIgnoreCase
                        ("d")))
                {
                    printer.setText(turnOutput + "Invalid direction. Enter 'r' "
                            + "for right or 'd' for down");
                    rOrD = JOptionPane.showInputDialog(null, printer);
                }
                if(rOrD.equalsIgnoreCase("r"))
                    direction = 1;
                else
                    direction = 2;

                //checking the dictionary and tile placement
                hand = hand2.clone();
                legalWord = realWord(newWord, dictionary);
                validPlay = validPlacement
                        (row, col, newWord, direction, hand, board);       

                //player2 turn
                if(legalWord == false)
                {
                    printer.setText(turnOutput + "Not a valid word. "
                            + "You lose your turn. Please pass the computer to "
                            + player1 + ".");
                    JOptionPane.showMessageDialog(null, printer);
                }
                else if(validPlay == false)
                {
                    printer.setText(turnOutput + "Not a valid tile placement. "
                            + "You lose your turn. Please pass the computer to "
                            + player1 + ".");
                    JOptionPane.showMessageDialog(null, printer);            
                }

                //completing a valid turn
                else
                {
                    turnScore = scoreTurn
                            (row, col, newWord, direction, board, multipliers);
                    score2 += turnScore;
                    if(direction == 1)
                    {  
                        for(int i = 0; i < newWord.length(); i++)
                        {
                            if(!((newWord.substring(i, i+1)).equalsIgnoreCase
                                (board[row][col + i])))
                            {
                                int n = 0;
                                boolean endSearch = false;
                                while(endSearch == false)
                                {

                                    if(newWord.substring
                                            (i, i+1).equalsIgnoreCase(hand2[n]))
                                    {
                                        board[row][col + i] = hand2[n];
                                        hand2[n] = " ";
                                        endSearch = true;
                                    }
                                    n++;
                                }
                            }    
                        }
                    }
                    else if(direction == 2)
                    {  
                        for(int i = 0; i < newWord.length(); i++)
                        {
                            if(!((newWord.substring(i, i+1)).equalsIgnoreCase
                                (board[row + i][col])))
                            {
                                int n = 0;
                                boolean endSearch = false;
                                while(endSearch == false)
                                {
                                    if(hand2[n].equalsIgnoreCase
                                            (newWord.substring(i, i+1)))
                                    {
                                        board[row + i][col] = hand2[n];
                                        hand2[n] = " ";
                                        endSearch = true;
                                    }
                                    n++;
                                }
                            }    
                        }
                    }

                    //draw new tiles
                    for(int i = 0; i < hand2.length; i++)
                    {
                        if(letters.size() > 0)
                        {
                            if(hand2[i].equals(" "))
                            {
                                int choice = rando.nextInt(letters.size());
                                hand2[i] = letters.get(choice);
                                letters.remove(choice);
                            }
                        }
                    }

                    //summarize turn and pass computer
                    printer.setText("Turn " + round + ":\n" + player1 + ": " + 
                            score1 + "   " + player2 + ": " + score2 + "\n"
                            + "Letters Remaining: " + letters.size() + "\n\n" + 
                            printBoard(board, multipliers) + printHand(hand2) +
                            "You scored " + turnScore + " points by" + " playin"
                            + "g '" + newWord + "' Please pass the computer to " 
                            + player1 + ".");
                    JOptionPane.showMessageDialog(null, printer);
                }
                if(gameOver(letters, hand2) == true)
                {
                    winner = 2;
                }

                round++;
            }    
        }
        
        if(winner == 1)
        {
            score1 += scoreHand(hand2);
            score2 -= scoreHand(hand2);
        }
        
        else if(winner == 2)
        {
            score2 += scoreHand(hand1);
            score1 -= scoreHand(hand1);
        }
        
        if(score1 > score2)
        {
            printer.setText(printBoard(board, multipliers) + "Congratulations "
                    + "to " + player1 + "! You won " + score1 + " to " + 
                    score2 + ".");
            JOptionPane.showMessageDialog(null, printer);
        }
        else if(score2 > score1)
        {
            printer.setText(printBoard(board, multipliers) + "Congratulations "
                    + "to " + player2 + "! You won " + score2 + " to " + 
                    score1 + ".");
            JOptionPane.showMessageDialog(null, printer);
        }
        else
        {
            printer.setText(printBoard(board, multipliers) + "What are the "
                    + "odds! You both tied " + score1 + " to " + score2 + ".");
            JOptionPane.showMessageDialog(null, printer);    
        }
    }
}