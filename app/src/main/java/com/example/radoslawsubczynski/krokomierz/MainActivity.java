package com.example.radoslawsubczynski.krokomierz;

import android.content.Context;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.github.clans.fab.FloatingActionButton;


public class MainActivity extends AppCompatActivity implements SensorEventListener, View.OnClickListener {
    private static int sum;

    private TextView tv_step;

    private TextView tv_meters;

    private SensorManager mSensorManager;

    private Sensor mStepCounterSensor;

    private Sensor mStepDetectorSensor;

    private final double STEP_FACTOR = 0.7;

    private RelativeLayout mRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_step = (TextView) findViewById(R.id.tv_step);
        tv_meters = (TextView) findViewById(R.id.tv_meters);
        FloatingActionButton menu1 = (FloatingActionButton)findViewById(R.id.subFloatingMenu1) ;
        FloatingActionButton menu2 = (FloatingActionButton)findViewById(R.id.subFloatingMenu2) ;
        mRoot = (RelativeLayout) findViewById(R.id.activity_main);
        menu1.setOnClickListener(this);
        menu2.setOnClickListener(this);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cart:

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
            default:
                return super.onOptionsItemSelected(item);
        }
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
                Snackbar.make(mRoot, "Jescze nie wiem co tutaj bedzie", Snackbar.LENGTH_LONG).show();
                break;
            case R.id.subFloatingMenu2:
                Snackbar.make(mRoot, "Tutaj beda znajdowac sie statystyki", Snackbar.LENGTH_LONG).show();
                break;
        }
    }
}
