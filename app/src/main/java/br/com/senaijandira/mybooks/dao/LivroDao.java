package br.com.senaijandira.mybooks.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import br.com.senaijandira.mybooks.model.Livro;

    @Dao
    public interface LivroDao {

    @Insert
    void inserir(Livro l);

    @Update
    void atualizar(Livro l);

    @Delete
        void deletar(Livro l);

    @Query("SELECT * FROM livro")
    Livro[] selecionarTodos();

    @Query("SELECT * FROM livro where id = :id")
    Livro getLivro(int id);

    @Query("SELECT * FROM livro where livroler = :estado")
        Livro[] selecionarTodos(int estado);




}
