package gud.template.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class OwnerRequestDTO {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
}
