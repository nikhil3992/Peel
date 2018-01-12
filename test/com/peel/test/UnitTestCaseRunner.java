package com.peel.test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class UnitTestCaseRunner {
	
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(PlaylistServiceTest.class);
		
	      for (Failure failure : result.getFailures()) {
	         System.out.println(failure.toString());
	      }
	      
	      if(result.wasSuccessful()) {
	    	  System.out.println("RESULT: All the Junit test casses are passed.");
	      }
	      
	      System.out.println("Total test cases ran : " + result.getRunCount());
	}
}
