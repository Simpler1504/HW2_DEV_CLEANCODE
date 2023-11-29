package org.example;

import java.util.Random;
import java.util.Scanner;

@SuppressWarnings({"java:S106", "java:S135"})
public class TicTacToe {

    private static final int BOARD_SIZE = 9;
    private static final char PLAYER_SYMBOL = 'X';
    private static final char COMPUTER_SYMBOL = 'O';
    private static final Random RANDOM = new Random();


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] board = initializeBoard();
        boolean boxEmpty = true;

        System.out.println("Enter box number to select. Enjoy!\n");

        while (true) {
            displayBoard(board);

            if (boxEmpty) {
                for (int i = 0; i < BOARD_SIZE; i++)
                    board[i] = ' ';
                boxEmpty = false;
            }

            playerMove(board, scanner);


            if (checkWinner(board, PLAYER_SYMBOL)) {
                displayBoard(board);
                System.out.println("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");
                break;
            } else if (checkWinner(board, COMPUTER_SYMBOL)) {
                displayBoard(board);
                System.out.println("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");
                break;
            } else if (isBoardFull(board)) {
                displayBoard(board);
                System.out.println("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");
                break;
            }
            computerMove(board);

        }
    }

    private static char[] initializeBoard() {
        return new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    }

    private static void displayBoard(char[] board) {
        System.out.println("\n " + board[0] + " | " + board[1] + " | " + board[2]);
        System.out.println("-----------");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5]);
        System.out.println("-----------");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8] + "\n");
    }

    private static void playerMove(char[] board, Scanner scanner) {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());

                if (isValidInput(input, board)) {
                    board[input - 1] = PLAYER_SYMBOL;
                    break;
                } else {
                    System.out.println("That one is already in use. Enter another.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter again.");
            }
        }
    }

    private static boolean isValidInput(int input, char[] board) {
        return input > 0 && input <= BOARD_SIZE && board[input - 1] != PLAYER_SYMBOL && board[input - 1] != COMPUTER_SYMBOL;
    }

    private static void computerMove(char[] board) {
        while (true) {
            int rand = RANDOM.nextInt(9);
            if (board[rand] != PLAYER_SYMBOL && board[rand] != COMPUTER_SYMBOL) {
                board[rand] = COMPUTER_SYMBOL;
                break;
            }
        }
    }

    private static boolean checkWinner(char[] board, char symbol) {
        // Check rows, columns, and diagonals for a winner
        return (board[0] == symbol && board[1] == symbol && board[2] == symbol) ||
                (board[3] == symbol && board[4] == symbol && board[5] == symbol) ||
                (board[6] == symbol && board[7] == symbol && board[8] == symbol) ||
                (board[0] == symbol && board[3] == symbol && board[6] == symbol) ||
                (board[1] == symbol && board[4] == symbol && board[7] == symbol) ||
                (board[2] == symbol && board[5] == symbol && board[8] == symbol) ||
                (board[0] == symbol && board[4] == symbol && board[8] == symbol) ||
                (board[2] == symbol && board[4] == symbol && board[6] == symbol);
    }

    private static boolean isBoardFull(char[] board) {
        for (char c : board) {
            if (c != PLAYER_SYMBOL && c != COMPUTER_SYMBOL) {
                return false;
            }
        }
        return true;
    }
}
