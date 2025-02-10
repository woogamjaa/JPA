package com.jpa.jpql.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data

@Entity
@Table(name="BOARD_COMMENT")
@SequenceGenerator(name="seqBoardCommentNo" , sequenceName = "seq_board_comment_no", allocationSize = 1)
public class BoardCommentEntity {
    @Id
    @Column(name= "BOARD_COMMENT_NO")
    @GeneratedValue(generator = "seqBoardCommentNo", strategy = GenerationType.SEQUENCE)
    private Long boardCommentNo;

    @Column(name="BOARD_COMMENT_LEVEL")
    private Integer boardCommentLevel;

    @ManyToOne
    @JoinColumn(name="BOARD_COMMENT_WRITER")
    private WebMemberEntity boardCommentWriter;

    @Column(name="BOARD_COMMENT_CONTENT")
    private String boardCommentContent;

    @Column(name="BOARD_COMMENT_DATE")
    private Date boardCommentDate;

//    @ToString.Exclude
//    @ManyToOne
//    @JoinColumn(name="BOARD_REF")
//    private BoardEntity boardRef;

    @Column(name="BOARD_REF")
    private Long boardRef;

}
