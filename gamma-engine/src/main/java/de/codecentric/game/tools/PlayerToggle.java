package de.codecentric.game.tools;

import de.codecentric.game.tictactoe.game.PlayerEnum;

public class PlayerToggle {

    private PlayerEnum gammaPlayer = PlayerEnum.X;

    private PlayerEnum opponentPlayer = PlayerEnum.O;

    public PlayerEnum getGammaPlayer() {
        return gammaPlayer;
    }

    public PlayerEnum getOpponentPlayer() {
        return opponentPlayer;
    }

    public void toggle() {
        if (gammaPlayer == PlayerEnum.X) {
            gammaPlayer = PlayerEnum.O;
            opponentPlayer = PlayerEnum.X;
        } else {
            gammaPlayer = PlayerEnum.X;
            opponentPlayer = PlayerEnum.O;
        }
    }
}
