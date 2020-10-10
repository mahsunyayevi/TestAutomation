package com.runner;

import com.core.BaseTest;

import com.driver.CreatedDriver;
import com.pages.MainPage;
import com.shared.results.TestsResults;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.lang.reflect.Method;

public class TestsRunner extends BaseTest {

    public static String testNo;

    @Parameters({"browser"})
    @BeforeMethod
    public void launchDriver(@Optional String browser, Method method) throws IOException {

        CreatedDriver createdDriver = new CreatedDriver();
        // Closed
        createdDriver.setDriver(wbdriver, "firefox");

        //Open
        // createdDriver.setDriver(wbdriver, browser);

        MainPage mainPage = new MainPage(getDriver());
        mainPage
                .openUrl()
                .popupCheck();

        testNo = getTestName(method);

        testStarted(testNo, getBrowserName());
    }

    @AfterMethod
    public void afterMethod(ITestResult testResult) {
        TestsResults testsResults = new TestsResults();
        testsResults.afterTestrResults(testResult);

        quitDriver();
    }

    public String getBrowserName() {
        Capabilities cap = (getDriver()).getCapabilities();
        return cap.getBrowserName().toUpperCase();
    }

    public String getTestName(Method method) {
        return method.getName();
    }

    public RemoteWebDriver getDriver() {
        return wbdriver.get();
    }

    public void quitDriver() {
        wbdriver.get().quit();
    }


}
