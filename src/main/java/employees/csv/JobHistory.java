package employees.csv;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.bean.CsvDate;
import com.opencsv.bean.processor.PreAssignmentProcessor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class JobHistory {

    @CsvBindByName(column = "EmpID")
    private int employeeId;

    @CsvBindByName(column = "ProjectID")
    private int projectId;

    @CsvDate(value = "yyyy-MM-dd")
    @CsvBindByName(column = "DateFrom")
    @PreAssignmentProcessor(processor = TrimWhiteSpaceProcessor.class)
    private LocalDate from;

    @CsvCustomBindByName(column = "DateTo", converter = LocalDateConverter.class)
    @PreAssignmentProcessor(processor = TrimWhiteSpaceProcessor.class)
    private LocalDate to;

}
