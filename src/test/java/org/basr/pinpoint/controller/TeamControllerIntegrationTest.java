package org.basr.pinpoint.controller;

import com.jayway.jsonpath.JsonPath;
import org.basr.pinpoint.model.User;
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
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = true)
@ActiveProfiles("test")
class TeamControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "test", roles = "ADMIN")
    void shouldCreateNewTeam() throws Exception {

        String requestJson = """
                {
                    "teamName": "TeamTesters",
                    "captainId": 1
                }
                """;

        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.post("/teams")
                        .contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        String locationHeader = result.getResponse().getHeader("Location");

        String id = JsonPath.read(result.getResponse().getContentAsString(), "$.id").toString();
        String teamName = JsonPath.read(result.getResponse().getContentAsString(), "$.teamName").toString();
        String captainId = JsonPath.read(result.getResponse().getContentAsString(), "$.captainId").toString();
        String captainFirstName = JsonPath.read(result.getResponse().getContentAsString(), "$.captainFirstName").toString();
        String captainLastName = JsonPath.read(result.getResponse().getContentAsString(), "$.captainLastName").toString();

        assertNotNull(locationHeader);
        assertTrue(locationHeader.matches(".*/teams/" + id));
        assertEquals("TeamTesters", teamName);
        assertEquals("1", captainId);
        assertEquals("Alice", captainFirstName);
        assertEquals("Johnson", captainLastName);
    }
}