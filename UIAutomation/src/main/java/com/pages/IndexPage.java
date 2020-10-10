package com.pages;

import com.shared.log;
import org.apache.poi.ss.formula.functions.Index;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class IndexPage extends MainPage {
    String testName = "";
    String browserName = "";

    public IndexPage(RemoteWebDriver driver, String test, String browser) {
        super(driver);
        browserName = browser;
        testName = test;
    }


    By homePage = By.id("browsing-gw-homepage");
    By btnAccount = By.cssSelector("[class='user-navigation'] [id=\"accountBtn\"]");
    By accountEmail = By.cssSelector("[id='accountBtn'] [class='user-email']");
    By btnSepetim = By.id("myBasketListItem");
    By inpAramaYap = By.cssSelector("[id=\"auto-complete-app\"] input[class='search-box']");

    public IndexPage anaSayfaEkranKontrol() {
        Assert.assertEquals(isElementExist(homePage), true);
        log.info(browserName + " " + testName + " Ana sayfa ekranının açıldığı logonun geldiği doğrulanır");
        return this;
    }


    public IndexPage girisYapNavKontrol() {
        Assert.assertEquals(isElementExist(btnAccount), true);
        log.info(browserName + " " + testName + " Ekranın sağ üstünde üyelik işlemleri giriş yap açılır menu bulunduğu doğrulanır");
        return this;
    }

    public IndexPage accountNav() throws InterruptedException {
        Thread.sleep(2000);
        moveToElement(btnAccount);
        log.info(browserName + " " + testName + " Ekranın sağ üstünde üyelik işlemleri açılır menu");
        return this;
    }

    public IndexPage girisYapBtn(){
        click(btnAccount);
        log.info(browserName + " " + testName + " Giriş yap butonu tıklanır");
        return this;
    }

    public IndexPage kullaniciKontrol(String account){
        String user = getTextOfElement(accountEmail);
        log.info(browserName + " " + testName + " Giriş yapılan Kullanıcı : " + account + " Kontrol edilen Kullanıcı : " + user + " kontrolü başarılı");
        Assert.assertEquals(account,user);
        return this;
    }

    public IndexPage aramaYap(String arama){
        sendKeys(inpAramaYap,arama,true);
        log.info(browserName + " " + testName + " Arama alanında " + arama + " aratılır");
        return this;
    }

    public IndexPage sepetim(){
        click(btnSepetim);
        log.info(browserName + " " + testName + " Sepete Git butonu tıklanır");
        return this;
    }

}
