package training.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import training.entity.ToDo;
import training.entity.ToDoStatus;
import training.service.ToDoService;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ToDoController.class)
class ToDoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToDoService toDoService;

    @MockBean
    private ModelMapper modelMapper;

    private ToDo toDo1;

    @BeforeEach
    public void setup() {
        toDo1 = new ToDo();
        toDo1.setId(1L);
        toDo1.setTitel("ToDo 1");
        toDo1.setDescription("Description for ToDo 1");
        toDo1.setPrio(1);
        toDo1.setDueDate("2022-12-31");
        toDo1.setStatus(ToDoStatus.IN_PROGRESS);
    }


    @WithMockUser
    @Test
    void deleteToDo() throws Exception {
        mockMvc.perform(delete("/api/todos/1")
                .with(csrf()))
                .andExpect(status().isOk());
    }

    @WithMockUser
    @Test
    void getToDo() throws Exception {

        when(toDoService.getTodo(1)).thenReturn(toDo1);

        mockMvc.perform(get("/api/todos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.titel").value(toDo1.getTitel()))
                .andExpect(jsonPath("$.description").value(toDo1.getDescription()))
                .andExpect(jsonPath("$.prio").value(toDo1.getPrio()))
                .andExpect(jsonPath("$.dueDate").value(toDo1.getDueDate()))
                .andExpect(jsonPath("$.status").value(toDo1.getStatus().toString()));

        verify(toDoService, times(1)).getTodo(1);
    }

    @WithMockUser
    @Test
    public void getAllToDos() throws Exception
    {

        List<ToDo> toDoList = new ArrayList<>();
        toDoList.add(toDo1);
        toDo1.setId(2L);
        toDoList.add(toDo1);
        toDo1.setId(3L);
        toDoList.add(toDo1);
        when(this.toDoService.getAllTodos()).thenReturn(toDoList);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/todos"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                                [
                                    {"id":3,"titel":"ToDo 1","description":"Description for ToDo 1","prio":1,"dueDate":"2022-12-31","status":"IN_PROGRESS"},
                                    {"id":3,"titel":"ToDo 1","description":"Description for ToDo 1","prio":1,"dueDate":"2022-12-31","status":"IN_PROGRESS"},
                                    {"id":3,"titel":"ToDo 1","description":"Description for ToDo 1","prio":1,"dueDate":"2022-12-31","status":"IN_PROGRESS"}
                                ]
                                """
                ));

    }
}