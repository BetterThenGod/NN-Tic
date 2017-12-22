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

        int startGameToggle = 0;

        for (int i = 0; i < trainingRuns; i++) {

            boolean gammaWonFlag = false;
            boolean randomWonFlag = false;
            boolean drawFlag = false;
            int moveNum = 0;

            while (!gammaWonFlag && !randomWonFlag && !drawFlag) {

                moveNum++;

                if (moveNum > 1 || startGameToggle % 2 == 0) {
                    int blueMove = neuralNet.makeMove(board.copy(), Owner.BLUE);
                    board.move(blueMove, Owner.BLUE);
                    gammaWonFlag = checkWon(board, Owner.BLUE, "Gamma won!");
                    drawFlag = checkDraw(board);
                    startGameToggle++;
                }

                if (!gammaWonFlag && !drawFlag) {
                    int redMove = randomPlayer.makeMove(board.copy());
                    board.move(redMove, Owner.RED);
                    randomWonFlag = checkWon(board, Owner.RED, "Mr. Random won!");
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
