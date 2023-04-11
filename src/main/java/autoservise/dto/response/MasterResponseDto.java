package autoservise.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasterResponseDto {
    private Long id;
    private String name;
    private List<Long> orderIds;
}
