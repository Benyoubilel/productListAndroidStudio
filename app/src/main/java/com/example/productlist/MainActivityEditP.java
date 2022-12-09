package com.example.productlist;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.ByteArrayOutputStream;

public class MainActivityEditP extends AppCompatActivity {
    EditText libelle,codebarre, prix;
    Button modifier,camera;
    CheckBox dispo;
    String aa;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_editp);


        libelle= findViewById(R.id.libelle);
        codebarre= findViewById(R.id.codebr);
        dispo = findViewById(R.id.dispo);
        prix= findViewById(R.id.price);

        modifier= findViewById(R.id.modifier);
        camera= findViewById(R.id.camera);
       // libelle.setText(lib);

        Intent data = getIntent();

        String lib=data.getStringExtra("libelle");
        libelle.setText(lib);


        if(ContextCompat.checkSelfPermission(MainActivityEditP.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivityEditP.this,new String[]{
                    Manifest.permission.CAMERA
            },101);
        }
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,1);
                //ImagePicker.Companion.with(MainActivityAjtP.this).cropp().MaxResultSize(1080,1080).start(101);
            }
        });
    }

    @Override
    protected  void  onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 1 && resultCode == Activity.RESULT_OK)
        {
            /*
            String lib=data.getStringExtra("libelle");
            libelle.setText(lib);
            //String code=data.getStringExtra("codeBarre");
            codebarre.setText(data.getStringExtra("codeBarre"));
           // String pr=data.getStringExtra("prix");
            dispo.setChecked(data.getBooleanExtra("disponible",false));
            //boolean disp=data.getBooleanExtra("disponible",false);
            //  String image=data.getStringExtra("image");
            */

               Bitmap im = (Bitmap) data.getExtras().get("data");
                ImageView imageview = (ImageView) findViewById(R.id.image);
                imageview.setImageBitmap(im);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                im.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream .toByteArray();
                aa= Base64.encodeToString(byteArray, Base64.DEFAULT);


        }

    }
}
