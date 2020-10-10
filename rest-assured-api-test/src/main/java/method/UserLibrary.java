package method;

import com.google.gson.Gson;
import entity.user.LoginResponse;
import entity.user.User;
import entity.user.UserResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import utility.log;
import static data.BaseData.USER_BASE_URL;
import static io.restassured.RestAssured.given;

public class UserLibrary {
    private RequestSpecification request = RestAssured.given().contentType(ContentType.JSON);
    private Gson gson = new Gson();


    public Response createdUser(User user) {

        JSONObject requestParams = new JSONObject();
        requestParams.put("id", user.getId());
        requestParams.put("username", user.getUsername());
        requestParams.put("firstname", user.getFirstName());
        requestParams.put("lastname", user.getLastName());
        requestParams.put("email", user.getEmail());
        requestParams.put("password", user.getPassword());
        requestParams.put("phone", user.getPhone());
        requestParams.put("userStatus", user.getUserStatus());

        log.info("userRequest json: " + requestParams.toJSONString());

        Response response = given().contentType(ContentType.JSON).body(requestParams).when().post(USER_BASE_URL);
        log.info("Response statu code : " + response.getStatusCode());

        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200.");
        log.info("New user is created");

        return response;
    }

    public Response getUser(User user) {

        Response response = request.contentType(ContentType.JSON).get(USER_BASE_URL + user.getUsername());
        log.info("Response statu code : " + response.getStatusCode());

        String responseBody = response.body().asString();

        UserResponse userResponse = gson.fromJson(responseBody, UserResponse.class);


        Assert.assertEquals(user.getUsername(), userResponse.getUsername());
        log.info("Post User Username: " + user.getUsername() + "Response User Username: " + userResponse.getUsername());

        Assert.assertEquals(user.getFirstName(), userResponse.getFirstName());
        log.info("Post User FirstName: " + user.getFirstName() + "Response User FirstName: " + userResponse.getFirstName());

        Assert.assertEquals(user.getLastName(), userResponse.getLastName());
        log.info("Post User LastName: " + user.getLastName() + "Response User LastName: " + userResponse.getLastName());

        Assert.assertEquals(user.getEmail(), userResponse.getEmail());
        log.info("Post User Email: " + user.getEmail() + "Response User Email: " + userResponse.getEmail());

        Assert.assertEquals(user.getPassword(), userResponse.getPassword());
        log.info("Post User Password: " + user.getPassword() + "Response User Password: " + userResponse.getPassword());

        Assert.assertEquals(user.getPhone(), userResponse.getPhone());
        log.info("Post User Phone: " + user.getPhone() + "Response User Phone: " + userResponse.getPhone());

        Assert.assertEquals(user.getUserStatus(), userResponse.getUserStatus());
        log.info("Post User UserStatus: " + user.getUserStatus() + "Response User Id: " + userResponse.getUserStatus());

        return response;
    }

    public Response getUserLogin(User user) {
        Response response = request.contentType(ContentType.JSON).get(USER_BASE_URL +user.getUsername());
        log.info("Response statu code : " + response.getStatusCode());

        return response;
    }

}
