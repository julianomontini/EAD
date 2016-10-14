package julianomontini.ead;

import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityEfetuarCadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);


    }

    public void efetuarCadastro(View view) {

        //Pega todos os campos do layout
        TextView nome = (TextView) findViewById(R.id.nome_cadastro);
        TextView email = (TextView) findViewById(R.id.email_cadastro);
        TextView senha = (TextView) findViewById(R.id.senha_cadastro);
        TextView confSenha = (TextView) findViewById(R.id.conf_senha);
        TextView telefone = (TextView) findViewById(R.id.telefone_cadastro);
        TextView cpf = (TextView) findViewById(R.id.cpf_cadastro);
        CheckBox check = (CheckBox) findViewById(R.id.check_cadastro);


        try {

            //Tenta instanciar a classe aluno com os dados passados na tela
            ClassDadosAluno aluno = new ClassDadosAluno(nome.getText().toString(),
                    email.getText().toString(),
                    senha.getText().toString(),
                    confSenha.getText().toString(),
                    telefone.getText().toString(),
                    cpf.getText().toString());

            //Verifica se os termos de privacidade foram aceitos
            if (!check.isChecked()){

                throw new ClassExceptions("Ler e aceitar os termos de privacidade");

            }

            //Abre a conexao com Schema
            SQLiteDatabase myDatabase = this.openOrCreateDatabase("Schema",MODE_PRIVATE,null);


            //Insere na tabela os dados da tela de cadastro
            myDatabase.execSQL("INSERT INTO usuario(nome, email, senha, telefone, cpf) VALUES("
                    + "'" + aluno.getNome()  +"'" + ","
                    + "'" + aluno.getEmail() +"'" + ","
                    + "'" + aluno.getSenha() +"'" + ","
                    + "'" + aluno.getTel()   +"'" + ","
                    + "'" + aluno.getCpf()   +"'"
                    + ")");

            //Informa que o usuario se cadastrou com sucesso
            Toast.makeText(ActivityEfetuarCadastro.this,"Cadastro Efetuado!", Toast.LENGTH_LONG).show();

            //Fecha a conexao com o banco de dados
            myDatabase.close();

        }
        //Trata exception de constraint
        catch (SQLiteConstraintException e){

            Toast.makeText(ActivityEfetuarCadastro.this,"O email já está cadastrado",Toast.LENGTH_LONG).show();

        }
        //Trata exceptions genericas
        catch (Exception e) {

            Toast.makeText(ActivityEfetuarCadastro.this, e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }
}