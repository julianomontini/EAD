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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ActivityCursosCadastrados extends AppCompatActivity {

    int mIdUsuario ;
    int mIdCurso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrados);

        mIdUsuario = (int)getIntent().getSerializableExtra("IdUsuario");
    }

    @Override
    protected void onResume() {
        super.onResume();

        criarLista();

    }

    public void mostrarEditar(View view){

        Toast.makeText(ActivityCursosCadastrados.this,"Aperte e segure no curso para remover",Toast.LENGTH_LONG).show();

    }

    private void criarLista() {

        final List<EncapsulaInfoCurso> informacoes = new ArrayList<>();
        final List<EncapsulaDadosExercicio> exercicios = new ArrayList<>();

        final List<List<EncapsulaDadosExercicio>> lista_exercicios = new ArrayList<>();


        try{

            SQLiteDatabase myDatabase = this.openOrCreateDatabase("Schema", MODE_PRIVATE, null);

            Cursor c = myDatabase.rawQuery("SELECT * FROM curso WHERE ID IN(SELECT n_curso FROM usuario_curso WHERE n_usuario = " + mIdUsuario + ")",null);
            Cursor d;

            if(c.getCount() == 0){

                new AlertDialog.Builder(ActivityCursosCadastrados.this)
                        .setTitle("Aviso")
                        .setMessage("Não há cursos cadastrados")

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

            int i = 1;

            c.moveToFirst();

            while(!c.isAfterLast()) {

                String nome = c.getString(indexNome);
                String desc = c.getString(indexDesc);
                int img = c.getInt(indexImg);
                int id = c.getInt(indexId);
                mIdCurso = id;

                informacoes.add(new EncapsulaInfoCurso(nome, desc, img, id));

                d = myDatabase.rawQuery("SELECT * FROM exercicio WHERE n_curso = " + id , null);

                int indexIdEx = d.getColumnIndex("n_exerc");
                int indexQuestao = d.getColumnIndex("questao");
                int indexOpc1 = d.getColumnIndex("opc1");
                int indexOpc2 = d.getColumnIndex("opc2");
                int indexOpc3 = d.getColumnIndex("opc3");
                int indexOpc4 = d.getColumnIndex("opc4");
                int indexCerta = d.getColumnIndex("certa");

                d.moveToFirst();

                while(!d.isAfterLast()){

                    int idEx = d.getInt(indexIdEx);
                    String questao = d.getString(indexQuestao);
                    String opc1 = d.getString(indexOpc1);
                    String opc2 = d.getString(indexOpc2);
                    String opc3 = d.getString(indexOpc3);
                    String opc4 = d.getString(indexOpc4);
                    int certa = d.getInt(indexCerta);

                    exercicios.add(new EncapsulaDadosExercicio(i,questao,opc1,opc2,opc3,opc4,idEx,certa,mIdCurso));

                    d.moveToNext();
                    i=i+1;
                }

                d.close();

                i =  1;

                lista_exercicios.add(new ArrayList<EncapsulaDadosExercicio>(exercicios));

                exercicios.clear();

                c.moveToNext();

                /*
E

                */
            }

            c.close();

            ListAdapter adapterBotoes = new AdapterBotoes(this, informacoes);
            ListView listBotoes = (ListView) findViewById(R.id.lista_cadastrados);

            listBotoes.setAdapter(adapterBotoes);

            listBotoes.setOnItemClickListener(

                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            Intent i = new Intent(ActivityCursosCadastrados.this, ActivityListaExercicios.class);

                            List<EncapsulaDadosExercicio> dadosExercicio = lista_exercicios.get(position);

                            i.putExtra("lista_exercicios", (Serializable)dadosExercicio);
                            i.putExtra("IDusuario",mIdUsuario);
                            i.putExtra("IDCurso",mIdCurso);
                            startActivity(i);

                        }
                    }
            );

            listBotoes.setLongClickable(true);

            listBotoes.setOnItemLongClickListener(
                    new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                            List<EncapsulaDadosExercicio> dadosExercicio = lista_exercicios.get(position);

                            EncapsulaDadosExercicio exercicio = dadosExercicio.get(0);

                            try {


                                SQLiteDatabase myDatabase = getApplicationContext().openOrCreateDatabase("Schema", MODE_PRIVATE, null);


                                myDatabase.execSQL("DELETE FROM usuario_curso WHERE n_curso = " + exercicio.getIDCurso() + " AND n_usuario = " + mIdUsuario);


                                new AlertDialog.Builder(ActivityCursosCadastrados.this)
                                        .setTitle("Aviso")
                                        .setMessage("Curso Desmatriculado")

                                        .setNeutralButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                recreate();
                                            }
                                        })
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .show();

                            }catch (Exception e){

                                Log.i("EROOOOOOOR",e.getMessage());

                            }
                            return true;

                        }
                    }
            );
        }
        catch (Exception e){

            Log.i("ERROOOOOO", e.getMessage());

        }

    }
}
