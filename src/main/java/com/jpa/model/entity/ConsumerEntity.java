package com.jpa.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name="consumer")
@SequenceGenerator(name="seqconsumerNo", sequenceName = "seq_consumer_no", allocationSize=1)
public class ConsumerEntity {

    @Id
    @GeneratedValue(generator = "seqconsumerNo", strategy = GenerationType.SEQUENCE)
    private Long consumerNo;

    @Column(nullable=false , unique=true)
    private String consumerId;

    @Column(nullable=false)
    private String consumerPwd;
    private String consumerName;

    @Column(nullable=false)
    private String consumerEmail;


    @ManyToMany(mappedBy = "consumerList")
    private List<ProductEntity> productList = new ArrayList<>();
}
