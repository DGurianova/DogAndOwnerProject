package gud.template.controller;


import gud.template.dto.DogToggleResponseDTO;
import gud.template.dto.OwnerRequestDTO;
import gud.template.dto.OwnerResponseDTO;
import gud.template.entity.Owner;
import gud.template.repository.DogRepository;
import gud.template.service.DogService;
import gud.template.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private DogService dogService;

    @PostMapping
    public void createOwner(@RequestBody OwnerRequestDTO request) {
        ownerService.createOwner(request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public OwnerResponseDTO getOwnerById(@PathVariable("id") Long id) {
        return ownerService.getOwnerById(id);
    }

    @GetMapping
    public List<OwnerResponseDTO> getAllOwners() {
        return ownerService.getAllOwners();
    }

    @PutMapping("/{id}/dogs/{dogId}")
    public DogToggleResponseDTO dogToggle(@PathVariable("id") Long id, @PathVariable("dogId") Long dogId) {

        return ownerService.toggleDog(id, dogId);
    }
}
