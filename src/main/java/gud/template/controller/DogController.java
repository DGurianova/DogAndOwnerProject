package gud.template.controller;

import gud.template.dto.DogRequestDTO;
import gud.template.dto.DogResponseDTO;
import gud.template.dto.OwnerResponseDTO;
import gud.template.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/dogs")
public class DogController {

    @Autowired
    private DogService dogService;


    @GetMapping("/unregistered")
    public List<DogResponseDTO> getAllUnregisteredDogs() {
        return dogService.getAllUnregisteredDogs();
    }

    @PostMapping
    public void createDog(@RequestBody DogRequestDTO request) {
        dogService.createDog(request);
    }


}
