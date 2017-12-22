package de.codecentric.neuralnet;

import de.codecentric.game.tictactoe.board.Board;
import de.codecentric.game.tictactoe.board.Player;
import de.codecentric.game.tools.RandomPlayer;
import de.codecentric.game.tools.TimeSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Training {

    @Autowired
    private NeuralNet neuralNet;

    @Autowired
    private RandomPlayer randomPlayer;

    @Value("${trainingRuns}")
    private int trainingRuns;

    @Value("${overallRuns}")
    private int overallRuns;

    @Value("${outerRuns}")
    private int outerRuns;


    public void start() {

        TimeSeries overallSeries = new TimeSeries();

        for (int j = 0; j < outerRuns; j++) {

            Board board = new Board();

            int gammaWins = 0;
            int randomWins = 0;
            int draws = 0;
            TimeSeries timeSeries = new TimeSeries();

            Player gammaColor = Player.O;
            Player randomColor = Player.X;

            boolean trainingEnabled = true;

            for (int i = 0; i < overallRuns; i++) {

                if (i > trainingRuns) {
                    trainingEnabled = false;
                }

                boolean gameEndedFlag = false;
                int moveNum = 0;

                while (!gameEndedFlag) {

                    moveNum++;
                    if (moveNum > 1 || gammaColor == Player.O) {
                        int gammaMove = neuralNet.makeMove(board.copy(), gammaColor, trainingEnabled);
                        //int gammaMove = randomPlayer.makeMove(board.copy());
                        board.move(gammaMove, gammaColor);
                        if (board.gameEnded()) {
                            gameEndedFlag = true;
                            if (board.isWon(gammaColor)) {
                                gammaWins++;
                            } else {
                                draws++;
                            }
                            board.initialize();
                        }
                    }

                    if (!gameEndedFlag) {
                        int randomMove = randomPlayer.makeMove(board.copy());
                        board.move(randomMove, randomColor);
                        if (board.gameEnded()) {
                            gameEndedFlag = true;
                            if (board.isWon(randomColor)) {
                                randomWins++;
                            } else {
                                draws++;
                            }
                            board.initialize();
                        }
                    }

                    if (gameEndedFlag) {
                        if (gammaColor == Player.O) {
                            gammaColor = Player.X;
                            randomColor = Player.O;
                        } else {
                            gammaColor = Player.O;
                            randomColor = Player.X;
                        }

                        timeSeries.add(gammaWins, randomWins, draws);
                    }
                }
            }

            System.out.println("Training results:");
            System.out.println("Gamma  wins: " + gammaWins);
            System.out.println("Random wins: " + randomWins);
            System.out.println("Draws      : " + draws);

            timeSeries.write("series/gamma-series-" + j + ".csv");

            overallSeries.add(gammaWins, randomWins, draws);
        }

        overallSeries.write("series/overall-series.csv");
    }

}
