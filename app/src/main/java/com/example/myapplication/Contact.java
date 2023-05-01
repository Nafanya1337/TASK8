package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Contact implements Parcelable {

    private String name;

    private long phone;

    private ArrayList<String> history;

    static ArrayList<String> namesSet = new ArrayList<>();

    Contact(String name, long phone, String[] history) {
        this.name = name;
        this.phone = phone;
        this.history = new ArrayList<String>(Arrays.asList(history));
        namesSet.add(name);
    }

    protected Contact(Parcel in) {
        name = in.readString();
        phone = in.readLong();
        history = in.readArrayList(ArrayList.class.getClassLoader());
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    public long getPhone() {
        return phone;
    }

    public ArrayList<String> getHistory() {
        return history;
    }

    public String getName() {
        return name;
    }

    public void setHistory(ArrayList<String> history) {
        this.history = history;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public static String[] getNamesArray() {
        return namesSet.toArray(new String[0]);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeLong(phone);
        dest.writeArray(history.toArray(new String[0]));
    }
}
