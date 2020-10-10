package com.pages;

import com.shared.log;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Urunler extends MainPage {


    String testName = "";
    String browserName = "";

    public Urunler(RemoteWebDriver driver, String test, String browser) {
        super(driver);
        browserName = browser;
        testName = test;
    }

    By urun = By.cssSelector("[class='prdct-cntnr-wrppr'] [class='p-card-wrppr'][data-id=\"51499396\"]");


    public Urunler urunSec(){
        click(urun);
        log.info(browserName + " " + testName + " Arama Sonuçların rasgele bir ürün seçildi");
        return this;
    }

}
