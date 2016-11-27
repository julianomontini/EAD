package julianomontini.ead;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ActivityCustomizarLayout extends FragmentActivity {

    int mIDUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTheme(ClassChangeTheme.getTheme(this));
        setContentView(R.layout.activity_customizar);

        mIDUsuario = (int)getIntent().getSerializableExtra("IdUsuario");
    }


    public void apagaConta(View view){

        new AlertDialog.Builder(ActivityCustomizarLayout.this)
                .setTitle("Apagar Conta")
                .setMessage("Tem certeza que deseja apagar a sua conta?")

                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        try{
                            SQLiteDatabase myDatabase = getApplicationContext().openOrCreateDatabase("Schema",MODE_PRIVATE,null);
                            myDatabase.execSQL("DELETE FROM usuario WHERE id = "+mIDUsuario);
                            myDatabase.execSQL("DELETE FROM usuario_curso WHERE n_usuario = "+mIDUsuario);
                            myDatabase.execSQL("DELETE FROM usuario_exerc WHERE n_aluno = "+mIDUsuario);
                            myDatabase.close();

                            Intent i = getBaseContext().getPackageManager()
                                    .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);

                        }catch (Exception e){
                            Log.i("ERROOOR",e.getMessage());
                        }

                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

    public void alteraSenha(View view){

        Intent i = new Intent(this,ActivityAlterarSenha.class);
        i.putExtra("IDUsuario",mIDUsuario);
        startActivity(i);

    }

    public void reiniciaConf(View view){

        new AlertDialog.Builder(ActivityCustomizarLayout.this)
                .setTitle("Reiniciar configurações")
                .setMessage("Tem certeza que deseja reiniciar as configurações?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

    public void salvarConf(View view){

        int marcado = getRadioMarcado();

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("Theme",marcado);
        editor.commit();

        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);

    }

    private int getRadioMarcado(){

        RadioButton r1 = (RadioButton)findViewById(R.id.opcRad1);
        RadioButton r2 = (RadioButton)findViewById(R.id.opcRad2);
        RadioButton r3 = (RadioButton)findViewById(R.id.opcRad3);

        if(r1.isChecked()){
            return 1;
        }else if(r2.isChecked()){
            return 2;
        }else if(r3.isChecked()){
            return 3;
        }else{
            return 1;
        }

    }
}
