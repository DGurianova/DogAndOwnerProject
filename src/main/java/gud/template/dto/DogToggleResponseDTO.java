package gud.template.dto;

import gud.template.entity.Dog;
import gud.template.entity.Owner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class DogToggleResponseDTO {
    private Owner owner;
    List<Dog> dogs;
}
