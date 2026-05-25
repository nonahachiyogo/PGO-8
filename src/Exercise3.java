import java.util.ArrayList;
import java.util.List;

public class Exercise3 {
    public static class Order {
        private final String orderNumber;
        private final String customerName;
        private final List<OrderItem> items = new ArrayList<>();

        public Order(String orderNumber, String customerName) {
            this.orderNumber = orderNumber;
            this.customerName = customerName;
        }

        // Static nested class: completely decoupled from an explicit outer class instance
        public static class OrderItem {
            private final String productName;
            private final double unitPrice;
            private final int quantity;

            public OrderItem(String productName, double unitPrice, int quantity) {
                this.productName = productName;
                this.unitPrice = unitPrice;
                this.quantity = quantity;
            }

            public double total() {
                return unitPrice * quantity;
            }
        }

        public void addItem(OrderItem item) {
            items.add(item);
        }

        public double total() {
            return items.stream().mapToDouble(OrderItem::total).sum();
        }

        public String getOrderNumber() { return orderNumber; }
        public String getCustomerName() { return customerName; }
    }

    // Record for summarizing state cleanly
    public record OrderSummary(String orderNumber, String customerName, double totalAmount) {}

    public static void main(String[] args) {
        Order order = new Order("ORD-100", "Anna Kowalska");

        // Instantiation syntax shows that OrderItem does not require a pre-existing order context
        order.addItem(new Order.OrderItem("Keyboard", 249.99, 1));
        order.addItem(new Order.OrderItem("Mouse", 99.99, 2));

        OrderSummary summary = new OrderSummary(
                order.getOrderNumber(),
                order.getCustomerName(),
                order.total()
        );

        System.out.println(summary);
        // Expected Output: OrderSummary[orderNumber=ORD-100, customerName=Anna Kowalska, totalAmount=449.97]
    }
}
