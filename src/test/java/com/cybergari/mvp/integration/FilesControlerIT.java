package com.cybergari.mvp.integration;

import com.cybergari.mvp.controller.FilesController;
import com.cybergari.mvp.file.FileService;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WireMockTest(httpsEnabled = true, httpsPort = 8443)
@SpringBootTest
public class FilesControlerIT {

    MockMvc mockMvc;
    @InjectMocks
    FilesController filesController;
    @Mock
    FileService fileService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void givenAValidRequest_WhenGettingAllFiles_ShouldReturnStatusOK() throws Exception {
        final ResponseDefinitionBuilder mockResponse = new ResponseDefinitionBuilder()
                .withBody("{}")
                .withStatus(200);
        stubFor(WireMock.get("/files/").willReturn(mockResponse));

        final MvcResult mvcResult = mockMvc.perform(get("/files/"))
                .andExpect(status().isOk())
                .andReturn();

        final String responseBody = mvcResult.getResponse().getContentAsString();
    }

    @Test
    public void givenAValidRequest_WhenGettingCompressesFiles_ShouldReturnStatusOK() throws Exception {
        final ResponseDefinitionBuilder mockResponse = new ResponseDefinitionBuilder()
                .withBody("{}")
                .withStatus(200);
        stubFor(WireMock.get("/files/compressed").willReturn(mockResponse));

        final MvcResult mvcResult = mockMvc.perform(get("/files/compressed"))
                .andExpect(status().isOk())
                .andReturn();

        final String responseBody = mvcResult.getResponse().getContentAsString();
    }

    @Test
    public void givenAValidRequest_WhenUncompressingFiles_ShouldReturnStatusOK() throws Exception {
        final String requestBody = mapper.writeValueAsString(List.of("ID"));
        final ResponseDefinitionBuilder mockResponse = new ResponseDefinitionBuilder()
                .withBody("{}")
                .withStatus(200);
        final StringValuePattern requestBodyPattern = new ContainsPattern(requestBody);
        stubFor(WireMock.put("/files/uncompress").willReturn(mockResponse));

        final MvcResult mvcResult = mockMvc.perform(put("/files/uncompress")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                )
                .andExpect(status().isOk())
                .andReturn();

        final String responseBody = mvcResult.getResponse().getContentAsString();
    }

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(filesController).build();
    }
}
