package gud.template.service;

import gud.template.dto.DogRequestDTO;
import gud.template.dto.DogResponseDTO;
import gud.template.dto.OwnerResponseDTO;
import gud.template.entity.Dog;

import java.util.List;

public interface DogService {
    Dog createDog(DogRequestDTO request);

    DogResponseDTO getDogById(Long id);

    List<DogResponseDTO> getAllDogs();

    List<DogResponseDTO> getAllUnregisteredDogs();

}
