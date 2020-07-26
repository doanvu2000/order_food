package com.example.oderfood;

import android.app.Activity;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    List<Food> foodList;
    IonClickFood ionClickFood;

    public void setIonClickFood(IonClickFood ionClickFood) {
        this.ionClickFood = ionClickFood;
    }

    public FoodAdapter(List<Food> foodList) {
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public FoodAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from( parent.getContext() );
        View view = inflater.inflate( R.layout.food_item, parent, false );
        ViewHolder viewHolder = new ViewHolder( view );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.ViewHolder holder, int position) {
        final Food food = foodList.get( position );
        holder.imgFood.setImageResource( food.getImgFood() );
        holder.tvNameFood.setText( food.getNameFood() );
        holder.tvPrice.setText( String.valueOf( food.getPriceFood() ) );
        holder.tvAmount.setText( String.valueOf( food.getAmount() ) );
        if (food.amount > 0) {
            RelativeLayout.LayoutParams rn = new RelativeLayout.LayoutParams( 480, 300 );
            rn.setMargins( 20,20,20,20 );
            holder.layout.setLayoutParams( rn );
            holder.tv_left.setVisibility( View.VISIBLE );
            holder.tv_right.setVisibility( View.VISIBLE );
            holder.tvAmount.setVisibility( View.VISIBLE );
            holder.tvAmount.setText( String.valueOf( food.amount ) );
        }

        holder.layout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ionClickFood.onClickLayout( food );
            }
        } );
        holder.imgFood.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ionClickFood.onClickImg( food );
            }
        } );
        holder.tvNameFood.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ionClickFood.onClickName( food );
            }
        } );

    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameFood, tvPrice, tvAmount;
        TextView tv_right, tv_left;
        ImageView imgFood;
        RelativeLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            imgFood = itemView.findViewById( R.id.imgFood );
            tvNameFood = itemView.findViewById( R.id.tvNamefood );
            tvPrice = itemView.findViewById( R.id.tvPrice );
            tvAmount = itemView.findViewById( R.id.tvAmount );
            layout = itemView.findViewById( R.id.layoutItem );
            tv_right = itemView.findViewById( R.id.tv_right );
            tv_left = itemView.findViewById( R.id.tv_left );

        }
    }

}
