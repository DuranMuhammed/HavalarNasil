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

public class MarqueIcAnadoluAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Integer> icAnadoluTempList;
    private ArrayList<String> icAnadoluIconDescList;


    public MarqueIcAnadoluAdapter(ArrayList<Integer> icAnadoluTempList, ArrayList<String> icAnadoluIconDescList) {
        this.icAnadoluTempList = icAnadoluTempList;
        this.icAnadoluIconDescList = icAnadoluIconDescList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //xml dosyasını bağlar ->Layout ınflater
        RecyclerView.ViewHolder viewHolder = null;
        
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.marque_ic_anadolu, parent,false);
        viewHolder = new IconTempHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        IconTempHolder iconTempHolder = new IconTempHolder(holder.itemView);
        iconTempHolder.bind();
    }

    @Override
    public int getItemCount() {
        // You can make count infinite time
        return 1000;
    }

    class IconTempHolder extends RecyclerView.ViewHolder{
        //Görünümler burada tanımlanacak
        ArrayList<TextView> icAnadoluTempTextList = new ArrayList<TextView>(13);
        ArrayList<ImageView> icAnadoluIconImageList = new ArrayList<ImageView>(13);
        public IconTempHolder(@NonNull View itemView) {
            super(itemView);
        }
        public void bind(){
            icAnadoluTempTextList.add(itemView.findViewById(R.id.textViewAnkaraTemp));
            icAnadoluTempTextList.add(itemView.findViewById(R.id.textViewKonyaTemp));
            icAnadoluTempTextList.add(itemView.findViewById(R.id.textViewKayseriTemp));
            icAnadoluTempTextList.add(itemView.findViewById(R.id.textViewEskisehirTemp));
            icAnadoluTempTextList.add(itemView.findViewById(R.id.textViewSivasTemp));
            icAnadoluTempTextList.add(itemView.findViewById(R.id.textViewYozgatTemp));
            icAnadoluTempTextList.add(itemView.findViewById(R.id.textViewAksarayTemp));
            icAnadoluTempTextList.add(itemView.findViewById(R.id.textViewNigdeTemp));
            icAnadoluTempTextList.add(itemView.findViewById(R.id.textViewNevsehirTemp));
            icAnadoluTempTextList.add(itemView.findViewById(R.id.textViewKirikkaleTemp));
            icAnadoluTempTextList.add(itemView.findViewById(R.id.textViewKaramanTemp));
            icAnadoluTempTextList.add(itemView.findViewById(R.id.textViewKirsehirTemp));
            icAnadoluTempTextList.add(itemView.findViewById(R.id.textViewCankiriTemp));

            icAnadoluIconImageList.add(itemView.findViewById(R.id.imageViewIcAnadolu1));
            icAnadoluIconImageList.add(itemView.findViewById(R.id.imageViewIcAnadolu2));
            icAnadoluIconImageList.add(itemView.findViewById(R.id.imageViewIcAnadolu3));
            icAnadoluIconImageList.add(itemView.findViewById(R.id.imageViewIcAnadolu4));
            icAnadoluIconImageList.add(itemView.findViewById(R.id.imageViewIcAnadolu5));
            icAnadoluIconImageList.add(itemView.findViewById(R.id.imageViewIcAnadolu6));
            icAnadoluIconImageList.add(itemView.findViewById(R.id.imageViewIcAnadolu7));
            icAnadoluIconImageList.add(itemView.findViewById(R.id.imageViewIcAnadolu8));
            icAnadoluIconImageList.add(itemView.findViewById(R.id.imageViewIcAnadolu9));
            icAnadoluIconImageList.add(itemView.findViewById(R.id.imageViewIcAnadolu10));
            icAnadoluIconImageList.add(itemView.findViewById(R.id.imageViewIcAnadolu11));
            icAnadoluIconImageList.add(itemView.findViewById(R.id.imageViewIcAnadolu12));
            icAnadoluIconImageList.add(itemView.findViewById(R.id.imageViewIcAnadolu13));
            for(int i=0; i<icAnadoluTempTextList.size(); i++){
                icAnadoluTempTextList.get(i).setText(" "+String.valueOf(icAnadoluTempList.get(i))+"°");
                switch (icAnadoluIconDescList.get(i)){
                    case "01d":
                        icAnadoluIconImageList.get(i).setImageResource(R.drawable.one_day);
                        break;
                    case "01n":
                        icAnadoluIconImageList.get(i).setImageResource(R.drawable.one_night);
                        break;
                    case "02d":
                        icAnadoluIconImageList.get(i).setImageResource(R.drawable.two_day);
                        break;
                    case "02n":
                        icAnadoluIconImageList.get(i).setImageResource(R.drawable.two_night);
                        break;
                    case "03d":
                        icAnadoluIconImageList.get(i).setImageResource(R.drawable.three_day);
                        break;
                    case "03n":
                        icAnadoluIconImageList.get(i).setImageResource(R.drawable.three_night);
                        break;
                    case "04d":
                        icAnadoluIconImageList.get(i).setImageResource(R.drawable.four_day);
                        break;
                    case "04n":
                        icAnadoluIconImageList.get(i).setImageResource(R.drawable.four_night);
                        break;
                    case "09d":
                        icAnadoluIconImageList.get(i).setImageResource(R.drawable.nine_day);
                        break;
                    case "09n":
                        icAnadoluIconImageList.get(i).setImageResource(R.drawable.nine_night);
                        break;
                    case "10d":
                        icAnadoluIconImageList.get(i).setImageResource(R.drawable.ten_day);
                        break;
                    case "10n":
                        icAnadoluIconImageList.get(i).setImageResource(R.drawable.ten_night);
                        break;
                    case "11d":
                        icAnadoluIconImageList.get(i).setImageResource(R.drawable.eleven_day);
                        break;
                    case "11n":
                        icAnadoluIconImageList.get(i).setImageResource(R.drawable.eleven_night);
                        break;
                    case "13d":
                        icAnadoluIconImageList.get(i).setImageResource(R.drawable.thirteen_day);
                        break;
                    case "13n":
                        icAnadoluIconImageList.get(i).setImageResource(R.drawable.thirteen_night);
                        break;
                    case "50d":
                        icAnadoluIconImageList.get(i).setImageResource(R.drawable.fifty_day);
                        break;
                    case "50n":
                        icAnadoluIconImageList.get(i).setImageResource(R.drawable.fifty_night);
                        break;

                }
            }

        }
    }
}
