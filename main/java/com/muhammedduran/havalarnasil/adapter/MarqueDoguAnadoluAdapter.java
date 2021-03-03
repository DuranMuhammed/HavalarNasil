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

public class MarqueDoguAnadoluAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Integer> doguAnadoluTempList;
    private ArrayList<String> doguAnadoluIconDescList;


    public MarqueDoguAnadoluAdapter(ArrayList<Integer> doguAnadoluTempList, ArrayList<String> doguAnadoluIconDescList) {
        this.doguAnadoluTempList = doguAnadoluTempList;
        this.doguAnadoluIconDescList = doguAnadoluIconDescList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //xml dosyasını bağlar ->Layout ınflater
        RecyclerView.ViewHolder viewHolder = null;

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.marque_dogu_anadolu, parent,false);
        viewHolder = new MarqueDoguAnadoluAdapter.IconTempHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MarqueDoguAnadoluAdapter.IconTempHolder iconTempHolder = new MarqueDoguAnadoluAdapter.IconTempHolder(holder.itemView);
        iconTempHolder.bind();
    }

    @Override
    public int getItemCount() {
        // You can make count infinite time
        return 1000;
    }

    class IconTempHolder extends RecyclerView.ViewHolder{
        //Görünümler burada tanımlanacak
        ArrayList<TextView> doguAnadoluTempTextList = new ArrayList<TextView>(14);
        ArrayList<ImageView> doguAnadoluIconImageList = new ArrayList<ImageView>(14);
        public IconTempHolder(@NonNull View itemView) {
            super(itemView);
        }
        public void bind(){
            doguAnadoluTempTextList.add(itemView.findViewById(R.id.textViewVanTemp));
            doguAnadoluTempTextList.add(itemView.findViewById(R.id.textViewMalatyaTemp));
            doguAnadoluTempTextList.add(itemView.findViewById(R.id.textViewErzurumTemp));
            doguAnadoluTempTextList.add(itemView.findViewById(R.id.textViewElazıgTemp));
            doguAnadoluTempTextList.add(itemView.findViewById(R.id.textViewAgriTemp));
            doguAnadoluTempTextList.add(itemView.findViewById(R.id.textViewMusTemp));
            doguAnadoluTempTextList.add(itemView.findViewById(R.id.textViewBitlisTemp));
            doguAnadoluTempTextList.add(itemView.findViewById(R.id.textViewKarsTemp));
            doguAnadoluTempTextList.add(itemView.findViewById(R.id.textViewHakkariTemp));
            doguAnadoluTempTextList.add(itemView.findViewById(R.id.textViewBingolTemp));
            doguAnadoluTempTextList.add(itemView.findViewById(R.id.textViewErzincanTemp));
            doguAnadoluTempTextList.add(itemView.findViewById(R.id.textViewIgdirTemp));
            doguAnadoluTempTextList.add(itemView.findViewById(R.id.textViewArdahanTemp));
            doguAnadoluTempTextList.add(itemView.findViewById(R.id.textViewTunceliTemp));

            doguAnadoluIconImageList.add(itemView.findViewById(R.id.imageViewDoguAnadolu1));
            doguAnadoluIconImageList.add(itemView.findViewById(R.id.imageViewDoguAnadolu2));
            doguAnadoluIconImageList.add(itemView.findViewById(R.id.imageViewDoguAnadolu3));
            doguAnadoluIconImageList.add(itemView.findViewById(R.id.imageViewDoguAnadolu4));
            doguAnadoluIconImageList.add(itemView.findViewById(R.id.imageViewDoguAnadolu5));
            doguAnadoluIconImageList.add(itemView.findViewById(R.id.imageViewDoguAnadolu6));
            doguAnadoluIconImageList.add(itemView.findViewById(R.id.imageViewDoguAnadolu7));
            doguAnadoluIconImageList.add(itemView.findViewById(R.id.imageViewDoguAnadolu8));
            doguAnadoluIconImageList.add(itemView.findViewById(R.id.imageViewDoguAnadolu9));
            doguAnadoluIconImageList.add(itemView.findViewById(R.id.imageViewDoguAnadolu10));
            doguAnadoluIconImageList.add(itemView.findViewById(R.id.imageViewDoguAnadolu11));
            doguAnadoluIconImageList.add(itemView.findViewById(R.id.imageViewDoguAnadolu12));
            doguAnadoluIconImageList.add(itemView.findViewById(R.id.imageViewDoguAnadolu13));
            doguAnadoluIconImageList.add(itemView.findViewById(R.id.imageViewDoguAnadolu14));


            for(int i=0; i<doguAnadoluTempTextList.size(); i++){
                doguAnadoluTempTextList.get(i).setText(" "+String.valueOf(doguAnadoluTempList.get(i))+"°");
                switch (doguAnadoluIconDescList.get(i)){
                    case "01d":
                        doguAnadoluIconImageList.get(i).setImageResource(R.drawable.one_day);
                        break;
                    case "01n":
                        doguAnadoluIconImageList.get(i).setImageResource(R.drawable.one_night);
                        break;
                    case "02d":
                        doguAnadoluIconImageList.get(i).setImageResource(R.drawable.two_day);
                        break;
                    case "02n":
                        doguAnadoluIconImageList.get(i).setImageResource(R.drawable.two_night);
                        break;
                    case "03d":
                        doguAnadoluIconImageList.get(i).setImageResource(R.drawable.three_day);
                        break;
                    case "03n":
                        doguAnadoluIconImageList.get(i).setImageResource(R.drawable.three_night);
                        break;
                    case "04d":
                        doguAnadoluIconImageList.get(i).setImageResource(R.drawable.four_day);
                        break;
                    case "04n":
                        doguAnadoluIconImageList.get(i).setImageResource(R.drawable.four_night);
                        break;
                    case "09d":
                        doguAnadoluIconImageList.get(i).setImageResource(R.drawable.nine_day);
                        break;
                    case "09n":
                        doguAnadoluIconImageList.get(i).setImageResource(R.drawable.nine_night);
                        break;
                    case "10d":
                        doguAnadoluIconImageList.get(i).setImageResource(R.drawable.ten_day);
                        break;
                    case "10n":
                        doguAnadoluIconImageList.get(i).setImageResource(R.drawable.ten_night);
                        break;
                    case "11d":
                        doguAnadoluIconImageList.get(i).setImageResource(R.drawable.eleven_day);
                        break;
                    case "11n":
                        doguAnadoluIconImageList.get(i).setImageResource(R.drawable.eleven_night);
                        break;
                    case "13d":
                        doguAnadoluIconImageList.get(i).setImageResource(R.drawable.thirteen_day);
                        break;
                    case "13n":
                        doguAnadoluIconImageList.get(i).setImageResource(R.drawable.thirteen_night);
                        break;
                    case "50d":
                        doguAnadoluIconImageList.get(i).setImageResource(R.drawable.fifty_day);
                        break;
                    case "50n":
                        doguAnadoluIconImageList.get(i).setImageResource(R.drawable.fifty_night);
                        break;

                }
            }

        }
    }
}

