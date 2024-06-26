package employees.csv;

import com.opencsv.bean.processor.StringProcessor;

public class TrimWhiteSpaceProcessor implements StringProcessor {

    @Override
    public String processString(String value) {
        return value.trim();
    }

    @Override
    public void setParameterString(String value) {
        // Ignoring default value coming from annotation property 'parameter'
    }

}