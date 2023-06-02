package selectClasses;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString

public class FindMaxProjectsClient {
    private String name;
    private int count;
}
