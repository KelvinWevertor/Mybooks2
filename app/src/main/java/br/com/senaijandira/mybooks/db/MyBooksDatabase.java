package br.com.senaijandira.mybooks.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import br.com.senaijandira.mybooks.dao.LivroDao;
import br.com.senaijandira.mybooks.model.Livro;

@Database(entities = {Livro.class} , version = 1)
public abstract class MyBooksDatabase extends RoomDatabase {
    public abstract LivroDao daoLivro();
}
