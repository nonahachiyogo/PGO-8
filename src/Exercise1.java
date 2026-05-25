import java.util.function.Predicate;
import java.util.ArrayList;
import java.util.List;
public class Exercise1 {
    public record UserForm(String email, String password, int age) {
        public UserForm {
            if (email == null || email.isBlank()) {
                throw new IllegalArgumentException("Email cannot be blank");
            }
            if (password == null || password.isBlank()) {
                throw new IllegalArgumentException("Password cannot be blank");
            }
        }
    }

    // Validator class managing a list of functional rules
    public static class UserValidator {
        private final List<Predicate<UserForm>> rules = new ArrayList<>();

        public void addRule(Predicate<UserForm> rule) {
            this.rules.add(rule);
        }

        public boolean isValid(UserForm form) {
            // Evaluates true only if all predicates match the form
            return rules.stream().allMatch(rule -> rule.test(form));
        }
    }

    public static void main(String[] args) {
        UserValidator validator = new UserValidator();

        // Passing business rules dynamically as lambdas
        validator.addRule(form -> form.email().contains("@"));
        validator.addRule(form -> form.password().length() >= 8);
        validator.addRule(form -> form.age() >= 18);

        UserForm validForm = new UserForm("anna@example.com", "secure123", 20);
        UserForm invalidForm = new UserForm("invalid-email", "123", 16);

        System.out.println("Is validForm valid? " + validator.isValid(validForm));     // Expected: true
        System.out.println("Is invalidForm valid? " + validator.isValid(invalidForm)); // Expected: false
    }
}
