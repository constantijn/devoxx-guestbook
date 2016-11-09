package com.xebia.sampleservice.guestbook;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "guestbook")
public class GuestBookEntry {

    @Id
    private long id;
    private String text;

    private GuestBookEntry() {
    }

    public GuestBookEntry(String text) {
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
