package training.entity;

public enum TodoStatus {
    IN_PROGRESS("In Arbeit"),
    COMPLETED("Fertiggestellt"),
    CANCELLED("Abgebrochen");

    private final String name;

    TodoStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}