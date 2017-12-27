package de.codecentric.neuralnet;

import de.codecentric.game.playing.AutoPlay;
import de.codecentric.game.tools.PlayerToggle;
import de.codecentric.game.tools.RandomEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Training {

    @Autowired
    private AutoPlay autoPlay;

    @Value("${training.runs}")
    private int trainingRuns;

    @Value("${learning.stage}")
    private int learningStage;

    public void train(GammaEngine gammaEngine) {

        RandomEngine opponentEngine = new RandomEngine();
        PlayerToggle playerToggle = new PlayerToggle();

        for (int i = 0; i < trainingRuns; i++) {
            autoPlay.play(gammaEngine, opponentEngine,
                    playerToggle.getGammaPlayer(), playerToggle.getOpponentPlayer(), true);
            playerToggle.toggle();
        }
    }
}
