package training.entity;

public enum ToDoStatus {
    IN_PROGRESS("In Arbeit"),
    COMPLETED("Fertiggestellt"),
    CANCELLED("Abgebrochen");

    private final String name;

    ToDoStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}