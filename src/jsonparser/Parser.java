/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonparser;


import java.util.Calendar;
/**
 *
 * @author darthvader
 */
public abstract class Parser {
	protected String fullFileDir;

	public Parser(String fullFileDir) {
		this.fullFileDir = fullFileDir;
	}

	public abstract void process();

	public void run(int numberOfRunTime) {
		long startTime = Calendar.getInstance().getTimeInMillis();
		for (int i = 0; i < numberOfRunTime; i++) {
			this.process();
		}
		long endTime = Calendar.getInstance().getTimeInMillis();
		System.out.println(String.format("%s,%s", this.getClass().getName(), (endTime - startTime)));
	}
}
