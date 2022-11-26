package com.example.productlist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {

    private ArrayList<Produit> produits;
    private Context context;
    byte[] decodedString;

    public ProductAdapter(MainActivity mainActivity, int activity_list_item, ArrayList<Produit> produits) {
        this.produits = produits;
        this.context = context;
    }

    public ProductAdapter(ArrayList<Produit> produits) {
        this.produits = produits;
    }


    @Override
    public int getCount() {
        return produits.size();
    }

    @Override
    public Object getItem(int position) {
        return produits.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            //LayoutInflater: instancier le fichier XML
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //convertview : ki ysir el defilement yostokiha fil vue mta3i
            convertView = (View) inflater.inflate(R.layout.productlayout, null);
        }
        //************************************
        TextView txtLibelle = (TextView)convertView.findViewById(R.id.Lib);
        TextView txtCodeBarre=(TextView)convertView.findViewById(R.id.codeB);
        TextView txtPrice=(TextView)convertView.findViewById(R.id.prix);
        TextView txtDisponible=(TextView)convertView.findViewById(R.id.dispo);
        CheckBox check =(CheckBox) convertView.findViewById(R.id.check);
        ImageView imageView=(ImageView)convertView.findViewById(R.id.image)  ;

        txtLibelle.setText(produits.get(position).getLibelle());
        txtCodeBarre.setText(produits.get(position).getCodeBarre());
        txtPrice.setText(produits.get(position).getPrix());
        //txtDisponible.setText(produits.get(position).getDisponible());
        check.setChecked(produits.get(position).getCheckTask());

        decodedString = Base64.decode(produits.get(position).getImage(),Base64.DEFAULT);
        Bitmap decodedByte= BitmapFactory.decodeByteArray(decodedString,0,decodedString.length);
        imageView.setImageBitmap(decodedByte);
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton vw,
                                         boolean isChecked) {

                (produits.get(position)).setCheckTask(
                        isChecked);

            }
        });
        return convertView;
    }

    public  ArrayList<Produit> getChecked() {
        ArrayList<Produit> lesTaches = new ArrayList<Produit>();
        for (Produit p : produits) {
            if (p.getCheckTask())
                lesTaches.add(p);
        }
        return lesTaches;
    }
    public void  ClearSelection()
    {
        for (Produit p: produits)
            p.setCheckTask(false);
    }

    public void remove(Produit i){
        produits.remove(i);
    }

    @Override
    public boolean isEmpty() {

        return produits.isEmpty();
    }
}
