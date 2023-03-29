package com.example.freetime;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.freetime.adapter.CommonAdapter;
import com.example.freetime.adapter.ViewHolder;
import com.example.freetime.presenter.RoomReservationPresenter;
import com.example.freetime.utils.SaveInfoUtils;
import com.example.freetime.view.IRoomReservationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RoomReservationActivity extends BaseActivity<RoomReservationPresenter, IRoomReservationView> implements IRoomReservationView {

    Spinner buildsSpinner;
    Spinner floorsSpinner;
    Spinner orderSpinner;
    ListView listView;
    Button btn;

    String bname;
    Integer floor;
    Integer isorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_reservation);
        buildsSpinner = findViewById(R.id.building);
        floorsSpinner = findViewById(R.id.floor);
        orderSpinner = findViewById(R.id.order);
        listView = findViewById(R.id.rooms_listview);
        btn = findViewById(R.id.room_search_button);

        List<String> data = new ArrayList<>();
        data.add("不可预约");
        data.add("可预约");
        data.add("全部");
        ArrayAdapter<String> orderadapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,data);
        orderSpinner.setAdapter(orderadapter);
        orderSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                isorder = i;
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.getRooms(bname, isorder, floor);
            }
        });

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
        if (status.equals("success")){
            Toast.makeText(this, "预约成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void dereserve(String status) {
        if (status.equals("success")){
            Toast.makeText(this, "取消预约成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getRooms(List<Object> rooms) {
        if (rooms != null){
            List<Map<String, Object>> data = new ArrayList<>();
            for (Object o:rooms){
                data.add((Map<String, Object>) o);
            }
            listView.setAdapter(new CommonAdapter<Map<String, Object>>(this, data, R.layout.room_item_view) {
                @Override
                public void convert(ViewHolder helper, Map<String, Object> item) {

                    helper.setText(R.id.item_building, (String) item.get("building"));
                    helper.setText(R.id.item_roomNo, (String) item.get("roomNo"));
                    helper.setText(R.id.item_isorder, (String) item.get("isOrder"));
                }
            });
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Map<String, Object> map = data.get(i);
                    if (Objects.equals((String) map.get("isOrder"), "NO")){
                        presenter.reserve(SaveInfoUtils.readInfo()[0], (String) map.get("roomNo"));
                    }else {
                        Toast.makeText(RoomReservationActivity.this, "该课室不可预约", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public void getBuildingSelector(List<Object> builds) {
        if (builds != null){
            List<String> data = new ArrayList<>();
            for (Object o:builds){
                data.add((String) o);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, data);
            buildsSpinner.setAdapter(adapter);
            buildsSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    bname = data.get(i);
                    presenter.getBuildingFloorSelector(bname);
                    System.out.println(bname);
                }
            });
        }
    }

    @Override
    public void getBuildingFloorSelector(List<Object> floors) {
        if (floors != null){
            List<Integer> data = new ArrayList<>();
            for (Object o:floors){
                data.add((Integer) o);
            }
            ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, data);
            buildsSpinner.setAdapter(adapter);
            buildsSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    floor = data.get(i);
                    System.out.println(floor);
                }
            });
        }
    }
}