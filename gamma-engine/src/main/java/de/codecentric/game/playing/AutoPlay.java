package de.codecentric.game.playing;

import de.codecentric.game.tictactoe.game.Board;
import de.codecentric.game.tictactoe.game.PlayerEnum;
import org.springframework.stereotype.Component;

@Component
public class AutoPlay {

    public GameResultEnum play(GameEngineInterface engine, GameEngineInterface opponent, PlayerEnum enginePlayer, PlayerEnum opponentPlayer, boolean trainingEnabled) {

        engine.resetBetweenGames();
        opponent.resetBetweenGames();

        Board board = new Board();
        GameResultEnum gameResult = null;
        int moveNum = 0;


        while (gameResult == null) {

            moveNum++;
            if (moveNum > 1 || enginePlayer == PlayerEnum.X) {
                int engineMove = engine.makeMove(board.copy(), enginePlayer, trainingEnabled);
                board.move(engineMove, enginePlayer);
                if (board.gameEnded()) {
                    if (board.isWon(enginePlayer)) {
                        gameResult = GameResultEnum.ENGINE_WON;
                    } else {
                        gameResult = GameResultEnum.DRAW;
                    }
                }
            }

            if (gameResult == null) {
                int opponentMove = opponent.makeMove(board.copy(), opponentPlayer, trainingEnabled);
                board.move(opponentMove, opponentPlayer);
                if (board.gameEnded()) {
                    if (board.isWon(opponentPlayer)) {
                        gameResult = GameResultEnum.OPPONENT_WON;
                    } else {
                        gameResult = GameResultEnum.DRAW;
                    }
                }
            }
        }

        return gameResult;
    }
}
