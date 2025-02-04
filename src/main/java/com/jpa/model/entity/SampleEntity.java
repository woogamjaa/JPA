package com.jpa.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class SampleEntity {

    @Id
    private long id;
    @Column(name = "sapmle_data") //필드는 데이터라고 할 꺼지만 컬럼은 이걸로 !
    private String data;
}
