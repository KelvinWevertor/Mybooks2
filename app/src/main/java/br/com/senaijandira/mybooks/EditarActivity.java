package br.com.senaijandira.mybooks;

import android.app.Activity;
import android.app.AlertDialog;
import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;

import br.com.senaijandira.mybooks.db.MyBooksDatabase;
import br.com.senaijandira.mybooks.model.Livro;

public class EditarActivity extends AppCompatActivity {

    Bitmap livroCapa;
    ImageView imgLivroCapa;
    EditText txtTitulo, txtDescricao;
    Livro livro;

    private final int COD_REQ_GALERIA = 101;

    private MyBooksDatabase myBooksDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        //INSTANCIANDO O BANCO
        myBooksDb = Room.databaseBuilder(getApplicationContext(),MyBooksDatabase.class, Utils.DATABASE_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();

        int id = getIntent().getIntExtra("Livro",0);
        System.out.print(id+": id retornado");
        livro =  myBooksDb.daoLivro().getLivro(id);

        System.out.print(id+": id retornado");

        txtTitulo = findViewById(R.id.txtTitulo);

        imgLivroCapa = findViewById(R.id.imgLivroCapa);

        txtDescricao = findViewById(R.id.txtDescricao);

        txtTitulo.setText(livro.getTitulo());
        txtDescricao.setText(livro.getDescricao());
        imgLivroCapa.setImageBitmap(Utils.toBitmap(livro.getCapa()));
        livroCapa = Utils.toBitmap(livro.getCapa());
    }

    public void abrirGaleria(View view) {

        Intent intent =
                new Intent(Intent.ACTION_GET_CONTENT);

        intent.setType("image/*");

        startActivityForResult(
                Intent.createChooser(intent,
                        "Selecione uma imagem"),
                COD_REQ_GALERIA
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == COD_REQ_GALERIA
                && resultCode == Activity.RESULT_OK){

            try{

                InputStream input =
                        getContentResolver()
                                .openInputStream(data.getData());

                //CONVERTENDO APRA BITMAP
                livroCapa = BitmapFactory.decodeStream(input);

                //Exibindo na tela
                imgLivroCapa.setImageBitmap(livroCapa);

            }catch (Exception ex){
                ex.printStackTrace();
            }


        }

    }

    public void salvarLivro(View view) {


        String titulo = txtTitulo.getText().toString();
        String descricao = txtDescricao.getText().toString();

        if(titulo.equals("") || descricao.equals("")){
            alert("ERRO!", "Preencha todos os campos");

        }else{


            byte[] capa = Utils.toByteArray(livroCapa);

            livro.setCapa(capa);
            livro.setTitulo(titulo);
            livro.setDescricao(descricao);



            //Inserir no banco de dados
            myBooksDb.daoLivro().atualizar(livro);

            alert("Cadastrado", "Livro editado com sucesso!");

        }

    }


    public void alert(String titulo, String msg){

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(titulo);
        alert.setMessage(msg);
        alert.setCancelable(false);
        alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alert.create().show();
    }
}