package tests;

import entity.user.User;
import io.restassured.response.Response;
import method.UserLibrary;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import runner.TestsRunner;
import utility.log;

public class UserTest extends TestsRunner {


    private User user;
    private UserLibrary userLibrary;


    @BeforeMethod
    public void BeforeMethod(){
        user = new User();
        userLibrary = new UserLibrary();
    }

    private int id = 0;
    private String username = createRandomText(10);
    private String firstName = createRandomText(5);
    private String lastName = createRandomText(7);
    private String email = createRandomText(5) + "gmail.com";
    private String password = createRandomText(9);
    private String phone = createTEL();
    private int userStatus = 0;



    @Test(enabled = true, description = "Created User")
    public void TS0001() {
        user.setId(id);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setUserStatus(userStatus);
        userLibrary.createdUser(user);
    }

    @Test(enabled = true, description = "Get User Control" , dependsOnMethods = {"TS0001"} )
    public void TS0002() {
        user.setId(id);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setUserStatus(userStatus);
        Response response = userLibrary.getUser(user);

        log.info("Response statu code : " + response.getStatusCode());

        log.info(response.jsonPath().getString("firstName"));

        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200.");
        log.info("Got user by username");
    }

    @Test(enabled = true, description = "Get User Login" , dependsOnMethods = {"TS0001"} )
    public void TS0003() {
        user.setUsername(username);
        user.setPassword(password);
        Response response = userLibrary.getUserLogin(user);
        log.info("Response statu code : " + response.getStatusCode());
        log.info("Response login success");
    }




}
