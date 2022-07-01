package gud.template.service.implementation;

import gud.template.dto.DogResponseDTO;
import gud.template.dto.DogToggleResponseDTO;
import gud.template.dto.OwnerRequestDTO;
import gud.template.dto.OwnerResponseDTO;
import gud.template.entity.Dog;
import gud.template.entity.Owner;
import gud.template.exception.DogOrOwnerNotFoundException;
import gud.template.repository.DogRepository;
import gud.template.repository.OwnerRepository;
import gud.template.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
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
    public OwnerResponseDTO getOwnerById(Long id) {
        Owner owner = ownerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<Dog> dogsList = dogRepository.findAllByOwnerId(id);

        List<DogResponseDTO> dogsListDTO = dogsList.stream()
                .map(dog -> DogResponseDTO.builder()
                        .id(dog.getId())
                        .nickname(dog.getNickname())
                        .breed(dog.getBreed())
                        .dateOfBirth(dog.getDateOfBirth())
                        .owner(dog.getOwner())
                        .registrationDate(dog.getRegistrationDate()).build())
                .collect(Collectors.toList());

        OwnerResponseDTO ownerResponseDTO = convertToDTO(owner);
        ownerResponseDTO.setDogsList(dogsListDTO);
        return ownerResponseDTO;

    }

    @Override
    public List<OwnerResponseDTO> getAllOwners() {
        return ownerRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
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
        Optional<Dog> dog = dogRepository.findById(dogId);

        if (newOwner.isPresent() && dog.isPresent()) {
            if (dog.get().getOwner() == null) {
                dog.get().setOwner(newOwner.get());
                dog.get().setRegistrationDate(LocalDate.now().toString());
            } else {
                dog.get().setOwner(null);
                dog.get().setRegistrationDate(null);
            }
        } else {
            throw new DogOrOwnerNotFoundException();
        }
        List<Dog> dogsList = dogRepository.findAllByOwnerId(ownerId);
        return DogToggleResponseDTO.builder()
                .owner(newOwner.get())
                .dogs(dogsList)
                .build();

    }
}
