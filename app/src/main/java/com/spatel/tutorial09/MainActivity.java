package com.spatel.tutorial09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private Button btnAsset,btnRead,btnWrite;
    private EditText edtFile;
    private TextView txtFileContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAsset = findViewById(R.id.btnReadAsset);
        btnRead = findViewById(R.id.btnReadFile);
        btnWrite = findViewById(R.id.btnWriteFile);
        edtFile = findViewById(R.id.edtFile);
        txtFileContent = findViewById(R.id.txtFileContent);
    }

    public void readAsset(View view) {
        try {
            InputStream iStream = getAssets().open("data.txt");  // to create pull between stream and file
            InputStreamReader isReader = new InputStreamReader(iStream); // set isReader object to traverse on the pull to reached location
            BufferedReader reader = new BufferedReader(isReader); // convert file into bufferReadeer object which means convert file into smaller file to access it using read or readline
            String str;
            StringBuilder sb = new StringBuilder();
            while((str = reader.readLine()) != null){
                sb.append(str);
            }
            txtFileContent.setText(sb.toString());
            iStream.close();
            isReader.close();
            reader.close();
            Toast.makeText(this,"Data read succesfully from Assets",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeFile(View view) {
        try {
            FileOutputStream fout = openFileOutput("myFile", Context.MODE_PRIVATE);
            String data = edtFile.getText().toString();
            fout.write(data.getBytes());
            fout.close();
            edtFile.setText("");
            Toast.makeText(this,"Data Inserted Succesdfully",Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readWile(View view) {
        try {
            FileInputStream fin = openFileInput("myFile");
            int c;
            String temp = "";
            while((c = fin.read()) != -1){
                temp = temp + ((char) c);
            }
            fin.close();
            txtFileContent.setText(temp);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}