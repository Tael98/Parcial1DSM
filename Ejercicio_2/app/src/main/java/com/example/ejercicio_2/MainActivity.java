package com.example.ejercicio_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public double a;
    public double b;
    public double c;
    public double x1;
    public double x2;
    private EditText txtA;
    private EditText txtB;
    private EditText txtC;
    private TextView Resultado;
    private TextView X1;
    private TextView X2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializando campos a utilizar
        txtA=(EditText)findViewById(R.id.editTextA);
        txtB=(EditText)findViewById(R.id.editTextB);
        txtC=(EditText)findViewById(R.id.editTextC);
        Resultado=(TextView)findViewById(R.id.textResultado);
        X1=(TextView)findViewById(R.id.textX1);
        X2=(TextView)findViewById(R.id.textX2);
        //Asignando método al botón
        Button clickButton = findViewById(R.id.btnCalcular);
        clickButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {   Calcular();  }
        });
    }

    public void Calcular() {
        if(numbersReady())
        {
            try
            {
                //Captura de variables
                a = Double.parseDouble(txtA.getText().toString());
                b = Double.parseDouble(txtB.getText().toString());
                c = Double.parseDouble(txtC.getText().toString());
                //Cálculos
                x1 = ((-1*b) + (Math.sqrt(Math.pow(b, 2) - 4*a*c))) / (2*a);
                x2 = ((-1*b) - (Math.sqrt(Math.pow(b, 2) - 4*a*c))) / (2*a);
                //Resultados
                Resultado.setVisibility(View.VISIBLE);
                X1.setVisibility(View.VISIBLE);
                if (String.valueOf(x1) == "NaN" || String.valueOf(x1) == "" || String.valueOf(x1) == null) X1.setText("X = i");
                else X1.setText("X = " + String.valueOf(x1));

                X2.setVisibility(View.VISIBLE);
                if (String.valueOf(x2) == "NaN" || String.valueOf(x2) == "" || String.valueOf(x2) == null) X2.setText("X = i");
                else X2.setText("X = " + String.valueOf(x2));
                Toast.makeText(this, "¡Cálculo exitoso!", Toast.LENGTH_SHORT).show();
                hideKeyboard(MainActivity.this);

            }
            catch (Exception e)
            {
                System.out.println("Error " + e.getMessage());
                Toast.makeText(this, "Por favor ingrese números válidos.", Toast.LENGTH_SHORT).show();
            }

        }
        else
        {
            Toast.makeText(this, "Por favor ingrese números válidos.", Toast.LENGTH_SHORT).show();
        }
    }

    //Método para corroborar que los números no estén vacíos
    public boolean numbersReady()
    {
        if((txtA.getText().toString() == "" || txtA.getText().toString() == null) || (txtB.getText().toString() == "" || txtB.getText().toString() == null) || (txtC.getText().toString() == "" || txtC.getText().toString() == null))
        {
            return false;
        }
        else return true;
    }

    //Método para ocultar el teclado
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}