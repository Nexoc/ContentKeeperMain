package at.davl.main.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
public class Folder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer folderId; //

    @Column(nullable = false)
    private Integer number; //

    @OneToMany
    @JoinColumn(name="userId", nullable=false)
    private User user;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "folder")
    private Set<Content> contents;
}