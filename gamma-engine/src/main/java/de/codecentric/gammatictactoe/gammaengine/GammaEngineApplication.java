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
    private Board board;

    @Autowired
    private NeuralNet neuralNet;

	public static void main(String[] args) {
		SpringApplication.run(GammaEngineApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        int inputToken = -1;
	    while (inputToken != 0) {

	        board.printToScreen();
	        System.out.print("Your move: ");

            inputToken = Integer.parseInt(scanner.nextLine());
            if (board.isValid(inputToken)) {
                board.move(inputToken, Owner.BLUE);

                if (board.won(Owner.BLUE)) {
                    board.printToScreen();
                    System.out.println("You won!");
                    System.exit(0);
                }

                int computerMove = neuralNet.calculate(board.copy());
                board.move(computerMove, Owner.RED);

                if (board.won(Owner.RED)) {
                    board.printToScreen();
                    System.out.println("Computer won!");
                    System.exit(0);
                }

                System.out.println("");
            } else {
                System.out.println("INVALID MOVE!");
            }
        }
        scanner.close();
    }
}
