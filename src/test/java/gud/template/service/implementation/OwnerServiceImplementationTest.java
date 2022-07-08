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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceImplementationTest {
    @MockBean
    OwnerRepository mockOwnerRepository = Mockito.mock(OwnerRepository.class);
    @MockBean
    DogRepository mockDogRepository = Mockito.mock(DogRepository.class);

    private OwnerServiceImplementation service;

    @BeforeEach
    private void beforeSetup() {
        service = new OwnerServiceImplementation();
    }

    @Test
    void createOwner() {
        OwnerRequestDTO dto = new OwnerRequestDTO(
                "Nik",
                "Nikitin",
                "02.03.1982"
        );

        Owner ownerManuallyCreated = Owner.builder()
                .firstName("Nik")
                .lastName("Nikitin")
                .dateOfBirth("02.03.1982")
                .build();
        service.setOwnerRepository(mockOwnerRepository);

        when(mockOwnerRepository.save(Mockito.any(Owner.class))).then(i -> i.getArgument(0));

        Owner ownerCreatedByServic = service.createOwner(dto);

        assertEquals(ownerCreatedByServic.getFirstName(), ownerManuallyCreated.getFirstName());
        assertEquals(ownerCreatedByServic.getLastName(), ownerManuallyCreated.getLastName());
        assertEquals(ownerCreatedByServic.getDateOfBirth(), ownerManuallyCreated.getDateOfBirth());

    }

    @Test
    void getOwnerById() {
        //GIVEN
        DogResponseDTO expectedDogResponseDto = new DogResponseDTO(
                2L,
                "Alpha",
                "Breed1",
                "01.01.01",
                null,
                ""
        );
        List<DogResponseDTO> dogResponseDTOList = new ArrayList<>();
        dogResponseDTOList.add(expectedDogResponseDto);
        OwnerResponseDTO expectedOwnerResponseDto = new OwnerResponseDTO(
                1L,
                "Nik",
                "Nikitin",
                "02.03.1982",
                dogResponseDTOList

        );
        Dog dog = Dog.builder()
                .id(2L)
                .nickname("Alpha")
                .breed("Breed1")
                .dateOfBirth("01.01.01")
                .owner(null)
                .registrationDate("")
                .build();
        List<Dog> dogList = new ArrayList<>();
        dogList.add(dog);

        Owner ownerManuallyCreated = Owner.builder()
                .firstName("Nik")
                .lastName("Nikitin")
                .dateOfBirth("02.03.1982")
                .build();
        service.setOwnerRepository(mockOwnerRepository);
        service.setDogRepository(mockDogRepository);

        when(mockOwnerRepository.findById(anyLong())).thenReturn(Optional.of(ownerManuallyCreated));
        when(mockDogRepository.findAllByOwnerId(anyLong())).thenReturn(dogList);

        //WHEN
        OwnerResponseDTO actualOwnerResponseDTO = service.getOwnerById(1L);

        //THEN
        assertEquals(expectedOwnerResponseDto.getFirstName(), actualOwnerResponseDTO.getFirstName());
        assertEquals(expectedOwnerResponseDto.getDogsList().size(), actualOwnerResponseDTO.getDogsList().size());
        assertEquals(expectedOwnerResponseDto.getLastName(), actualOwnerResponseDTO.getLastName());
        assertEquals(expectedOwnerResponseDto.getDateOfBirth(), actualOwnerResponseDTO.getDateOfBirth());
    }

    @Test
    void getAllOwners() {
        Owner ownerOneManuallyCreated = Owner.builder()
                .firstName("Nik")
                .lastName("Nikitin")
                .dateOfBirth("02.03.1982")
                .build();

        Owner ownerTwoManuallyCreated = Owner.builder()
                .firstName("Jane")
                .lastName("Johnson")
                .dateOfBirth("10.07.1985")
                .build();
        service.setOwnerRepository(mockOwnerRepository);

        List<Owner> ownerList = new ArrayList<>();
        ownerList.add(ownerOneManuallyCreated);
        ownerList.add(ownerTwoManuallyCreated);

        when(mockOwnerRepository.findAll()).thenReturn(ownerList);

        List<OwnerResponseDTO> result = service.getAllOwners();

        OwnerResponseDTO ownerOneResponseDTO = result.get(0);
        assertEquals(ownerOneResponseDTO.getFirstName(), ownerOneManuallyCreated.getFirstName());
        assertEquals(ownerOneResponseDTO.getLastName(), ownerOneManuallyCreated.getLastName());
        assertEquals(ownerOneResponseDTO.getDateOfBirth(), ownerOneManuallyCreated.getDateOfBirth());

        OwnerResponseDTO ownerTwoResponseDTO = result.get(1);
        assertEquals(ownerTwoResponseDTO.getFirstName(), ownerTwoManuallyCreated.getFirstName());
        assertEquals(ownerTwoResponseDTO.getLastName(), ownerTwoManuallyCreated.getLastName());
        assertEquals(ownerTwoManuallyCreated.getDateOfBirth(), ownerTwoManuallyCreated.getDateOfBirth());

    }

    @Test
    void toggleDog() {
        Owner ownerManuallyCreated = Owner.builder()
                .id(1L)
                .firstName("Nik")
                .lastName("Nikitin")
                .dateOfBirth("02.03.1982")
                .build();

        Dog dog = Dog.builder()
                .id(2L)
                .nickname("Alpha")
                .breed("Breed1")
                .dateOfBirth("01.01.01")
                .owner(ownerManuallyCreated)
                .registrationDate(LocalDate.now().toString())
                .build();
        List<Dog> dogList = new ArrayList<>();
        dogList.add(dog);


        service.setOwnerRepository(mockOwnerRepository);
        service.setDogRepository(mockDogRepository);

        when(mockOwnerRepository.findById(anyLong())).thenReturn(Optional.of(ownerManuallyCreated));
        when(mockDogRepository.findById(anyLong())).thenReturn(Optional.of(dog));
        when(mockDogRepository.findAllByOwnerId(anyLong())).thenReturn(dogList);

        DogToggleResponseDTO expectedDogToggleResponseDTO = DogToggleResponseDTO.builder()
                .owner(ownerManuallyCreated)
                .dogs(dogList)
                .build();
        DogToggleResponseDTO actualDogToggleResponseDTO = service.toggleDog(1L, 2L);

        assertEquals(expectedDogToggleResponseDTO.getOwner(), actualDogToggleResponseDTO.getOwner());
        assertEquals(expectedDogToggleResponseDTO.getDogs(), actualDogToggleResponseDTO.getDogs());

    }
}