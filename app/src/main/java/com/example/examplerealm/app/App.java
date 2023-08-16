package com.example.examplerealm.app;

import android.app.Application;

import com.example.examplerealm.model.Board;
import com.example.examplerealm.model.Note;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class App extends Application {

    public static AtomicInteger boardId = new AtomicInteger();
    public static AtomicInteger noteId = new AtomicInteger();

    @Override
    public void onCreate() {
        setUpRealmConfig();

        Realm realm = Realm.getDefaultInstance();
        boardId = getIdByTable(realm, Board.class);
        noteId = getIdByTable(realm, Note.class);
        realm.close();

        super.onCreate();
    }

    private void setUpRealmConfig() {
        Realm.init(getApplicationContext());
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    private <T extends RealmObject> AtomicInteger getIdByTable(Realm realm, Class<T> anyClass) {
        RealmResults<T> results = realm.where(anyClass).findAll();
        return (results.size() > 0) ? new AtomicInteger(results.max("id").intValue()) : null;
    }
}
