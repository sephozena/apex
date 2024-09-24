package functions;

import org.openqa.selenium.WebDriver;
import base.BaseClass;

public class OrderManagement extends BaseClass {
    private OrderActions orderActions;

    public OrderManagement(WebDriver driver) {
        super();
        this.driver = driver;
        this.orderActions = new OrderActions();
    }

    public void manageOrders() {
        orderActions
            .createOrder("Sample Order")
            .updateOrder("Sample Order", "Updated Order")
            .deleteOrder("Updated Order");
    }

    public class OrderActions {
        public OrderActions createOrder(String orderName) {
            System.out.println("Order created: " + orderName);
            return this;
        }

        public OrderActions updateOrder(String oldOrderName, String newOrderName) {
            System.out.println("Order updated from: " + oldOrderName + " to " + newOrderName);
            return this;
        }

        public OrderActions deleteOrder(String orderName) {
            System.out.println("Order deleted: " + orderName);
            return this;
        }
    }
}
