package com.jpa.model.복합키entity;


import jakarta.persistence.*;


@Entity
@SequenceGenerators(
        {
                @SequenceGenerator(name="seqFirstId", sequenceName = "seq_first_id", allocationSize = 1),
                @SequenceGenerator(name="seqSecondId", sequenceName = "seq_second_id", allocationSize = 1)
        }
)

@IdClass(CompositeKeyMapping.class)//원래의 표준 방식.
public class CompositeEntity {


    @Id
    @GeneratedValue(generator="seqFirstId", strategy = GenerationType.SEQUENCE)
    private Long firstId;

    @Id
    @GeneratedValue(generator="seqSecondId", strategy = GenerationType.SEQUENCE)
    private Long secondId;
    private String data;



}
