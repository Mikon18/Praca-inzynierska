package com.eti.wiki.matrix;

public class IdentityMatrix extends Matrix {

    public IdentityMatrix(int n) {
        super(n);
        for(int i = 0; i < n; i++){
            setMatrix(i, i, 1.0);
        }
    }

}
