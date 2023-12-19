package com.cybergari.mvp.integration;

import com.cybergari.mvp.controller.ReportController;
import com.cybergari.mvp.fixtures.ReportConfirmationFixture;
import com.cybergari.mvp.report.ReportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.github.tomakehurst.wiremock.matching.ContainsPattern;
import com.github.tomakehurst.wiremock.matching.StringValuePattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WireMockTest(httpsEnabled = true, httpsPort = 8443)
@SpringBootTest
public class ReportControllerIT {

    @Autowired
    WebApplicationContext webApplicationContext;
    MockMvc mockMvc;

    @InjectMocks
    ReportController reportController;
    @Mock
    ReportService reportService;

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(reportController).build();
    }

    @Test
    public void givenAValidUserId_WhenGeneratingReport_ShouldReturnACompleteReport() throws Exception {
        final ResponseDefinitionBuilder mockResponse = new ResponseDefinitionBuilder()
                .withBody("{}")
                .withStatus(200);
        stubFor(WireMock.get("/reports/ID").willReturn(mockResponse));

        final MvcResult mvcResult = mockMvc.perform(get("/reports/ID"))
                .andExpect(status().isOk())
                .andReturn();

        final String responseBody = mvcResult.getResponse().getContentAsString();
    }

    @Test
    public void givenAValidReportConfirmation_WhenExecutingReport_ShouldReturnAnEmptyList() throws Exception {
        final String requestBody = mapper.writeValueAsString(ReportConfirmationFixture.load());

        final ResponseDefinitionBuilder mockResponse = new ResponseDefinitionBuilder()
                .withBody("{}")
                .withStatus(200);
        final StringValuePattern requestBodyPattern = new ContainsPattern(requestBody);
        stubFor(WireMock.put("/reports").withRequestBody(requestBodyPattern).willReturn(mockResponse));

        final MvcResult mvcResult = mockMvc.perform(put("/reports")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andReturn();
    }
}
