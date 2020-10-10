package com.core;

import com.data.BaseData;
import com.shared.log;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseTest {


    public static ThreadLocal<RemoteWebDriver> wbdriver = new ThreadLocal<RemoteWebDriver>();


    public void testStarted(String testid, String browser) {
        log.info(browser + " Tarayıcısında Web uygulaması başlatıldı");
        log.info(browser + "  URL " + BaseData.url + " Başarıyla açıldı");
        log.info(browser + " " + testid + "  Test Başlatılmıştır" );
    }






}
