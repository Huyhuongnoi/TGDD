package view;

import model.OrderDetail;
import model.Product;
import service.MobileWorld;

import java.util.ArrayList;
import java.util.Scanner;

public class View {
    public static MobileWorld mobileWorld = new MobileWorld();
    public static Scanner scanner = new Scanner(System.in);

    public static void viewRegistrationInformation() {
        System.out.print("Enter your id: ");
        String customerId = scanner.nextLine();
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter your age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter your sex: ");
        String sex = scanner.nextLine();
        mobileWorld.customerRegistration(customerId, customerName, age, sex);
    }

    public static void viewProduct() {
        ArrayList<Product> productArrayList = mobileWorld.productList();
        for (Product product : productArrayList) {
            System.out.println(product.toString());
        }
    }

    public static void viewPurchaseHistory() {
        System.out.print("Enter your customer id : ");
        String userId = scanner.nextLine();
        ArrayList<OrderDetail> orderDetailArrayList = mobileWorld.purchaseHistory(userId);
        for (OrderDetail orderDetail : orderDetailArrayList) {
            System.out.println(orderDetail.toString());
        }
    }

    public static void viewBuyProduct() {
        System.out.print("Enter customer id: ");
        String customerId = scanner.nextLine();
        System.out.print("Enter product id: ");
        String productId = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        mobileWorld.buyMobilePhone(customerId, productId, quantity);
    }

    public static void main(String[] args) {
        boolean run = true;
        while (run) {
            System.out.println("Welcome to the mobile world\n1.Customer Registration\n2.Buy Mobile Phone\n3.View Purchase History");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    viewRegistrationInformation();
                    break;
                case 2:
                    System.out.println("list of products:");
                    viewProduct();
                    viewBuyProduct();
                    break;
                case 3:
                    viewPurchaseHistory();
                    break;
                default:
                    run = false;
                    break;
            }
        }
    }
}
