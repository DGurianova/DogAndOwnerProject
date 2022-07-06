package gud.template.service.implementation;

import gud.template.dto.DogRequestDTO;
import gud.template.dto.DogResponseDTO;
import gud.template.entity.Dog;
import gud.template.entity.Owner;
import gud.template.repository.DogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


class DogServiceImplementationTest {
    @MockBean
    DogRepository mockDogRepository = Mockito.mock(DogRepository.class);

    private DogServiceImplementation service;

    @BeforeEach
    private void beforeSetup() {
        service = new DogServiceImplementation();
    }

    @Test
    void createDog() {
        //Given When Then in Java

        //Given is the section that lays out the pre-conditions for the test, ie whatever state you're assuming the world to be in before you start.
        //The When clause performs the action being tested.
        //The Then statement checks that the post condition holds.

        //GIVEN
        DogRequestDTO dto = new DogRequestDTO(
                "Hatiko",
                "Laika",
                "01.01.2021",
                null
        );
        Dog dogManyallyCreated = Dog.builder()
                .nickname("Hatiko")
                .breed("Laika")
                .dateOfBirth("01.01.2021")
                .registrationDate(null)
                .build();
        service.setDogRepository(mockDogRepository);


        when(mockDogRepository.save(Mockito.any(Dog.class))).then(i -> i.getArgument(0));
        //WHEN
        Dog dogCreatedByService = service.createDog(dto);

        //THEN
        assertEquals(dogCreatedByService.getBreed(), dogManyallyCreated.getBreed());
        assertEquals(dogCreatedByService.getNickname(), dogManyallyCreated.getNickname());


    }

    @Test
    void getDogById() {
        DogResponseDTO dto = new DogResponseDTO(
                1L,
                "Hatiko",
                "Laika",
                "01.01.2021",
                null,
                null
        );
        Dog dogManyallyCreated = Dog.builder()
                .nickname("Hatiko")
                .breed("Laika")
                .dateOfBirth("01.01.2021")
                .registrationDate(null)
                .build();
        service.setDogRepository(mockDogRepository);

        when(mockDogRepository.findById(anyLong())).thenReturn(Optional.of(dogManyallyCreated));

        DogResponseDTO dogCreatedByService = service.getDogById(1L);

        assertEquals(dogCreatedByService.getNickname(), dogManyallyCreated.getNickname());
        assertEquals(dogCreatedByService.getBreed(), dogManyallyCreated.getBreed());
        assertEquals(dogCreatedByService.getDateOfBirth(), dogManyallyCreated.getDateOfBirth());


    }

    @Test
    void getDogByIdWhenThereIsNoSuchDogExists() {
        DogResponseDTO dto = new DogResponseDTO(
                null,
                "NotExists",
                "NoSuchDog",
                null,
                null,
                null
        );
        service.setDogRepository(mockDogRepository);

        when(mockDogRepository.findById(anyLong())).thenReturn(Optional.empty());
        ResponseStatusException aThrows = assertThrows(ResponseStatusException.class, () -> {
            service.getDogById(1l);
        });



    }

    @Test
    void getAllDogs() {
        Dog dogOne = Dog.builder()
                .nickname("Hatiko")
                .breed("Laika")
                .dateOfBirth("01.01.2021")
                .registrationDate(null)
                .build();

        Dog dogTwo = Dog.builder()
                .nickname("Leo")
                .breed("Jack-Russel")
                .dateOfBirth("01.04.2021")
                .registrationDate("23.09.2021")
                .build();
        service.setDogRepository(mockDogRepository);

        List<Dog> dogsList = new ArrayList<>();
        dogsList.add(dogOne);
        dogsList.add(dogTwo);

        when(mockDogRepository.findAll()).thenReturn(dogsList);

        List<DogResponseDTO> result = service.getAllDogs();

        DogResponseDTO dogOneResponseDTO = result.get(0);
        assertEquals(dogOneResponseDTO.getNickname(), dogOne.getNickname());
        assertEquals(dogOneResponseDTO.getBreed(), dogOne.getBreed());
        assertEquals(dogOneResponseDTO.getDateOfBirth(), dogOne.getDateOfBirth());

        DogResponseDTO dogTwoResponseDTO = result.get(1);
        assertEquals(dogTwoResponseDTO.getNickname(), dogTwo.getNickname());
        assertEquals(dogTwoResponseDTO.getBreed(), dogTwo.getBreed());
        assertEquals(dogTwoResponseDTO.getDateOfBirth(), dogTwo.getDateOfBirth());

    }

    @Test
    void getAllUnregisteredDogs() {
        Dog dogOne = Dog.builder()
                .nickname("Hatiko")
                .breed("Laika")
                .dateOfBirth("01.01.2021")
                .registrationDate(null)
                .owner(null)
                .build();

        Owner ownerOfDogTwo = new Owner(1L, "Nikita", "Nikitin", "12.10.1990");

        Dog dogTwo = Dog.builder()
                .nickname("Leo")
                .breed("Jack-Russel")
                .dateOfBirth("01.04.2021")
                .registrationDate("23.09.2021")
                .owner(ownerOfDogTwo)
                .build();
        service.setDogRepository(mockDogRepository);

        List<Dog> dogsList = new ArrayList<>();
        dogsList.add(dogOne);
        dogsList.add(dogTwo);

        when(mockDogRepository.findAll()).thenReturn(dogsList);

        List<DogResponseDTO> result = service.getAllUnregisteredDogs();

        assertEquals(1, result.size());

        DogResponseDTO dogOneResponseDTO = result.get(0);
        assertEquals(dogOne.getNickname(), dogOneResponseDTO.getNickname());
        assertEquals(dogOne.getBreed(), dogOneResponseDTO.getBreed());
        assertEquals(dogOne.getDateOfBirth(), dogOneResponseDTO.getDateOfBirth());
        assertEquals(dogOne.getOwner(), dogOneResponseDTO.getOwner());
    }
}
