package gud.template.entity;

import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

@Entity
@Table(name = "dog")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Dog {
    @Id
    @GeneratedValue
    @Column(name = "dog_id")
    private Long id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "breed")
    private String breed;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @Column(name = "registration_date")
    private String registrationDate;

}
