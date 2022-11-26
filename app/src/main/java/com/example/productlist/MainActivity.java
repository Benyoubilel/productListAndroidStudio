package com.example.productlist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {
    Button btnAjt,btnUpd,btnDel;
    EditText libelle,codebarre,prix;
    CheckBox dipo;
    private ListView listView;
    private ArrayList<Produit> listeDesProduit = new ArrayList<Produit>();
    private ProductAdapter adapter;
    private SQLProduct db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new SQLProduct(this);
        listeDesProduit = db.getListProduits();
        listView=findViewById(R.id.liste) ;
        adapter = new ProductAdapter(MainActivity.this, android.R.layout.activity_list_item,listeDesProduit);
        listView.setAdapter(adapter);

        btnAjt=findViewById(R.id.ajouter);
        btnAjt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, MainActivityAjtP.class);
                startActivity(intent);

            }
        });

    }
    @Override
    protected  void  onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 100 && resultCode == Activity.RESULT_OK)
        {
            String libelle=data.getStringExtra("libelle");
            String codebarre=data.getStringExtra("codeBarre");
            String prix=data.getStringExtra("prix");
           // String numero=data.getStringExtra("disponible");
            String image=data.getStringExtra("image");
            Produit t = new Produit();
            t.setLibelle(libelle);
            t.setCodeBarre(codebarre);
            t.setPrix(prix);
            t.setImage(image);
            long rowId = db.AddTask(t);
           t.setId((int) rowId);
            listeDesProduit.add(t);


            //code variable
            listeDesProduit = db.getListProduits();

            listView=(ListView) findViewById(R.id.liste) ;

            adapter = new ProductAdapter(this, android.R.layout.activity_list_item, listeDesProduit);

            listView.setAdapter(adapter);

        }
    }

    }