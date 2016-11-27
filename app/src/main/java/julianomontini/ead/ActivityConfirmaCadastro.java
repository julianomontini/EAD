package julianomontini.ead;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class ActivityConfirmaCadastro extends AppCompatActivity{

    ClassDadosAluno aluno;
    Random random = new Random();
    Integer codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTheme(ClassChangeTheme.getTheme(this));
        setContentView(R.layout.activity_confirma_cadastro);
        aluno = (ClassDadosAluno) getIntent().getSerializableExtra("DadosAluno");

        enviaEmail();
    }

    public void verificaCodigo(View view){
        String enviado = "";
        String codigoString = "";
        EditText textoCodigo = (EditText)findViewById(R.id.textCodigoConf);

        enviado = textoCodigo.getText().toString();
        codigoString = codigo.toString();

        if(enviado.equals(codigoString)){
            salvaBanco();
        }else{
            new AlertDialog.Builder(ActivityConfirmaCadastro.this)
                    .setTitle("Erro")
                    .setMessage("Código incorreto!")

                    .setNeutralButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }

    private void enviaEmail() {

        codigo = random.nextInt((999999 - 111111)+1)+111111;
        ClassSendEmail sendEmail = new ClassSendEmail();
        sendEmail.sendConf(this,aluno.getEmail(),codigo);
    }

    private void salvaBanco(){


        try{
            SQLiteDatabase myDatabase = this.openOrCreateDatabase("Schema",MODE_PRIVATE,null);
            //Insere na tabela os dados da tela de cadastro
            myDatabase.execSQL("INSERT INTO usuario(nome, email, senha, telefone, cpf) VALUES("
                    + "'" + aluno.getNome()  +"'" + ","
                    + "'" + aluno.getEmail() +"'" + ","
                    + "'" + aluno.getSenha() +"'" + ","
                    + "'" + aluno.getTel()   +"'" + ","
                    + "'" + aluno.getCpf()   +"'"
                    + ")");

            //Fecha a conexao com o banco de dados
            myDatabase.close();
        }catch (Exception e){
            new AlertDialog.Builder(ActivityConfirmaCadastro.this)
                    .setTitle("Erro")
                    .setMessage("Email em uso")

                    .setNeutralButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

        new AlertDialog.Builder(ActivityConfirmaCadastro.this)
                .setTitle("Sucesso")
                .setMessage("Cadastro efetuado com sucesso")

                .setNeutralButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = getBaseContext().getPackageManager()
                                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
