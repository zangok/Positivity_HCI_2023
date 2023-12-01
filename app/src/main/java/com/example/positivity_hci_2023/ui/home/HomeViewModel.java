package com.example.positivity_hci_2023.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Here's where your past meetups will go!");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setText(String newText) {
        Log.d("newtext",newText);
        mText.setValue(newText);
        Log.d("newtext",mText.getValue());
    }
}