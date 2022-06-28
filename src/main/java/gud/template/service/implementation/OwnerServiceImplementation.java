package gud.template.service.implementation;

import gud.template.dto.DogToggleResponseDTO;
import gud.template.dto.OwnerRequestDTO;
import gud.template.dto.OwnerResponseDTO;
import gud.template.entity.Dog;
import gud.template.entity.Owner;
import gud.template.repository.DogRepository;
import gud.template.repository.OwnerRepository;
import gud.template.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OwnerServiceImplementation implements OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private DogRepository dogRepository;

    @Override
    public void createOwner(OwnerRequestDTO request) {
        Owner owner = Owner.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dateOfBirth(request.getDateOfBirth())
                .build();
        ownerRepository.save(owner);

    }

    @Override
    public List<OwnerResponseDTO> getAllOwners() {
        return ownerRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OwnerResponseDTO getOwnerById(Long id) {
        return ownerRepository
                .findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    private OwnerResponseDTO convertToDTO(Owner owner) {
        return OwnerResponseDTO.builder()
                .id(owner.getId())
                .firstName(owner.getFirstName())
                .lastName(owner.getLastName())
                .dateOfBirth(owner.getDateOfBirth())
                .build();
    }

    @Override
    public DogToggleResponseDTO toggleDog(Long ownerId, Long dogId) {
        Optional<Owner> newOwner = ownerRepository.findById(ownerId);
        if (newOwner.isPresent()) {
            Optional<Dog> dog = dogRepository.findById(dogId);
            if (dog.isPresent()) {
                editDog(newOwner.get(), dog.get());
            }
        }

        return DogToggleResponseDTO.builder().build();
    }

    private void editDog(Owner newOwner, Dog dog) {

    }
}
