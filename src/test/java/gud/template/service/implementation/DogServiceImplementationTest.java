package gud.template.service.implementation;

import gud.template.dto.DogRequestDTO;
import gud.template.entity.Dog;
import gud.template.repository.DogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

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


        Mockito.when(mockDogRepository.save(Mockito.any(Dog.class))).then(i -> i.getArgument(0));
        //WHEN
        Dog dogCreatedByService = service.createDog(dto);

        //THEN
        assertEquals(dogCreatedByService.getBreed(), dogManyallyCreated.getBreed());
        assertEquals(dogCreatedByService.getNickname(), dogManyallyCreated.getNickname());


    }


//    @Test
//    void getAllUnregisteredDogs() {
}
