import java.util.Scanner;
import java.util.Random;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class TicTacToe {
    
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String[] args) {


        // Initialize gameboard
        char[][] gameBoard = {{' ', '|', ' ', '|', ' '}, 
                              {'-', '+', '-', '+', '-'}, 
                              {' ', '|', ' ', '|', ' '}, 
                              {'-', '+', '-', '+', '-'}, 
                              {' ', '|', ' ', '|', ' '}};

        //Method that prints gameboard
        printGameBoard(gameBoard);

        // while loop so we can keep getting questions
        while(true)
        {
            // Allow for user input
            Scanner scan = new Scanner(System.in);
            
            // Ask user for what place to put their piece
            System.out.printf("Enter your placement (1-9): ");
            int playerPosition = scan.nextInt();
            
            // Ask again if position is taken
            while(playerPositions.contains(playerPosition) || cpuPositions.contains(playerPosition))
            {
                System.out.printf("Position taken! Enter a correct position: ");
                playerPosition = scan.nextInt();
            }
            
            System.out.println();

            // Method that places a piece down
            placePiece(gameBoard, playerPosition, "player");

            // create an object of the Random class and have the cpu's position use the random number
            Random rand = new Random();
            int cpuPosition = rand.nextInt(9) + 1;                  // nextInt(9) for nums from 0 - 8, & + 1 to make it 1 - 9
            
            // Generate another random number if position is taken
            while(playerPositions.contains(cpuPosition) || cpuPositions.contains(cpuPosition))
            {
                System.out.printf("Position taken! Enter a correct position: ");
                cpuPosition = rand.nextInt(9) + 1;
            }

            placePiece(gameBoard, cpuPosition, "cpu");

            printGameBoard(gameBoard);

            String result = checkWinner();

            System.out.println(result);
        }

    }


    // Methods

    // Method for printing out gameboard
    public static void printGameBoard(char[][] gameBoard) {
        for(char[] row : gameBoard)
        {
            for(char c : row)
            {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(char[][] gameBoard, int position, String user) {
        
        char symbol = ' ';

        if(user.equals("player"))
        {
            symbol = 'X';
            playerPositions.add(position);
        }
        else if(user.equals("cpu"))
        {
            symbol = 'O';
            cpuPositions.add(position);
        }

        switch(position)
        {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;


        }
    }

    public static String checkWinner() {
        
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List bottomRow = Arrays.asList(7, 8, 9);

        List leftColumn = Arrays.asList(1, 4, 7);
        List midColumn = Arrays.asList(2, 5, 8);
        List rightColumn = Arrays.asList(3, 6, 9);

        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(3, 5, 7);

        
        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(bottomRow);
        winning.add(leftColumn);
        winning.add(midColumn);
        winning.add(rightColumn);
        winning.add(cross1);
        winning.add(cross2);


        for(List l : winning)
        {
            if(playerPositions.containsAll(l))
            {
                return "\n\n=================================\n<<<Congratulations! You won!!!>>>\n=================================";
            }
            else if(cpuPositions.containsAll(l))
            {
                return "\n\n><>< CPU wins. Sorry! ><><";
            }
            else if(playerPositions.size() + cpuPositions.size() == 9)
            {
                return "CAT!";
            }
        }

        return "";
    }

}
