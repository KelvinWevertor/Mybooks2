package br.com.senaijandira.mybooks.fragments;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import br.com.senaijandira.mybooks.R;
import br.com.senaijandira.mybooks.Utils;
import br.com.senaijandira.mybooks.adapter.LivroAdapter;
import br.com.senaijandira.mybooks.db.MyBooksDatabase;
import br.com.senaijandira.mybooks.model.Livro;

//No fragment vc pode usar o comando
    //getContext() para obter o contexto
    //EX:
    //Toast.makeText(getContext(), "oies", Toast.LENGTH_SHORT).show();

public class Fragment1 extends Fragment {

    public ListView lista;

    public LivroAdapter listcontrole;

    public MyBooksDatabase bancocontrole;
    

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_layout1, container, false);

        lista = v.findViewById(R.id.list_livros);

        //bancocontrole = Room.databaseBuilder(getContext().getApplicationContext(),MyBooksDatabase.class, Utils.DATABASE_NAME).fallbackToDestructiveMigration().build();

        bancocontrole = Room.databaseBuilder(getContext().getApplicationContext(),MyBooksDatabase.class, Utils.DATABASE_NAME).allowMainThreadQueries().build();

        listcontrole = new LivroAdapter(getContext(),  bancocontrole);

        lista.setAdapter(listcontrole);

        return v;
    }


    @Override
    public void onResume() {
        super.onResume();

       Livro[] livrosgeral = bancocontrole.daoLivro().selecionarTodos();

        //Livro[] livrosgeral=new Livro[]{new Livro(0,null,"titulo","descrissao")};
        listcontrole.clear();

       listcontrole.addAll(livrosgeral);



    }
}

