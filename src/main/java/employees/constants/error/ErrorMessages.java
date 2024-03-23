package employees.constants.error;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorMessages {

    public static final String ERR_MSG_VALID_DATE_RANGE = "'to' cannot be before 'from'";

}
