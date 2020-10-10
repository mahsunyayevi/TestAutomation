package com.pages;

import com.shared.log;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UrunDetay extends MainPage {

    String testName = "";
    String browserName = "";

    public UrunDetay(RemoteWebDriver driver, String test, String browser) {
        super(driver);
        browserName = browser;
        testName = test;
    }

    By txtUrunAdi = By.cssSelector("[class='pr-in-w'] div[class='pr-in-nm']");
    By txtUrunFiyat = By.cssSelector("[class='pr-in-w']  span[class*=\"prc\"]");
    By btnSepeteEkle = By.cssSelector("button[class*='add-to-bs']");


    public UrunDetay urunBilgisiDosyaYaz() throws IOException {
        String urunAdi = urunAdiAl();
        String urunFiyat = urunFiyatAl();
        textFileWrite("Ürün Adı: " + urunAdi + " ||| Ürün Fiyatı: " + urunFiyat);
        log.info(browserName + " " + testName + " Ürün Adı: " + urunAdi + " Ürün Fiyatı: " + urunFiyat + " Metin dosyasına yazdırdı");
        return this;
    }

    public UrunDetay urunSepetEkle() {
        click(btnSepeteEkle);
        log.info(browserName + " " + testName + " Ürün Sepete Eklendi");
        return this;
    }

    public String urunFiyatAl() {
        return driver.findElements(txtUrunFiyat).get(1).getText();
    }

    public String urunAdiAl() {
        return getTextOfElement(txtUrunAdi).substring(0,26);
    }


}
