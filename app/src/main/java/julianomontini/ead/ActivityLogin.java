package julianomontini.ead;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityLogin extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        super.setTheme(R.style.AppThemeGreen);
    }

    @Override
    protected void onResume() {
        super.onResume();

        inicializarDados();
    }

    public void iniciarCadastro(View view) {

        Intent chamarCadastro = new Intent(this, ActivityEfetuarCadastro.class);
        startActivity(chamarCadastro);
    }

    public void iniciarReset(View view) {

        Intent chamarReset = new Intent(this, ActivityEfetuarReset.class);
        startActivity(chamarReset);

    }

    public void efetuarLogin(View view) {

        TextView login = (TextView)findViewById(R.id.email_login);
        TextView senha = (TextView)findViewById(R.id.senha_login);

        SQLiteDatabase myDatabase = this.openOrCreateDatabase("Schema",MODE_PRIVATE,null);

        try{

            if (login.getText().toString().length()> 0 && senha.getText().toString().length()> 0){

                Cursor c = myDatabase.rawQuery("SELECT ID,email, senha FROM usuario WHERE "+
                        "email = " + "'" + login.getText().toString() + "'" + " AND " +
                        "senha = " + "'" + senha.getText().toString() + "'",null);

                int indexId = c.getColumnIndex("ID");
                int indexNome = c.getColumnIndex("email");
                int indexSenha = c.getColumnIndex("senha");

                c.moveToFirst();

                if(c.getCount() > 0){

                    int idUsuario = c.getInt(indexId);

                    Intent entrar = new Intent(this, ActivityVisaoGeral.class);
                    entrar.putExtra("IdUsuario",idUsuario);
                    startActivity(entrar);

                }else{

                    Toast.makeText(ActivityLogin.this,"Usuario ou senha incorretos",Toast.LENGTH_LONG).show();

                }

            }
            else{

                Toast.makeText(ActivityLogin.this,"Favor preencher login e senha",Toast.LENGTH_LONG).show();

            }

        }catch (Exception e){

            Toast.makeText(ActivityLogin.this,"Usuario ou senha incorretos",Toast.LENGTH_LONG).show();

        }



    }

    public void execSQL(View view){

        try {


            SQLiteDatabase myDatabase = this.openOrCreateDatabase("Schema", MODE_PRIVATE, null);

            //myDatabase.execSQL("DROP TABLE usuario");
            myDatabase.execSQL("DROP TABLE usuario_curso");
            myDatabase.execSQL("DROP TABLE usuario_exerc");


            myDatabase.close();

            inicializarDados();

        }catch (Exception e){

            Log.i("ERROOOOOR",e.getMessage());

        }

    }

    private void inicializarDados() {

        ViewGroup scrollView = (ViewGroup) findViewById(R.id.ScrollLogin);
        scrollView.setBackground(ResourcesCompat.getDrawable(getResources(),R.color.md_teal_700,null));
        ClassChangeStyle.recursiveLoopChildren(scrollView,ActivityLogin.this,2);
        inicializaCadastro();
        inicializaCursos();
        inicializaExercicios();
        inicializaAlunoCursos();
        inicializaCadastroExercicio();

    }

    private void inicializaCadastroExercicio() {

        try {


            SQLiteDatabase myDatabase = this.openOrCreateDatabase("Schema", MODE_PRIVATE, null);

            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS usuario_exerc( " +
                    "n_aluno INT, " +
                    "n_curso INT, " +
                    "n_exerc INT," +
                    "status INT," +
                    "respondida INT,"+
                    "UNIQUE(n_aluno,n_curso,n_exerc))");

            myDatabase.close();



        }catch (Exception e){

            Log.i("ERROOOOOR",e.getMessage());

        }

    }

    private void inicializaAlunoCursos() {

        try {


            SQLiteDatabase myDatabase = this.openOrCreateDatabase("Schema", MODE_PRIVATE, null);

            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS usuario_curso( " +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "n_usuario INT, " +
                    "n_curso INT)");

            myDatabase.close();



        }catch (Exception e){

            Log.i("ERROOOOOR",e.getMessage());

        }

    }

    private void inicializaExercicios() {

        try{

            SQLiteDatabase myDatabase = this.openOrCreateDatabase("Schema", MODE_PRIVATE, null);

            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS exercicio(" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "n_curso INT," +
                    "n_exerc INT," +
                    "questao INT," +
                    "opc1 TEXT," +
                    "opc2 TEXT," +
                    "opc3 TEXT," +
                    "opc4 TEXT," +
                    "certa INT," +
                    "UNIQUE(n_curso,n_exerc))");

            myDatabase.execSQL("INSERT OR REPLACE INTO exercicio(n_curso, n_exerc, questao, opc1, opc2, opc3, opc4, certa) VALUES(" +
                    "1, 1, "
                    +"'" + getString(R.string.hist_quest1) + "',"
                    +"'" + getString(R.string.hist_opc1) + "',"
                    +"'" + getString(R.string.hist_opc2) + "',"
                    +"'" + getString(R.string.hist_opc3) + "',"
                    +"'" + getString(R.string.hist_opc4) + "',"
                    + "4" + ")");

            myDatabase.execSQL("INSERT OR REPLACE INTO exercicio(n_curso, n_exerc, questao, opc1, opc2, opc3, opc4, certa) VALUES(" +
                    "1, 2, "
                    +"'" + getString(R.string.hist_quest2) + "',"
                    +"'" + getString(R.string.hist_opc_2_1) + "',"
                    +"'" + getString(R.string.hist_opc_2_2) + "',"
                    +"'" + getString(R.string.hist_opc_2_3) + "',"
                    +"'" + getString(R.string.hist_opc_2_4) + "',"
                    + "3" + ")");

            myDatabase.execSQL("INSERT OR REPLACE INTO exercicio(n_curso, n_exerc, questao, opc1, opc2, opc3, opc4, certa) VALUES(" +
                    "3, 1, "
                    +"'" + "Quanto e 1 + 1?" + "',"
                    +"'" + "1" + "',"
                    +"'" + "2" + "',"
                    +"'" + "3" + "',"
                    +"'" + "4" + "',"
                    + "2" + ")");

            myDatabase.execSQL("INSERT OR REPLACE INTO exercicio(n_curso, n_exerc, questao, opc1, opc2, opc3, opc4, certa) VALUES(" +
                    "3, 2, "
                    +"'" + "Quanto e 1 + 2?" + "',"
                    +"'" + "1" + "',"
                    +"'" + "2" + "',"
                    +"'" + "3" + "',"
                    +"'" + "5" + "',"
                    + "3" + ")");

            myDatabase.execSQL("INSERT OR REPLACE INTO exercicio(n_curso, n_exerc, questao, opc1, opc2, opc3, opc4, certa) VALUES(" +
                    "2, 1, "
                    +"'" + "Aonde o Brasil fica?" + "',"
                    +"'" + "America Do Sul" + "',"
                    +"'" + "Asia" + "',"
                    +"'" + "Africa" + "',"
                    +"'" + "America do Norte" + "',"
                    + "1" + ")");

            myDatabase.execSQL("INSERT OR REPLACE INTO exercicio(n_curso, n_exerc, questao, opc1, opc2, opc3, opc4, certa) VALUES(" +
                    "2, 2, "
                    +"'" + "A capital mato-grossense é a cidade de:?" + "',"
                    +"'" + "Goiania" + "',"
                    +"'" + "Porto Velho" + "',"
                    +"'" + "Belem" + "',"
                    +"'" + "Cuiaba" + "',"
                    + "4" + ")");

            myDatabase.execSQL("INSERT OR REPLACE INTO exercicio(n_curso, n_exerc, questao, opc1, opc2, opc3, opc4, certa) VALUES(" +
                    "4, 1, "
                    +"'" + "_____ you fine?" + "',"
                    +"'" + "Are" + "',"
                    +"'" + "Is" + "',"
                    +"'" + "Am" + "',"
                    +"'" + "Who" + "',"
                    + "1" + ")");

            myDatabase.execSQL("INSERT OR REPLACE INTO exercicio(n_curso, n_exerc, questao, opc1, opc2, opc3, opc4, certa) VALUES(" +
                    "4, 2, "
                    +"'" + "_____ I late?" + "',"
                    +"'" + "Are" + "',"
                    +"'" + "Is" + "',"
                    +"'" + "Am" + "',"
                    +"'" + "Who" + "',"
                    + "3" + ")");

            myDatabase.execSQL("INSERT OR REPLACE INTO exercicio(n_curso, n_exerc, questao, opc1, opc2, opc3, opc4, certa) VALUES(" +
                    "5, 1, "
                    +"'" + "Como se diz tapete em tapete em espanhol?" + "',"
                    +"'" + "Ventana" + "',"
                    +"'" + "Cucaracha" + "',"
                    +"'" + "Alfombra" + "',"
                    +"'" + "Pestana" + "',"
                    + "3" + ")");

            myDatabase.execSQL("INSERT OR REPLACE INTO exercicio(n_curso, n_exerc, questao, opc1, opc2, opc3, opc4, certa) VALUES(" +
                    "5, 2, "
                    +"'" + "Qual das palavras abaixo não é um meio de transporte?" + "',"
                    +"'" + "Camion" + "',"
                    +"'" + "Computadora" + "',"
                    +"'" + "Tren" + "',"
                    +"'" + "Coche" + "',"
                    + "3" + ")");


            myDatabase.close();

        }catch (Exception e){

            Log.i("ERROOOOOOR",e.getMessage());

        }



    }

    private void inicializaCadastro() {

        try {

            SQLiteDatabase myDatabase = this.openOrCreateDatabase("Schema",MODE_PRIVATE,null);

            //Cria tabela usuario se ela já nao existir, email é chave primaria
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS usuario(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nome VARCHAR, " +
                    "email VARCHAR UNIQUE, " +
                    "senha VARCHAR, " +
                    "telefone VARCHAR, " +
                    "cpf VARCHAR)");

            myDatabase.close();

        }
        catch (Exception e){

            Log.i("ERROOOOOR",e.getMessage());

        }

    }

    private void inicializaCursos() {

        try {

            SQLiteDatabase myDatabase = this.openOrCreateDatabase("Schema", MODE_PRIVATE, null);

            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS curso(" +
                    "ID INTEGER PRIMARY KEY," +
                    "nome VARCHAR UNIQUE," +
                    "descricao TEXT," +
                    "imagem INT)");

            myDatabase.execSQL("INSERT OR REPLACE INTO curso(ID,nome,descricao,imagem) VALUES("
                    + "1,"
                    + "'" + getString(R.string.historia) + "',"
                    + "'" + getString(R.string.historia_desc) + "',"
                    + R.drawable.historia + ")");

            myDatabase.execSQL("INSERT OR REPLACE INTO curso(ID,nome,descricao,imagem) VALUES("
                    + "2,"
                    + "'" + getString(R.string.geografica) + "',"
                    + "'" + getString(R.string.geografia_desc) + "',"
                    + R.drawable.geografia1 + ")");

            myDatabase.execSQL("INSERT OR REPLACE INTO curso(ID,nome,descricao,imagem) VALUES("
                    + "3,"
                    + "'" + getString(R.string.matematica) + "',"
                    + "'" + getString(R.string.matematica_dec) + "',"
                    + R.drawable.matematica + ")");

            myDatabase.execSQL("INSERT OR REPLACE INTO curso(ID,nome,descricao,imagem) VALUES("
                    + "4,"
                    + "'" + getString(R.string.ingles) + "',"
                    + "'" + getString(R.string.ingles_desc) + "',"
                    + R.drawable.ingles + ")");

            myDatabase.execSQL("INSERT OR REPLACE INTO curso(ID,nome,descricao,imagem) VALUES("
                    + "5,"
                    + "'" + getString(R.string.espanhol) + "',"
                    + "'" + getString(R.string.espanhol_desc) + "',"
                    + R.drawable.espanhol + ")");

            myDatabase.close();


        }catch (Exception e){

            Log.i("ERROOOOOR",e.getMessage());

        }

    }

}
