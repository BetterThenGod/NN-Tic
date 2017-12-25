package de.codecentric.game.playing;

import de.codecentric.game.tictactoe.board.Board;
import de.codecentric.game.tictactoe.board.Player;

public interface Engine {

    int makeMove(Board board, Player player, boolean trainingEnables);

}
