package julianomontini.ead;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

public class ActivityExercicio extends AppCompatActivity {

    private EncapsulaDadosExercicio mInformacoes;
    private List<EncapsulaDadosExercicio> mLista;
    private int mPosicao;
    private int mIDUsuario;
    RadioButton mOpc1 , mOpc2 , mOpc3, mOpc4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicios);
        mLista = (List<EncapsulaDadosExercicio>)getIntent().getSerializableExtra("lista");
        mIDUsuario = (int)getIntent().getSerializableExtra("IDUsuario");
        mPosicao = (int) getIntent().getSerializableExtra("posicao");
        mInformacoes = mLista.get(mPosicao);
        montaExercicio();
    }

    private void montaExercicio() {

        TextView numeroQuestao = (TextView)findViewById(R.id.numero_exercicio);
        TextView questao = (TextView)findViewById(R.id.questao_exercicio);
        mOpc1 = (RadioButton)findViewById(R.id.questao_1);
        mOpc2 = (RadioButton)findViewById(R.id.questao_2);
        mOpc3 = (RadioButton)findViewById(R.id.questao_3);
        mOpc4 = (RadioButton)findViewById(R.id.questao_4);

        numeroQuestao.setText(mInformacoes.getNumeroQuestao());
        questao.setText(mInformacoes.getQuestao());
        mOpc1.setText(mInformacoes.getOpc1());
        mOpc2.setText(mInformacoes.getOpc2());
        mOpc3.setText(mInformacoes.getOpc3());
        mOpc4.setText(mInformacoes.getOpc4());
    }

    public void avancaExerc(View view){

        validaExerc();

        if(mPosicao < mLista.size()-1){

            mPosicao++;
            mudaExercicio(mPosicao);

        }else{

            new AlertDialog.Builder(ActivityExercicio.this)
                    .setTitle("Aviso")
                    .setMessage("Esse é o primeiro exercício")

                    .setNeutralButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }

    }

    public void voltaExerc(View view){

        validaExerc();

        if(mPosicao == 0){

            new AlertDialog.Builder(ActivityExercicio.this)
                    .setTitle("Aviso")
                    .setMessage("Esse é o primeiro exercício")

                    .setNeutralButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }
        else{

            mPosicao--;
            mudaExercicio(mPosicao);
        }


    }

    private void validaExerc(){

        int marcada;
        int status = 0;
        SQLiteDatabase myDatabase = this.openOrCreateDatabase("Schema", MODE_PRIVATE, null);

        if(mOpc1.isChecked()){
            marcada = 1;
        }else if(mOpc2.isChecked()){
            marcada = 2;
        }else if(mOpc3.isChecked()){
            marcada = 3;
        }else if(mOpc4.isChecked()){
            marcada = 4;
        }else{
            marcada = 5;
        }

        if(marcada == mInformacoes.getCerta()){

            Toast.makeText(ActivityExercicio.this,"Resposta Correta",Toast.LENGTH_LONG).show();
            status = 1;

        }else if(marcada != 5){

            Toast.makeText(ActivityExercicio.this,"Resposta Incorreta",Toast.LENGTH_LONG).show();
            status = 2;
        }

        myDatabase.execSQL("INSERT OR REPLACE INTO usuario_exerc VALUES(" +
                mIDUsuario +"," +mInformacoes.getIDCurso()+"," + mInformacoes.getId()+","+ status +")");

        Log.i("RESULTADOOOO","Usuario: " + mIDUsuario + " Curso: " + mInformacoes.getIDCurso() + " Numero ex: "+mInformacoes.getId() +" Status: " +status);

        myDatabase.close();


    }

    private void mudaExercicio(int posicao){

        Intent i = new Intent(ActivityExercicio.this,ActivityExercicio.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.putExtra("posicao",posicao);
        i.putExtra("lista", (Serializable) mLista);
        i.putExtra("IDUsuario",mIDUsuario);
        startActivity(i);
        finish();

    }
}