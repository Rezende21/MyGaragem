package com.example.mygaragem.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.mygaragem.entidade.Motorista;

import java.util.ArrayList;
import java.util.List;

public class Garagem_db extends SQLiteOpenHelper {

    public Garagem_db(@Nullable Context context) {
        super(context, Constantes.BD_NOME, null, Constantes.BD_VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE TB_GARAGEM( ");
        query.append(" ID_MOTORISTA INTEGER PRIMARY KEY AUTOINCREMENT,");
        query.append(" NOME_MOTORISTA TEXT(30) NOT NULL,");
        query.append(" TELEFONE TEXT(20) NOT NULL,");
        query.append(" NOME_CARRO TEXT,");
        query.append(" PLACA TEXT,");
        query.append(" VAGA TEXT,");
        query.append(" VALOR TEXT)");

        db.execSQL(query.toString());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void salvarmotorista(Motorista motorista) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = getContentValuesMotorista(motorista);
        db.insert("TB_GARAGEM", null,contentValues);
    }

    public List<Motorista> listamotorista() {
        List<Motorista> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("TB_GARAGEM", null,null,null,null,null,"NOME_MOTORISTA");

        while (cursor.moveToNext()) {
            Motorista motorista = new Motorista();
            setMotorista(cursor,motorista);
            lista.add(motorista);
        }

        return lista;
    }

    public void deletarmotorista(int idMotorista) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("TB_GARAGEM", "ID_MOTORISTA = ? ", new String[] {String.valueOf(idMotorista)});
    }

    public void updatemotorista(Motorista motorista) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = getContentValuesMotorista(motorista);

        db.update("TB_GARAGEM", contentValues, "ID_MOTORISTA = ?", new String[] {String.valueOf(motorista.getIdMotorista())});
    }

    public Motorista consultaporid(int idMotorista) {
        Motorista motorista = new Motorista();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("TB_GARAGEM", null, "ID_MOTORISTA = ?", new String[] {String.valueOf(idMotorista)},
                null, null, "NOME_MOTORISTA");

        if (cursor.moveToNext()) {
            setMotorista(cursor,motorista);
        }

        return motorista;
    }

    private void setMotorista (Cursor cursor, Motorista motorista) {
        motorista.setIdMotorista(cursor.getInt(cursor.getColumnIndex("ID_MOTORISTA")));
        motorista.setNome_motorista(cursor.getString(cursor.getColumnIndex("NOME_MOTORISTA")));
        motorista.setTelefone(cursor.getString(cursor.getColumnIndex("TELEFONE")));
        motorista.setNome_carro(cursor.getString(cursor.getColumnIndex("NOME_CARRO")));
        motorista.setPlaca(cursor.getString(cursor.getColumnIndex("PLACA")));
        motorista.setVaga(cursor.getString(cursor.getColumnIndex("VAGA")));
        motorista.setValor(cursor.getString(cursor.getColumnIndex("VALOR")));
    }

    private ContentValues getContentValuesMotorista(Motorista motorista) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("NOME_MOTORISTA", motorista.getNome_motorista());
        contentValues.put("TELEFONE", motorista.getTelefone());
        contentValues.put("NOME_CARRO",motorista.getNome_carro());
        contentValues.put("PLACA", motorista.getPlaca());
        contentValues.put("VAGA", motorista.getVaga());
        contentValues.put("VALOR", motorista.getValor());

        return contentValues;
    }

}
