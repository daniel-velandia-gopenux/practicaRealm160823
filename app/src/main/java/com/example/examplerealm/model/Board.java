package com.example.examplerealm.model;

import com.example.examplerealm.app.App;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

@RealmClass
public class Board extends RealmObject {

    @PrimaryKey
    private int id;
    @Required
    private String title;
    @Required
    private Date created;

    private RealmList<Note> notes;

    public Board() {
    }

    public Board(String title) {
        this.id = App.boardId.incrementAndGet();
        this.title = title;
        this.created = new Date();
        this.notes = new RealmList<Note>();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreated() {
        return created;
    }

    public RealmList<Note> getNotes() {
        return notes;
    }
}
