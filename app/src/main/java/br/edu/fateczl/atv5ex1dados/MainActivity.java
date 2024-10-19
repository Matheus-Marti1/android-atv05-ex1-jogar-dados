/*
@author:<Matheus Augusto Marti>
 */

package br.edu.fateczl.atv5ex1dados;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RadioButton rbUmDado;
    private RadioGroup rgQtdeDados;
    private Spinner spTipoDado;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rbUmDado = findViewById(R.id.rbUmDado);
        rbUmDado.setChecked(true);
        rgQtdeDados = findViewById(R.id.rgQtdeDados);
        spTipoDado = findViewById(R.id.spTipoDado);
        tvResultado = findViewById(R.id.tvResultado);
        Button btnJogar = findViewById(R.id.btnJogar);

        preencheSpinner();
        btnJogar.setOnClickListener(op -> jogarDado());
    }

    private void jogarDado() {
        StringBuilder stringResultado = new StringBuilder();
        stringResultado.append("Resultado: ");
        int idRb = rgQtdeDados.getCheckedRadioButtonId();
        RadioButton qtdeDados = findViewById(idRb);
        int qtde = Integer.parseInt((String) qtdeDados.getText());
        String dadoSelecionado = (String) spTipoDado.getSelectedItem();
        int qtdeFaces = Integer.parseInt(dadoSelecionado.split("D")[1]);
        int cont = 0;
        while (cont < qtde) {
            int face = (int)(Math.random() * qtdeFaces) + 1;
            stringResultado.append(face);
            if (cont < qtde - 1) {
                stringResultado.append(", ");
            }
            cont++;
        }
        tvResultado.setText(stringResultado.toString());
    }

    private void preencheSpinner() {
        List<String> lista = new ArrayList<>();
        lista.add("D4");
        lista.add("D6");
        lista.add("D8");
        lista.add("D10");
        lista.add("D12");
        lista.add("D20");
        lista.add("D100");

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, lista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTipoDado.setAdapter(adapter);
    }
}