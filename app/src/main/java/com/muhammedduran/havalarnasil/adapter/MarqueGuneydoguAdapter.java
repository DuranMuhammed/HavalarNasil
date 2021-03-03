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

public class MarqueGuneydoguAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Integer> guneydoguTempList;
    private ArrayList<String> guneydoguIconDescList;


    public MarqueGuneydoguAdapter(ArrayList<Integer> guneydoguTempList, ArrayList<String> guneydoguIconDescList) {
        this.guneydoguTempList = guneydoguTempList;
        this.guneydoguIconDescList = guneydoguIconDescList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //xml dosyasını bağlar ->Layout ınflater
        RecyclerView.ViewHolder viewHolder = null;

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.marque_guneydogu, parent,false);
        viewHolder = new MarqueGuneydoguAdapter.IconTempHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MarqueGuneydoguAdapter.IconTempHolder iconTempHolder = new MarqueGuneydoguAdapter.IconTempHolder(holder.itemView);
        iconTempHolder.bind();
    }

    @Override
    public int getItemCount() {
        // You can make count infinite time
        return 1000;
    }

    class IconTempHolder extends RecyclerView.ViewHolder{
        //Görünümler burada tanımlanacak
        ArrayList<TextView> guneydoguTempTextList = new ArrayList<TextView>(9);
        ArrayList<ImageView> guneydoguIconImageList = new ArrayList<ImageView>(9);
        public IconTempHolder(@NonNull View itemView) {
            super(itemView);
        }
        public void bind(){
            guneydoguTempTextList.add(itemView.findViewById(R.id.textViewGaziantepTemp));
            guneydoguTempTextList.add(itemView.findViewById(R.id.textViewSanliurfaTemp));
            guneydoguTempTextList.add(itemView.findViewById(R.id.textViewDiyarbakirTemp));
            guneydoguTempTextList.add(itemView.findViewById(R.id.textViewMardinTemp));
            guneydoguTempTextList.add(itemView.findViewById(R.id.textViewAdiyamanTemp));
            guneydoguTempTextList.add(itemView.findViewById(R.id.textViewBatmanTemp));
            guneydoguTempTextList.add(itemView.findViewById(R.id.textViewSirnakTemp));
            guneydoguTempTextList.add(itemView.findViewById(R.id.textViewSiirtTemp));
            guneydoguTempTextList.add(itemView.findViewById(R.id.textViewKilisTemp));

            guneydoguIconImageList.add(itemView.findViewById(R.id.imageViewGuneydogu1));
            guneydoguIconImageList.add(itemView.findViewById(R.id.imageViewGuneydogu2));
            guneydoguIconImageList.add(itemView.findViewById(R.id.imageViewGuneydogu3));
            guneydoguIconImageList.add(itemView.findViewById(R.id.imageViewGuneydogu4));
            guneydoguIconImageList.add(itemView.findViewById(R.id.imageViewGuneydogu5));
            guneydoguIconImageList.add(itemView.findViewById(R.id.imageViewGuneydogu6));
            guneydoguIconImageList.add(itemView.findViewById(R.id.imageViewGuneydogu7));
            guneydoguIconImageList.add(itemView.findViewById(R.id.imageViewGuneydogu8));
            guneydoguIconImageList.add(itemView.findViewById(R.id.imageViewGuneydogu9));


            for(int i=0; i<guneydoguTempTextList.size(); i++){
                guneydoguTempTextList.get(i).setText(" "+String.valueOf(guneydoguTempList.get(i))+"°");
                switch (guneydoguIconDescList.get(i)){
                    case "01d":
                        guneydoguIconImageList.get(i).setImageResource(R.drawable.one_day);
                        break;
                    case "01n":
                        guneydoguIconImageList.get(i).setImageResource(R.drawable.one_night);
                        break;
                    case "02d":
                        guneydoguIconImageList.get(i).setImageResource(R.drawable.two_day);
                        break;
                    case "02n":
                        guneydoguIconImageList.get(i).setImageResource(R.drawable.two_night);
                        break;
                    case "03d":
                        guneydoguIconImageList.get(i).setImageResource(R.drawable.three_day);
                        break;
                    case "03n":
                        guneydoguIconImageList.get(i).setImageResource(R.drawable.three_night);
                        break;
                    case "04d":
                        guneydoguIconImageList.get(i).setImageResource(R.drawable.four_day);
                        break;
                    case "04n":
                        guneydoguIconImageList.get(i).setImageResource(R.drawable.four_night);
                        break;
                    case "09d":
                        guneydoguIconImageList.get(i).setImageResource(R.drawable.nine_day);
                        break;
                    case "09n":
                        guneydoguIconImageList.get(i).setImageResource(R.drawable.nine_night);
                        break;
                    case "10d":
                        guneydoguIconImageList.get(i).setImageResource(R.drawable.ten_day);
                        break;
                    case "10n":
                        guneydoguIconImageList.get(i).setImageResource(R.drawable.ten_night);
                        break;
                    case "11d":
                        guneydoguIconImageList.get(i).setImageResource(R.drawable.eleven_day);
                        break;
                    case "11n":
                        guneydoguIconImageList.get(i).setImageResource(R.drawable.eleven_night);
                        break;
                    case "13d":
                        guneydoguIconImageList.get(i).setImageResource(R.drawable.thirteen_day);
                        break;
                    case "13n":
                        guneydoguIconImageList.get(i).setImageResource(R.drawable.thirteen_night);
                        break;
                    case "50d":
                        guneydoguIconImageList.get(i).setImageResource(R.drawable.fifty_day);
                        break;
                    case "50n":
                        guneydoguIconImageList.get(i).setImageResource(R.drawable.fifty_night);
                        break;

                }
            }

        }
    }
}

