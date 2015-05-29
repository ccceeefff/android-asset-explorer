package com.phunware.assetexplorer;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.DisplayMetrics;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by cef on 5/28/15.
 */
public class Assets {

    public static List<Asset> list = new ArrayList<Asset>();
    public static List<Density> densities = new ArrayList<Density>();

    static {
        addDrawables();
        addMipmaps();
        Collections.sort(list);
        addDensities();
    }

    private static void addDrawables(){
        Field[] drawables = com.phunware.assetlibrary.R.drawable.class.getFields();
        for (Field f : drawables) {
            try {
                int resId = f.getInt(com.phunware.assetlibrary.R.drawable.class);
                String name = f.getName();
                Asset a = new Asset(name, resId);
                list.add(a);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void addMipmaps(){
        Field[] mipmaps = com.phunware.assetlibrary.R.mipmap.class.getFields();
        for (Field f : mipmaps) {
            try {
                int resId = f.getInt(com.phunware.assetlibrary.R.mipmap.class);
                String name = f.getName();
                Asset a = new Asset(name, resId);
                list.add(a);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void addDensities(){
        densities.add(new Density("Default", DisplayMetrics.DENSITY_DEFAULT));
        densities.add(new Density("Density 280", DisplayMetrics.DENSITY_280));
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            densities.add(new Density("Density 400", DisplayMetrics.DENSITY_400));
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            densities.add(new Density("Density 560", DisplayMetrics.DENSITY_560));
        }
        densities.add(new Density("Low", DisplayMetrics.DENSITY_LOW));
        densities.add(new Density("Medium", DisplayMetrics.DENSITY_MEDIUM));
        densities.add(new Density("High", DisplayMetrics.DENSITY_HIGH));
        densities.add(new Density("X-High", DisplayMetrics.DENSITY_XHIGH));
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
            densities.add(new Density("XX-High", DisplayMetrics.DENSITY_XXHIGH));
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2){
            densities.add(new Density("XXX-High", DisplayMetrics.DENSITY_XXXHIGH));
        }
        densities.add(new Density("TV", DisplayMetrics.DENSITY_TV));
    }

    public static class Asset implements Parcelable, Comparable<Asset>{
        public String name;
        public int resId;

        public Asset(String name, int resId){
            this.name = name;
            this.resId = resId;
        }

        public Asset(Parcel in){
            this.name = in.readString();
            this.resId = in.readInt();
        }

        @Override
        public String toString() {
            return this.name;
        }

        public static Parcelable.Creator<Asset> CREATOR = new Creator<Asset>(){
            @Override
            public Asset createFromParcel(Parcel source) {
                return new Asset(source);
            }

            @Override
            public Asset[] newArray(int size) {
                return new Asset[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(name);
            dest.writeInt(resId);
        }

        @Override
        public int compareTo(Asset another) {
            return name.compareTo(another.name);
        }
    }

    public static class Density implements Parcelable {

        public String name;
        public int code;

        public Density(String name, int code){
            this.name = name;
            this.code = code;
        }

        public Density(Parcel in){
            this.name = in.readString();
            this.code = in.readInt();
        }

        public static Parcelable.Creator<Density> CREATOR = new Parcelable.Creator<Density>(){
            @Override
            public Density createFromParcel(Parcel source) {
                return new Density(source);
            }

            @Override
            public Density[] newArray(int size) {
                return new Density[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(name);
            dest.writeInt(code);
        }

    }

}
