package de.codecentric.game.playing;

import de.codecentric.game.tictactoe.game.Board;
import de.codecentric.game.tictactoe.game.PlayerEnum;
import de.codecentric.neuralnet.GammaEngine;
import de.codecentric.neuralnet.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class HumanPlay {

    @Autowired
    private Training training;

    @Value("${learning.stage}")
    private int learningStage;

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
                board.move(inputToken, PlayerEnum.O);
                boolean isWon = checkWon(board, PlayerEnum.O, "You isWon!");
                boolean isDraw = checkDraw(board);

                if (!isWon && !isDraw) {
                    int computerMove = gammaEngine.makeMove(board.copy(), PlayerEnum.X, false);
                    board.move(computerMove, PlayerEnum.X);
                    checkWon(board, PlayerEnum.X, "Computer isWon!");

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

    private boolean checkWon(Board board, PlayerEnum owner, String message) {
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
