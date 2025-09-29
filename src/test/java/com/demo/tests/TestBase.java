package com.demo.tests;

import com.demo.fw.ApplicationManager;
import org.openqa.selenium.remote.Browser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", Browser.CHROME.browserName()));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(enabled = false)
    public void tearDown() {
        app.stop();
    }
    @BeforeMethod
    public void logTestStart(Method method) {
        logger.info("Start test " + method.getName());
    }
    @AfterMethod
    public void logTestStop(ITestResult result) {
        if (result.isSuccess()){
            logger.info("Test passed: " + result.getMethod().getMethodName());
        } else {
            logger.error("Test failed: " + result.getMethod().getMethodName() +
                    "Screenshot path: " + app.getUser().takeScreenshot());
        }
        logger.info("Stop test ");
        logger.info("********************************");
    }
}
