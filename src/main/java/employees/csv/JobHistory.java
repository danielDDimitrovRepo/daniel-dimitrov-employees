package employees.csv;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.bean.CsvDate;
import com.opencsv.bean.processor.PreAssignmentProcessor;

import java.time.LocalDate;

public class JobHistory {

    @CsvBindByName(column = "EmpID", required = true)
    private int employeeId;

    @CsvBindByName(column = "ProjectID", required = true)
    private int projectId;

    @CsvDate(value = "yyyy-MM-dd")
    @CsvBindByName(column = "DateFrom", required = true)
    @PreAssignmentProcessor(processor = TrimWhiteSpaceProcessor.class)
    private LocalDate from;

    @CsvCustomBindByName(column = "DateTo", required = true, converter = LocalDateConverter.class)
    @PreAssignmentProcessor(processor = TrimWhiteSpaceProcessor.class)
    private LocalDate to;

    public JobHistory() {
    }

    public int getEmployeeId() {
        return this.employeeId;
    }

    public int getProjectId() {
        return this.projectId;
    }

    public LocalDate getFrom() {
        return this.from;
    }

    public LocalDate getTo() {
        return this.to;
    }

    public String toString() {
        return "JobHistory(employeeId=" + this.getEmployeeId() + ", projectId=" + this.getProjectId() + ", from=" + this.getFrom() + ", to=" + this.getTo() + ")";
    }
}
