package com.example.freetime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.freetime.presenter.RoomReservationPresenter;
import com.example.freetime.view.IRoomReservationView;

import java.util.List;

public class RoomReservationActivity extends BaseActivity<RoomReservationPresenter, IRoomReservationView> implements IRoomReservationView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_reservation);

    }

    @Override
    protected RoomReservationPresenter createPresenter() {
        return new RoomReservationPresenter();
    }

    @Override
    public void showErrorMessage(String msg) {
        if (msg != null){
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void reserve(String status) {

    }

    @Override
    public void dereserve(String status) {

    }

    @Override
    public void getRooms(List<Object> rooms) {

    }
}