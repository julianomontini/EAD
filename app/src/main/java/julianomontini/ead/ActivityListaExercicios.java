package julianomontini.ead;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.Serializable;
import java.util.List;

public class ActivityListaExercicios extends AppCompatActivity {

    private List<EncapsulaDadosExercicio> mInformacoes;
    private int mIDUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_exercicios);

        mIDUsuario = (int) getIntent().getSerializableExtra("IDusuario");

        mInformacoes = (List<EncapsulaDadosExercicio>) getIntent().getSerializableExtra("lista_exercicios");
    }

    @Override
    protected void onResume() {
        super.onResume();

        criarLista();
    }

    private void criarLista() {

        ListAdapter adapterExercicios = new AdapterListaExercicios(this, mInformacoes);
        ListView listaExercicios = (ListView) findViewById(R.id.lista_exercicios);

        listaExercicios.setAdapter(adapterExercicios);

        listaExercicios.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent i = new Intent(ActivityListaExercicios.this,ActivityExercicio.class);
                        i.putExtra("posicao",position);
                        i.putExtra("lista", (Serializable) mInformacoes);
                        i.putExtra("IDUsuario",mIDUsuario);
                        startActivity(i);
                    }
                }
        );

    }
}
