package com.proship.omrs.todoList.controller;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.sql.Date;
import java.sql.Timestamp;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import com.proship.omrs.todoList.entity.TodoList;
import com.proship.omrs.todoList.repository.TodoListRepository;
import com.proship.omrs.user.entity.User;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.web.context.WebApplicationContext;
import org.mockito.ArgumentCaptor;

@RunWith(JUnitPlatform.class)
@SpringBootTest
@ExtendWith(SpringExtension.class)
//@SpringJUnitJupiterWebConfig(WebConfig.class)
public class TestTodoListController {
	
	
	@Mock
	TodoListRepository repo;
//	
//	@Mock
//	TodoListController controller;
//	
	@InjectMocks
	TodoListController controller;

	@Autowired
    WebApplicationContext wac;

	MockMvc mockMvc;

    @Captor
    ArgumentCaptor argCaptor;
	
	@BeforeAll
	static void initAll(){

    }
	@SuppressWarnings("deprecation")
	@BeforeEach
	void init(){
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

	}

	@Test
	void testCreate()throws Exception{
        TodoList todoList = setTodoList();
        todoList.setId(null);
        todoList.setCreateDate(null);

        String json =  asJsonString(todoList);
                doReturn(todoList).when(repo).save(todoList);


        mockMvc.perform(post("/todo/create")
                .contentType((MediaType.APPLICATION_JSON))
                .content(json))
                .andExpect(status().isOk());

        verify(repo,times(1)).save((TodoList)argCaptor.capture());
        TodoList newTodo =(TodoList)argCaptor.getValue();
        assertEquals(todoList.getDueDate(),newTodo.getDueDate());
        verifyNoMoreInteractions(repo);

	}
	
	@Test
	void testGet()throws  Exception{
        TodoList todoList = setTodoList();

		when(repo.findOne((long)2)).thenReturn(todoList);

        this.mockMvc.perform(get("/todo/2"))
                .andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.text").value("todoList test 1"));
		
	}
	@Test
	void testUpdate() throws Exception {
        TodoList todoList = setTodoList();

        //doNothing().when(repo).save(todoList);

        when(repo.findOne(todoList.getId())).thenReturn(todoList);
        todoList.setText("new text");

        String json =  asJsonString(todoList);
        doReturn(todoList).when(repo).save(todoList);

        mockMvc.perform(put("/todo/"+todoList.getId())
                .contentType((MediaType.APPLICATION_JSON))
                .content(json))
                .andExpect(status().isOk());
        verify(repo,times(1)).findOne(todoList.getId());
        verify(repo,times(1)).save((TodoList)argCaptor.capture());
        TodoList newTodo =(TodoList)argCaptor.getValue();
        assertEquals(todoList.getText(),newTodo.getText());
        verifyNoMoreInteractions(repo);
	}
	@Test
	void testList(){
		
	}
	@Test
    void testDelete() throws Exception {
        TodoList todoList = setTodoList();

        doNothing().when(repo).delete(todoList.getId());

        mockMvc.perform(
                delete("/todo/"+ todoList.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(todoList.getId()));


        verify(repo, times(1)).delete(todoList.getId());
        verifyNoMoreInteractions(repo);
    }
	@AfterEach
	void tearDown(){
		
	}
	@AfterAll
	static void tearAll(){
		
	}
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static TodoList setTodoList(){

        TodoList todoList = new TodoList();
        todoList.setCreateDate(new Timestamp(System.currentTimeMillis()));
        todoList.setDone(false);
        todoList.setDueDate(new Timestamp(new Date(2017,10,5).getTime()));
        todoList.setId((long) 2);
        todoList.setPriorityId((long)2);
        todoList.setText("todoList test 1");
        User user = new User();
        user.setId((long)1);
        user.setName("testUser");
        todoList.setUser(user);
        return todoList;
    }
}	
