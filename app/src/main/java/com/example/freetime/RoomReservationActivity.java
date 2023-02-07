package com.example.freetime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.freetime.presenter.RoomReservationPresenter;
import com.example.freetime.view.IRoomReservationView;

public class RoomReservationActivity extends BaseActivity<RoomReservationPresenter, IRoomReservationView> implements IRoomReservationView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_reservation);
    }

    @Override
    protected RoomReservationPresenter createPresenter() {
        return null;
    }

    @Override
    public void showErrorMessage(String msg) {

    }
}