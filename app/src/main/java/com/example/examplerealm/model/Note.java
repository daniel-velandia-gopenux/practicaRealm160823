package com.example.examplerealm.model;

import com.example.examplerealm.app.App;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

@RealmClass
public class Note extends RealmObject {

    @PrimaryKey
    private int id;
    @Required
    private String description;
    @Required
    private Date created;

    public Note() {
    }

    public Note(String description) {
        this.id = App.noteId.incrementAndGet();
        this.description = description;
        this.created = new Date();
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }
}
