package com.idcard.saverpro.Adapter;


import static com.idcard.saverpro.Activity.adap.selectedImage;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fxn.stash.Stash;
import com.idcard.saverpro.Model.Value;
import com.idcard.saverpro.R;
import com.idcard.saverpro.Utils.dataa;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    Context context;
    List<Value> valueList;

    public CustomAdapter(Context context, List<Value> valueList) {
        this.context = context;
        this.valueList = valueList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        return new ViewHolder(view);
    }

//    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.imageView1.setImageResource(getImageUsingType(this.valueList.get(position).getImageType()));
        holder.imageView1.setImageResource(this.valueList.get(position).getImageType());
        holder.imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int image= valueList.get(position).getImageType();
                Log.d("image", String.valueOf(valueList.get(position).getImageType()));
                Log.d("image", String.valueOf(valueList.get(position).getImageType()));
                selectedImage.setImageResource(image);

                Stash.put(dataa.lat, position);

            }
        });

    }

    @Override
    public int getItemCount() {
        return valueList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView1, imageView2, imageView3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView1=itemView.findViewById(R.id.imageView1);
        }
    }
}

