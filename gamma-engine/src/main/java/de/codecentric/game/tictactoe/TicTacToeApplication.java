package de.codecentric.game.tictactoe;

import de.codecentric.game.playing.HumanPlay;
import de.codecentric.game.playing.SelfPlay;
import de.codecentric.neuralnet.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("de.codecentric")
public class TicTacToeApplication implements CommandLineRunner {

    @Autowired
    private SelfPlay selfPlay;

    @Autowired
    private HumanPlay humanPlay;

    @Autowired
    private Training training;

    @Value("${learning.stage}")
    private int learningStage;

    @Value("${selfplay.enabled}")
    private boolean selfplayEnabled;

    @Value("${humanplay.enabled}")
    private boolean humanplayEnabled;


    public static void main(String[] args) {
		SpringApplication.run(TicTacToeApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        if (selfplayEnabled) {
            selfPlay.play();
        }

        if (humanplayEnabled) {
            humanPlay.play();
        }
    }
}
