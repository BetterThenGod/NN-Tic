package de.codecentric.game.playing;

import de.codecentric.game.tictactoe.game.Board;
import de.codecentric.game.tictactoe.game.Player;

public interface Engine {

    int makeMove(Board board, Player player, boolean trainingEnables);

    void newGame();

}
