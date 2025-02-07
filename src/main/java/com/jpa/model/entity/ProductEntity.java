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
@Table(name="product")
@SequenceGenerator(name="seqproductNo", sequenceName = "seq_product_no", allocationSize=1)
public class ProductEntity {
    @Id
    @Column(name="product_No")
    @GeneratedValue(generator = "seqproductNo", strategy = GenerationType.SEQUENCE)
    private Long productNo;

    @Column(nullable=false , unique=true)
    private String productName;
    private Integer price;

    //구매회원
    @ManyToMany
    @JoinTable
            (name= "product_consumer",
            joinColumns = @JoinColumn(name="product_ref"),
            inverseJoinColumns = @JoinColumn(name="consumer_ref"))
    private List<ConsumerEntity> consumerList = new ArrayList<>();


    public void setConsumerList(List<ConsumerEntity> consumers) {
        if(consumers != null && consumers.size() > 0) {
            if(consumerList.size() > 0) {
                for (ConsumerEntity consumer : consumerList) {
                    consumer.getProductList().remove(this);
                }
            }
            consumers.forEach(consumer -> {
                    consumer.getProductList().add(this);
            });
            this.consumerList = consumerList;

        }else{
            throw new IllegalArgumentException("잘못된 매개변수가 전달됨");
        }
    }
}
