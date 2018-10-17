package br.com.senaijandira.mybooks.fragments;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import br.com.senaijandira.mybooks.R;
import br.com.senaijandira.mybooks.Utils;
import br.com.senaijandira.mybooks.adapter.LivroAdapter;
import br.com.senaijandira.mybooks.db.MyBooksDatabase;
import br.com.senaijandira.mybooks.model.Livro;

public class Fragment2 extends android.support.v4.app.Fragment {

    public ListView lista;

    public LivroAdapter listcontrole;

    public MyBooksDatabase bancocontrole;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_layout2, container, false);

        lista = v.findViewById(R.id.list_livros);

        bancocontrole = Room.databaseBuilder(getContext().getApplicationContext(),MyBooksDatabase.class, Utils.DATABASE_NAME).allowMainThreadQueries().build();

        listcontrole = new LivroAdapter(getContext(),  bancocontrole);

        lista.setAdapter(listcontrole);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        Livro[] btnler = bancocontrole.daoLivro().selecionarTodos(1);

        //Livro[] livrosgeral=new Livro[]{new Livro(0,null,"titulo","descrissao")};

        listcontrole.addAll(btnler);



    }



}
