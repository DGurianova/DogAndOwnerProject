package gud.template.repository;

import gud.template.entity.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DogRepository extends JpaRepository<Dog, Long> {
    List<Dog> findAllByOwnerId(Long ownerId);

}
