package com.proship.omrs.document.seamanBook.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.document.base.entity.DocumentFormDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "seamans_book_form_date_tts")
public class SeamansBookFormDate extends DocumentFormDate{

    @Id
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
