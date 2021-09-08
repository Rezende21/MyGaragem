package com.example.mygaragem.tipos_de_msg;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.mygaragem.Lista_Motorista;
import com.example.mygaragem.R;

public class alert {


    public static void alert_pagar(Activity activity, String titulo, String txt) {
        AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
        alertDialog.setTitle(titulo);
        alertDialog.setMessage(txt);
        /* parte do codigo comentada é uma imprementação para que o app saiba se o motorista pagou ou nao aquele mes ainda vai ser implementado */
//        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Pago", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
//        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Não Pagou", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.copyFrom(alertDialog.getWindow().getAttributes());
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        alertDialog.show();
        alertDialog.getWindow().setAttributes(params);

    }
        /* metodo para que o dono do app possa alterar os dados do usuario  */
    public static void alert_deletar_editar(Activity activity, String titulo, String txt, DialogInterface.OnClickListener onClickListener){
        AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
        alertDialog.setTitle(titulo);
        alertDialog.setMessage(txt);
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Deletar", onClickListener);
        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.copyFrom(alertDialog.getWindow().getAttributes());
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        alertDialog.show();
        alertDialog.getWindow().setAttributes(params);

    }
}
