package method;

import com.google.gson.Gson;
import entity.order.Order;
import entity.order.OrderResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import utility.log;

import static data.BaseData.ORDER_BASE_URL;
import static io.restassured.RestAssured.given;

public class OrderLibrary {

    private Gson gson = new Gson();


    public Response createdOrder(Order order) {

        JSONObject requestParams = new JSONObject();
        requestParams.put("id", order.getId());
        requestParams.put("petId", order.getPetId());
        requestParams.put("quantity", order.getQuantity());
        requestParams.put("shipDate", order.getShipDate());
        requestParams.put("status", order.getStatus());
        requestParams.put("complete", true);

        log.info("userRequest json: " + requestParams.toJSONString());

        Response response = given().contentType(ContentType.JSON).body(requestParams).when().post(ORDER_BASE_URL);
        log.info("Response statu code : " + response.getStatusCode());

        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200.");
        log.info("New Order is created");

        String responseBody = response.body().asString();

        OrderResponse orderResponse = gson.fromJson(responseBody, OrderResponse.class);

        log.info(orderResponse.toString());


        Assert.assertEquals(order.getPetId(), orderResponse.getPetId());
        log.info("Post Order Pet id: " + order.getPetId() + "Response Order Pet id: " + orderResponse.getPetId());

        Assert.assertEquals(order.getQuantity(), orderResponse.getQuantity());
        log.info("Post Order Quantity: " + order.getQuantity() + "Response Order uantity: " + orderResponse.getQuantity());

        Assert.assertEquals(order.getShipDate(), orderResponse.getShipDate());
        log.info("Post Order ShipDate: " + order.getShipDate() + "Response Order ShipDate: " + orderResponse.getShipDate());

        Assert.assertEquals(order.getStatus(), orderResponse.getStatus());
        log.info("Post Order Status: " + order.getStatus() + "Response Order Status: " + orderResponse.getStatus());

        return response;
    }


}
