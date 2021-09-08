package com.example.mygaragem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mygaragem.db.Garagem_db;
import com.example.mygaragem.entidade.Motorista;

public class AddnewMotorista extends AppCompatActivity {

    EditText edit_nome_motorista, edit_telefone, edit_nome_carro, edit_placa_carro, edit_vaga, edit_valor;
    Button bt_salva;
    private Motorista motorista;
    private Garagem_db garagem_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnewcarr);
        getSupportActionBar().setTitle("Add Motorista");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        motorista = new Motorista();
        garagem_db = new Garagem_db(this);

        edit_nome_motorista = findViewById(R.id.edit_nome_motorista);
        edit_telefone = findViewById(R.id.edit_telefone);
        edit_nome_carro =  findViewById(R.id.edit_nome_carro);
        edit_placa_carro = findViewById(R.id.edit_placa_carro);
        edit_vaga = findViewById(R.id.edit_vaga);
        edit_valor = findViewById(R.id.edit_valor);

        bt_salva = findViewById(R.id.bt_salva);
        bt_salva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Motorista m = montar_motorista();
                if (!validacao(m)){
                    garagem_db.salvarmotorista(m);
                    Toast.makeText(AddnewMotorista.this,"Cadastro ok",Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(AddnewMotorista.this, "Cadastro nao efetuado!!", Toast.LENGTH_LONG).show();
                }
            }

        });
    }

    public Motorista montar_motorista() {
        motorista.setNome_motorista(edit_nome_motorista.getText().toString());
        motorista.setNome_carro(edit_nome_carro.getText().toString());
        motorista.setTelefone(edit_telefone.getText().toString());
        motorista.setPlaca(edit_placa_carro.getText().toString());
        motorista.setVaga(edit_vaga.getText().toString());
        motorista.setValor(edit_valor.getText().toString());

        return motorista;
    }

    public boolean validacao(Motorista motorista) {
        boolean erro = false;
        if (motorista.getNome_motorista().equals("") || motorista.getNome_motorista().equals(null)) {
            edit_nome_motorista.setError("Esse campo esta vazio!!!");
            erro = true;
        }
        if (motorista.getTelefone().equals("") || motorista.getTelefone().equals(null)) {
            edit_telefone.setError("Esse campo esta vazio!!!");
            erro = true;
        } else if (motorista.getTelefone().length() < 11 ) {
            edit_telefone.setError("Verifica se o campo foi preenchido corretamente");
            erro = true;
        }

        return erro;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}