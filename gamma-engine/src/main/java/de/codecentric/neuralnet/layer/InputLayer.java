package de.codecentric.neuralnet.layer;

import de.codecentric.game.tictactoe.board.Board;
import de.codecentric.game.tictactoe.board.Field;
import de.codecentric.neuralnet.neuron.InputNeuron;

import java.util.ArrayList;
import java.util.List;

public class InputLayer extends AbstractLayer {

    private List<InputNeuron> inputNeurons;


    @Override
    public void subInitialize() {
        inputNeurons = new ArrayList<>();
        for (int i = 0; i < getNeuronNum(); i++) {
            InputNeuron n = new InputNeuron();
            n.initialize(i,0, 9);
            inputNeurons.add(n);
        }
    }

    public void fire(Board board) {

        int neuronNum = 0;
        for (int i = 1; i <= 9; i++) {
            inputNeurons.get(neuronNum).fire(board.getField(i));
            neuronNum++;
            inputNeurons.get(neuronNum).fire(board.getField(i));
            neuronNum++;
            inputNeurons.get(neuronNum).fire(board.getField(i));
            neuronNum++;
        }
    }

    @Override
    public InputNeuron getNeuron(int num) {
        return inputNeurons.get(num);
    }

    public List<Double> getOutputWeigths(int num) {

        List<Double> weights = new ArrayList<>();
        for (int i = 0; i < getNeuronNum(); i++) {
            weights.add(inputNeurons.get(i).getOutputWeights().get(num));
        }

        return weights;
    }

    public List<Field> getFields() {
        List<Field> fields = new ArrayList<>();
        for (int i = 0; i < getNeuronNum(); i++) {
            fields.add(inputNeurons.get(i).getField());
        }

        return fields;
    }

    public void reward(int index) {

        int inputNumIndex = index * 3;
        for (int i = 0; i < getNeuronNum(); i++) {

            if (i == inputNumIndex) {
                InputNeuron neuron = inputNeurons.get(i);
                neuron.getOutputWeights().set(index, neuron.getOutputWeights().get(index) + 0.025d);

                if (neuron.getOutputWeights().get(index) >= 1) {
                    neuron.getOutputWeights().set(index, 0.999d);
                }
            }
        }
    }
}
