package com.example.positivity_hci_2023;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PastMeetupViewModel extends ViewModel {
        private MutableLiveData<String> uiState = new MutableLiveData<>(new String("Here's where your past meetups will go!"));

        public LiveData<String> getUiState() {
                return uiState;
        }

        public void setUiState(String val) {
                uiState.setValue(new String(val));
        }
}
