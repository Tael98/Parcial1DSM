package com.example.ejercicio_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class SalariosCalculados extends AppCompatActivity {

    private TextView nombre1, nombre2, nombre3, isss1, isss2, isss3,
            afp1, afp2, afp3, renta1, renta2, renta3,
            salario_maximo, salario_minimo, mayores_tres,
            liquido1, liquido2, liquido3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salarios_calculados);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        ArrayList<Double> isss = (ArrayList<Double>) b.getSerializable("des_isss");
        ArrayList<Double> salarios = (ArrayList<Double>) b.getSerializable("salarios");
        ArrayList<Double> afp = (ArrayList<Double>) b.getSerializable("afp");
        ArrayList<Double> renta = (ArrayList<Double>) b.getSerializable("renta");
        String[] nombres = intent.getStringArrayExtra("nombres");

        Double salario_max = intent.getDoubleExtra("salario_max", 0.0);
        Double salario_min = intent.getDoubleExtra("salario_min", 0.0);
        Integer mayores_tr = intent.getIntExtra("mayores_tr", 0);
        String mayor_empleado = intent.getStringExtra("empleado_max");
        String menor_empleado = intent.getStringExtra("empleado_min");

        nombre1 = findViewById(R.id.textNombre1);
        nombre1.setText(nombres[0]);
        nombre2 = findViewById(R.id.textNombre2);
        nombre2.setText(nombres[1]);
        nombre3 = findViewById(R.id.textNombre3);
        nombre3.setText(nombres[2]);

        isss1 = findViewById(R.id.isss1);
        isss1.setText(String.format("%.2f", isss.get(0)));
        isss2 = findViewById(R.id.isss2);
        isss2.setText(String.format("%.2f", isss.get(1)));
        isss3 = findViewById(R.id.isss3);
        isss3.setText(String.format("%.2f", isss.get(2)));

        afp1 = findViewById(R.id.afp1);
        afp1.setText(String.format("%.2f", afp.get(0)));
        afp2 = findViewById(R.id.afp2);
        afp2.setText(String.format("%.2f", afp.get(1)));
        afp3 = findViewById(R.id.afp3);
        afp3.setText(String.format("%.2f", afp.get(2)));

        renta1 = findViewById(R.id.renta1);
        renta1.setText(String.format("%.2f", renta.get(0)));
        renta2 = findViewById(R.id.renta2);
        renta2.setText(String.format("%.2f", renta.get(1)));
        renta3 = findViewById(R.id.renta3);
        renta3.setText(String.format("%.2f", renta.get(2)));

        liquido1 = findViewById(R.id.liquido1);
        liquido1.setText(String.format("%.2f", salarios.get(0)));
        liquido2 = findViewById(R.id.liquido2);
        liquido2.setText(String.format("%.2f", salarios.get(1)));
        liquido3 = findViewById(R.id.liquido3);
        liquido3.setText(String.format("%.2f", salarios.get(2)));

        salario_maximo = findViewById(R.id.sal_max);
        salario_maximo.setText(String.format("El salario máximo es de %.2f, obtenido por %s", salario_max, mayor_empleado));
        salario_minimo = findViewById(R.id.sal_minimo);
        salario_minimo.setText(String.format("El salario mínimo es de %.2f, obtenido por %s", salario_min, menor_empleado));
        mayores_tres = findViewById(R.id.mayores_tres);
        mayores_tres.setText(String.format("%d empleados tienen un salario mayor a 300", mayores_tr));
    }
}