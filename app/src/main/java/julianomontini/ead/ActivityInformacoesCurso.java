package julianomontini.ead;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityInformacoesCurso extends AppCompatActivity {

    private EncapsulaInfoCurso mInformacoes;

    int mIdUsuario;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_curso);
        mInformacoes = (EncapsulaInfoCurso) getIntent().getSerializableExtra("id");
        mIdUsuario = (int)getIntent().getSerializableExtra("IdUsuario");
        montaTelas();
    }

    private void montaTelas() {

        TextView nomeCurso = (TextView) findViewById(R.id.cursoDescricao);
        TextView descCurso = (TextView) findViewById(R.id.textoDescricao);
        ImageView imgCurso = (ImageView) findViewById(R.id.imagemDescricao);

        nomeCurso.setText("Sobre o curso\n" + mInformacoes.getNomeCurso());
        descCurso.setText(mInformacoes.getDescCurso());
        imgCurso.setImageResource(mInformacoes.getSrcImagem());
    }

    public void cadastraCurso(View view){

        try{

            SQLiteDatabase myDatabase = this.openOrCreateDatabase("Schema", MODE_PRIVATE, null);

            myDatabase.execSQL("INSERT OR REPLACE INTO usuario_curso(n_usuario,n_curso) VALUES(" + mIdUsuario +"," + mInformacoes.getId() +")" );

            myDatabase.close();

            Toast.makeText(getApplicationContext(),"Curso cadastrado",Toast.LENGTH_LONG).show();

            finish();
        }catch (Exception e){

            Log.i("ERROOOOOR",e.getMessage());

        }



    }
}
