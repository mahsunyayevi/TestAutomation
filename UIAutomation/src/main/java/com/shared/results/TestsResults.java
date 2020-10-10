package com.shared.results;

import com.shared.log;
import org.testng.ITestResult;

import static com.google.common.base.Throwables.getStackTraceAsString;

public class TestsResults {

    public TestsResults afterTestrResults(ITestResult testResult){
        String testResults = "";

        String result = "unknown";
        switch (testResult.getStatus()) {
            case 1:
                result = "SUCCESS";
                break;
            case 2:
                result = "FAILURE";
                break;
            case 3:
                result = "SKIP";
                break;
            case 4:
                result = "SUCCESS_PERCENTAGE_FAILURE";
                break;
            case 16:
                result = "STARTED";
                break;
        }
        testResults += "///////////////////////////////////////////////////////";
        testResults += "///////////////////////////////////////////////////////";
        testResults += "\nTEST: " + testResult.getMethod().getMethodName() + "\n";
        testResults += "\nSTATUS: " + result + "\n";
        testResults += "\nDESCRIPTION: " + testResult.getMethod().getDescription() + "\n";
        if (testResult.getThrowable() != null) {
            testResults += "\nERROR: " + getStackTraceAsString(testResult.getThrowable()) + "\n";
        }
        testResults += "///////////////////////////////////////////////////////";
        testResults += "///////////////////////////////////////////////////////";

        log.info(testResults);

        return this;
    }

}
