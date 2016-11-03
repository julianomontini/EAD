package julianomontini.ead;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class DisplayAlert {

    public static AlertDialog confirmationDialog(Context context, String message, DialogInterface.OnClickListener ok ,DialogInterface.OnClickListener cancel) {
        return new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("OK", ok)
                .setNegativeButton("Cancelar",cancel)
                .setCancelable(false)
                .show();
    }

    public static AlertDialog neutralDialog(Context context, String message){
        return new AlertDialog.Builder(context)
                .setMessage(message)
                .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Nao faz nada
                    }
                })
                .show();
    }

}
