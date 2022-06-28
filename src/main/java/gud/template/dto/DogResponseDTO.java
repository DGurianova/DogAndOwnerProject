package gud.template.dto;

import gud.template.entity.Owner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class DogResponseDTO {
    private Long id;
    private String nickname;
    private String breed;
    private String dateOfBirth;
    private Owner owner;

}
