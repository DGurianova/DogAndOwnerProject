package gud.template.dto;

import gud.template.entity.Dog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class DogToggleResponseDTO {
    private String owner;
    List<Dog> dogs;
}
