package julianomontini.ead;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ActivityDesempenho extends AppCompatActivity {

    int mIDUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTheme(ClassChangeTheme.getTheme(this));
        setContentView(R.layout.activity_desempenho);
        mIDUsuario = (int) getIntent().getSerializableExtra("IDUsuario");
        criarInfos();
    }

    private void criarInfos() {

        final List<EncapsulaDesempenho> infos = new ArrayList<>();


        try {

            SQLiteDatabase myDatabase = this.openOrCreateDatabase("Schema", MODE_PRIVATE, null);

            Cursor c = myDatabase.rawQuery("SELECT ID, nome FROM curso WHERE ID IN(SELECT n_curso FROM usuario_curso WHERE n_usuario = "+mIDUsuario+")",null);

            int indexId = c.getColumnIndex("ID");
            int indexNome = c.getColumnIndex("nome");

            c.moveToFirst();

            while(!c.isAfterLast()){

                String nomeCurso = c.getString(indexNome);
                int idCurso = c.getInt(indexId);

                Cursor d = myDatabase.rawQuery("SELECT * FROM exercicio WHERE n_curso ="+idCurso,null);

                int countEx = d.getCount();

                if(d.getCount() == 0){

                    throw new ClassExceptions("Sem exercicios para o curso" + idCurso);

                }

                d = myDatabase.rawQuery("SELECT * FROM usuario_exerc WHERE n_aluno = "+mIDUsuario+" AND n_curso = " + idCurso + " AND status = "+ 1,null);

                int countCerta = d.getCount();

                d = myDatabase.rawQuery("SELECT * FROM usuario_exerc WHERE n_aluno = "+mIDUsuario+" AND n_curso = " + idCurso + " AND status = "+ 2,null);

                int countErrada = d.getCount();

                infos.add(new EncapsulaDesempenho(nomeCurso,countCerta,countErrada,countCerta+countErrada,countEx));

                c.moveToNext();

            }

            Log.i("RESULTADOOO",String.valueOf(c.getCount()));

            myDatabase.close();

        }catch (Exception e){

            Log.i("ERROOOOO",e.getMessage());

        }


        //infos.add(new EncapsulaDesempenho("História", 25, 7, 32, 100));

        ListAdapter adapterDesempenho = new AdapterDesempenho(this, infos);
        ListView listBotoes = (ListView) findViewById(R.id.list_desempenho);

        listBotoes.setAdapter(adapterDesempenho);

    }
}
