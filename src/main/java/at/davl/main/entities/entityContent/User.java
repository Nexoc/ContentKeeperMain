package at.davl.main.entities.entityContent;


import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.Set;

@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @NotBlank(message = "The username field can't be blank")
    @Column(unique = true)
    private String username;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private boolean auth;
    private Date accessExpired;
    private Date refreshExpired;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    private Set<Folder> folders;
}
