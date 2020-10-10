package com.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class TredyolLibrary extends BaseLibrary {

    public TredyolLibrary(RemoteWebDriver driver) {
        super(driver);
    }

    public TredyolLibrary() {
    }

    protected WebElement sepetUrunKontrol(String columnInput){
        int rowCount = 0;
        List<WebElement> allRows = findElements(By.cssSelector("[class=\"pb-merchant-group\"]"));
        rowCount = allRows.size();
        if (rowCount == 0)
            return null;
        WebElement elem = null;
        for (WebElement row : allRows) {
            try {
                elem = row;
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (elem.findElement(By.cssSelector("[class=\"pb-basket-item\"] a p[class=\"pb-item\"]")).getText().contains(columnInput)) {
                return row;
            }
        }
        return null;
    }
}
