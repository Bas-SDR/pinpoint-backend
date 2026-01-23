package org.basr.pinpoint.controller;

import com.jayway.jsonpath.JsonPath;
import org.basr.pinpoint.model.User;
import org.basr.pinpoint.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateNewUser() throws Exception {

        String requestJson = """
                {
                    "firstName": "Bob",
                    "lastName": "Doe",
                    "email": "bobdoe@email.com",
                    "password": "password"
                }
                """;

        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.post("/users")
                .contentType(APPLICATION_JSON)
                .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        String locationHeader = result.getResponse().getHeader("Location");

        //https://stackoverflow.com/questions/47763332/how-to-extract-value-from-json-response-when-using-spring-mockmvc?rq=3
        String id = JsonPath.read(result.getResponse().getContentAsString(), "$.id").toString();
        String firstName = JsonPath.read(result.getResponse().getContentAsString(), "$.firstName").toString();
        String lastName = JsonPath.read(result.getResponse().getContentAsString(), "$.lastName").toString();

        assertNotNull(locationHeader);
        assertTrue(locationHeader.matches(".*/users/" + id));
        assertEquals("Bob", firstName);
        assertEquals("Doe", lastName);
    }
}