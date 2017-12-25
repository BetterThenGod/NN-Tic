package de.codecentric.game.playing;

import de.codecentric.game.tictactoe.board.Board;
import de.codecentric.game.tictactoe.board.Player;
import org.springframework.stereotype.Component;

@Component
public class AutoPlay {

    public GameResult play(Engine engine, Engine opponent, Player enginePlayer, Player opponentPlayer, boolean trainingEnabled) {

        GameResult gameResult = null;
        int moveNum = 0;

        Board board = new Board();
        while (gameResult == null) {

            moveNum++;
            if (moveNum > 1 || enginePlayer == Player.X) {
                int engineMove = engine.makeMove(board.copy(), enginePlayer, trainingEnabled);
                board.move(engineMove, enginePlayer);
                if (board.gameEnded()) {
                    if (board.isWon(enginePlayer)) {
                        gameResult = GameResult.ENGINE_WON;
                    } else {
                        gameResult = GameResult.DRAW;
                    }
                }
            }

            if (gameResult == null) {
                int opponentMove = opponent.makeMove(board.copy(), opponentPlayer, trainingEnabled);
                board.move(opponentMove, opponentPlayer);
                if (board.gameEnded()) {
                    if (board.isWon(opponentPlayer)) {
                        gameResult = GameResult.OPPONENT_WON;
                    } else {
                        gameResult = GameResult.DRAW;
                    }
                }
            }
        }

        return gameResult;
    }
}
