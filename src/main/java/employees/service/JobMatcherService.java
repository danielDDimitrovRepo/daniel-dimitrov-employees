package employees.service;

import employees.csv.JobHistory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static employees.constants.error.ErrorMessages.ERR_MSG_VALID_DATE_RANGE;

@Service
public class JobMatcherService {

    public Optional<JobMatcherResult> matchLongestJobHistoryPair(List<JobHistory> jobHistory) {

        JobMatcherResult.JobMatcherResultBuilder resultBuilder = new JobMatcherResult.JobMatcherResultBuilder();
        long maxDaysOverlap = 0;

        for (int i = 0; i < jobHistory.size(); i++) {
            for (int x = i + 1; x < jobHistory.size(); x++) {

                JobHistory left = jobHistory.get(i);
                JobHistory right = jobHistory.get(x);

                if (left.getProjectId() != right.getProjectId()) {
                    continue;
                }

                long daysOverlap = getDaysOverlap(left, right);

                if (daysOverlap > maxDaysOverlap) {
                    maxDaysOverlap = daysOverlap;
                    resultBuilder
                            .firstEmployee(left.getEmployeeId())
                            .secondEmployee(right.getEmployeeId())
                            .projectId(right.getProjectId())
                            .daysWorkedTogether(maxDaysOverlap);
                }

            }
        }

        return maxDaysOverlap > 0 ? Optional.of(resultBuilder.build()) : Optional.empty();
    }

    private long getDaysOverlap(JobHistory left, JobHistory right) {

        LocalDate leftFrom = left.getFrom();
        LocalDate leftTo = left.getTo();
        LocalDate rightFrom = right.getFrom();
        LocalDate rightTo = right.getTo();

        if (leftTo.isBefore(leftFrom) || rightTo.isBefore(rightFrom)) {
            throw new IllegalArgumentException(ERR_MSG_VALID_DATE_RANGE);
        }

        if (leftTo.isBefore(rightFrom) || rightTo.isBefore(leftFrom)) {
            return 0;
        }

        LocalDate laterFrom = leftFrom.isAfter(rightFrom) ? leftFrom : rightFrom;
        LocalDate earlierFrom = leftTo.isBefore(rightTo) ? leftTo : rightTo;

        return ChronoUnit.DAYS.between(laterFrom, earlierFrom);
    }

}
