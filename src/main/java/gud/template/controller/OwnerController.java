package gud.template.controller;


import gud.template.dto.DogToggleResponseDTO;
import gud.template.dto.OwnerRequestDTO;
import gud.template.dto.OwnerResponseDTO;
import gud.template.entity.Owner;
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

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public OwnerResponseDTO getOwnerById(@PathVariable("id") Long id) {
        return ownerService.getOwnerById(id);
    }

    @PostMapping
    public void createOwner(@RequestBody OwnerRequestDTO request) {
        ownerService.createOwner(request);
    }

    @PutMapping("/{id}/dogs/{dogs_id}")
    public DogToggleResponseDTO dogToggle(@PathVariable("id") Long id, @PathVariable("dogs_id") Long dogsId) {
        return ownerService.toggleDog(id, dogsId);
    }

    @GetMapping
    public List<OwnerResponseDTO> getAllOwners() {
        return ownerService.getAllOwners();
    }
}
