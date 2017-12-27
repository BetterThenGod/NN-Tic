package de.codecentric.game.tools;

import de.codecentric.game.playing.AutoPlay;
import de.codecentric.game.playing.GameResultEnum;
import de.codecentric.neuralnet.GammaEngine;
import de.codecentric.neuralnet.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SelfPlay {

    @Autowired
    private RandomEngine randomEngine;

    @Autowired
    private AutoPlay autoPlay;

    @Autowired
    private Training training;

    @Value("${selfplay.games}")
    private int selfplayGames;

    @Value("${selfplay.matches}")
    private int selfplayMatches;

    @Value("${learning.stage}")
    private int learningStage;

    public void play() {

        TimeSeries overallSeries = new TimeSeries();
        for (int j = 0; j < selfplayMatches; j++) {

            GammaEngine gammaEngine = new GammaEngine(learningStage);
            training.train(gammaEngine);

            int gammaWins = 0;
            int opponentWins = 0;
            int draws = 0;
            TimeSeries timeSeries = new TimeSeries();

            PlayerToggle playerToggle = new PlayerToggle();

            for (int i = 0; i < selfplayGames; i++) {

                GameResultEnum gameResult = autoPlay.play(gammaEngine, randomEngine,
                        playerToggle.getGammaPlayer(), playerToggle.getOpponentPlayer(), false);

                if (gameResult == GameResultEnum.DRAW) {
                    draws++;
                } else if (gameResult == GameResultEnum.ENGINE_WON) {
                    gammaWins++;
                } else if (gameResult == GameResultEnum.OPPONENT_WON) {
                    opponentWins++;
                }

                timeSeries.add(gammaWins, opponentWins, draws);
                playerToggle.toggle();
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
}
