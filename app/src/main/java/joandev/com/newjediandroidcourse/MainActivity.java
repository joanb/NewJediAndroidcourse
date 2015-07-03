package joandev.com.newjediandroidcourse;

import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DialogFragment.OnCompleteListener{

    Button time;
    TextView hourTV;
    TextView numberTV;

    Integer hours = new Integer(0);
    Integer minutes = new Integer(0);


    private TimePickerDialog.OnTimeSetListener timeSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time = (Button) findViewById(R.id.timeButton);
        time.setOnClickListener(this);
        hourTV = (TextView) findViewById(R.id.timeView);
        numberTV = (TextView) findViewById(R.id.numberTV);


        timeSetListener = new TimePickerDialog.OnTimeSetListener(){
          @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute){
              hours = hourOfDay;
              minutes = minute;
              refreshTimes();
          }
        };
    }

    private void refreshTimes() {
        hourTV.setText(hours.toString() + " : " + minutes.toString());
    }


    //IMplementacion de la interfaz del fragment, se llama al darle al boton de aceptar
    @Override
    public void onComplete(String res) {
        numberTV.setText(res);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.timeButton)
            new TimePickerDialog(view.getContext(), timeSetListener, hours, minutes, true).show();
    }

    public void dialogShow(View v) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        android.app.DialogFragment dialogFragment = DialogFragment.newInstance();
        dialogFragment.show(ft, "dialog");
    }


}
