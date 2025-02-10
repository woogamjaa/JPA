package com.jpa.jpql.entity;


import com.jpa.model.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data

@Entity(name="board")
@Table(name="BOARD")
@SequenceGenerator(name="seqBoardNo" , sequenceName = "seq_board_no", allocationSize = 1)
public class BoardEntity {
    @Id
    @GeneratedValue(generator = "seqBoardNo" , strategy = GenerationType.SEQUENCE)
    @Column(name="BOARD_NO")
    private Long boardNo;

    @Column(name="BOARD_TITLE")
    private String boardTitle;

    @ManyToOne
    @JoinColumn(name="BOARD_WRITER")
    private WebMemberEntity boardWriter;

    @Column(name="BOARD_CONTENT")
    private String boardContent;

    @Column(name="BOARD_ORIGINAL_FILENAME")
    private String boardOriginalFilename;

    @Column(name="BOARD_RENAMED_FILENAME")
    private String boardRenamedFilename;

    @Column(name="BOARD_DATE")
    private Date boardDate;

    @Column(name="BOARD_READCOUNT")
    private Integer boardReadcount;

//    @OneToMany(mappedBy = "boardRef")
//    private List<BoardCommentEntity> members;

    public BoardEntity() {}
    public BoardEntity(String boardTitle,
                       Integer boardReadcount,
                       Date boardDate) {
        this.boardTitle = boardTitle;
        this.boardReadcount = boardReadcount;
        this.boardDate = boardDate;
    }
}
