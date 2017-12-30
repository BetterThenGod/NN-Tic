package de.codecentric.neuralnet.neuron;

import de.codecentric.game.tictactoe.game.Field;

public class InputNeuron extends Neuron {

    private Field field;

    private double value;

    public void activate(Field field) {
        this.field = field;

        switch (field.getNumber()) {

            case 1:
            case 3:
            case 7:
            case 9:
                value = 0.2d;
                break;

            case 2:
            case 4:
            case 6:
            case 8:
                value = 0.3d;
                break;

            case 5:
                value = 0.4d;
                break;
        }
    }

    public Field getField() {
        return field;
    }

    public double getValue() {
        return value;
    }
}
