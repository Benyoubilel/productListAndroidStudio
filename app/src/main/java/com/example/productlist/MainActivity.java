package com.example.productlist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Console;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {
    Button btnAjt,btnUpd,btnDel;
    EditText libelle,codebarre,prix;
    CheckBox dipo;
    private ListView listView;
    private ArrayList<Produit> listeDesProduit = new ArrayList<>();
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.liste) ;

        adapter = new ProductAdapter(MainActivity.this,listeDesProduit);
        listView.setAdapter(adapter);

        btnAjt=findViewById(R.id.ajouter);
        btnAjt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, MainActivityAjtP.class);
                startActivityForResult(intent, 101);
            }
        });

        btnUpd=findViewById(R.id.modifier);
        ArrayList<Produit> checkedItems = adapter.getChecked();


            btnUpd.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    ArrayList<Produit> checkedItems = adapter.getChecked();
                    int itemCount = checkedItems.size();
                    if(checkedItems.isEmpty() || itemCount>1){
                        Toast.makeText(MainActivity.this,"item not selected or u have selected 2 items",Toast.LENGTH_LONG).show();

                    }else {
                        Intent intent = new Intent(MainActivity.this, MainActivityEditP.class);



                              Produit  info =checkedItems.get(0);
                             // System.out.println(info.toString());
                                //Toast.makeText(MainActivity.this,  info.getCodeBarre(),Toast.LENGTH_LONG).show();

                                intent.putExtra("id", info.getId());
                                intent.putExtra("libelle", info.getLibelle());
                                intent.putExtra("codeBarre",  info.getCodeBarre());
                                intent.putExtra("disponible", info.getDisponible());
                                intent.putExtra("prix", info.getPrix());
                                intent.putExtra("image", info.getImage());
                                 startActivityForResult(intent, 1);
                            }



                }
            });

    }

    @Override
    protected  void  onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 101 && resultCode == Activity.RESULT_OK)
        {
            Produit t = new Produit();
            String libelle=data.getStringExtra("libelle");
            String codebarre=data.getStringExtra("codeBarre");
            String prix=data.getStringExtra("prix");
            boolean dispo=data.getBooleanExtra("disponible",false);
            String image=data.getStringExtra("image");
            t.setLibelle(libelle);
            t.setCodeBarre(codebarre);
            t.setPrix(prix);
           t.setDisponible(dispo);
            t.setImage(image);
            listeDesProduit.add(t);
            adapter.notifyDataSetChanged();
        }
        else  if (requestCode == 1 && resultCode == Activity.RESULT_OK)
        {
            Produit t = new Produit();
            int id=data.getIntExtra("id",0);
            String libelle=data.getStringExtra("libelle");
            String codebarre=data.getStringExtra("codeBarre");
            String prix=data.getStringExtra("prix");
            boolean dispo=data.getBooleanExtra("disponible",false);
            String image=data.getStringExtra("image");
            t.setId(id);
            t.setLibelle(libelle);
            t.setCodeBarre(codebarre);
            t.setPrix(prix);
            t.setDisponible(dispo);
            t.setImage(image);
            listeDesProduit.set(id,t);
            adapter.notifyDataSetChanged();
        }
    }

    public void deleteProductButtonClick(View view)
    {
        ArrayList<Produit> checkedItems = adapter.getChecked();
        Toast.makeText(this,String.valueOf(checkedItems.size()),Toast.LENGTH_LONG).show();
        int itemCount = checkedItems.size();
        for(int i=itemCount-1; i >= 0; i--){
            adapter.remove(checkedItems.get(i));
        }
        adapter.ClearSelection();
        adapter.notifyDataSetChanged();
    }
/*
    public void UpdateProductButtonClick(View view) {
        // ArrayList<Produit> checkedItems = adapter.getChecked();
        Intent i = new Intent(MainActivity.this, MainActivityAjtP.class);
        startActivityForResult(i, 101);

    }
    */



    }