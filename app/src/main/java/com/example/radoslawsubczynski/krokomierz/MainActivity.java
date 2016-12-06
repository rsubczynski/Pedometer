package com.example.radoslawsubczynski.krokomierz;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.radoslawsubczynski.krokomierz.Database.DBHelper;
import com.example.radoslawsubczynski.krokomierz.Database.DefaultDataProviderComponent;
import com.example.radoslawsubczynski.krokomierz.Database.Listeners.OnGetAllCotacts;
import com.github.clans.fab.FloatingActionButton;


public class MainActivity extends AppCompatActivity implements SensorEventListener, View.OnClickListener,OnGetAllCotacts {
    private static int sum;


    private TextView tv_step;

    private TextView tv_meters;

    private SensorManager mSensorManager;

    private Sensor mStepCounterSensor;

    private Sensor mStepDetectorSensor;

    private double STEP_FACTOR = 0.7;

    private RelativeLayout mRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_step = (TextView) findViewById(R.id.tv_step);
        tv_meters = (TextView) findViewById(R.id.tv_meters);
        FloatingActionButton floatingActionButtonWalk = (FloatingActionButton)findViewById(R.id.subFloatingMenu1) ;
        FloatingActionButton floatingActionButtonDatabase = (FloatingActionButton)findViewById(R.id.subFloatingMenu2) ;
        FloatingActionButton floatingActionButtonRefresh = (FloatingActionButton)findViewById(R.id.subFloatingMenu3) ;
        mRoot = (RelativeLayout) findViewById(R.id.activity_main);
        floatingActionButtonWalk.setOnClickListener(this);
        floatingActionButtonDatabase.setOnClickListener(this);
        floatingActionButtonRefresh.setOnClickListener(this);

        mSensorManager = (SensorManager)
                getSystemService(Context.SENSOR_SERVICE);
        mStepCounterSensor = mSensorManager
                .getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        mStepDetectorSensor = mSensorManager
                .getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_directions_walk_white_24px);
        printScore(sum, STEP_FACTOR);


    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        if (sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            sum++;
            printScore(sum, STEP_FACTOR);
        }
    }

    private void printScore(int sum, double stepFactor) {
        tv_step.setText(String.format("%d", sum));
        tv_meters.setText(String.format("%.2f", sum * stepFactor));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


    protected void onResume() {

        super.onResume();

        mSensorManager.registerListener(this, mStepCounterSensor,

                SensorManager.SENSOR_DELAY_FASTEST);
        mSensorManager.registerListener(this, mStepDetectorSensor,

                SensorManager.SENSOR_DELAY_FASTEST);

    }

    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this, mStepCounterSensor);
        mSensorManager.unregisterListener(this, mStepDetectorSensor);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.subFloatingMenu1:
                DefaultDataProviderComponent.getInstance().registerListenerGetAllContats(this);
                DefaultDataProviderComponent.getInstance().getAllCotacts();
                break;

            case R.id.subFloatingMenu2:
                AlertDialogRefreshScore();
                break;

            case R.id.subFloatingMenu3:
                alertDialogGetGetHeigth();
                break;
        }
    }

    private void AlertDialogRefreshScore() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.alert_warning)
                .setMessage(R.string.alert_quest)
                .setPositiveButton(R.string.alert_yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        sum = 0;
                        tv_step.setText("0");
                        tv_meters.setText("0");
                    }
                })
                .setNegativeButton(R.string.alert_no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void alertDialogGetGetHeigth() {
        final EditText input = new EditText(MainActivity.this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        input.setRawInputType(Configuration.KEYBOARD_12KEY);
        new AlertDialog.Builder(this)
                .setView(input)
                .setTitle(R.string.alert_warning)
                .setMessage(getString(R.string.alert_give_me_your_height))

                .setPositiveButton(R.string.alert_yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(input.length()>0){
                            double lengStep = Utils.lengthStep(input.getText().toString().trim());
                            Snackbar.make(mRoot, getString(R.string.alert_lenght_step) + lengStep, Snackbar.LENGTH_LONG).show();}
                        else {
                            Snackbar.make(mRoot, getString(R.string.alert_too_small), Snackbar.LENGTH_LONG).show();
                        }

                    }
                })
                .setNegativeButton(R.string.alert_no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


    @Override
    public void onResponseGetAllCotactsSucces(String cos) {

    }

    @Override
    public void onResponseOnGetAllCotactsFail() {

    }
}
