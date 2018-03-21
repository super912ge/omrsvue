package com.proship.omrs.document.seamansBook.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.document.base.entity.DocumentDetail;

import javax.persistence.*;

@Entity
@Table(name = "seamans_book_detail_tts")
public class SeamansBookDetail extends DocumentDetail{

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seamans_book_detail_tts_id_seq")
    @SequenceGenerator(
            name="seamans_book_detail_tts_id_seq",
            sequenceName="seamans_book_detail_tts_id_sequence"
    )
    private Long id;

    @ManyToOne
    @JsonIgnore
    private SeamansBook seamansBook;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SeamansBook getSeamansBook() {
        return seamansBook;
    }

    public void setSeamansBook(SeamansBook seamansBook) {
        this.seamansBook = seamansBook;
    }
}
