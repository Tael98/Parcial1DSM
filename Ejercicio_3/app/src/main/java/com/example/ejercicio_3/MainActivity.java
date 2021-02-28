package com.example.ejercicio_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText nombre1;
    private EditText nombre2;
    private EditText nombre3;
    private EditText cargo1;
    private EditText cargo2;
    private EditText cargo3;
    private EditText horas1;
    private EditText horas2;
    private EditText horas3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre1 = findViewById(R.id.textNombre1);
        nombre2 = findViewById(R.id.textNombre2);
        nombre3 = findViewById(R.id.textNombre3);


        cargo1 = findViewById(R.id.textCargo1);
        cargo2 = findViewById(R.id.textCargo2);
        cargo3 = findViewById(R.id.textCargo3);

        horas1 = findViewById(R.id.textHoras1);
        horas2 = findViewById(R.id.textHoras2);
        horas3 = findViewById(R.id.textHoras3);

    }

    public void calcular(View view){
        Intent intent = new Intent(this, Resultados.class);

        String nombre1 = this.nombre1.getText().toString();
        String nombre2 = this.nombre2.getText().toString();
        String nombre3 = this.nombre3.getText().toString();
        String[] nombres = new String[] {nombre1, nombre2, nombre3 };

        String cargo1 = this.cargo1.getText().toString();
        String cargo2 = this.cargo2.getText().toString();
        String cargo3 = this.cargo3.getText().toString();
        String[] cargos = new String[]{cargo1, cargo2, cargo3 };

        Integer horas1 = Integer.parseInt(this.horas1.getText().toString());
        Integer horas2 = Integer.parseInt(this.horas2.getText().toString());
        Integer horas3 = Integer.parseInt(this.horas3.getText().toString());
        Integer[] horas = new Integer[]{horas1, horas2, horas3 };

        if((horas1 < 0) || (horas2 < 0) || (horas3 < 0)){
            Toast notificacion= Toast.makeText(this,"Ingrese un número de horas válido.",Toast.LENGTH_LONG);
            notificacion.show();
        }

        List<Double> salarios = new ArrayList<>();
        List<Double> des_isss = new ArrayList<>();
        List<Double> des_afp = new ArrayList<>();
        List<Double> des_renta = new ArrayList<>();

        for(int i = 0; i < 3; i++){
            Double salario = 0.0;
            if(horas[i] < 160){
                salario = horas[i] * 9.75;
            }else{
                Integer extra = horas[i] - 160;
                salario = (160 * 9.75) + (extra * 11.50);
            }

            Double isss = salario * 0.0525;
            des_isss.add(isss);
            Double afp = salario * 0.0688;
            des_afp.add(afp);
            Double renta = salario * 0.1;
            des_renta.add(renta);

            Double total_desc = isss + afp + renta;
            Double sal_liq = salario - total_desc;

            if(cargos[0] == "Gerente" && cargos[1] == "Asistente" && cargos[0] == "Secretaria"){
                salarios.add(sal_liq);
                continue;
            }else if(cargos[i] == "Gerente"){
                sal_liq += sal_liq * 0.1;
            }else if(cargos[i] == "Asistente"){
                sal_liq += sal_liq * 0.05;
            }else{
                sal_liq += sal_liq * 0.03;
            }

            salarios.add(sal_liq);
        }

        Double mayor = Collections.max(salarios);
        Double menor = Collections.min(salarios);
        Integer posicion_mayor = salarios.indexOf(mayor);
        Integer posicion_menor = salarios.indexOf(menor);
        String mayorEmpleado = nombres[posicion_mayor];
        String menorEmpleado = nombres[posicion_menor];
        Integer mayores = 0;
        for (int i = 0; i < 3; i++){
            if (salarios.get(i) > 300.00){
                mayores++;
            }
        }

        Bundle b = new Bundle();
        b.putSerializable("des_isss", (Serializable)des_isss);
        b.putSerializable("afp", (Serializable)des_afp);
        b.putSerializable("renta", (Serializable)des_renta);
        b.putSerializable("salarios", (Serializable)salarios);
        intent.putExtra("nombres", nombres);
        intent.putExtras(b);
        intent.putExtra("salario_max", mayor);
        intent.putExtra("salario_min", menor);
        intent.putExtra("mayores_tr", mayores);
        intent.putExtra("empleado_max", mayorEmpleado);
        intent.putExtra("empleado_min", menorEmpleado);
        startActivity(intent);
    }
}