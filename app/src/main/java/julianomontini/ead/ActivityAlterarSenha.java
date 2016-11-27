package julianomontini.ead;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ActivityAlterarSenha extends AppCompatActivity {

    int mUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTheme(ClassChangeTheme.getTheme(this));
        setContentView(R.layout.activity_alterar_senha);

        mUserId = (int)getIntent().getSerializableExtra("IDUsuario");
    }

    public void trocaSenha(View view) {

        TextView senhaAntiga = (TextView)findViewById(R.id.senhaAntiga);
        TextView senhaNova = (TextView)findViewById(R.id.senhaNova);
        TextView confSenha = (TextView)findViewById(R.id.confSenhaNova);

        if(senhaAntiga.getText().length() > 0 && senhaNova.length() > 0){
            if(senhaNova.getText().toString().equals(confSenha.getText().toString())){

                SQLiteDatabase myDatabase = this.openOrCreateDatabase("Schema", MODE_PRIVATE, null);

                Cursor c = myDatabase.rawQuery("SELECT * FROM usuario WHERE ID = " + mUserId + " AND senha = " + senhaAntiga.getText(), null);

                if(c.getCount() > 0){

                    myDatabase.execSQL("UPDATE usuario SET senha =" + senhaNova.getText() + " WHERE ID = " + mUserId);

                    DisplayAlert.neutralDialog(this,"Senha alterada com sucesso");

                }
                else{
                    Toast.makeText(ActivityAlterarSenha.this,"Senha antiga incorreta",Toast.LENGTH_LONG).show();
                }

            }
            else{

                Toast.makeText(ActivityAlterarSenha.this,"As senhas sao diferentes",Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(ActivityAlterarSenha.this,"Senha vazia",Toast.LENGTH_LONG).show();
        }

    }
}
