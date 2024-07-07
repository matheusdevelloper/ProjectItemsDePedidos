package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import entities_enums.OrderStatus;

public class Order {
    
    DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private LocalDateTime moment;
    private OrderStatus status;
    
    private Client client;
    private List<OrderItem> items = new ArrayList<>();

    public Order() {
    }


    public Order(LocalDateTime moment, OrderStatus status, Client client) {
        this.moment = moment;
        this.status = status;
        this.client = client;
    }

    public void addItem(OrderItem item){
        items.add(item);
    }

    public void removeItem(OrderItem item) {
        items.remove(item);
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


    public Client getClient() {
        return client;
    }


    public void setClient(Client client) {
        this.client = client;
    }


    public List<OrderItem> getItems() {
        return items;
    }
    
    public Double total(){
        Double sum = 0.0;
        for(OrderItem item : items){
            sum += item.subTotal();
        }
        return sum;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ORDER SUMMARY:\n");
        sb.append("Order moment: "+fmt1.format(moment)+"\n");
        sb.append("Order Status: "+status+"\n");
        sb.append("Client: "+client.getName()+" ("+fmt2.format(client.getBirthDate())+") - ("+client.getEmail()+") \n");
        sb.append("Order items:\n");
        for(OrderItem item : items){
           sb.append(item.getProduct().getName()+", $"+String.format("%.2f", item.getProduct().getPrice())
                 +", Quantity: "+item.getQuantity()+", SubTotal: $"+String.format("%.2f", item.subTotal())+"\n");
        }
        sb.append("Total price: $"+String.format("%.2f", total()));
        return sb.toString();
        
    }
    
    
}
