package training.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import training.entity.ToDoStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToDoUpdate {
    @NotNull
    @Positive
    private Long id;
    @NotNull
    private Integer prio;
    @NotBlank
    private String dueDate;
    @NotNull
    private ToDoStatus status;
}
