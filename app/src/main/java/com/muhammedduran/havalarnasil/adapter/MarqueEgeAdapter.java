package com.muhammedduran.havalarnasil.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.muhammedduran.havalarnasil.R;

import java.util.ArrayList;

public class MarqueEgeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Integer> egeTempList;
    private ArrayList<String> egeIconDescList;


    public MarqueEgeAdapter(ArrayList<Integer> egeTempList, ArrayList<String> egeIconDescList) {
        this.egeTempList = egeTempList;
        this.egeIconDescList = egeIconDescList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //xml dosyasını bağlar ->Layout ınflater
        RecyclerView.ViewHolder viewHolder = null;

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.marque_ege, parent,false);
        viewHolder = new IconTempHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MarqueEgeAdapter.IconTempHolder iconTempHolder = new MarqueEgeAdapter.IconTempHolder(holder.itemView);
        iconTempHolder.bind();
    }

    @Override
    public int getItemCount() {
        // You can make count infinite time
        return 1000;
    }

    class IconTempHolder extends RecyclerView.ViewHolder{
        //Görünümler burada tanımlanacak
        ArrayList<TextView> egeTempTextList = new ArrayList<TextView>(8);
        ArrayList<ImageView> egeIconImageList = new ArrayList<ImageView>(8);
        public IconTempHolder(@NonNull View itemView) {
            super(itemView);
        }
        public void bind(){
            egeTempTextList.add(itemView.findViewById(R.id.textViewIzmirTemp));
            egeTempTextList.add(itemView.findViewById(R.id.textViewManisaTemp));
            egeTempTextList.add(itemView.findViewById(R.id.textViewAydinTemp));
            egeTempTextList.add(itemView.findViewById(R.id.textViewDenizliTemp));
            egeTempTextList.add(itemView.findViewById(R.id.textViewMuglaTemp));
            egeTempTextList.add(itemView.findViewById(R.id.textViewAfyonTemp));
            egeTempTextList.add(itemView.findViewById(R.id.textViewKutahyaTemp));
            egeTempTextList.add(itemView.findViewById(R.id.textViewUsakTemp));

            egeIconImageList.add(itemView.findViewById(R.id.imageViewEge1));
            egeIconImageList.add(itemView.findViewById(R.id.imageViewEge2));
            egeIconImageList.add(itemView.findViewById(R.id.imageViewEge3));
            egeIconImageList.add(itemView.findViewById(R.id.imageViewEge4));
            egeIconImageList.add(itemView.findViewById(R.id.imageViewEge5));
            egeIconImageList.add(itemView.findViewById(R.id.imageViewEge6));
            egeIconImageList.add(itemView.findViewById(R.id.imageViewEge7));
            egeIconImageList.add(itemView.findViewById(R.id.imageViewEge8));


            for(int i=0; i<egeTempTextList.size(); i++){
                egeTempTextList.get(i).setText(" "+String.valueOf(egeTempList.get(i))+"°");
                switch (egeIconDescList.get(i)){
                    case "01d":
                        egeIconImageList.get(i).setImageResource(R.drawable.one_day);
                        break;
                    case "01n":
                        egeIconImageList.get(i).setImageResource(R.drawable.one_night);
                        break;
                    case "02d":
                        egeIconImageList.get(i).setImageResource(R.drawable.two_day);
                        break;
                    case "02n":
                        egeIconImageList.get(i).setImageResource(R.drawable.two_night);
                        break;
                    case "03d":
                        egeIconImageList.get(i).setImageResource(R.drawable.three_day);
                        break;
                    case "03n":
                        egeIconImageList.get(i).setImageResource(R.drawable.three_night);
                        break;
                    case "04d":
                        egeIconImageList.get(i).setImageResource(R.drawable.four_day);
                        break;
                    case "04n":
                        egeIconImageList.get(i).setImageResource(R.drawable.four_night);
                        break;
                    case "09d":
                        egeIconImageList.get(i).setImageResource(R.drawable.nine_day);
                        break;
                    case "09n":
                        egeIconImageList.get(i).setImageResource(R.drawable.nine_night);
                        break;
                    case "10d":
                        egeIconImageList.get(i).setImageResource(R.drawable.ten_day);
                        break;
                    case "10n":
                        egeIconImageList.get(i).setImageResource(R.drawable.ten_night);
                        break;
                    case "11d":
                        egeIconImageList.get(i).setImageResource(R.drawable.eleven_day);
                        break;
                    case "11n":
                        egeIconImageList.get(i).setImageResource(R.drawable.eleven_night);
                        break;
                    case "13d":
                        egeIconImageList.get(i).setImageResource(R.drawable.thirteen_day);
                        break;
                    case "13n":
                        egeIconImageList.get(i).setImageResource(R.drawable.thirteen_night);
                        break;
                    case "50d":
                        egeIconImageList.get(i).setImageResource(R.drawable.fifty_day);
                        break;
                    case "50n":
                        egeIconImageList.get(i).setImageResource(R.drawable.fifty_night);
                        break;

                }
            }

        }
    }
}

