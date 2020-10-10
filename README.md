# Test Automation

A Trendyol account needs to be entered in the excel file in the documents folder in the home directory.

## Cross Driver
```testNG
@Parameters({"browser"})
@BeforeMethod
public void launchDriver(@Optional String browser, Method method) throws IOException {

     CreatedDriver createdDriver = new CreatedDriver();
     // Closed
     // createdDriver.setDriver(wbdriver, "firefox");

     //Open
     createdDriver.setDriver(wbdriver, browser);

     MainPage mainPage = new MainPage(getDriver());
     mainPage
             .openUrl()
             .popupCheck();

     testNo = getTestName(method);

     testStarted(testNo, getBrowserName());
}
```

Cross Test Runner under Test Suite folder in home directory to run You must use the xml file.
