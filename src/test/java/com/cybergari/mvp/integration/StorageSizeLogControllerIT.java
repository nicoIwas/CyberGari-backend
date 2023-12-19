package com.cybergari.mvp.integration;

import com.cybergari.mvp.fixtures.StorageSizeLogFixture;
import com.cybergari.mvp.storagesizelog.StorageSizeLogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WireMockTest(httpsEnabled = true, httpsPort = 8443)
@SpringBootTest
public class StorageSizeLogControllerIT {

    @Autowired
    WebApplicationContext webApplicationContext;
    MockMvc mockMvc;

    @Autowired
    StorageSizeLogRepository repository;
    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getUserById_ReturnsUserLogs() throws Exception {
        repository.save(StorageSizeLogFixture.load());

        final ResponseDefinitionBuilder mockResponse = new ResponseDefinitionBuilder()
                .withBody("{}")
                .withStatus(200);
        stubFor(WireMock.get("/storage-size-log/ID").willReturn(mockResponse));

        final MvcResult mvcResult = mockMvc.perform(get("/storage-size-log/ID"))
                .andExpect(status().isOk())
                .andReturn();

        final String responseBody = mvcResult.getResponse().getContentAsString();
    }


}
