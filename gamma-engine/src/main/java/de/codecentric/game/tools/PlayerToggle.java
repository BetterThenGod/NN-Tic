package de.codecentric.game.tools;

import de.codecentric.game.tictactoe.game.Player;

public class PlayerToggle {

    private Player gammaPlayer = Player.X;

    private Player opponentPlayer = Player.O;

    public Player getGammaPlayer() {
        return gammaPlayer;
    }

    public Player getOpponentPlayer() {
        return opponentPlayer;
    }

    public void toggle() {
        if (gammaPlayer == Player.X) {
            gammaPlayer = Player.O;
            opponentPlayer = Player.X;
        } else {
            gammaPlayer = Player.X;
            opponentPlayer = Player.O;
        }
    }
}
