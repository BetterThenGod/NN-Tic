package de.codecentric.game.playing;

import de.codecentric.game.tictactoe.game.Board;
import de.codecentric.game.tictactoe.game.PlayerEnum;

public interface GameEngineInterface {

    int makeMove(Board board, PlayerEnum forPlayer, boolean trainingEnables);

    void resetBetweenGames();

}
