package com.telran.demoqa.listener;

   import org.testng.ITestContext;
	import org.testng.ITestListener;
	import org.testng.ITestResult;
	  
	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;

	public class MyListener implements ITestListener {
	 
	    public static final Logger log = LoggerFactory.getLogger(MyListener.class);

	    private static String getTestMethodName(ITestResult iTestResult) {
	        return iTestResult.getName();
	    }

	    @Override
	    public void onTestStart(ITestResult result) {
	        log.info("Entering TestListener.onTestStart method " + getTestMethodName(result) + " start");
	 
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	        log.info("Entering TestListener.onTestSuccess method " + getTestMethodName(result) + " succeed");
	 
	    }

	    @Override
	    public void onTestFailure(ITestResult iTestResult) {
	 
	          log.error("Entering TestListener.onTestFailure method " + getTestMethodName(iTestResult) + " failed");

	 
	    }
	 

	    @Override
	    public void onTestSkipped(ITestResult iTestResult) {
	        log.info("Entering TestListener.onTestSkipped method " + getTestMethodName(iTestResult) + " skipped!");
	        log.error("skip exception:: "+iTestResult.getThrowable());
	        iTestResult.getThrowable().printStackTrace();
	    }

	    @Override
	    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
	        log.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	 
	    }
	 

	    @Override
	    public void onStart(ITestContext context) {
	        log.info("Entering TestListener.onStart method " + context.getName());
	         
	    }
	 

	    @Override
	    public void onFinish(ITestContext context) {
	        log.info("Entering TestListener.onFinish method " + context.getName());

	 
	    }
	 
	}
