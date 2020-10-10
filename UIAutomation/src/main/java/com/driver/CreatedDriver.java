package com.driver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.util.concurrent.TimeUnit;

import static com.data.BaseData.DEFAULT_WAIT;

public class CreatedDriver extends SetBrowser {

    RemoteWebDriver driver = null;


    public void setDriver(ThreadLocal<RemoteWebDriver> webDriver, String browser) {
        System.setProperty("webdriver.http.factory", "apache");
        if (browser.equalsIgnoreCase(chrome)) {
            driver = new ChromeDriver(setChromeDriver());
        } else if (browser.equalsIgnoreCase(firefox)) {
            driver = new FirefoxDriver(setFirefoxDriver());
        }

        timeouts();
        maximizeWindow();
        webDriver.set(driver);
    }

    private void maximizeWindow() {
        driver.manage().window().maximize();
    }

    private void timeouts() {
        driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT, TimeUnit.SECONDS);
    }



}
