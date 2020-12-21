package com.example.ex113;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 *  * @author		Shahar Yani
 *  * @version  	1.0
 *  * @since		11/12/2020
 *
 *  * This MainActivity.class uses a internal file in order to present, save or remove the data\
 *     according to the user's choice.
 *  */
public class MainActivity extends AppCompatActivity {

    EditText eT;
    TextView inputView;
    String input, toDisplay, line;

    /**
     *  In the onCreate method the app first check if there a data in the internal file and
     *  displays it to the user. (The app reads from the internal file)
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eT = findViewById(R.id.eT);
        inputView = findViewById(R.id.inputView);

        try {
            FileInputStream fis = openFileInput("UserData.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            toDisplay = sb.toString();
            inputView.setText(toDisplay);
            isr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * In saveTheState the app writes the state of the app varibles in the internal file
     * @param view
     */
    public void saveTheState(View view) {
        try {
            input = eT.getText().toString();
            FileOutputStream fos = openFileOutput("UserData.txt", MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            toDisplay += input;
            bw.write(toDisplay);
            bw.close();
            inputView.setText(toDisplay);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resetHistory(View view) {
        toDisplay = "";
        inputView.setText(toDisplay);
    }

    /**
     *  In exitFromActivity method the app saves the internal file and close the
     *  Activity
     * @param view
     */
    public void exitFromActivity(View view) {
        try {
            FileInputStream fis = openFileInput("UserData.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            toDisplay = sb.toString();
            isr.close();
            finish();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String title = item.getTitle().toString();
        if (title.equals("Credits")){
            Intent si = new Intent(this,CreditsActivity.class);
            startActivity(si);
        }
        return super.onOptionsItemSelected(item);
    }
}