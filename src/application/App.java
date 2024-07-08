import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import entities.Client;
import entities.Order;
import entities.OrderItem;
import entities.Product;
import entities_enums.OrderStatus;

public class App {
    public static void main(String[] args) throws Exception {
       
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        

        System.out.println("Enter cliente data: ");
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Birth data (DD/MM/YYYY): ");
        String birthStr = sc.nextLine();
        LocalDate birth = LocalDate.parse(birthStr, fmt);
        

        System.out.println("Enter order data: ");
        System.out.print("Status: ");
        String status = sc.nextLine();
        Order order = new Order(LocalDateTime.now(), OrderStatus.valueOf(status), new Client(name, email, birth));
        
        do {

                
            System.out.println("===== Order System =====");
            System.out.print("1 - Add Product\n"
                            +"2 - Remover Product\n"
                            +"3 - Sair\n");
            int desire = sc.nextInt();
            sc.nextLine();

            switch (desire) {

                case 1:

                    System.out.print("How many items to this order? ");
                    int items = sc.nextInt();
                    sc.nextLine();
            
                    for(int i=0; i<items; i++){
            
                        System.out.println("Enter #"+(i+1)+" item data:");
                        System.out.print("Product name: ");
                        String nameProduct = sc.nextLine();
                        System.out.print("Id product: ");
                        String id = sc.next().substring(0, 2);
                        System.out.print("Product price: ");
                        double price = sc.nextDouble();
                        System.out.print("Quantity: ");
                        int quantity = sc.nextInt();
                        sc.nextLine();
                        OrderItem orderItem = new OrderItem(quantity, price, new Product(id, nameProduct, price));
                        order.addItem(orderItem);
                        
                
                    }
                    System.out.println();
                    System.out.println("Product added successfully!");
                    System.out.println();
                    System.out.println(order.toString());
                    
                    break;

                case 2:
                    
                    System.out.print("What is the product code you want to remove? ");
                    String id = sc.next().substring(0, 2);
                    order.removeItem(id);
                   
                    break;

                case 3:
                    sc.close();
                    return;
                default:
                    break;
            }

        }while(true);

        
       

    }
}
