package com.cybergari.mvp.integration;

import com.cybergari.mvp.fixtures.UserDataFixture;
import com.cybergari.mvp.user.UserData;
import com.cybergari.mvp.user.UserDataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.github.tomakehurst.wiremock.matching.ContainsPattern;
import com.github.tomakehurst.wiremock.matching.StringValuePattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WireMockTest(httpsEnabled = true, httpsPort = 8443)
@SpringBootTest
public class UserControllerIT {

    @Autowired
    WebApplicationContext webApplicationContext;
    MockMvc mockMvc;
    @Autowired
    UserDataRepository userDataRepository;
    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getUserById_ReturnsUserData() throws Exception {
        userDataRepository.save(UserDataFixture.load());

        final ResponseDefinitionBuilder mockResponse = new ResponseDefinitionBuilder()
                .withBody("{}")
                .withStatus(200);
        stubFor(WireMock.get("/users/ID").willReturn(mockResponse));

        final MvcResult mvcResult = mockMvc.perform(get("/users/ID"))
                .andExpect(status().isOk())
                .andReturn();

        final String responseBody = mvcResult.getResponse().getContentAsString();
    }

    @Test
    public void updateUserData_ReturnsUpdatedValues() throws Exception {
        userDataRepository.save(UserDataFixture.load());

        final String requestBody = mapper.writeValueAsString(givenAModifiedUserData());
        final ResponseDefinitionBuilder mockResponse = new ResponseDefinitionBuilder()
                .withBody("{}")
                .withStatus(200);
        final StringValuePattern requestBodyPattern = new ContainsPattern(requestBody);
        stubFor(WireMock.put("/users").withRequestBody(requestBodyPattern).willReturn(mockResponse));

        final MvcResult mvcResult = mockMvc.perform(put("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andReturn();

        final String responseBody = mvcResult.getResponse().getContentAsString();
    }

    private UserData givenAModifiedUserData() {
        final UserData userToUpdate = UserDataFixture.load();
        userToUpdate.getUserConfig().setNumVisualizations(true);
        userToUpdate.getUserConfig().setAutExclusion(true);
        userToUpdate.getUserConfig().setLastSeen(true);
        return userToUpdate;
    }
}
