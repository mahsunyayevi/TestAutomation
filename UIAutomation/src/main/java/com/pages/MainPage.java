package com.pages;

import com.core.BaseLibrary;
import com.core.TredyolLibrary;
import com.data.BaseData;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class MainPage  extends TredyolLibrary {

    By popupHomePage = By.cssSelector("[class=\"homepage-popup\"]");
    By btnCloseHomePopup = By.cssSelector("[title=\"Close\"]");


    public MainPage() {
    }

    public <T> MainPage(RemoteWebDriver driver) {
        super(driver);
    }


    public MainPage openUrl(){
        navigateTo(BaseData.url);
        return this;
    }


    public MainPage popupCheck(){
     if (isElementExist(popupHomePage)){
        click(btnCloseHomePopup);
     }
    return this;
    }
}
