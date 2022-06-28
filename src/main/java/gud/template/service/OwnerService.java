package gud.template.service;

import gud.template.dto.DogToggleResponseDTO;
import gud.template.dto.OwnerRequestDTO;
import gud.template.dto.OwnerResponseDTO;

import java.util.List;

public interface OwnerService {
    void createOwner(OwnerRequestDTO request);

    List<OwnerResponseDTO> getAllOwners();

    OwnerResponseDTO getOwnerById(Long id);

    DogToggleResponseDTO toggleDog(Long ownerId, Long dogId);
}
