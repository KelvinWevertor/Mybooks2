package br.com.senaijandira.mybooks;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.senaijandira.mybooks.R;
import br.com.senaijandira.mybooks.db.MyBooksDatabase;
import br.com.senaijandira.mybooks.fragments.Fragment1;
import br.com.senaijandira.mybooks.fragments.Fragment2;
import br.com.senaijandira.mybooks.fragments.Fragment3;
import br.com.senaijandira.mybooks.model.Livro;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    FragmentManager fm;



    TabLayout tab_menu;

    BottomNavigationView bottom_nav;
    private MyBooksDatabase myBooksDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager();

        //** código para o TAB MENU **/
        tab_menu = findViewById(R.id.tab_menu);

        myBooksDb = Room.databaseBuilder(getApplicationContext(),MyBooksDatabase.class, Utils.DATABASE_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();




        tab_menu.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == 0) {
                    openFragment1(null);
                }

                if (tab.getPosition() == 1) {
                    openFragment2(null);

                }

                if (tab.getPosition() == 2) {
                    openFragment3(null);

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //** FIM código tab **//


        openFragment1(null);

    }

    public void openFragment1(View view) {

        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.frame_layout, new Fragment1());

        ft.commit();


    }

    public void openFragment2(View view) {
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.frame_layout, new Fragment2());

        ft.commit();
    }

    public void openFragment3(View view) {
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.frame_layout, new Fragment3());

        ft.commit();
    }

    public void abrirCadastro(View view) {
        startActivity(new Intent(this,CadastroActivity.class));
    }
}
