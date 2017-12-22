package de.codecentric.game.tictactoe;

import de.codecentric.game.tictactoe.board.Board;
import de.codecentric.game.tictactoe.board.Player;
import de.codecentric.game.tools.RandomPlayer;
import de.codecentric.game.tools.TimeSeries;
import de.codecentric.neuralnet.NeuralNet;
import de.codecentric.neuralnet.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Scanner;

@SpringBootApplication
@ComponentScan("de.codecentric")
public class TicTacToeApplication implements CommandLineRunner {

    @Autowired
    private NeuralNet neuralNet;

    @Autowired
    private Training training;

    public static void main(String[] args) {
		SpringApplication.run(TicTacToeApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        training.start();
	    play();
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
                board.move(inputToken, Player.O);
                boolean isWon = checkWon(board, Player.O, "You isWon!");
                boolean isDraw = checkDraw(board);

                if (!isWon && !isDraw) {
                    int computerMove = neuralNet.makeMove(board.copy(), Player.X, false);
                    board.move(computerMove, Player.X);
                    checkWon(board, Player.X, "Computer isWon!");

                    System.out.println("");
                }
            } else {
                System.out.println("INVALID MOVE!");
            }
        }
        scanner.close();
    }

    private boolean checkWon(Board board, Player owner, String message) {
        if (board.isWon(owner)) {
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
