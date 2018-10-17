package br.com.senaijandira.mybooks.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "livro")
public class Livro {

    @PrimaryKey(autoGenerate = true)
    private int id;

    //A imagem de capa é um array de bytes
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] capa;

    private String titulo;
    private String descricao;

    private int livroler;

    public int getLivroler() {
        return livroler;
    }

    public Livro(){}

    public Livro(int id, byte[] capa, String titulo, String descricao, int livroler ){
        this.id= id;
        this.capa = capa;
        this.titulo = titulo;
        this.descricao = descricao;
        this.livroler = livroler;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getCapa() {
        return capa;
    }

    public void setCapa(byte[] capa) {
        this.capa = capa;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setLivroler(int livroler) {
        this.livroler = livroler; }
}