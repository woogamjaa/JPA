package com.jpa.model.영속성전이;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@Entity
public class PersistenceParentEntity {
    @Id
    private Long no;
    private String title;

    @OneToMany(mappedBy = "parent" , cascade = {CascadeType.PERSIST})
    private List<PersistenceChildEntity> children = new ArrayList<>();

    public void setChildren(List<PersistenceChildEntity> children) {
        if(this.children.size()>0) {
           this.children.forEach(child ->{
                child.setParent(null);
            });
        }
        this.children = children;
        children.forEach(child -> child.setParent(this));
    }
}
