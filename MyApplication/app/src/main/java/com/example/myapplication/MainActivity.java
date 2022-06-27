package com.example.myapplication;

import static com.example.myapplication.R.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView mTextViewData;
    TextView mTextViewData2;
    private DatabaseReference mDatabase;
    private Button mBtnMaps;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        mBtnMaps = findViewById(id.btnMaps);
        mBtnMaps.setOnClickListener(this);
        mTextViewData = (TextView) findViewById(id.textViewData);
        mTextViewData2 = (TextView) findViewById(id.textViewData2);
        mDatabase = FirebaseDatabase.getInstance("https://miubicacion-c8285-default-rtdb.firebaseio.com/").getReference();



        mDatabase.child("usuarios").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Obtener mp = snapshot.getValue(Obtener.class);
                    Double longitud = mp.getLongitud();
                    Double latitud = mp.getLatutid();

                    mTextViewData.setText("latitud "+latitud);
                    mTextViewData2.setText("longitud "+longitud);



                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnMaps :Intent intent = new Intent(MainActivity.this,MapsActivity.class);
            startActivity(intent);
            break;
        }
    }

}