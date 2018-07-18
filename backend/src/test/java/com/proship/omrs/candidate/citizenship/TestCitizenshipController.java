//package com.proship.omrs.candidate.citizenship;
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.proship.omrs.candidate.citizenship.controller.CitizenshipController;
//import com.proship.omrs.candidate.citizenship.entity.ParticipantCitizenship;
//import com.proship.omrs.candidate.citizenship.entity.ParticipantCitizenshipOverride;
//import com.proship.omrs.candidate.citizenship.repository.ParticipantCitizenshipOverrideRepository;
//import com.proship.omrs.candidate.participant.entity.Participant;
//import com.proship.omrs.citizenship.controller.CitizenshipController;
//import com.proship.omrs.citizenship.entity.Citizenship;
//import com.proship.omrs.citizenship.repository.CitizenshipRepository;
//import com.proship.omrs.user.entity.User;
//import com.proship.omrs.utils.util.Utils;
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.platform.runner.JUnitPlatform;
//import org.junit.runner.RunWith;
//import org.mockito.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import javax.rmi.CORBA.Util;
//import java.sql.Date;
//import java.sql.Timestamp;
//import static org.mockito.Mockito.*;
//import static org.mockito.Mockito.verifyNoMoreInteractions;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(JUnitPlatform.class)
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//public class TestCitizenshipController {
//
//    @Mock
//    ParticipantCitizenshipOverrideRepository repo;
//
//    @InjectMocks
//    CitizenshipController controller;
//
//    @Autowired
//    WebApplicationContext wac;
//
//    MockMvc mockMvc;
//
//    @Captor
//    ArgumentCaptor argCaptor;
//
//    @BeforeAll
//    static void initAll(){
//
//    }
//    @SuppressWarnings("deprecation")
//    @BeforeEach
//    void init(){
//        MockitoAnnotations.initMocks(this);
//        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
//
//    }
//    @Test
//    void testCreate()throws Exception{
//        ParticipantCitizenshipOverride citizenship = setCitizenship();
//        citizenship.setId(null);
//        citizenship.setCreateDate(null);
//
//        String json =  asJsonString(citizenship);
//        doReturn(citizenship).when(repo).save(citizenship);
//
//
//        mockMvc.perform(post("/todo/create")
//                .contentType((MediaType.APPLICATION_JSON))
//                .content(json))
//                .andExpect(status().isOk());
//
//        verify(repo,times(1)).save((Citizenship)argCaptor.capture());
//        Citizenship newTodo =(Citizenship)argCaptor.getValue();
//        assertEquals(citizenship.getDueDate(),newTodo.getDueDate());
//        verifyNoMoreInteractions(repo);
//
//    }
//
//    @Test
//    void testGet()throws  Exception{
//        Citizenship citizenship = setCitizenship();
//
//        when(repo.getOne((long)2)).thenReturn(citizenship);
//
//        this.mockMvc.perform(get("/todo/2"))
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(jsonPath("$.text").value("citizenship test 1"));
//
//    }
//    @Test
//    void testUpdate() throws Exception {
//        ParticipantCitizenshipOverride citizenship = setCitizenship();
//
//        //doNothing().when(repo).save(citizenship);
//
//        when(repo.getOne(citizenship.getId())).thenReturn(citizenship);
//        citizenship.setText("new text");
//
//        String json =  asJsonString(citizenship);
//        doReturn(citizenship).when(repo).save(citizenship);
//
//        mockMvc.perform(put("/todo/"+citizenship.getId())
//                .contentType((MediaType.APPLICATION_JSON))
//                .content(json))
//                .andExpect(status().isOk());
//        verify(repo,times(1)).getOne(citizenship.getId());
//        verify(repo,times(1)).save((Citizenship)argCaptor.capture());
//        ParticipantCitizenship newTodo =(ParticipantCitizenship)argCaptor.getValue();
//        assertEquals(citizenship.getText(),newTodo.getText());
//        verifyNoMoreInteractions(repo);
//    }
//    @Test
//    void testList(){
//
//    }
//    @Test
//    void testDelete() throws Exception {
//        ParticipantCitizenship citizenship = setCitizenship();
//
//        doNothing().when(repo).delete(citizenship.getId());
//
//        mockMvc.perform(
//                delete("/todo/"+ citizenship.getId()))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(citizenship.getId()));
//
//
//        verify(repo, times(1)).delete(citizenship.getId());
//        verifyNoMoreInteractions(repo);
//    }
//    @AfterEach
//    void tearDown(){
//
//    }
//    @AfterAll
//    static void tearAll(){
//
//    }
//    public static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
////    public static ParticipantCitizenshipOverride setCitizenship(){
////
////        ParticipantCitizenshipOverride citizenship = new ParticipantCitizenshipOverride();
////        citizenship.setNexttransactiontime(Utils.getInfiniteTimestamp());
////        ParticipantCitizenship pc = new ParticipantCitizenship();
////        pc.setCountryId(9l);
////        pc.setCreatorId(12l);
////        citizenship.setParticipantCitizenship();
////        citizenship.setDueDate(new Timestamp(new Date(2017,10,5).getTime()));
////        citizenship.setId((long) 2);
////        citizenship.setPriorityId((long)2);
////        citizenship.setText("citizenship test 1");
////        User user = new User();
////        user.setId((long)1);
////        user.setName("testUser");
////        citizenship.setUser(user);
////        return citizenship;
////    }
//}
