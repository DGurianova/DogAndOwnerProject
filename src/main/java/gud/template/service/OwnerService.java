package gud.template.service;

import gud.template.dto.DogToggleResponseDTO;
import gud.template.dto.OwnerRequestDTO;
import gud.template.dto.OwnerResponseDTO;

import java.util.List;

public interface OwnerService {
    void createOwner(OwnerRequestDTO request);

    OwnerResponseDTO getOwnerById(Long id);

    List<OwnerResponseDTO> getAllOwners();

    DogToggleResponseDTO toggleDog(Long ownerId, Long dogId);
}
