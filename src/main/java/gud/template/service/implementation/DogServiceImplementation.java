package gud.template.service.implementation;

import gud.template.dto.DogRequestDTO;
import gud.template.dto.DogResponseDTO;
import gud.template.dto.OwnerResponseDTO;
import gud.template.entity.Dog;
import gud.template.entity.Owner;
import gud.template.repository.DogRepository;
import gud.template.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import static org.reflections.util.ConfigurationBuilder.build;

@Service
public class DogServiceImplementation implements DogService {

    @Autowired
    private DogRepository dogRepository;

    @Override
    public void createDog(DogRequestDTO request) {
        Dog dog = Dog.builder()
                .nickname(request.getNickname())
                .breed(request.getBreed())
                .dateOfBirth(request.getDateOfBirth())
                .registrationDate(null)
                .build();

        dogRepository.save(dog);

    }

    @Override
    public List<DogResponseDTO> getAllUnregisteredDogs() {
        return dogRepository.findAll()
                .stream()
                .filter(dog -> dog.getOwner() == null)
                .map(this::convertToDTO)
                .collect(Collectors.toList());

    }

    private DogResponseDTO convertToDTO(Dog dog) {
        return DogResponseDTO.builder()
                .id(dog.getId())
                .nickname(dog.getNickname())
                .breed(dog.getBreed())
                .dateOfBirth(dog.getDateOfBirth())
                .owner(dog.getOwner())
                .build();
    }
}
