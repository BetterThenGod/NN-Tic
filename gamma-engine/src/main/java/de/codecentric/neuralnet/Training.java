package de.codecentric.neuralnet;

import de.codecentric.game.playing.AutoPlay;
import de.codecentric.game.playing.GameResult;
import de.codecentric.game.tictactoe.board.Board;
import de.codecentric.game.tictactoe.board.Player;
import de.codecentric.game.tools.RandomEngine;
import de.codecentric.game.tools.TimeSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Training {

    @Autowired
    private GammaEngine gammaEngine;

    @Autowired
    private RandomEngine randomeEngine;

    @Autowired
    private AutoPlay autoPlay;

    @Value("${training.runs}")
    private int trainingRuns;

    @Value("${training.opponent}")
    private String trainingOpponent;

    @Value("${autoplay.games}")
    private int autoplayGames;

    @Value("${autoplay.matches}")
    private int autoplayMatches;

    @Value("${autoplay.opponent}")
    private String autoplayOpponent;


    private Player gammaPlayer = Player.X;

    private Player opponentPlayer = Player.O;


    public void start() {

        Player gammaPlayer = Player.X;
        Player opponentPlayer = Player.O;

        TimeSeries overallSeries = new TimeSeries();

        for (int j = 0; j < autoplayMatches; j++) {

            gammaEngine.initialize();
            for (int i = 0; i < trainingRuns; i++) {
                if (trainingOpponent.equals("random")) {
                    autoPlay.play(gammaEngine, randomeEngine, gammaPlayer, opponentPlayer, true);
                } else if (trainingOpponent.equals("gamma")) {
                    autoPlay.play(gammaEngine, gammaEngine, gammaPlayer, opponentPlayer, true);
                } else {
                    throw new RuntimeException("Unknown engine defined for training.opponent");
                }
                togglePlayer();
            }

            int gammaWins = 0;
            int opponentWins = 0;
            int draws = 0;
            TimeSeries timeSeries = new TimeSeries();

            for (int i = 0; i < autoplayGames; i++) {

                GameResult gameResult;

                if (autoplayOpponent.equals("random")) {
                    gameResult = autoPlay.play(gammaEngine, randomeEngine, gammaPlayer, opponentPlayer, false);
                } else if (autoplayOpponent.equals("gamma")) {
                    gameResult = autoPlay.play(gammaEngine, gammaEngine, gammaPlayer, opponentPlayer, false);
                } else {
                    throw new RuntimeException("Unknown engine defined for autoplay.opponent");
                }

                if (gameResult == GameResult.DRAW) {
                    draws++;
                } else if (gameResult == GameResult.ENGINE_WON) {
                    gammaWins++;
                } else if (gameResult == GameResult.OPPONENT_WON) {
                    opponentWins++;
                }

                timeSeries.add(gammaWins, opponentWins, draws);

                togglePlayer();
            }

            System.out.println("Training results:");
            System.out.println("Gamma  wins  : " + gammaWins);
            System.out.println("Opponent wins: " + opponentWins);
            System.out.println("Draws        : " + draws);

            timeSeries.write("series/gamma-series-" + j + ".csv");
            overallSeries.add(gammaWins, opponentWins, draws);
        }

        overallSeries.write("series/overall-series.csv");
    }

    private void togglePlayer() {
        if (gammaPlayer == Player.X) {
            gammaPlayer = Player.O;
            opponentPlayer = Player.X;
        } else {
            gammaPlayer = Player.X;
            opponentPlayer = Player.O;
        }
    }
}
