package com.jpa.model.영속성전이;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;
@Data
@Entity
public class PersistenceChildEntity {
    @Id
    private Long id;

    private String name;

    @ManyToOne
    private PersistenceParentEntity parent;
}
