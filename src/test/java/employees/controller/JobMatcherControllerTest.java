package employees.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import employees.service.JobMatcherResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.file.Files;

import static employees.constants.error.ErrorMessages.ERR_MSG_VALID_DATE_RANGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class JobMatcherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Value("classpath:/controller/job-history.csv")
    private Resource csvResource;
    @Value("classpath:/controller/job-history-no-matching-employees.csv")
    private Resource csvResourceWithNoMatchingEmployees;
    @Value("classpath:/controller/job-history-invalid-date-range.csv")
    private Resource csvResourceWithInvalidDateRange;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void matchLongestJobHistoryPairFromFile_validCsvWithMatchingPair_returnsMatchingPairResult() throws Exception {

        MockMultipartFile file
                = new MockMultipartFile(
                "csv",
                "controller/job-history.csv",
                MediaType.TEXT_PLAIN_VALUE,
                Files.readAllBytes(csvResource.getFile().toPath()));

        MvcResult mvcResult = mockMvc.perform(multipart("/job-history/longest-pair").file(file))
                .andExpect(status().isOk())
                .andReturn();

        JobMatcherResult result = objectMapper.readValue(mvcResult.getResponse().getContentAsByteArray(), JobMatcherResult.class);
        JobMatcherResult expected = JobMatcherResult.builder()
                .firstEmployee(218)
                .secondEmployee(143)
                .projectId(10)
                .daysWorkedTogether(8).build();

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void matchLongestJobHistoryPairFromFile_validCsvWithNoMatchingEmployees_returnsBadRequestWithMessage() throws Exception {

        MockMultipartFile file
                = new MockMultipartFile(
                "csv",
                "controller/job-history-no-matching-employees.csv",
                MediaType.TEXT_PLAIN_VALUE,
                Files.readAllBytes(csvResourceWithNoMatchingEmployees.getFile().toPath()));

        mockMvc.perform(multipart("/job-history/longest-pair").file(file))
                .andExpect(status().isNoContent());
    }

    @Test
    void matchLongestJobHistoryPairFromFile_validCsvWithInvalidDateRange_returnsBadRequestWithMessage() throws Exception {

        MockMultipartFile file
                = new MockMultipartFile(
                "csv",
                "controller/job-history-invalid-date-range.csv",
                MediaType.TEXT_PLAIN_VALUE,
                Files.readAllBytes(csvResourceWithInvalidDateRange.getFile().toPath()));

        mockMvc.perform(multipart("/job-history/longest-pair").file(file))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertThat(result.getResolvedException()).isInstanceOf(IllegalArgumentException.class))
                .andExpect(result -> assertThat(ERR_MSG_VALID_DATE_RANGE).isNotNull().isEqualTo(result.getResolvedException().getMessage()));
    }

}