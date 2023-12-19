package com.cybergari.mvp.integration;

import com.cybergari.mvp.fixtures.TagEntityFixture;
import com.cybergari.mvp.fixtures.TagFixture;
import com.cybergari.mvp.tag.TagRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WireMockTest(httpsEnabled = true, httpsPort = 8443)
@SpringBootTest
public class TagControllerIT {

    @Autowired
    WebApplicationContext webApplicationContext;
    MockMvc mockMvc;
    @Autowired
    TagRepository repository;
    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        repository.deleteAll();
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getUserById_ReturnsAllUserTags() throws Exception {
        repository.save(TagEntityFixture.load());

        final ResponseDefinitionBuilder mockResponse = new ResponseDefinitionBuilder()
                .withBody("{}")
                .withStatus(200);
        stubFor(WireMock.get("/tags/ID").willReturn(mockResponse));

        final MvcResult mvcResult = mockMvc.perform(get("/tags/ID"))
                .andExpect(status().isOk())
                .andReturn();

        final String responseBody = mvcResult.getResponse().getContentAsString();
    }

    @Test
    public void deleteTagByNameAndUserId_ReturnsStatusOK() throws Exception {
        repository.save(TagEntityFixture.load());

        final ResponseDefinitionBuilder mockResponse = new ResponseDefinitionBuilder()
                .withBody("{}")
                .withStatus(200);
        stubFor(WireMock.delete("/tags/ID/name").willReturn(mockResponse));

        final MvcResult mvcResult = mockMvc.perform(delete("/tags/ID/name"))
                .andExpect(status().isOk())
                .andReturn();

        final String responseBody = mvcResult.getResponse().getContentAsString();
    }

    @Test
    public void savingNewTag_ReturnsStatusOK() throws Exception {
        final String requestBody = mapper.writeValueAsString(TagFixture.load());

        final ResponseDefinitionBuilder mockResponse = new ResponseDefinitionBuilder()
                .withBody("{}")
                .withStatus(200);
        final StringValuePattern requestBodyPattern = new ContainsPattern(requestBody);
        stubFor(WireMock.post("/tags/ID").withRequestBody(requestBodyPattern).willReturn(mockResponse));

        final MvcResult mvcResult = mockMvc.perform(post("/tags/ID")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andReturn();

        final String responseBody = mvcResult.getResponse().getContentAsString();
    }
}
