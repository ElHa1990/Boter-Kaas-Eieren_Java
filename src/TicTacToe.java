import java.util.Scanner;

public class TicTacToe {
    Scanner scanner = new Scanner(System.in);
    String nameFirstPlayer;
    String nameSecondPlayer;
    int activePlayer = 1;
    int moves = 0;
    boolean activeGame = true;
    String marker = "X";
    String playerName = "";
    String[] numbersBord = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

    public void setupGame() {
        // Show rules tic-tac-toe
        System.out.println("RULES FOR TIC-TAC-TOE\n" +
                "1. The game is played on a grid that's 3 squares by 3 squares.\n" +
                "2. You are X, your friend is O. Players take turns putting their marks in empty squares.\n" +
                "3. The first player to get 3 of his/her marks in a row (up, down, across, or diagonally) is the winner.\n" +
                "4. When all 9 squares are full, the game is over. If no player has 3 marks in a row, the game ends in a tie. \n");

        // Enter and show name player one
        System.out.println("Enter name first player:");
        nameFirstPlayer = scanner.nextLine();
        System.out.println("Name first player: " + nameFirstPlayer + "\n");

        // Enter and show name player two
        System.out.println("Enter name second player:");
        nameSecondPlayer = scanner.nextLine();
        System.out.println("Name second player: " + nameSecondPlayer + "\n");

        System.out.println("Okay " + nameFirstPlayer + " and " + nameSecondPlayer + " let's play! \n");

        printBord();
        playGame();
    }

    public void printBord() {

        String bord = "_" + numbersBord[0] + "_|_" + numbersBord[1] + "_|_" + numbersBord[2] + "_\n" +
                "_" + numbersBord[3] + "_|_" + numbersBord[4] + "_|_" + numbersBord[5] + "_\n" +
                " " + numbersBord[6] + " | " + numbersBord[7] + " | " + numbersBord[8] + " \n";

        System.out.println(bord);
    }

    public void setPosition() {
        // Check number or other keyboard character, only numbers between 1 and 9.

        boolean isInt = scanner.hasNextInt();

        if (isInt) {
            int number = scanner.nextInt();

            if (number > 9 || number < 1) {
                System.out.println("Please enter a number between 1 and 9.");
                setPosition();
                return;
            }

            // Check if number is already an O or X
            if(numbersBord[number - 1].equals("X") || numbersBord[number - 1].equals("O")) {
                System.out.println("A marker is already placed on the number you entered. Please try again.");
                scanner.nextLine();
                setPosition();
            } else {
                numbersBord[number - 1] = marker;
                printBord();
            }
        } else {
            System.out.println("Not a number, " + playerName + " please enter a number where you would like to place the " + marker + ":" );
            scanner.nextLine();
            setPosition();

        }
    }

    public void playGame() {
         /* While the game is active, ask players alternately where to place their marker until there is a winner or the game ended in a draw.
        When game has ended ask if they want to play again or end the game */

        while (activeGame) {
            moves++;

            if(activePlayer == 1) {
                marker = "X";
                playerName = nameFirstPlayer;

                // Ask player where to place the X
                String textPlayerOne = playerName + " please enter the number where you would like to place the " + marker + ":";
                System.out.println(textPlayerOne);

                activePlayer = 2;
            }
            else if (activePlayer == 2) {
                marker = "O";
                playerName = nameSecondPlayer;

                // Ask player where to place the O
                String textPlayerTwo = playerName + " please enter the number where you would like to place the " + marker + ":";
                System.out.println(textPlayerTwo);

                activePlayer = 1;
            }

            // Check entered number or other keyboard character
            setPosition();

            // End game --> winning or draw
            endGame();
            scanner.nextLine();
        }
    }

    public void endGame() {

        // Win
        String win = "Congratulations " + playerName +", you won!";
        if (numbersBord[0].equals(marker) && numbersBord[1].equals(marker) && numbersBord[2].equals(marker)) {
            System.out.println(win);
            resetGame();
        } else if (numbersBord[3].equals(marker) && numbersBord[4].equals(marker) && numbersBord[5].equals(marker)) {
            System.out.println(win);
            resetGame();
        } else if (numbersBord[6].equals(marker) && numbersBord[7].equals(marker) && numbersBord[8].equals(marker)) {
            System.out.println(win);
            resetGame();
        } else if (numbersBord[0].equals(marker) && numbersBord[3].equals(marker) && numbersBord[6].equals(marker)) {
            System.out.println(win);
            resetGame();
        } else if (numbersBord[1].equals(marker) && numbersBord[4].equals(marker) && numbersBord[7].equals(marker)) {
            System.out.println(win);
            resetGame();
        } else if (numbersBord[2].equals(marker) && numbersBord[5].equals(marker) && numbersBord[8].equals(marker)) {
            System.out.println(win);
            resetGame();
        } else if (numbersBord[0].equals(marker) && numbersBord[4].equals(marker) && numbersBord[8].equals(marker)) {
            System.out.println(win);
            resetGame();
        } else if (numbersBord[2].equals(marker) && numbersBord[4].equals(marker) && numbersBord[6].equals(marker)) {
            System.out.println(win);
            resetGame();
        } // Draw
        else if (moves == 9) {
            System.out.println("Your game has ended, it's a draw!");
            resetGame();
        }
    }

    public void resetGame() {

        //stop while loop
        activeGame = false;
        System.out.println("Press Y if you would like to play a new game. Press N to leave the game.");

        // Check entered keyboard entry
        char keyboardEnter = scanner.next().charAt(0);

        /* if Y: play new game, if N: stop game, any other keyboard entry repeat question.
        89 is the ASCII value for 'Y' */
        if (keyboardEnter == 89) {

            // reset the bord, all X and O should be removed and replaced with numbers
            numbersBord = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"};

            // reset activePlayer
            activePlayer = 1;

            // reset number of moves
            moves = 0;

            // Next line so that first player name isn't 'Enter' and start game
            scanner.nextLine();
            activeGame = true;
            setupGame();

        // if N, stop paying the game. ASCII value for 'N' is 78
        } else if (keyboardEnter == 78) {
            System.out.println("Thank you for playing, have a nice day!");
            activeGame = false;
        } else {
            resetGame();
        }
    }

}

