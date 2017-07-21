package com.eti.wiki.matrix;

public class Matrix {
    private double[][] matrix;
    private int size;

    public Matrix(int n){
            this.matrix = new double[n][n];
            this.size = n;
    }

    public double getMatrix(int x, int y) {
            return matrix[x][y];
    }

    public void setMatrix(int x, int y, double value) {
            this.matrix[x][y] = value;
    }

    public int getSize() {
            return size;
    }

    public void setSize(int size) {
            this.size = size;
    }
	
}
