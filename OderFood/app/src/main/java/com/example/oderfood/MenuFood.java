package com.example.oderfood;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuFood extends AppCompatActivity {
    TextView tvUserName, tvCartCounter, tvPriceCounter;
    ImageView imgCart;

    Button btnOrder;
    RecyclerView lvFoodOrder;
    List<Food> listMenu, listCart;
    int mPossition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_menu_food );
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        tvUserName = findViewById( R.id.tvUserName );
        tvCartCounter = findViewById( R.id.tvcartCounter );
        tvPriceCounter = findViewById( R.id.tvPriceCounter );
        imgCart = findViewById( R.id.imgCart );
        lvFoodOrder = findViewById( R.id.lvMenuFoodOrder );
        btnOrder = findViewById( R.id.btnOrder );
        //nhận intent
        final Intent intent = getIntent();
        String userName = intent.getStringExtra( "username" );
        tvUserName.setText( userName );

        //Xử lí RecycleView
        listMenu = new ArrayList<>();
        listCart = new ArrayList<>();
        listMenu.add( new Food( R.drawable.tra_sua, "Trà Sữa", 20 ) );
        listMenu.add( new Food( R.drawable.matcha, "Matcha", 25 ) );
        listMenu.add( new Food( R.drawable.coffe, "Coffe", 20 ) );
        listMenu.add( new Food( R.drawable.sinh_to_dua_hau, "Sinh tố", 15 ) );
        listMenu.add( new Food( R.drawable.tra_chanh, "Trà chanh", 12 ) );
        listMenu.add( new Food( R.drawable.che_buoi, "Chè bưởi", 10 ) );
        listMenu.add( new Food( R.drawable.tao_pho, "Tào Phớ", 7 ) );
        listMenu.add( new Food( R.drawable.sua_chua_mit, "Sữa chua", 10 ) );
        listMenu.add( new Food( R.drawable.dua_dam, "Dừa dầm", 25 ) );
        listMenu.add( new Food( R.drawable.tra_sua, "Trà Sữa", 25 ) );
        listMenu.add( new Food( R.drawable.matcha, "Matcha", 25 ) );
        listMenu.add( new Food( R.drawable.coffe, "Coffe", 25 ) );
        listMenu.add( new Food( R.drawable.sinh_to_dua_hau, "Sinh tố", 20 ) );
        listMenu.add( new Food( R.drawable.com_ga, "Cơm gà", 40 ) );
        listMenu.add( new Food( R.drawable.com_rang, "Cơm rang", 30 ) );
        listMenu.add( new Food( R.drawable.com_suon, "Cơm sườn", 40 ) );

        FoodAdapter adapter = new FoodAdapter( listMenu );
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager( getBaseContext(), 3, RecyclerView.VERTICAL, false );
        lvFoodOrder.setAdapter( adapter );
        lvFoodOrder.setLayoutManager( layoutManager );
        adapter.setIonClickFood( new IonClickFood() {//chuyển object sang
            @Override
            public void onClickName(Food food) {
                int counter = Integer.parseInt( tvCartCounter.getText().toString() );
                counter++;
                tvCartCounter.setText( String.valueOf( counter ) );
                int priceCounter = Integer.parseInt( tvPriceCounter.getText().toString() );
                priceCounter += food.getPriceFood();
                tvPriceCounter.setText( String.valueOf( priceCounter ) );
                mPossition = listMenu.indexOf( food );
                listCart.add( food );

            }

            @Override
            public void onClickImg(Food food) {
                int counter = Integer.parseInt( tvCartCounter.getText().toString() );
                counter++;
                tvCartCounter.setText( String.valueOf( counter ) );
                int priceCounter = Integer.parseInt( tvPriceCounter.getText().toString() );
                priceCounter += food.getPriceFood();
                tvPriceCounter.setText( String.valueOf( priceCounter ) );
                mPossition = listMenu.indexOf( food );
                listCart.add( food );
            }

            @Override
            public void onClickLayout(Food food) {
                int counter = Integer.parseInt( tvCartCounter.getText().toString() );
                counter++;
                tvCartCounter.setText( String.valueOf( counter ) );
                int priceCounter = Integer.parseInt( tvPriceCounter.getText().toString() );
                priceCounter += food.getPriceFood();
                tvPriceCounter.setText( String.valueOf( priceCounter ) );
                mPossition = listMenu.indexOf( food );
                listCart.add( food );
            }
        } );
        //Button Order
        btnOrder.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( getBaseContext(), "Đặt hàng thành công, chúc bạn ngon miệng.", Toast.LENGTH_SHORT ).show();
                tvCartCounter.setText( "0" );
                tvPriceCounter.setText( "0" );
                listCart.clear();
            }
        } );
        //OnclickCart
        imgCart.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent( getBaseContext(), Cart.class );
                intent1.putExtra( "totalPrice", tvPriceCounter.getText().toString() );
                intent1.putParcelableArrayListExtra( "list", (ArrayList<Food>) listCart );
                startActivity( intent1 );
            }
        } );
    }
}
