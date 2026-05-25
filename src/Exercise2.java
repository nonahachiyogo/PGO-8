public class Exercise2 {
    public record ServiceOrder(String clientName, int hours, double hourRate) {}

    @FunctionalInterface
    public interface PriceStrategy {
        double calculate(ServiceOrder order);
    }

    public static class PriceCalculator {
        public double calculate(ServiceOrder order, PriceStrategy strategy) {
            return strategy.calculate(order);
        }
    }

    public static void main(String[] args) {
        ServiceOrder order = new ServiceOrder("Alpha Company", 10, 120.0);
        PriceCalculator calculator = new PriceCalculator();

        // Concrete strategic pricing rules encapsulated as clean lambdas
        PriceStrategy standard = o -> o.hours() * o.hourRate();
        PriceStrategy discount = o -> o.hours() * o.hourRate() * 0.90;
        PriceStrategy weekend = o -> o.hours() * o.hourRate() * 1.25;

        System.out.println("Standard Price: " + calculator.calculate(order, standard)); // Expected: 1200.0
        System.out.println("Discount Price: " + calculator.calculate(order, discount)); // Expected: 1080.0
        System.out.println("Weekend Price:  " + calculator.calculate(order, weekend));  // Expected: 1500.0
    }
}
