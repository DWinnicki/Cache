package com.winnicki.cache;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etData;
    Button btnSaveICache, btnSaveECache, btnLoadICache, btnLoadECache;
    TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        etData = (EditText)findViewById(R.id.etData);
        btnSaveICache = (Button)findViewById(R.id.btnSaveICache);
        btnSaveECache = (Button)findViewById(R.id.btnSaveECache);
        btnLoadICache = (Button)findViewById(R.id.btnLoadICache);
        btnLoadECache = (Button)findViewById(R.id.btnLoadECache);
        tvData = (TextView)findViewById(R.id.tvData);

        btnSaveICache.setOnClickListener(this);
        btnSaveECache.setOnClickListener(this);
        btnLoadICache.setOnClickListener(this);
        btnLoadECache.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSaveICache:
                saveInternalData();
                break;
            case R.id.btnSaveECache:
                saveExternalCache();
                break;
            case R.id.btnLoadICache:
                loadInternalCache();
                break;
            case R.id.btnLoadECache:
                loadExternalCache();
                break;
        }
    }

    private void saveInternalData() {
        String data = etData.getText().toString();
        File folder = getCacheDir();
        File fileId = new File(folder, "internal_cache.txt");
        saveData(data, fileId);
    }

    private void saveData(String data, File fileId) {
        try {
            FileOutputStream fos = new FileOutputStream(fileId);
            fos.write(data.getBytes());
            Toast.makeText(this, "Data saved successfully in \n" + fileId.getAbsolutePath(), Toast.LENGTH_LONG).show();
            Log.d("ICACHE", fileId.getAbsolutePath());
            fos.close();
        } catch (Exception e) {
            Log.d("ICACHE", e.getMessage());
        }
    }

    private void saveExternalCache() {

    }

    private void loadInternalCache() {
        File folder = getCacheDir();
        File fileId = new File(folder, "internal_cache.txt");
        String data = loadData(fileId);
        tvData.setText(data);
    }

    private String loadData(File fileId) {
        try {
            FileInputStream fis = new FileInputStream(fileId);
            int read;
            StringBuilder sb = new StringBuilder();
            while((read = fis.read()) != -1) {
                sb.append((char)read);
            }
            fis.close();
            return sb.toString();
        } catch (Exception e) {
            Log.d("ICACHE", e.getMessage());
            return null;
        }
    }

    private void loadExternalCache() {

    }
}
