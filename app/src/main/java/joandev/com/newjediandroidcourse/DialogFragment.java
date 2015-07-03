package joandev.com.newjediandroidcourse;

/**
 * Created by joanbarroso on 3/7/15.
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class DialogFragment extends android.app.DialogFragment implements View.OnClickListener{

    EditText number1;
    Button confirmButton;
    TextView dTv;
    TextView textView5;

    // En caso de querer pasarle parámetros, los pasaríamos por aquí
    //y los guardaríamos con un bunlde, mediente el comando f.setArguments(myBundle)
    static DialogFragment newInstance() {
        DialogFragment f = new DialogFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dialog, container, false);


        /*Inicializamos las vistas, al estar dentro de un Fragment, necesitamos de la vista inflada
        para buscar en sus elementos*/
        dTv = (TextView) v.findViewById(R.id.dTv);
        textView5 = (TextView) v.findViewById(R.id.textView5);
        number1 = (EditText) v.findViewById(R.id.number1);
        confirmButton = (Button) v.findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(this);

        return  v;
    }
    

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    //Cogemos el numero si hay, si no, mostramos un toast pidiendo que lo introduzcan
    @Override
    public void onClick(View view) {
        boolean badEntry =  false;
        String solution = new String();
        if (!number1.getText().toString().equals("")){
            solution = number1.getText().toString();
        }
        else badEntry = true;
        if (badEntry) Toast.makeText(getActivity().getApplicationContext(), "Fill the gap before confirmation, please", Toast.LENGTH_SHORT).show();
        else {
            this.mListener.onComplete(solution);
            dismiss();
        }
    }


    //Interfaz por la que nos comunicamos con la Activity
    public static interface OnCompleteListener {
        public abstract void onComplete(String res);
    }

    private OnCompleteListener mListener;

    // Hacemos que la Activity haga de listener
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnCompleteListener)activity;
        }
        catch (final ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnCompleteListener");
        }
    }
}
