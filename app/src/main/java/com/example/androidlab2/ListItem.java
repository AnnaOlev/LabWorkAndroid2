package com.example.androidlab2;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

public class ListItem implements Parcelable{

    private UUID mUUID;
    private String graphics;
    private String name;
    private String helptext;

    private ListItem(Parcel in) {
        graphics = in.readString();
        name = in.readString();
        helptext = in.readString();
    }

    ListItem(){
        graphics = "https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/images/tech/";
        name = null;
        helptext = null;
        mUUID = UUID.randomUUID();
    }

    public static final Creator<ListItem> CREATOR = new Creator<ListItem>() {
        @Override
        public ListItem createFromParcel(Parcel in) {
            return new ListItem(in);
        }

        @Override
        public ListItem[] newArray(int size) {
            return new ListItem[size];
        }
    };

    public String getGraphics() {
        return graphics;
    }

    public void setGraphics(String graphics) {
        this.graphics += graphics;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHelptext() {
        return helptext;
    }

    public void setHelptext(String helptext) {
        this.helptext = helptext;
    }

    @Override
    public String toString(){
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(graphics);
        parcel.writeString(name);
        parcel.writeString(helptext);
    }
}

