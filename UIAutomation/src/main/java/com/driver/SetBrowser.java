package com.driver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SetBrowser {

    protected final String chrome = "chrome";
    protected final String firefox = "firefox";

    protected DesiredCapabilities setChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        DesiredCapabilities capability = DesiredCapabilities.chrome();
        capability.setCapability(ChromeOptions.CAPABILITY, options);
        capability.setBrowserName(chrome);
        capability.setJavascriptEnabled(true);
        return capability;
    }

    protected DesiredCapabilities setFirefoxDriver() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        DesiredCapabilities capability = DesiredCapabilities.firefox();
        capability.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
        capability.setBrowserName(firefox);
        capability.setJavascriptEnabled(true);
        return capability;
    }
}
