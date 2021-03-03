package com.muhammedduran.havalarnasil.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.muhammedduran.havalarnasil.R;

import java.util.ArrayList;

public class MarqueMarmaraAdapter extends
        RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Integer> marmaraTempList;
    private ArrayList<String>  marmaraIconDescList;


    public MarqueMarmaraAdapter(ArrayList<Integer> marmaraTempList, ArrayList<String> marmaraIconDescList) {
        this.marmaraTempList = marmaraTempList;
        this.marmaraIconDescList = marmaraIconDescList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //xml dosyasını bağlar ->Layout ınflater
        RecyclerView.ViewHolder viewHolder = null;

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.marque_marmara, parent,false);
        viewHolder = new MarqueMarmaraAdapter.IconTempHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MarqueMarmaraAdapter.IconTempHolder iconTempHolder = new MarqueMarmaraAdapter.IconTempHolder(holder.itemView);
        iconTempHolder.bind();
    }

    @Override
    public int getItemCount() {
        // You can make count infinite time
        return 1000;
    }

    class IconTempHolder extends RecyclerView.ViewHolder{
        //Görünümler burada tanımlanacak
        ArrayList<TextView> marmaraTempTextList = new ArrayList<TextView>(11);
        ArrayList<ImageView> marmaraIconImageList = new ArrayList<ImageView>(11);
        public IconTempHolder(@NonNull View itemView) {
            super(itemView);
        }
        public void bind(){
            marmaraTempTextList.add(itemView.findViewById(R.id.textViewIstanbulTemp));
            marmaraTempTextList.add(itemView.findViewById(R.id.textViewBursaTemp));
            marmaraTempTextList.add(itemView.findViewById(R.id.textViewKocaeliTemp));
            marmaraTempTextList.add(itemView.findViewById(R.id.textViewBalikesirTemp));
            marmaraTempTextList.add(itemView.findViewById(R.id.textViewTekirdagTemp));
            marmaraTempTextList.add(itemView.findViewById(R.id.textViewSakaryaTemp));
            marmaraTempTextList.add(itemView.findViewById(R.id.textViewCanakkaleTemp));
            marmaraTempTextList.add(itemView.findViewById(R.id.textViewEdirneTemp));
            marmaraTempTextList.add(itemView.findViewById(R.id.textViewKirklareliTemp));
            marmaraTempTextList.add(itemView.findViewById(R.id.textViewYalovaTemp));
            marmaraTempTextList.add(itemView.findViewById(R.id.textViewBilecikTemp));

            marmaraIconImageList.add(itemView.findViewById(R.id.imageViewMarmara1));
            marmaraIconImageList.add(itemView.findViewById(R.id.imageViewMarmara2));
            marmaraIconImageList.add(itemView.findViewById(R.id.imageViewMarmara3));
            marmaraIconImageList.add(itemView.findViewById(R.id.imageViewMarmara4));
            marmaraIconImageList.add(itemView.findViewById(R.id.imageViewMarmara5));
            marmaraIconImageList.add(itemView.findViewById(R.id.imageViewMarmara6));
            marmaraIconImageList.add(itemView.findViewById(R.id.imageViewMarmara7));
            marmaraIconImageList.add(itemView.findViewById(R.id.imageViewMarmara8));
            marmaraIconImageList.add(itemView.findViewById(R.id.imageViewMarmara9));
            marmaraIconImageList.add(itemView.findViewById(R.id.imageViewMarmara10));
            marmaraIconImageList.add(itemView.findViewById(R.id.imageViewMarmara11));

            for(int i=0; i<marmaraTempTextList.size(); i++){
                marmaraTempTextList.get(i).setText(" "+String.valueOf(marmaraTempList.get(i))+"°");
                switch (marmaraIconDescList.get(i)){
                    case "01d":
                        marmaraIconImageList.get(i).setImageResource(R.drawable.one_day);
                        break;
                    case "01n":
                        marmaraIconImageList.get(i).setImageResource(R.drawable.one_night);
                        break;
                    case "02d":
                        marmaraIconImageList.get(i).setImageResource(R.drawable.two_day);
                        break;
                    case "02n":
                        marmaraIconImageList.get(i).setImageResource(R.drawable.two_night);
                        break;
                    case "03d":
                        marmaraIconImageList.get(i).setImageResource(R.drawable.three_day);
                        break;
                    case "03n":
                        marmaraIconImageList.get(i).setImageResource(R.drawable.three_night);
                        break;
                    case "04d":
                        marmaraIconImageList.get(i).setImageResource(R.drawable.four_day);
                        break;
                    case "04n":
                        marmaraIconImageList.get(i).setImageResource(R.drawable.four_night);
                        break;
                    case "09d":
                        marmaraIconImageList.get(i).setImageResource(R.drawable.nine_day);
                        break;
                    case "09n":
                        marmaraIconImageList.get(i).setImageResource(R.drawable.nine_night);
                        break;
                    case "10d":
                        marmaraIconImageList.get(i).setImageResource(R.drawable.ten_day);
                        break;
                    case "10n":
                        marmaraIconImageList.get(i).setImageResource(R.drawable.ten_night);
                        break;
                    case "11d":
                        marmaraIconImageList.get(i).setImageResource(R.drawable.eleven_day);
                        break;
                    case "11n":
                        marmaraIconImageList.get(i).setImageResource(R.drawable.eleven_night);
                        break;
                    case "13d":
                        marmaraIconImageList.get(i).setImageResource(R.drawable.thirteen_day);
                        break;
                    case "13n":
                        marmaraIconImageList.get(i).setImageResource(R.drawable.thirteen_night);
                        break;
                    case "50d":
                        marmaraIconImageList.get(i).setImageResource(R.drawable.fifty_day);
                        break;
                    case "50n":
                        marmaraIconImageList.get(i).setImageResource(R.drawable.fifty_night);
                        break;

                }
            }
        }
    }
}
