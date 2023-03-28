package training.exeption;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.xmlunit.validation.Languages;
import training.controller.ToDoController;
import training.entity.ToDo;
import training.entity.ToDoStatus;
import training.service.ToDoService;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@ContextConfiguration(classes = GlobalExceptionHandler.class)
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MessageSource messageSource;
    @MockBean
    private ToDoService toDoService;
    @MockBean
    private ModelMapper modelMapper;

    private ToDo toDo1;

    @BeforeEach
    public void setup() throws Exception {
        when(messageSource.getMessage(any(), any(), eq(Locale.ENGLISH))).thenReturn("english error");
        when(messageSource.getMessage(any(), any(), eq(Locale.GERMAN))).thenReturn("Die ID: 6 wurde kein TODO gefunden!!!");

        this.mockMvc = MockMvcBuilders.standaloneSetup(new ToDoController(toDoService, modelMapper)).setControllerAdvice(new GlobalExceptionHandler()).build();
    }

    @Test
    public void entityNotFoundTest() throws Exception {
        Locale.setDefault(Locale.ENGLISH);

        mockMvc.perform(get("/api/todos/6"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Die ID: 6 wurde kein TODO gefunden!!!"));
    }



}