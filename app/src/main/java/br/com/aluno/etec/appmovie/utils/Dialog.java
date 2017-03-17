package br.com.aluno.etec.appmovie.utils;

import android.content.Context;
import android.view.View;

import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by Jose on 17/03/2017.
 *
 */

public class Dialog {

    public static void show(Context context, String titulo, String msg){

        final MaterialDialog dialog = new MaterialDialog(context);
        dialog.setTitle(titulo);
        dialog.setMessage(msg);
        dialog.setNegativeButton("OK", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
