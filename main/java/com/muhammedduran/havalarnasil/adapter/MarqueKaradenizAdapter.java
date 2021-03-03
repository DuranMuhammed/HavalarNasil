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

public class MarqueKaradenizAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Integer> karadenizTempList;
    private ArrayList<String> karadenizIconDescList;


    public MarqueKaradenizAdapter(ArrayList<Integer> karadenizTempList, ArrayList<String> karadenizIconDescList) {
        this.karadenizTempList = karadenizTempList;
        this.karadenizIconDescList = karadenizIconDescList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //xml dosyasını bağlar ->Layout ınflater
        RecyclerView.ViewHolder viewHolder = null;

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.marque_karadeniz, parent,false);
        viewHolder = new MarqueKaradenizAdapter.IconTempHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MarqueKaradenizAdapter.IconTempHolder iconTempHolder = new MarqueKaradenizAdapter.IconTempHolder(holder.itemView);
        iconTempHolder.bind();
    }

    @Override
    public int getItemCount() {
        // You can make count infinite time
        return 1000;
    }

    class IconTempHolder extends RecyclerView.ViewHolder{
        //Görünümler burada tanımlanacak
        ArrayList<TextView> karadenizTempTextList = new ArrayList<TextView>(18);
        ArrayList<ImageView> karadenizIconImageList = new ArrayList<ImageView>(18);
        public IconTempHolder(@NonNull View itemView) {
            super(itemView);
        }
        public void bind(){
            karadenizTempTextList.add(itemView.findViewById(R.id.textViewSamsunTemp));
            karadenizTempTextList.add(itemView.findViewById(R.id.textViewTrabzonTemp));
            karadenizTempTextList.add(itemView.findViewById(R.id.textViewOrduTemp));
            karadenizTempTextList.add(itemView.findViewById(R.id.textViewZonguldakTemp));
            karadenizTempTextList.add(itemView.findViewById(R.id.textViewTokatTemp));
            karadenizTempTextList.add(itemView.findViewById(R.id.textViewCorumTemp));
            karadenizTempTextList.add(itemView.findViewById(R.id.textViewGiresunTemp));
            karadenizTempTextList.add(itemView.findViewById(R.id.textViewKastamonuTemp));
            karadenizTempTextList.add(itemView.findViewById(R.id.textViewDuzceTemp));
            karadenizTempTextList.add(itemView.findViewById(R.id.textViewRizeTemp));
            karadenizTempTextList.add(itemView.findViewById(R.id.textViewAmasyaTemp));
            karadenizTempTextList.add(itemView.findViewById(R.id.textViewBoluTemp));
            karadenizTempTextList.add(itemView.findViewById(R.id.textViewKarabukTemp));
            karadenizTempTextList.add(itemView.findViewById(R.id.textViewSinopTemp));
            karadenizTempTextList.add(itemView.findViewById(R.id.textViewBartinTemp));
            karadenizTempTextList.add(itemView.findViewById(R.id.textViewArtvinTemp));
            karadenizTempTextList.add(itemView.findViewById(R.id.textViewGumushaneTemp));
            karadenizTempTextList.add(itemView.findViewById(R.id.textViewBayburtTemp));

            karadenizIconImageList.add(itemView.findViewById(R.id.imageViewKaradeniz1));
            karadenizIconImageList.add(itemView.findViewById(R.id.imageViewKaradeniz2));
            karadenizIconImageList.add(itemView.findViewById(R.id.imageViewKaradeniz3));
            karadenizIconImageList.add(itemView.findViewById(R.id.imageViewKaradeniz4));
            karadenizIconImageList.add(itemView.findViewById(R.id.imageViewKaradeniz5));
            karadenizIconImageList.add(itemView.findViewById(R.id.imageViewKaradeniz6));
            karadenizIconImageList.add(itemView.findViewById(R.id.imageViewKaradeniz7));
            karadenizIconImageList.add(itemView.findViewById(R.id.imageViewKaradeniz8));
            karadenizIconImageList.add(itemView.findViewById(R.id.imageViewKaradeniz9));
            karadenizIconImageList.add(itemView.findViewById(R.id.imageViewKaradeniz10));
            karadenizIconImageList.add(itemView.findViewById(R.id.imageViewKaradeniz11));
            karadenizIconImageList.add(itemView.findViewById(R.id.imageViewKaradeniz12));
            karadenizIconImageList.add(itemView.findViewById(R.id.imageViewKaradeniz13));
            karadenizIconImageList.add(itemView.findViewById(R.id.imageViewKaradeniz14));
            karadenizIconImageList.add(itemView.findViewById(R.id.imageViewKaradeniz15));
            karadenizIconImageList.add(itemView.findViewById(R.id.imageViewKaradeniz16));
            karadenizIconImageList.add(itemView.findViewById(R.id.imageViewKaradeniz17));
            karadenizIconImageList.add(itemView.findViewById(R.id.imageViewKaradeniz18));

            for(int i=0; i<karadenizTempTextList.size(); i++){
                karadenizTempTextList.get(i).setText(" "+String.valueOf(karadenizTempList.get(i))+"°");
                switch (karadenizIconDescList.get(i)){
                    case "01d":
                        karadenizIconImageList.get(i).setImageResource(R.drawable.one_day);
                        break;
                    case "01n":
                        karadenizIconImageList.get(i).setImageResource(R.drawable.one_night);
                        break;
                    case "02d":
                        karadenizIconImageList.get(i).setImageResource(R.drawable.two_day);
                        break;
                    case "02n":
                        karadenizIconImageList.get(i).setImageResource(R.drawable.two_night);
                        break;
                    case "03d":
                        karadenizIconImageList.get(i).setImageResource(R.drawable.three_day);
                        break;
                    case "03n":
                        karadenizIconImageList.get(i).setImageResource(R.drawable.three_night);
                        break;
                    case "04d":
                        karadenizIconImageList.get(i).setImageResource(R.drawable.four_day);
                        break;
                    case "04n":
                        karadenizIconImageList.get(i).setImageResource(R.drawable.four_night);
                        break;
                    case "09d":
                        karadenizIconImageList.get(i).setImageResource(R.drawable.nine_day);
                        break;
                    case "09n":
                        karadenizIconImageList.get(i).setImageResource(R.drawable.nine_night);
                        break;
                    case "10d":
                        karadenizIconImageList.get(i).setImageResource(R.drawable.ten_day);
                        break;
                    case "10n":
                        karadenizIconImageList.get(i).setImageResource(R.drawable.ten_night);
                        break;
                    case "11d":
                        karadenizIconImageList.get(i).setImageResource(R.drawable.eleven_day);
                        break;
                    case "11n":
                        karadenizIconImageList.get(i).setImageResource(R.drawable.eleven_night);
                        break;
                    case "13d":
                        karadenizIconImageList.get(i).setImageResource(R.drawable.thirteen_day);
                        break;
                    case "13n":
                        karadenizIconImageList.get(i).setImageResource(R.drawable.thirteen_night);
                        break;
                    case "50d":
                        karadenizIconImageList.get(i).setImageResource(R.drawable.fifty_day);
                        break;
                    case "50n":
                        karadenizIconImageList.get(i).setImageResource(R.drawable.fifty_night);
                        break;

                }
            }

        }
    }
}
