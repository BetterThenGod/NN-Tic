package de.codecentric.game.tools;

import de.codecentric.game.playing.GameEngineInterface;
import de.codecentric.game.tictactoe.game.Board;
import de.codecentric.game.tictactoe.game.PlayerEnum;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class RandomEngine implements GameEngineInterface {

    @Override
    public int makeMove(Board board, PlayerEnum forPlayer, boolean trainingEnabled) {

        List<Integer> validMoves = board.validMoves();
        int randomNum = ThreadLocalRandom.current().nextInt(0, validMoves.size());

        return validMoves.get(randomNum);
    }

    @Override
    public void resetBetweenGames() {
        // Do nothing
    }
}
