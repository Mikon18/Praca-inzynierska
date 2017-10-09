package com.eti.wiki.matrix;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import com.eti.wiki.database.DatabaseSession;

public class AdjacencyMatrix extends Matrix{
    private double [] linkCount;
    private static final String TABLE = "wikireference";
    private StringBuilder sqlQuery = new StringBuilder("");
    public Map<Integer, Integer> pageIdMapX = new HashMap<>();
    private Map<Integer, Integer> pageIdMapY = new HashMap<>();
    public AdjacencyMatrix(int n, String keyword) throws FileNotFoundException {
        super(n);
        createSqlQuery(keyword);
        fillMatrix();
        modifyAdjacencyMatrix();
        setLinkCount();
    }
    private void createSqlQuery(String keyword) {
    	sqlQuery.append(String.format("SELECT * FROM %1$s WHERE", TABLE));
    	if(!keyword.contains(",")) {
    		sqlQuery.append(String.format(" keyword='%1$s'", keyword));
    	} else {
    		String[] keywords = keyword.split(",");
    		for(int i = 0; i < keywords.length; i++) {
    			sqlQuery.append(String.format(" keyword='%1$s' ", keywords[i]));
    			if(i+1 != keyword.length()) {
    				sqlQuery.append("OR");
    			}
    		}
    	}
		
	}
	public void modifyAdjacencyMatrix(){
        for(int i = 0; i < getSize(); i++){
            for(int j = 0; j < getSize(); j++){
                if(getMatrix(j, i) == 1.0){
                    break;
                }
                if(j+1 == getSize()){
                    for(int k = 0; k < getSize(); k++){
                        if(k!=i) setMatrix(k, i, 1.0);
                    }
                }

            }
        }	
    }
    public void setLinkCount(){
        this.linkCount = new double[getSize()];
        int a = 0;
        for(int i = 0; i < getSize(); i++){
            for(int j = 0; j < getSize(); j++){
                if(getMatrix(j, i) == 1.0) {
                    a++;
                }
            }
            linkCount[i] = a;
            a = 0;
        }
    }
    public double[] getLinkCount() {
        return linkCount;
    }	
    public void fillMatrix(){
    	Session session = DatabaseSession.getSessionFactory().getCurrentSession();
    	try
    	{
	        session.getTransaction().begin();
	        @SuppressWarnings("unchecked")
	        List<Object> references = (List<Object>)session.createSQLQuery(sqlQuery.toString()).list();
	        Iterator<Object> itr = references.iterator();
	        int x = 0;
	        int y = 0;
	        while(itr.hasNext()){
	            Object[] obj = (Object[]) itr.next();
	            if(pageIdMapX.containsKey(Integer.valueOf(String.valueOf(obj[0]))))
	            {
	                if(pageIdMapY.containsKey(Integer.valueOf(String.valueOf(obj[2]))))
	                {
	
	                }
	                else
	                {
	                    pageIdMapY.put(Integer.valueOf(String.valueOf(obj[2])), Integer.valueOf(y));
	                    int value =  pageIdMapX.get(Integer.valueOf(String.valueOf(obj[0])));
	                    setMatrix(value, y++, 1.0);					
	                }				
	            }
	            else
	            {
	                if(pageIdMapY.containsKey(Integer.valueOf(String.valueOf(obj[2]))))
	                {
	                    pageIdMapX.put(Integer.valueOf(String.valueOf(obj[0])), Integer.valueOf(x));
	                    int value =  pageIdMapY.get(Integer.valueOf(String.valueOf(obj[2])));
	                    setMatrix(x++, value, 1.0);
	
	                }
	                else
	                {
	                    pageIdMapX.put(Integer.valueOf(String.valueOf(obj[0])), Integer.valueOf(x));
	                    pageIdMapY.put(Integer.valueOf(String.valueOf(obj[2])), Integer.valueOf(y));
	                    setMatrix(x++, y++, 1.0);
	                }
	            }
	            if(x == getSize() || y == getSize())
	            {
	                break;
	            }
	        }
	        session.getTransaction().commit();	
    	}
    	catch (RuntimeException e) {
    	    session.getTransaction().rollback();
    	    throw e;
    	}
    }
}

