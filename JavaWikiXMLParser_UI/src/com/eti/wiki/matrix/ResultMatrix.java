package com.eti.wiki.matrix;

public class ResultMatrix extends Matrix {

    private static final double DAMPING = 0.85;

    public ResultMatrix(int n, AdjacencyMatrix adjacencyMatrix, DiagonalMatrix diagonalMatrix, 
                    IdentityMatrix identityMatrix) {
        super(n);
        double sum = 0;
        for(int i = 0; i < getSize(); i++){
            for(int j = 0; j < getSize(); j++){
                sum = 0;
                for(int k = 0; k < getSize(); k++){
                    sum += DAMPING*adjacencyMatrix.getMatrix(i, k)*diagonalMatrix.getMatrix(k, j);
                }
                setMatrix(i, j, sum);
            }
        }
        for(int i = 0; i < getSize(); i++){
            for(int j = 0; j < getSize(); j++){
                setMatrix(i, j, identityMatrix.getMatrix(i, j) - getMatrix(i,j));
            }
        }
    }

}

