package julianomontini.ead;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityMostrarCursos extends AppCompatActivity {


    int mIdUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTheme(ClassChangeTheme.getTheme(this));
        setContentView(R.layout.activity_cursos_disponiveis);

        mIdUsuario = (int)getIntent().getSerializableExtra("IdUsuario");

    }

    @Override
    protected void onResume() {
        super.onResume();

        criarLista();
    }

    private void criarLista() {

        final List<EncapsulaInfoCurso> informacoes = new ArrayList<>();

        try{

            SQLiteDatabase myDatabase = this.openOrCreateDatabase("Schema", MODE_PRIVATE, null);
            Cursor c = myDatabase.rawQuery("SELECT * FROM curso WHERE ID NOT IN(SELECT n_curso FROM usuario_curso WHERE n_usuario = " + mIdUsuario + ")",null);

            if(c.getCount() == 0){

                new AlertDialog.Builder(ActivityMostrarCursos.this)
                        .setTitle("Aviso")
                        .setMessage("Todos os exercicios já estão cadastrados")

                        .setNeutralButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }

            int indexNome,indexDesc,indexImg,indexId;

            indexNome = c.getColumnIndex("nome");
            indexDesc = c.getColumnIndex("descricao");
            indexImg = c.getColumnIndex("imagem");
            indexId = c.getColumnIndex("ID");

            c.moveToFirst();

            while(!c.isAfterLast()){
                String nome = c.getString(indexNome);
                String desc = c.getString(indexDesc);
                int img = c.getInt(indexImg);
                int id = c.getInt(indexId);
                informacoes.add(new EncapsulaInfoCurso(nome,desc,img,id));
                c.moveToNext();
            }
        }
        catch (Exception e){

            Log.i("ERROOOOOO", e.getMessage());

        }

        ListAdapter adapterBotoes = new AdapterBotoes(this, informacoes);
        ListView listBotoes = (ListView) findViewById(R.id.listBotoes);

        listBotoes.setAdapter(adapterBotoes);

        listBotoes.setOnItemClickListener(

                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        EncapsulaInfoCurso displayInfo = informacoes.get(position);

                        Intent i = new Intent(ActivityMostrarCursos.this, ActivityInformacoesCurso.class);
                        i.putExtra("id", displayInfo);
                        i.putExtra("IdUsuario",mIdUsuario);
                        startActivity(i);

                    }
                }

        );

    }


}
