package employees.csv;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Component
public class CsvParser {

    public List<JobHistory> parseJobHistoryCsv(MultipartFile file) {

        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            CsvToBean<JobHistory> csvReader = new CsvToBeanBuilder(reader)
                    .withType(JobHistory.class)
                    .withSeparator(',')
                    .withIgnoreEmptyLine(true)
                    .build();

            return csvReader.parse();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
