package de.codecentric.neuralnet.neuron;

import de.codecentric.game.tictactoe.board.Field;


public class InputNeuron extends Neuron {


    private Field field;

    public void fire(Field field) {
        this.field = field;
    }

    public Field getField() {
        return field;
    }
}
