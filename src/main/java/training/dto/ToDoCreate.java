package training.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import training.entity.ToDoStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToDoCreate {

    @NotBlank
    private String titel;
    private String description;
    @NotNull
    private Integer prio;
    @NotBlank
    private String dueDate;
    @NotNull
    private ToDoStatus status;
}
