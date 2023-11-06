package com.example.positivity_hci_2023.ui.meetup;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MeetupViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MeetupViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Log your meetups today!");
    }

    public LiveData<String> getText() {
        return mText;
    }
}