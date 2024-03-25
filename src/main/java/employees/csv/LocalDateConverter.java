package employees.csv;

import com.opencsv.bean.AbstractBeanField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateConverter extends AbstractBeanField {

    @Override
    protected Object convert(String s) {
        if (s.isBlank() || s.equals("NULL")) {
            return LocalDate.now();
        }

        return LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

}