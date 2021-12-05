package com.telran.demoqa.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import com.telran.demoqa.listener.SeleniumListener;

@Listeners(SeleniumListener.class)
public class TestBase {

    public WebDriver driver;
    private static String OS = System.getProperty("os.name").toLowerCase();
    public static final Logger logger = LoggerFactory.getLogger(SeleniumListener.class);


    @BeforeClass
    public void setUp(ITestContext context) {
    	logger.info("=========launching chrome browser============"); 
	    if(OS.contains("win"))
	    {
	    System.setProperty("webdriver.chrome.driver", "src/test/resources/browserBinaries/chromedriver.exe");
	    }
        driver = new ChromeDriver();
        driver.manage().window().maximize();


        driver.get("https://demoqa.com");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        context.setAttribute("WebDriver", driver);
    }

    @AfterClass(enabled = true)
    public void tearDown() {
        driver.quit();
        logger.info("=========Closing chrome browser============"); 
    }

}
