package gud.template.service;

import gud.template.dto.DogRequestDTO;
import gud.template.dto.DogResponseDTO;
import gud.template.dto.OwnerResponseDTO;

import java.util.List;

public interface DogService {
    void createDog(DogRequestDTO request);

    List<DogResponseDTO> getAllUnregisteredDogs();

}
