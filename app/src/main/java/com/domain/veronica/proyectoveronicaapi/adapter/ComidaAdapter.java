package com.domain.veronica.proyectoveronicaapi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.domain.veronica.proyectoveronicaapi.R;
import com.domain.veronica.proyectoveronicaapi.model.Comida;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Veronica on 20/09/2017.
 */

public class ComidaAdapter extends RecyclerView.Adapter<ComidaAdapter.DeviceViewHolder>  {
    private Context context;
    private List<Comida> dataset;
    private onComidaSelectedListener onComidaSelectedListener;

    public interface onComidaSelectedListener {
        void onComidaSelectedListener(Comida miinterface);
    }

    public ComidaAdapter(Context context, onComidaSelectedListener listener) {
        this.dataset = new ArrayList<>();
        this.context = context;
        this.onComidaSelectedListener = listener;
    }

    @Override
    public DeviceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comida, parent, false);
        return new DeviceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DeviceViewHolder holder, int position) {
        //donde esta el diseño de una celdita.
        Comida comida = dataset.get(position);
        holder.titleTextView.setText(comida.getTitle());

        // TODO: Usar Glide para descargar la imagen y colocarla en el fotoImageView

        //estoy en un activity? no. entonces
        Glide.with(context)
                .load(comida.getImageUrl())
                .into(holder.imageImageView);

        holder.setDeviceSelectedListener(comida, onComidaSelectedListener);



    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


    public static class DeviceViewHolder extends RecyclerView.ViewHolder {

        View cardView;

        ImageView imageImageView;
        TextView titleTextView;

        public DeviceViewHolder(View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardView);

            titleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
            imageImageView = (ImageView) itemView.findViewById(R.id.imageImageView);
        }

        public void setDeviceSelectedListener(final Comida miinterface, final onComidaSelectedListener onComidaSelectedListener) {
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onComidaSelectedListener.onComidaSelectedListener(miinterface);
                }
            });
        }
    }
    public void add(Comida comida) {
        dataset.add(comida);
        notifyDataSetChanged();
    }
    public void setDataset(List<Comida> comidas) {
        if (comidas == null) {
            dataset = new ArrayList<>();
        } else {
            dataset = comidas;
        }
        notifyDataSetChanged();
    }

    public void clear() {
        dataset.clear();
        notifyDataSetChanged();
    }
}
