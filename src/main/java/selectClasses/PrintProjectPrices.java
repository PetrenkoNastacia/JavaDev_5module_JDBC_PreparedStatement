package selectClasses;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString

public class PrintProjectPrices {
    private String id;
    private int price;
}
