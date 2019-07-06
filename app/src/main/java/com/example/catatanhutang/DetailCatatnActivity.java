package com.example.catatanhutang;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DetailCatatnActivity extends AppCompatActivity {


    public static final String KEY_ID = "key_id";
    RealmHelper realm;
    EditText edJudul, edJumlah, edTanggal;
    Button btnUpdate, btnDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_catatn);

        realm = new RealmHelper(DetailCatatnActivity.this);

        final int dataID = getIntent().getIntExtra(KEY_ID, 0);


        final CatatanModel data = realm.showOnData(dataID);

        edJudul = findViewById(R.id.ed_judul);
        edJumlah = findViewById(R.id.ed_jumlah);
        edTanggal = findViewById(R.id.ed_tanggal);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);

        edJudul.setText(data.getJudul());
        edJumlah.setText(data.getJumlahhutang());
        edTanggal.setText(data.getTanggal());

        edTanggal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //date picker

                Calendar calendar = Calendar.getInstance();

                // Date nowDate = calendar.getTime();

                int nowYear = calendar.get(Calendar.YEAR);
                int nowMonth = calendar.get(Calendar.MONTH);
                int nowDay = calendar.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dialog = new DatePickerDialog(DetailCatatnActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar cal = Calendar.getInstance();
                        cal.set(year, month, dayOfMonth);
                        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                        edTanggal.setText(dateformat.format(cal.getTime()));
                    }
                }, nowYear, nowMonth, nowDay);
                dialog.show();


            }


        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CatatanModel catatan = new CatatanModel();
                catatan.setId(dataID);
                catatan.setJudul(edJudul.getText().toString());
                catatan.setJumlahhutang(edJumlah.getText().toString());
                catatan.setTanggal(edTanggal.getText().toString());

                realm.updateData(catatan);
                finish();



            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm.deleteData(dataID);
                finish();


            }
        });
    }
}
