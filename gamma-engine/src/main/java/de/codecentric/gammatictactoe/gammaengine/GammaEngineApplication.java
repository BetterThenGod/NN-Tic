package de.codecentric.gammatictactoe.gammaengine;

import de.codecentric.gammatictactoe.gammaengine.board.Board;
import de.codecentric.gammatictactoe.gammaengine.board.Owner;
import de.codecentric.gammatictactoe.gammaengine.neuralnet.NeuralNet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Scanner;

@SpringBootApplication
@ComponentScan("de.codecentric.gammatictactoe.gammaengine")
public class GammaEngineApplication implements CommandLineRunner {

    @Autowired
    private NeuralNet neuralNet;

	public static void main(String[] args) {
		SpringApplication.run(GammaEngineApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        train();
	    play();
    }


    public void train() {

        Board board = new Board();

        for (int i = 0; i < 10; i++) {
            boolean wonFlag = false;
            while (!wonFlag) {

                int blueMove = neuralNet.calculate(board.copy());
                board.move(blueMove, Owner.BLUE);
                wonFlag = checkWon(board, Owner.BLUE, "Blue won!");

                int redMove = neuralNet.calculate(board.copy());
                board.move(redMove, Owner.RED);
                wonFlag = checkWon(board, Owner.RED, "Red won!");
            }
        }
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
                checkWon(board, Owner.BLUE, "You won!");

                int computerMove = neuralNet.calculate(board.copy());
                board.move(computerMove, Owner.RED);
                checkWon(board, Owner.RED, "Computer won!");

                System.out.println("");
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
}
