package employees.controller;

import employees.csv.CsvParser;
import employees.csv.JobHistory;
import employees.service.JobMatcherResult;
import employees.service.JobMatcherService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/job-history")
public class JobMatcherController {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(JobMatcherController.class);

    private final CsvParser csvParser;
    private final JobMatcherService jobMatcherService;

    @Autowired
    public JobMatcherController(CsvParser csvParser, JobMatcherService jobMatcherService) {
        this.csvParser = csvParser;
        this.jobMatcherService = jobMatcherService;
    }

    @PostMapping(path = "/longest-pair")
    public ResponseEntity<JobMatcherResult> matchLongestJobHistoryPairFromFile(@RequestParam("csv") MultipartFile file) {

        List<JobHistory> jobHistory = csvParser.parseJobHistoryCsv(file);
        log.info("Processing CSV JobHistory entries: {}", jobHistory);

        Optional<JobMatcherResult> result = jobMatcherService.matchLongestJobHistoryPair(jobHistory);

        if (result.isPresent()) {
            log.info("Match found: {}", result.get());
            return ResponseEntity.ok(result.get());
        }

        log.info("No match found");
        return ResponseEntity.noContent().build();
    }

}
