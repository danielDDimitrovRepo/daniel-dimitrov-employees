package employees.service;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JobMatcherResult {

    private int firstEmployee;
    private int secondEmployee;
    private int projectId;
    private long daysWorkedTogether;

}
