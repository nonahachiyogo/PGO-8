public class Exercise4 {
    public static class LibraryAccount {
        private final String readerName;
        private final int borrowedBooks;
        private final int lateDays;

        public LibraryAccount(String readerName, int borrowedBooks, int lateDays) {
            this.readerName = readerName;
            this.borrowedBooks = borrowedBooks;
            this.lateDays = lateDays;
        }

        // Non-static Inner Class: intimately tied to a specific instance of LibraryAccount
        public class FineCalculator {
            public double calculate() {
                // Directly reads fields of the surrounding outer instance
                return borrowedBooks * lateDays * 1.50;
            }
        }

        public String getReaderName() { return readerName; }
    }

    @FunctionalInterface
    public interface MessagePrinter {
        void print(String message);
    }

    public static void main(String[] args) {
        LibraryAccount account = new LibraryAccount("John Smith", 3, 5);

        // Instantiation syntax explicitly links the calculator to our specific 'account' instance
        LibraryAccount.FineCalculator calculator = account.new FineCalculator();

        double fine = calculator.calculate();

        // MessagePrinter implemented cleanly as a lambda
        MessagePrinter printer = message -> System.out.println("[LIBRARY] " + message);
        printer.print("Reader: " + account.getReaderName() + ", fine: " + fine + " PLN");
        // Expected Output: [LIBRARY] Reader: John Smith, fine: 22.5 PLN
    }
}
