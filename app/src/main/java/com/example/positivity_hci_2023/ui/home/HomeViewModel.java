package com.example.positivity_hci_2023.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("See how much you've used your phone today, doctors say this value may surprise you");
    }

    public LiveData<String> getText() {
        return mText;
    }
}