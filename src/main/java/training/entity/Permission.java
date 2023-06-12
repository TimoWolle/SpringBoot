package training.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum Permission {
    TODO_READ("Leserechte ToDo"),
    TODO_READ_ALL("Leserechte für ALLE ToDo"),
    TODO_CREATE("Erstellrechte ToDo"),
    TODO_UPDATE("Updaterechte ToDo"),
    TODO_DELETE("Löschrechte ToDo"),

    USER_READ("Leserechte Benutzer"),
    USER_READ_ALL("Leserechte für ALLE Benutzer"),
    USER_WRITE("Schreibrechte Benutzer");
    private final String permission;
}
