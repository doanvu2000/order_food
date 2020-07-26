package com.example.oderfood;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Food implements Parcelable {
    int imgFood;
    String nameFood;
    int priceFood;
    int amount;

    public Food(int imgFood, String nameFood, int priceFood) {
        this.imgFood = imgFood;
        this.nameFood = nameFood;
        this.priceFood = priceFood;
        this.amount = 0;
    }

    protected Food(Parcel in) {
        imgFood = in.readInt();
        nameFood = in.readString();
        priceFood = in.readInt();
        amount = in.readInt();
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

    public int getImgFood() {
        return imgFood;
    }

    public void setImgFood(int imgFood) {
        this.imgFood = imgFood;
    }

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public int getPriceFood() {
        return priceFood;
    }

    public void setPriceFood(int priceFood) {
        this.priceFood = priceFood;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imgFood);
        dest.writeString(nameFood);
        dest.writeInt(priceFood);
        dest.writeInt(amount);
    }
}
