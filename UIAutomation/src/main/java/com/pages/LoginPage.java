package com.pages;

import com.shared.log;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LoginPage extends MainPage {


    String testName = "";
    String browserName = "";

    public LoginPage(RemoteWebDriver driver, String test, String browser) {
        super(driver);
        browserName = browser;
        testName = test;
    }


    By inpEmail = By.id("login-email");
    By inpSifre = By.id("login-password-input");
    By btnGirisYap = By.cssSelector("[class='q-layout login'] button[type='submit']");


    public LoginPage emailDoldur(String email) {
        sendKeys(inpEmail, email);
        log.info(browserName + " " + testName + " Login ekranın Email : " + email + " olarak doldurulur");
        return this;
    }

    public LoginPage sifreDoldur(String sifre) {
        sendKeys(inpSifre, sifre);
        log.info(browserName + " " + testName + " Login ekranın Sifre  : " + sifre + " olarak doldurulur");
        return this;
    }

    public LoginPage girisYapBtn() {
        click(btnGirisYap);
        log.info(browserName + " " + testName + " Login ekranın giriş yap butonu tıklanır");
        return this;
    }


}
