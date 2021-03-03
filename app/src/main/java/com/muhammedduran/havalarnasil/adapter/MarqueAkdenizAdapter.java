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

public class MarqueAkdenizAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Integer> akdenizTempList;
    private ArrayList<String> akdenizIconDescList;


    public MarqueAkdenizAdapter(ArrayList<Integer> akdenizTempList, ArrayList<String> akdenizIconDescList) {
        this.akdenizTempList = akdenizTempList;
        this.akdenizIconDescList = akdenizIconDescList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //xml dosyasını bağlar ->Layout ınflater
        RecyclerView.ViewHolder viewHolder = null;

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.marque_akdeniz, parent,false);
        viewHolder = new MarqueAkdenizAdapter.IconTempHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MarqueAkdenizAdapter.IconTempHolder iconTempHolder = new MarqueAkdenizAdapter.IconTempHolder(holder.itemView);
        iconTempHolder.bind();
    }

    @Override
    public int getItemCount() {
        // You can make count infinite time
        return 1000;
    }

    class IconTempHolder extends RecyclerView.ViewHolder{
        //Görünümler burada tanımlanacak
        ArrayList<TextView> akdenizTempTextList = new ArrayList<TextView>(8);
        ArrayList<ImageView> akdenizIconImageList = new ArrayList<ImageView>(8);
        public IconTempHolder(@NonNull View itemView) {
            super(itemView);
        }
        public void bind(){
            akdenizTempTextList.add(itemView.findViewById(R.id.textViewAntalyaTemp));
            akdenizTempTextList.add(itemView.findViewById(R.id.textViewAdanaTemp));
            akdenizTempTextList.add(itemView.findViewById(R.id.textViewMersinTemp));
            akdenizTempTextList.add(itemView.findViewById(R.id.textViewHatayTemp));
            akdenizTempTextList.add(itemView.findViewById(R.id.textViewKahramanmarasTemp));
            akdenizTempTextList.add(itemView.findViewById(R.id.textViewOsmaniyeTemp));
            akdenizTempTextList.add(itemView.findViewById(R.id.textViewIspartaTemp));
            akdenizTempTextList.add(itemView.findViewById(R.id.textViewBurdurTemp));

            akdenizIconImageList.add(itemView.findViewById(R.id.imageViewAkdeniz1));
            akdenizIconImageList.add(itemView.findViewById(R.id.imageViewAkdeniz2));
            akdenizIconImageList.add(itemView.findViewById(R.id.imageViewAkdeniz3));
            akdenizIconImageList.add(itemView.findViewById(R.id.imageViewAkdeniz4));
            akdenizIconImageList.add(itemView.findViewById(R.id.imageViewAkdeniz5));
            akdenizIconImageList.add(itemView.findViewById(R.id.imageViewAkdeniz6));
            akdenizIconImageList.add(itemView.findViewById(R.id.imageViewAkdeniz7));
            akdenizIconImageList.add(itemView.findViewById(R.id.imageViewAkdeniz8));


            for(int i=0; i<akdenizTempTextList.size(); i++){
                akdenizTempTextList.get(i).setText(" "+String.valueOf(akdenizTempList.get(i))+"°");
                switch (akdenizIconDescList.get(i)){
                    case "01d":
                        akdenizIconImageList.get(i).setImageResource(R.drawable.one_day);
                        break;
                    case "01n":
                        akdenizIconImageList.get(i).setImageResource(R.drawable.one_night);
                        break;
                    case "02d":
                        akdenizIconImageList.get(i).setImageResource(R.drawable.two_day);
                        break;
                    case "02n":
                        akdenizIconImageList.get(i).setImageResource(R.drawable.two_night);
                        break;
                    case "03d":
                        akdenizIconImageList.get(i).setImageResource(R.drawable.three_day);
                        break;
                    case "03n":
                        akdenizIconImageList.get(i).setImageResource(R.drawable.three_night);
                        break;
                    case "04d":
                        akdenizIconImageList.get(i).setImageResource(R.drawable.four_day);
                        break;
                    case "04n":
                        akdenizIconImageList.get(i).setImageResource(R.drawable.four_night);
                        break;
                    case "09d":
                        akdenizIconImageList.get(i).setImageResource(R.drawable.nine_day);
                        break;
                    case "09n":
                        akdenizIconImageList.get(i).setImageResource(R.drawable.nine_night);
                        break;
                    case "10d":
                        akdenizIconImageList.get(i).setImageResource(R.drawable.ten_day);
                        break;
                    case "10n":
                        akdenizIconImageList.get(i).setImageResource(R.drawable.ten_night);
                        break;
                    case "11d":
                        akdenizIconImageList.get(i).setImageResource(R.drawable.eleven_day);
                        break;
                    case "11n":
                        akdenizIconImageList.get(i).setImageResource(R.drawable.eleven_night);
                        break;
                    case "13d":
                        akdenizIconImageList.get(i).setImageResource(R.drawable.thirteen_day);
                        break;
                    case "13n":
                        akdenizIconImageList.get(i).setImageResource(R.drawable.thirteen_night);
                        break;
                    case "50d":
                        akdenizIconImageList.get(i).setImageResource(R.drawable.fifty_day);
                        break;
                    case "50n":
                        akdenizIconImageList.get(i).setImageResource(R.drawable.fifty_night);
                        break;

                }
            }

        }
    }
}
