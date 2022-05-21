package com.example.blockgraming19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import java.util.ArrayList;

public class DRAGER extends AppCompatActivity {
    private ArrayList<String> list;
    private DragListAdapter dragListAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drager);

        //просто заполняем массив что бы список не был пуст
        list = new ArrayList<String>();
        for(int i=1; i<5; i++){
            list.add("" + i);
        }

        //инициализируем лист вью
        DragListView dragListView = (DragListView) findViewById(R.id.listView);
        //заполняем адаптер
        dragListAdapter = new DragListAdapter(this, list);
        //выводим в листвью
        dragListView.setAdapter(dragListAdapter);
    }
}

