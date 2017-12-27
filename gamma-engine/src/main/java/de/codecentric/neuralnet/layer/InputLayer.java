package de.codecentric.neuralnet.layer;

import de.codecentric.game.tictactoe.game.Board;
import de.codecentric.game.tictactoe.game.Field;
import de.codecentric.neuralnet.neuron.InputNeuron;

import java.util.ArrayList;
import java.util.List;

public class InputLayer extends AbstractLayer {

    private List<InputNeuron> inputNeurons;

    @Override
    public void subInitialize() {
        inputNeurons = new ArrayList<>();
        for (int i = 0; i < getNumberOfNeurons(); i++) {
            InputNeuron n = new InputNeuron();
            n.initialize(i,0, 9);
            inputNeurons.add(n);
        }
    }

    public void fire(Board board) {

        int neuronNum = 0;
        for (int i = 1; i <= 9; i++) {

            //
            // Three neurons always representing the same field to differ between empty fields,
            // own fields and fields occupied by an opponent
            //
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
        for (int i = 0; i < getNumberOfNeurons(); i++) {
            weights.add(inputNeurons.get(i).getOutputWeights().get(num));
        }

        return weights;
    }

    public List<Field> getFields() {
        List<Field> fields = new ArrayList<>();
        for (int i = 0; i < getNumberOfNeurons(); i++) {
            fields.add(inputNeurons.get(i).getField());
        }

        return fields;
    }

    public void reward(int index, double value, List<Integer> listOfInputNeuronIndexes) {

        for (Integer i : listOfInputNeuronIndexes) {
            inputNeurons.get(i).getOutputWeights().set(index, inputNeurons.get(i).getOutputWeights().get(index) + 0.01d);
        }

        int inputNumIndex = index * 3;
        for (int i = 0; i < getNumberOfNeurons(); i++) {
            if (i == inputNumIndex) {
                InputNeuron neuron = inputNeurons.get(i);
                neuron.getOutputWeights().set(index, neuron.getOutputWeights().get(index) + value);

                if (neuron.getOutputWeights().get(index) >= 1) {
                    neuron.getOutputWeights().set(index, 0.999d);
                }
            }
        }



    }
}
