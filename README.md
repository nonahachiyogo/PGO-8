# PGO-8
# Java Exercises: Functional Interfaces, Nested Classes, and Records

A clean implementation of four Java mini-applications showcasing modern Java features (Java 17+), functional programming principles, and specific object-oriented design patterns.

---

## 💡 Key Conceptual Explanations

### Exercise 1: User Validation (Records & Predicates)
* The `UserForm` record utilizes a compact constructor (`public UserForm { ... }`). This feature allows us to inject validation rules natively without rewriting boilerplate field assignments (`this.x = x`), ensuring invalid objects cannot be instantiated.
* By storing rules as a `List<Predicate<UserForm>>`, we can freely add or remove validation criteria at runtime via lambdas without modifying the core validation engine.

### Exercise 2: Service Price Calculator (Functional Interfaces)
* It is explicitly annotated with `@FunctionalInterface` and contains **exactly one abstract method** (`calculate`). This unique structural definition is what allows us to swap concrete pricing behaviors cleanly on the fly using concise lambda expressions (`o -> o.hours() * o.hourRate()`).

### Exercise 3: Store Order (Static Nested Classes)
* `OrderItem` is a **static nested class** because an item logically belongs under the namespace of an `Order`, but it does not need access to the instance variables of a specific order parent object to compute its own subtotal.
* Instantiating with `new Order.OrderItem(...)` proves that the nested class can exist completely independently of an outer class instance.

### Exercise 4: Library Account (Inner Classes vs. Lambdas)
* Unlike Exercise 3, `FineCalculator` is a **non-static inner class**. It is intrinsically linked to a live instance of `LibraryAccount` because it must implicitly read the outer class's fields (`borrowedBooks` and `lateDays`) directly from that specific instance context.

---

## 🛠️ Project Requirements

* **Java Version:** Java 17 or higher.
* **Concepts Used:** Records, Lambdas, `Predicate<T>`, Functional Interfaces, Static Nested Classes, Non-static Inner Classes.