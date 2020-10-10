package com.core;

import com.data.BaseData;
import com.shared.log;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BaseLibrary {


    protected RemoteWebDriver driver;

    public <T> BaseLibrary(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public BaseLibrary() {
    }


    public void navigateTo(String url) {
        try {
            driver.get(url);
            driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
            Wait(500);
        } catch (Exception e) {
            log.info("Error while getting app url : " + e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Use this method to find element by cssSelector
     *
     * @param by
     * @param index
     * @return A WebElement, or an empty if nothing matches
     * @throws InterruptedException
     */
    protected WebElement findElement(By by, int... index)  {
        WebElement element = null;
        try {
            if (index.length == 0)
                element = driver.findElement(by);
            else
                element = driver.findElements(by).get(index[0]);

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);arguments[0].focus();",
                    element);
        } catch (Exception e) {
            log.error("Error while clicking webelement : " + e);
            throw new RuntimeException(e);
        }
        return element;
    }
    /**
     * Use this method to find elements by cssSelector
     *
     * @param by
     * @return A list of WebElements, or an empty if nothing matches
     */
    protected List<WebElement> findElements(By by)  {
        List<WebElement> webElements = null;
        try {
            webElements = driver.findElements(by);
        } catch (Exception e) {
            log.error("Error while listing webelements by css selector : " + e);
            log.info("Error while listing webelements by css selector : " + e);

            throw new RuntimeException(e);
        }
        return webElements;
    }


    /**
     * Use this method click to element
     *
     * @param by
     * @throws InterruptedException
     */
    protected void click(By by) {
        WebElement element;
        try {
            element = driver.findElement(by);
            element.click();
        } catch (Exception e) {
            log.info("Error while clicking webelement : " + e);

            throw new RuntimeException(e);
        }
    }
    /**
     * Use this method click to element
     *
     * @param by
     * @throws InterruptedException
     */
    protected void click(By by,int size) {
        WebElement element;
        try {
            element = driver.findElements(by).get(size);
            element.click();
        } catch (Exception e) {
            log.info("Error while clicking webelement : " + e);

            throw new RuntimeException(e);
        }
    }

    protected void clickJS(By by)  {
        WebElement element = findElement(by);
        JavascriptExecutor executor = driver;
        executor.executeScript("arguments[0].click();", element);
    }

    protected void clickJS(WebElement element)  {
        JavascriptExecutor executor = driver;
        executor.executeScript("arguments[0].click();", element);
    }

    protected void waitElement(By by) throws InterruptedException {
        boolean durum = getTextOfElement(by).length() > 0;
        int i = 0;
        while (!durum) {
            Thread.sleep(1000);
            durum = getTextOfElement(by).length() > 0;
            i += 1;
            if (i == 20) {
                break;
            }
        }

    }

    /**
     * Use this method to simulate typing into an element if it is enable. Send
     * enter if pressEnter is true, do nothing otherwise. Note : Before sending
     * operation, element is cleared.
     *
     * @param text
     * @param by
     * @throws InterruptedException
     */
    protected void sendKeys(By by, String text) {
        WebElement element = null;
        try {
            element = findElement(by);
            element.sendKeys(text);
        } catch (Exception e) {
            log.error("Error while filling field : " + e);

            throw new RuntimeException(e);
        }
    }

    /**
     * Use this method to simulate typing into an element if it is enable. Send
     * enter if pressEnter is true, do nothing otherwise. Note : Before sending
     * operation, element is cleared.
     *
     * @param by
     * @param text
     * @param pressEnter
     * @throws InterruptedException
     */
    protected void sendKeys(By by, String text, boolean pressEnter)  {

        WebElement element = null;
        try {
            element = findElement(by);
                element.sendKeys(text);
                if (pressEnter) {
                    element.sendKeys(Keys.ENTER);
                }
        } catch (Exception e) {
            log.error("Error while filling field : " + e);
            throw new RuntimeException(e);
        }
    }


    /**
     *
     * @param by
     * @throws InterruptedException
     */
    protected void moveToElement(By by){
        Actions builder = new Actions(driver);
        WebElement element = findElement(by);
        builder.moveToElement(element).build().perform();
    }

    /**
     * Return true if element exist, false otherwise.
     *
     * @param by
     * @return True if element exists, false otherwise.
     */
    protected boolean isElementExist(By by) {
        return isElementExist(by, 15);
    }

    protected boolean isElementExist(By by, int timeSeconds) {

        driver.manage().timeouts().implicitlyWait(timeSeconds, TimeUnit.SECONDS);
        boolean isExist = driver.findElements(by).size() > 0;
        driver.manage().timeouts().implicitlyWait(BaseData.DEFAULT_WAIT, TimeUnit.SECONDS);

        return isExist;
    }


    public void Wait(int millisecond) throws InterruptedException {
        Thread.sleep(millisecond);
    }

    /**
     * Get the visible (i.e. not hidden by CSS) innerText of this element.
     *
     * @param by
     * @param index
     * @return The innerText of this element.
     * @throws InterruptedException
     */
    protected String getTextOfElement(By by, int... index) {
        String text = null;
        try {
            if (index.length == 0)
                text = driver.findElement(by).getText();
            else
                text = driver.findElements(by).get(index[0]).getText();
        } catch (Exception e) {

            throw new RuntimeException(e);
        }
        return text;
    }


    protected void textFileWrite(String text) throws IOException {
        File filedata = new File("documents/urun.txt");
        FileWriter writecsv = new FileWriter(filedata);
        writecsv.write(text);
        writecsv.close();
    }

}
