package selectClasses;

import lombok.Data;
import lombok.Builder;
import lombok.ToString;

import java.time.LocalDate;

@Data
@Builder
@ToString

public class FindLongestProject {
    private String name;
    private LocalDate startDate;
    private LocalDate finishDate;
    private int monthCount;
}
