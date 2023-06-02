package selectClasses;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString

public class FindMaxSalaryWorker {
    private String name;
    private int salary;
}
