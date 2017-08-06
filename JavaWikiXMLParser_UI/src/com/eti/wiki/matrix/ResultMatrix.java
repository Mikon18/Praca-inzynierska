package com.eti.wiki.matrix;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class ResultMatrix extends Matrix {

    private static final double DAMPING = 0.85;

    public ResultMatrix(int n, AdjacencyMatrix adjacencyMatrix, DiagonalMatrix diagonalMatrix, 
                    IdentityMatrix identityMatrix, JLabel jlabel, JProgressBar jProgressBar1) {
        super(n);
        double sum = 0;
        for(int i = 0; i < getSize(); i++){
            for(int j = 0; j < getSize(); j++){
                sum = 0;
                for(int k = 0; k < getSize(); k++){
                    sum += DAMPING*adjacencyMatrix.getMatrix(i, k)*diagonalMatrix.getMatrix(k, j);
                }
                setMatrix(i, j, sum);
                if(i%100 == 0)
                {
                	jProgressBar1.setValue((int) (80.0 * ((double) ((double) i / (double) getSize()))));
                	jlabel.setText("Tworzenie macierzy wynikowej: " + i + " z " + getSize());
                }
            }
        }
        for(int i = 0; i < getSize(); i++){
            for(int j = 0; j < getSize(); j++){
                setMatrix(i, j, identityMatrix.getMatrix(i, j) - getMatrix(i,j));
            }
        }
    }

}

