package com.example.mygaragem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mygaragem.db.Garagem_db;
import com.example.mygaragem.entidade.Motorista;
import com.example.mygaragem.tipos_de_msg.alert;

import java.util.ArrayList;
import java.util.List;

public class Lista_Motorista extends AppCompatActivity {

    ListView lst_lista_motorista;
    private Garagem_db garagem_db; // banco de dados
    private List<Motorista> listamotoristas; // lista de motorista do banco de dados
    private int posicaoselecionada;
    private ArrayAdapter arrayAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Lista motoristas");


        lst_lista_motorista = findViewById(R.id.lst_lista_motorista);
        garagem_db = new Garagem_db(this);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        mostra_motoristas();

        // list view
        lst_lista_motorista.setOnItemClickListener(clickListener); // 1
        lst_lista_motorista.setOnCreateContextMenuListener(contextMenuListener); // 2
        lst_lista_motorista.setOnItemLongClickListener(onItemLongClickListener); // 3

    } ///////////////////////// metodos /////////////////////////////////////////////////////

    @Override
    protected void onResume() {
        mostra_motoristas();
        super.onResume();
    }

    public void vai_p_AddnewMotorista(View view) {
        Intent intent = new Intent(Lista_Motorista.this, AddnewMotorista.class);
        startActivity(intent);
    }

    public void mostra_motoristas() { // metodo para mostra o motorista sempre que tiver alguma alteração
        listamotoristas = garagem_db.listamotorista();

        List<String> valores = new ArrayList<>();
        for (Motorista m : listamotoristas) {
            valores.add(m.getNome_motorista());
        }
        arrayAdapter.clear();
        arrayAdapter.addAll(valores);
        lst_lista_motorista.setAdapter(arrayAdapter);
    }
            // escutores do list view
    private AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() { // 1
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Motorista motorista = garagem_db.consultaporid(listamotoristas.get(position).getIdMotorista());

            StringBuilder info = new StringBuilder();
            info.append("MOTORISTA: " + motorista.getNome_motorista());
            info.append("\nTELEFONE: " + motorista.getTelefone());
            info.append("\nCARRO: " + motorista.getNome_carro());
            info.append("\nPLACA: " + motorista.getPlaca());
            info.append("\nVAGA: " + motorista.getVaga());
            info.append("\nVALOR: " + motorista.getValor());

            alert.alert_pagar(Lista_Motorista.this, "Info", info.toString());

        }
    };

    private View.OnCreateContextMenuListener contextMenuListener = new View.OnCreateContextMenuListener() { // 2
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Opções").add(1,10, 1,"Deletar");
            menu.add(1,20,1,"Editar");
        }
    };

    private AdapterView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() { // 3
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            posicaoselecionada = position;
            return false;
        }
    };

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 10:
                alert.alert_deletar_editar(Lista_Motorista.this,"Remover Motorisra",
                        "Deseja realmente remover esse motorista?", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int id = listamotoristas.get(posicaoselecionada).getIdMotorista();
                                garagem_db.deletarmotorista(id);
                                mostra_motoristas();
                                arrayAdapter.notifyDataSetChanged();

                            }
                        });
                break;
            case 20:
                Motorista motorista = garagem_db.consultaporid(listamotoristas.get(posicaoselecionada).getIdMotorista());
                Intent intent = new Intent(Lista_Motorista.this, editar_activity.class);
                intent.putExtra("motorista", motorista);
                startActivity(intent);
                break;
        }
        return super.onContextItemSelected(item);
    }


}