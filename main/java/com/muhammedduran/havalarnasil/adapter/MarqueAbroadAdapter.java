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

public class MarqueAbroadAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Integer> abroadTempList;
    private ArrayList<String> abroadIconDescList;

    public MarqueAbroadAdapter(ArrayList<Integer> abroadTempList, ArrayList<String> abroadIconDescList) {
        this.abroadTempList = abroadTempList;
        this.abroadIconDescList = abroadIconDescList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //xml dosyasını bağlar ->Layout ınflater
        RecyclerView.ViewHolder viewHolder = null;

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.marque_abroad, parent,false);
        viewHolder = new MarqueAbroadAdapter.IconTempHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MarqueAbroadAdapter.IconTempHolder iconTempHolder = new MarqueAbroadAdapter.IconTempHolder(holder.itemView);
        iconTempHolder.bind();
    }

    @Override
    public int getItemCount() {
        // You can make count infinite time
        return 1000;
    }

    class IconTempHolder extends RecyclerView.ViewHolder{

        //Görünümler burada tanımlanacak
        ArrayList<TextView> abroadTempTextList = new ArrayList<TextView>(16);
        ArrayList<ImageView> abroadIconImageList = new ArrayList<ImageView>(16);
        public IconTempHolder(@NonNull View itemView) {
            super(itemView);
        }
        public void bind(){
            abroadTempTextList.add(itemView.findViewById(R.id.textViewMekkeTemp));
            abroadTempTextList.add(itemView.findViewById(R.id.textViewMedineTemp));
            abroadTempTextList.add(itemView.findViewById(R.id.textViewKudusTemp));
            abroadTempTextList.add(itemView.findViewById(R.id.textViewTurkistanTemp));
            abroadTempTextList.add(itemView.findViewById(R.id.textViewOtukenTemp));
            abroadTempTextList.add(itemView.findViewById(R.id.textViewKasgarTemp));
            abroadTempTextList.add(itemView.findViewById(R.id.textViewBakuTemp));
            abroadTempTextList.add(itemView.findViewById(R.id.textViewTaskentTemp));
            abroadTempTextList.add(itemView.findViewById(R.id.textViewAskabatTemp));
            abroadTempTextList.add(itemView.findViewById(R.id.textViewSemerkantTemp));
            abroadTempTextList.add(itemView.findViewById(R.id.textViewBuharaTemp));
            abroadTempTextList.add(itemView.findViewById(R.id.textViewHocaliTemp));
            abroadTempTextList.add(itemView.findViewById(R.id.textViewSaraybosnaTemp));
            abroadTempTextList.add(itemView.findViewById(R.id.textViewSimferopolTemp));
            abroadTempTextList.add(itemView.findViewById(R.id.textViewKerkukTemp));
            abroadTempTextList.add(itemView.findViewById(R.id.textViewIslamabadTemp));

            abroadIconImageList.add(itemView.findViewById(R.id.imageViewAbroad1));
            abroadIconImageList.add(itemView.findViewById(R.id.imageViewAbroad2));
            abroadIconImageList.add(itemView.findViewById(R.id.imageViewAbroad3));
            abroadIconImageList.add(itemView.findViewById(R.id.imageViewAbroad4));
            abroadIconImageList.add(itemView.findViewById(R.id.imageViewAbroad5));
            abroadIconImageList.add(itemView.findViewById(R.id.imageViewAbroad6));
            abroadIconImageList.add(itemView.findViewById(R.id.imageViewAbroad7));
            abroadIconImageList.add(itemView.findViewById(R.id.imageViewAbroad8));
            abroadIconImageList.add(itemView.findViewById(R.id.imageViewAbroad9));
            abroadIconImageList.add(itemView.findViewById(R.id.imageViewAbroad10));
            abroadIconImageList.add(itemView.findViewById(R.id.imageViewAbroad11));
            abroadIconImageList.add(itemView.findViewById(R.id.imageViewAbroad12));
            abroadIconImageList.add(itemView.findViewById(R.id.imageViewAbroad13));
            abroadIconImageList.add(itemView.findViewById(R.id.imageViewAbroad14));
            abroadIconImageList.add(itemView.findViewById(R.id.imageViewAbroad15));
            abroadIconImageList.add(itemView.findViewById(R.id.imageViewAbroad16));



            for(int i=0; i<abroadTempTextList.size(); i++){
                abroadTempTextList.get(i).setText(" "+String.valueOf(abroadTempList.get(i))+"°");
                switch (abroadIconDescList.get(i)){
                    case "01d":
                        abroadIconImageList.get(i).setImageResource(R.drawable.one_day);
                        break;
                    case "01n":
                        abroadIconImageList.get(i).setImageResource(R.drawable.one_night);
                        break;
                    case "02d":
                        abroadIconImageList.get(i).setImageResource(R.drawable.two_day);
                        break;
                    case "02n":
                        abroadIconImageList.get(i).setImageResource(R.drawable.two_night);
                        break;
                    case "03d":
                        abroadIconImageList.get(i).setImageResource(R.drawable.three_day);
                        break;
                    case "03n":
                        abroadIconImageList.get(i).setImageResource(R.drawable.three_night);
                        break;
                    case "04d":
                        abroadIconImageList.get(i).setImageResource(R.drawable.four_day);
                        break;
                    case "04n":
                        abroadIconImageList.get(i).setImageResource(R.drawable.four_night);
                        break;
                    case "09d":
                        abroadIconImageList.get(i).setImageResource(R.drawable.nine_day);
                        break;
                    case "09n":
                        abroadIconImageList.get(i).setImageResource(R.drawable.nine_night);
                        break;
                    case "10d":
                        abroadIconImageList.get(i).setImageResource(R.drawable.ten_day);
                        break;
                    case "10n":
                        abroadIconImageList.get(i).setImageResource(R.drawable.ten_night);
                        break;
                    case "11d":
                        abroadIconImageList.get(i).setImageResource(R.drawable.eleven_day);
                        break;
                    case "11n":
                        abroadIconImageList.get(i).setImageResource(R.drawable.eleven_night);
                        break;
                    case "13d":
                        abroadIconImageList.get(i).setImageResource(R.drawable.thirteen_day);
                        break;
                    case "13n":
                        abroadIconImageList.get(i).setImageResource(R.drawable.thirteen_night);
                        break;
                    case "50d":
                        abroadIconImageList.get(i).setImageResource(R.drawable.fifty_day);
                        break;
                    case "50n":
                        abroadIconImageList.get(i).setImageResource(R.drawable.fifty_night);
                        break;

                }
            }

        }
    }
}
