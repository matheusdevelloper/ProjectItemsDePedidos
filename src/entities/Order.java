package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import entities_enums.OrderStatus;

public class Order {

    private LocalDateTime moment;
    private OrderStatus status;

    private List<OrderItem> items = new ArrayList<>();

    public Order() {
    }

    public Order(LocalDateTime moment, OrderStatus status) {
        this.moment = moment;
        this.status = status;
    }

    public LocalDateTime getMoment() {
        return moment;
    }

    public void setMoment(LocalDateTime moment) {
        this.moment = moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    
    public void addItem(OrderItem item){

    }

    public void removeItem(OrderItem item) {

    }

    public Double total(){
        Double sum = 0.0;
        for(OrderItem item : items){
            sum += item.subTotal();
        }
        return sum;
    }
    
}
