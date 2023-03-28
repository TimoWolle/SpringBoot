package training.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import training.entity.ToDo;
import training.entity.ToDoStatus;
import training.service.ToDoService;
import org.modelmapper.ModelMapper;

import static org.mockito.Mockito.*;
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

    @Test
    void insert() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(get("/api/todos/1"))
                .andExpect(status().isOk());
    }

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

    @Test
    public void getAllToDos() throws Exception
    {
        //LISTE NOCH EINFÜGEN when(this.toDoService.getAllTodos()).thenReturn(this.);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                                [
                                    {
                                        "id": 1,
                                        "title": "Schulung",
                                        "description": "Springboot",
                                        "date": "März",
                                        "status": true
                                    },
                                    {
                                        "id": 2,
                                        "title": "Termin",
                                        "description": "Hausarzt",
                                        "date": "Morgen",
                                        "status": false
                                    },
                                    {
                                        "id": 3,
                                         "title": "Termin",
                                        "description": "Hausarzt",
                                        "date": "Morgen",
                                        "status": false
                                    }
                                ]
                                """
                ));

    }
}