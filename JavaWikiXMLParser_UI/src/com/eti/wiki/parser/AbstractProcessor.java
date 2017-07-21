package com.eti.wiki.parser;

import java.util.Observable;
import java.util.Observer;

import com.eti.wiki.model.WikiPage;

public abstract class AbstractProcessor implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		if( arg instanceof WikiPage){
			WikiPage casted = (WikiPage)arg;
			
			process(casted);
		}
	}
	
	public abstract void process(WikiPage page);
}
