package com.eti.wiki.matrix;

public class DiagonalMatrix extends Matrix {

    public DiagonalMatrix(int n, double[] count) {
        super(n);
        for(int i = 0; i < getSize(); i++){
            for(int j = 0; j < getSize(); j++){
                if(i == j){
                    setMatrix(i, j, 1/count[i]);
                }
                else{ 
                    setMatrix(i, j, 0);	
                }
            }
        }
    }

}
