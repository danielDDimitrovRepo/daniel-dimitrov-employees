package employees.controller;

import employees.csv.CsvParser;
import employees.csv.JobHistory;
import employees.service.JobMatcherResult;
import employees.service.JobMatcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class JobMatcherController {

    private final CsvParser csvParser;
    private final JobMatcherService jobMatcherService;

    @PostMapping(path = "/job-history/longest-pair", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<JobMatcherResult> matchLongestJobHistoryPairFromFile(@RequestParam("csv") MultipartFile file) {

        List<JobHistory> jobHistory = csvParser.parseJobHistoryCsv(file);

        Optional<JobMatcherResult> result = jobMatcherService.matchLongestJobHistoryPair(jobHistory);

        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

}
