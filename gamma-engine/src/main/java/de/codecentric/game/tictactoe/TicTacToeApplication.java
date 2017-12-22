package de.codecentric.game.tictactoe;

import de.codecentric.game.tictactoe.board.Board;
import de.codecentric.game.tictactoe.board.Owner;
import de.codecentric.game.tools.RandomPlayer;
import de.codecentric.game.tools.TimeSeries;
import de.codecentric.neuralnet.NeuralNet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@ComponentScan("de.codecentric")
public class TicTacToeApplication implements CommandLineRunner {

    @Autowired
    private NeuralNet neuralNet;

    @Autowired
    private RandomPlayer randomPlayer;

    @Value("${trainingRuns}")
    private int trainingRuns;

	public static void main(String[] args) {
		SpringApplication.run(TicTacToeApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        train();
	    play();
    }


    public void train() {

        Board board = new Board();

        int gammaWins = 0;
        int randomWins  = 0;
        int draws = 0;
        TimeSeries timeSeries = new TimeSeries();

        Owner gammaColor = Owner.BLUE;
        Owner randomColor = Owner.RED;

        for (int i = 0; i < trainingRuns; i++) {

            boolean gammaWonFlag = false;
            boolean randomWonFlag = false;
            boolean drawFlag = false;
            int moveNum = 0;

            while (!gammaWonFlag && !randomWonFlag && !drawFlag) {

                moveNum++;

                if (moveNum > 1 || gammaColor == Owner.BLUE) {
                    int blueMove = neuralNet.makeMove(board.copy(), gammaColor);
                    board.move(blueMove, gammaColor);
                    gammaWonFlag = checkWon(board, gammaColor, "Gamma won as " + gammaColor + "!");
                    drawFlag = checkDraw(board);
                }

                if (!gammaWonFlag && !drawFlag) {
                    int redMove = randomPlayer.makeMove(board.copy());
                    board.move(redMove, Owner.RED);
                    randomWonFlag = checkWon(board, randomColor, "Mr. Random won as " + randomColor + "!");
                    drawFlag = checkDraw(board);
                }

                if (gammaWonFlag) {
                    gammaWins++;
                } else if (randomWonFlag) {
                    randomWins++;
                } else if (drawFlag) {
                    draws++;
                }

                if (gammaWonFlag || randomWonFlag || drawFlag) {
                    if (gammaColor == Owner.BLUE) {
                        gammaColor = Owner.RED;
                        randomColor = Owner.BLUE;
                    } else {
                        gammaColor = Owner.BLUE;
                        randomColor = Owner.RED;
                    }

                    timeSeries.add(gammaWins, randomWins, draws);
                }
            }
        }

        System.out.println("Training results:");
        System.out.println("Gamma  wins: " + gammaWins);
        System.out.println("Random wins: " + randomWins);
        System.out.println("Draws      : " + draws);

        timeSeries.write();
    }

    public void play() {

	    Board board = new Board();

        Scanner scanner = new Scanner(System.in);
        int inputToken = -1;
        while (inputToken != 0) {

            board.printToScreen();
            System.out.print("Your move: ");

            inputToken = Integer.parseInt(scanner.nextLine());
            if (board.isValid(inputToken)) {
                board.move(inputToken, Owner.BLUE);
                boolean isWon = checkWon(board, Owner.BLUE, "You won!");
                boolean isDraw = checkDraw(board);

                if (!isWon && !isDraw) {
                    int computerMove = neuralNet.makeMove(board.copy(), Owner.RED);
                    board.move(computerMove, Owner.RED);
                    checkWon(board, Owner.RED, "Computer won!");

                    System.out.println("");
                }
            } else {
                System.out.println("INVALID MOVE!");
            }
        }
        scanner.close();
    }

    private boolean checkWon(Board board, Owner owner, String message) {
        if (board.won(owner)) {
            board.printToScreen();
            System.out.println(message);
            board.initialize();
            return true;
        }

        return  false;
    }

    private boolean checkDraw(Board board) {
	    if (board.validMoves().size() == 0) {
            board.printToScreen();
            System.out.println("Draw!");
            board.initialize();
            return true;
        }

        return false;
    }
}
