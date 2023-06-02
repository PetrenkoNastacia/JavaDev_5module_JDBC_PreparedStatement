package selectClasses;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@Builder
@ToString

public class FindYoungestEldestWorker {
    private String name;
    private String type;
    private LocalDate birthday;
}
