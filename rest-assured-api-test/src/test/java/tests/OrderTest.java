package tests;

import entity.order.Order;
import entity.user.User;
import method.OrderLibrary;
import method.UserLibrary;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import runner.TestsRunner;

public class OrderTest extends TestsRunner {

    private Order order;
    private OrderLibrary orderLibrary;
    @BeforeMethod
    public void BeforeMethod(){
        order = new Order();
        orderLibrary = new OrderLibrary();
    }

    private int id = 0;
    private int petId = 0;
    private int quantity = 0;
    private String shipDate = getNowDateFormat();
    private String status = "placed";
    private boolean complete = true;



    @Test(enabled = true, description = "Created Store Order")
    public void TS0001() {
        order.setId(id);
        order.setPetId(petId);
        order.setQuantity(quantity);
        order.setShipDate(shipDate);
        order.setStatus(status);
        order.setComplete(complete);
        orderLibrary.createdOrder(order);
    }



}
