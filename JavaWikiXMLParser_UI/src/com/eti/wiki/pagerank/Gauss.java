package com.eti.wiki.pagerank;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

import org.hibernate.Query;
import org.hibernate.Session;
import com.eti.wiki.database.DatabaseSession;
import com.eti.wiki.matrix.AdjacencyMatrix;
import com.eti.wiki.matrix.DiagonalMatrix;
import com.eti.wiki.matrix.IdentityMatrix;
import com.eti.wiki.matrix.ResultMatrix;

public class Gauss 
{
    private static final double DAMPING = 0.85;
    private static final double epsilon = 1E-04;
    private int size;
    private long tStart;
    private double[] vector;
    private double[][] L;
    private double[][] D;
    private double[][] U;
    private double[] matrixX;
    private double[] matrixX2;
    private ResultMatrix resultMatrix;
    private AdjacencyMatrix adjacencyMatrix;
    private IdentityMatrix identityMatrix;
    private DiagonalMatrix diagonalMatrix;
    private JProgressBar jpb;
    private JLabel jl;
    public Gauss(int n, JProgressBar jProgressBar1, JLabel jlabel) throws FileNotFoundException{
    	this.jpb = jProgressBar1;
    	this.jl = jlabel;
        this.tStart = System.currentTimeMillis();
        this.size = n;
        jl.setText("Tworzenie macierzy pomocniczych");
        this.vector = new double[n];
        for (int i = 0; i < n; i++)
        {
            vector[i] = (1 - DAMPING) / n;
        }
        this.L = new double[n][n];
        this.D = new double[n][n];
        this.U = new double[n][n];
        this.matrixX = new double[n];
        this.matrixX2 = new double[n];
        jl.setText("Tworzenie macierzy s¹siedztwa");
        this.adjacencyMatrix = new AdjacencyMatrix(n);
        jl.setText("Tworzenie macierzy jednostkowej");
        this.identityMatrix = new IdentityMatrix(n);
        jl.setText("Tworzenie macierzy diagonalnej");
        this.diagonalMatrix = new DiagonalMatrix(n, this.adjacencyMatrix.getLinkCount());
        jpb.setValue(jProgressBar1.getValue() + 5);
        this.resultMatrix = new ResultMatrix(n, adjacencyMatrix, diagonalMatrix, identityMatrix, jlabel, jProgressBar1);
    }
    public  Map<Integer, Double> solveGaussSeidel(JProgressBar jProgressBar1, JLabel jlabel) throws FileNotFoundException
    {
    	this.jpb = jProgressBar1;
    	this.jl = jlabel;
        boolean finish = false;		
        jl.setText("Tworzenie macierzy U, L, D");
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(i<j){
                    U[i][j] = resultMatrix.getMatrix(i, j);
                }
                else if(i>j){
                    L[i][j] = resultMatrix.getMatrix(i, j);
                }
                else{
                    D[i][j] = resultMatrix.getMatrix(i, j);
                }
            }
        }	
        for(int i = 0; i < size; i++){
            D[i][i] = 1/D[i][i];
        }

        for(int i = 0; i < size; i++){
            vector[i] *= D[i][i];
        }

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < i; j++)
            {
                L[i][j] *= D[i][i];
            }
        }

        for (int i = 0; i < size; i++)
        {
            for (int j = i + 1; j < size; j++)
            {
                U[i][j] *= D[i][i];
            }
        }	
        int iter = 1;
        double minDiff = 0;
        while (!finish)
        {
            for (int i = 0; i < size; i++)
            {
                matrixX2[i] = matrixX[i];
            }
            for (int i = 0; i < size; i++)
            {
                matrixX[i] = vector[i];
                for (int j = 0; j < i; j++)
                {
                    matrixX[i] -= L[i][j] * matrixX[j];
                }
                for (int j = i + 1; j < size; j++)
                {
                    matrixX[i] -= U[i][j] * matrixX[j];
                }
            }
            for (int i = 0; i < size; i++)
            {
                if (Math.abs(matrixX[i] - matrixX2[i]) < epsilon)
                {
                    finish = true;
                    jl.setText("");
                    this.jpb.setValue(90);
                }
                if(Math.abs(matrixX[i] - matrixX2[i]) < minDiff)
                {
                	minDiff = Math.abs(matrixX[i] - matrixX2[i]);
                }

            }
            if(!finish)
            {
            	jl.setText("Obliczanie.. Iter " + iter++);
            }
        }
        Map<Integer, Double> pageRank = new TreeMap<Integer, Double>();
        for(int i = 0; i < matrixX.length; i++){
            for(Map.Entry<Integer, Integer> entry : this.adjacencyMatrix.pageIdMapX.entrySet())
            {
                if(entry.getValue() == i)
                {
                    pageRank.put(entry.getKey(), matrixX[i]);
                }

            }
        }
        Map<Integer, Double> sortedPageRank = sortPageRank(pageRank);
     /*   PrintWriter pr = new PrintWriter("D://pagerank.txt");
        for(Map.Entry<Integer, Double> entry : sortedPageRank.entrySet()){
            System.out.println(entry.getKey() + "/" + entry.getValue());
            String title = findTitle(entry.getKey());
            pr.println(entry.getKey() + " " + entry.getValue() + " " + title);
        }
        pr.close();
        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        double elapsedSeconds = tDelta / 1000.0;
        System.out.println(elapsedSeconds / 60);*/
        return sortedPageRank;
    }
    public String findTitle(Integer key)
    {
    	String title = "";
    	Session session = DatabaseSession.getSessionFactory().getCurrentSession();
    	try
    	{
    		session.getTransaction().begin();
	        String id = String.valueOf(key);
	        String sqlQuery = "SELECT * FROM wikipage WHERE id="+id;
	        @SuppressWarnings("unchecked")
	        List<Object> references = (List<Object>)session.createSQLQuery(sqlQuery).list();
	        Iterator<Object> itr = references.iterator();
	        while(itr.hasNext()){
	            Object[] obj = (Object[]) itr.next();
	            title = String.valueOf(obj[1]);
	
	        }
	        session.getTransaction().commit();	
    	}
    	catch (RuntimeException e) {
    	    session.getTransaction().rollback();
    	    throw e;
    	}
        return title;
    }
    private Map<Integer, Double> sortPageRank(Map<Integer, Double> pageRank) 
    {
        List<Map.Entry<Integer, Double>> list = new LinkedList<Map.Entry<Integer, Double>>(pageRank.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Double>>(){
            public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2){
                    return(o2.getValue()).compareTo(o1.getValue());
            }
        });
        Map<Integer, Double> sortedPageRank = new LinkedHashMap<Integer, Double>();
        for(Map.Entry<Integer, Double> entry : list){
            sortedPageRank.put(entry.getKey(), entry.getValue());
        }
        return sortedPageRank;
    }

}

