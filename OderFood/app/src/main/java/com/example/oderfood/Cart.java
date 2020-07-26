package com.example.oderfood;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {
    ImageView imgback;
    TextView tvPrice;
    TextView tv_right, tv_left, tvAmount;
    RecyclerView rcvListOrder;
    List<Food> listOrder;
    RelativeLayout layoutItem;
    Food food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_cart );
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        imgback = findViewById( R.id.imgBack );
        tvPrice = findViewById( R.id.tvPriceCounterOrder );
        rcvListOrder = findViewById( R.id.lvFoodOrder );
        layoutItem = findViewById( R.id.layoutItem );
        Intent intent = getIntent();
        String price = intent.getStringExtra( "totalPrice" );
        tvPrice.setText( price );
        imgback.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );
        listOrder = new ArrayList<>();
        listOrder = (List<Food>) intent.getSerializableExtra( "list" );
        FoodAdapter foodAdapter = new FoodAdapter( listOrder );
        for (int i = 0; i < listOrder.size(); i++) {
            int count = countItem( listOrder, listOrder.get( i ).nameFood ); // so luong
            listOrder.get( i ).amount = count;
            int j = i + 1;
            for (int k = i+1; k < listOrder.size(); k++) {
                if (listOrder.get( k ).nameFood.equals( listOrder.get( i ).nameFood ) == true) {
                    listOrder.remove( k );
                    k--;
                }
            }
        }

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager( getBaseContext(), 2,RecyclerView.VERTICAL, false );
        rcvListOrder.setLayoutManager( layoutManager );
        rcvListOrder.setAdapter( foodAdapter );
        foodAdapter.setIonClickFood( new IonClickFood() {

            @Override
            public void onClickName(Food food) {
            }

            @Override
            public void onClickImg(Food food) {
            }

            @Override
            public void onClickLayout(Food food) {
            }
        } );
    }

    public int countItem(List<Food> list, String name) {
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get( i ).nameFood.equals( name ) == true) {
                count++;
            }
        }
        return count;
    }
}
