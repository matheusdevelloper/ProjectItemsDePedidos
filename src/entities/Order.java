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
    
    //Método função é adicionar os item em list
    public void addItem(OrderItem item){
        items.add(item);
    }
    
    //Método função é remover os item da list
    public void removeItem(String id) {

        OrderItem productId = items.stream().filter(item -> item.getProduct().getId().equals(id)).findFirst().orElse(null);

        if(productId != null){
            items.remove(productId);
            System.out.println("Product removed successfully!");
            System.out.println();
            System.out.println(toString());
            System.out.println();
        }else {
            System.out.println();
            System.out.println("non-existent product!");
            System.out.println();
        }
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
    
    //Função total() contabilizar o total preço unitaio de todos os items existentes na lista
    public Double total(){
        Double sum = 0.0;
        for(OrderItem item : items){
            sum += item.subTotal();
        }
        return sum;
    }

    //Função imprimir todos os dados do cliente e em seguida os dados do pedido do cliente feito
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ORDER SUMMARY:\n");
        sb.append("Order moment: "+fmt1.format(moment)+"\n");
        sb.append("Order Status: "+status+"\n");
        sb.append("Client: "+client.getName()+" ("+fmt2.format(client.getBirthDate())+") - ("+client.getEmail()+") \n");
        sb.append("----------------------------------\n");
        sb.append("Order items:\n");
        for(OrderItem item : items){
           sb.append("Cod - "+item.getProduct().getId()+", "+item.getProduct().getName()+", $"+String.format("%.2f", item.getProduct().getPrice())
                 +", Quantity: "+item.getQuantity()+", SubTotal: $"+String.format("%.2f", item.subTotal())+"\n");
        }
        sb.append("Total price: $"+String.format("%.2f", total())+"\n");
        sb.append("----------------------------------\n");
        return sb.toString();
        
    }
    
    
}
