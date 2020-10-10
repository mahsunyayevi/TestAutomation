package entity.order;


import lombok.Data;

@Data
public class Order {
    private int id;
    private int petId;
    private int quantity;
    private String shipDate;
    private String status;
    private boolean complete;
}
