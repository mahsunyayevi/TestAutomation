package com.pages;


import com.shared.log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class Sepet extends MainPage {


    String testName = "";
    String browserName = "";

    public Sepet(RemoteWebDriver driver, String test, String browser) {
        super(driver);
        browserName = browser;
        testName = test;
    }


    By txturunFiyat = By.cssSelector("[class=\"pb-basket-item-price\"]");
    By textUrunSilOnay = By.cssSelector("[class=\"content-body\"] p[class=\"description\"] span");
    By btnUrunArttir = By.cssSelector("button[class='ty-numeric-counter-button']");
    By btnUrunSil = By.cssSelector("[class=\"pb-basket-item-actions\"] button i");
    By btnUrunSilOnay = By.cssSelector("[class=\"content-body\"] button[class*='btn-remove']");
    By sepetKontrol = By.cssSelector("[id=\"basketNoProductPage\"] [class*='emptyBasketInfoBox '] span");


    public Sepet UrunVeFiyatKontrol(String urun, String fiyat) {
        WebElement element = sepetUrunKontrol(urun);
        log.info(browserName + " " + testName + " Ürün sepet ekranın listelendiği kontrolü başarılı");
        String urunfiyat = element.findElements(txturunFiyat).get(0).getText();
        if (urunfiyat.contains(fiyat)) {
            Assert.assertEquals(urunfiyat.contains(fiyat), true);
            log.info(browserName + " " + testName + " Ürün fiyat kontrolü başarılı");
        }
        return this;
    }

    public Sepet urunSayisArttir(String urun, int urunSayisi) {
        WebElement element = sepetUrunKontrol(urun);
        log.info(browserName + " " + testName + " Ürün sepet ekranın listelendiği kontrolü başarılı");
        for (int i = 0; i < urunSayisi; i++) {
            clickJS(element.findElement(btnUrunArttir));
        }
        return this;
    }

    public Sepet urunSil(String urun) throws InterruptedException {
        Thread.sleep(1000);
        WebElement element = sepetUrunKontrol(urun);
        log.info(browserName + " " + testName + " Ürün sepet ekranın listelendiği kontrolü başarılı");
        element.findElement(btnUrunSil).click();
        log.info(browserName + " " + testName + " Ürün sepeten kaldırıldı");
        return this;
    }

    public Sepet urunSilOnay(String urun) throws InterruptedException {
        waitElement(textUrunSilOnay);
        String silinenUrun = getTextOfElement(textUrunSilOnay);
        Assert.assertEquals(silinenUrun.contains(urun), true);
        log.info(browserName + " " + testName + " Silmek istenilen ürün: " + urun + " Silinen Ürün: " + silinenUrun);
        click(btnUrunSilOnay);
        log.info(browserName + " " + testName + " Ürün başarıyla silindi");
        return this;
    }

    public Sepet silinenUrunKontrol(String mesaj) throws InterruptedException {
        waitElement(sepetKontrol);
        String sepetMesaj = getTextOfElement(sepetKontrol);
        Assert.assertEquals(sepetMesaj,mesaj);
        log.info(browserName + " " + testName + " Sepette ürün olmadığı kontrolü başarılı");
        return this;
    }
}
