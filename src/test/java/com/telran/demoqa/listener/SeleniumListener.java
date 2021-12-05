package com.telran.demoqa.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class SeleniumListener implements  ITestListener{
	 
    public static final Logger logger = LoggerFactory.getLogger(SeleniumListener.class);
	
	private static String currentDir = null;

    static {
        try {
            currentDir = new File(".").getCanonicalPath();
            String folderPath = currentDir + "\\target\\failure_screenshots\\";
            File theDir = new File(folderPath);
            if (!theDir.exists()) {
                    theDir.mkdirs();
            }
            } catch (IOException e) {
            System.err.println("Error while detecting current working dir. Reason:" + e.getMessage());
        }
    }

	@Override
	public void onFinish(ITestContext context) {

		logger.info( "===============================");
		logger.info( "********* TEST SUMMARY*********" );
		logger.info( "** TOTAL TESTS : " + context.getAllTestMethods().length);
		logger.info( "** PASSED : " + context.getPassedTests().size());
		logger.info( "** FAILED : " + context.getFailedTests().size());
		logger.info( "** SKIPPED : " + context.getSkippedTests().size());
		logger.info( "===============================");
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		logger.error("**SKIPPED TEST** - " + tr.getName());
	}

	@Override
	public void onTestStart(ITestResult tr) {
		logger.info( "## STARTING TEST ## -> " + tr.getInstance().getClass().getSimpleName()+tr.getName());
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		logger.info( "**SUCCESSFULLY EXECUTED TEST ** -> " + tr.getInstance().getClass().getSimpleName()+"."+tr.getName());
	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}
	
	 @Override
	    public void onTestFailure(ITestResult tr) {
		 logger.error( "** METHOD ->  "+tr.getMethod().getMethodName() +" Failed  - CAPTURING SCREENSHOT**");
	      //  WebDriver webDriver = findWebDriverByReflection(tr.getInstance()    );
	        ITestContext context = tr.getTestContext();
		    WebDriver webDriver =  (WebDriver) context.getAttribute("WebDriver");
	        if (webDriver == null) {
	            System.err.println(String.format("The test class '%s' does not have any field/method of type 'org.openqa.selenium.WebDriver'. " +
	                    "ScreenshotTestListener can not continue ...", tr.getInstance().getClass().getSimpleName()));
	            return;
	        }
	        Calendar calendar = Calendar.getInstance();
	        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
	        File src = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
	        try {
	            
	            Path screenshotPath = Paths.get(currentDir, "target/failure_screenshots/", "screenshot_" + tr.getMethod().getMethodName() + "_" + formater.format(calendar.getTime()) + ".png");
	            System.out.println("copying " + screenshotPath);
	            logger.error( "SCREENSHOT for failed method "+ tr.getMethod().getMethodName()+ " Generated at "+screenshotPath);

	            Files.copy(src.toPath(), screenshotPath, StandardCopyOption.REPLACE_EXISTING);
	        } catch (IOException e) {
	            System.err.println("error during the screenshot copy file operation:" + e.getMessage());
	        }
	        logger.error( "**TEST FAILED** - " + tr.getName());
	    }
	    //default visibility of this method for testing purposes
	    WebDriver findWebDriverByReflection(Object obj) {
	        Class<?> c = obj.getClass();
	        Field[] fields = c.getDeclaredFields();
	        WebDriver webDriver = null;
	        for (Field eachField : fields) {
	            eachField.setAccessible(true);
	            if (eachField.getType() == WebDriver.class) {
	                try {
	                    webDriver = (WebDriver) eachField.get(obj);

	                } catch (IllegalAccessException e) {
	                    System.err.println(String.format("error accessing [%s] property of the [%s] instance", eachField.getName(),
	                            obj.getClass().getName()));
	                    return null;
	                } finally {
	                    eachField.setAccessible(false);
	                }
	                break;
	            }
	        }
	        if (webDriver == null){
	            webDriver = tryToFindWebDriverInPublic(obj);
	        }
	        if (webDriver == null){
	            webDriver = tryToFindWebDriverInPrivate(obj);
	        }
	        return webDriver;
	    }

	    /**
	     * Try to find out the WebDriver within public methods, including the inherited methods
	     * @param obj
	     * @return
	     */
	    private static WebDriver tryToFindWebDriverInPublic(Object obj){
	        WebDriver webDriver = null;
	        Method[] methods = obj.getClass().getMethods();
	        return processMethods(methods, obj);
	    }

	    private static WebDriver tryToFindWebDriverInPrivate(Object obj){
	        WebDriver webDriver = null;
	        Method[] methods = obj.getClass().getDeclaredMethods();
	        return processMethods(methods, obj);
	    }

	    private static WebDriver processMethods(Method[] methods, Object obj){
	        for (Method eachMethod : methods) {
	            if (eachMethod.getReturnType() == WebDriver.class) {
	                eachMethod.setAccessible(true);
	                try {
	                    return (WebDriver) eachMethod.invoke(obj);
	                } catch (Exception e) {
	                    System.err.println(String.format("error accessing [%s] method of the [%s] instance. Error: %s",
	                            eachMethod.getName(), obj.getClass().getName(), e.getMessage()));
	                }finally {
	                    eachMethod.setAccessible(false);
	                }
	            }
	        }
	        return null;
	    }

}
