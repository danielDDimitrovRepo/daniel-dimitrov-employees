package employees.csv;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@Component
public class CsvParser {

    public List<JobHistory> parseJobHistoryCsv(MultipartFile file) {

        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            CsvToBean<JobHistory> csvReader = new CsvToBeanBuilder(reader)
                    .withType(JobHistory.class)
                    .withSeparator(',')
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreEmptyLine(true)
                    .build();

            return csvReader.parse();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
