package julianomontini.ead;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class ActivityEfetuarReset extends AppCompatActivity {

    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTheme(ClassChangeTheme.getTheme(this));
        setContentView(R.layout.activity_recuperar_senha);
    }

    public void enviarReset(View view) {

        Integer senha = random.nextInt((999999 - 111111)+1)+111111;

        TextView email = (TextView) findViewById(R.id.emailReset);

        ClassSendEmail sendEmail = new ClassSendEmail();
        sendEmail.resetPass(this,email.getText().toString(),senha);

        try{
            SQLiteDatabase myDatabase = this.openOrCreateDatabase("Schema",MODE_PRIVATE,null);
            //Insere na tabela os dados da tela de cadastro
            myDatabase.execSQL("UPDATE usuario SET senha = " + senha + " WHERE email = '" + email.getText().toString() +"'");

            //Fecha a conexao com o banco de dados
            myDatabase.close();

            new AlertDialog.Builder(ActivityEfetuarReset.this)
                    .setTitle("Sucesso")
                    .setMessage("Se a conta estiver ativa na nossa base, a senha foi atualizada")

                    .setNeutralButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }catch (Exception e){
            new AlertDialog.Builder(ActivityEfetuarReset.this)
                    .setTitle("Erro")
                    .setMessage("Ocorreu o erro " + e.getMessage())

                    .setNeutralButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

    }
}
