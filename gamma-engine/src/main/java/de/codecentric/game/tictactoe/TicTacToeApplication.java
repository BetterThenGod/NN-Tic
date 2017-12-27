package de.codecentric.game.tictactoe;

import de.codecentric.game.tictactoe.game.Board;
import de.codecentric.game.tictactoe.game.Player;
import de.codecentric.game.tools.SelfPlay;
import de.codecentric.neuralnet.GammaEngine;
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
    private SelfPlay selfPlay;

    @Autowired
    private Training training;

    @Value("${learning.stage}")
    private int learningStage;

    public static void main(String[] args) {
		SpringApplication.run(TicTacToeApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        selfPlay.play();
	    play();
    }


    public void play() {

        GammaEngine gammaEngine = new GammaEngine(learningStage);
        training.train(gammaEngine);

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
                    int computerMove = gammaEngine.makeMove(board.copy(), Player.X, false);
                    board.move(computerMove, Player.X);
                    checkWon(board, Player.X, "Computer isWon!");

                    System.out.println("");
                } else {
                    gammaEngine.newGame();
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
