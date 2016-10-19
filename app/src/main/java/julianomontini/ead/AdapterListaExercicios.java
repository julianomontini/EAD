package julianomontini.ead;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterListaExercicios extends ArrayAdapter<EncapsulaDadosExercicio> {


    public AdapterListaExercicios(Context context, List<EncapsulaDadosExercicio> exercicios) {
        super(context, R.layout.adapter_lista_exercicios, exercicios.toArray(new EncapsulaDadosExercicio[exercicios.size()]));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());

        View customView = inflater.inflate(R.layout.adapter_lista_exercicios, parent, false);

        EncapsulaDadosExercicio exercicio = getItem(position);


        TextView numeroEx = (TextView)customView.findViewById(R.id.numero_lista_exercicio);
        numeroEx.setText("Exercício " + exercicio.getNumeroQuestao());

        try{
            SQLiteDatabase myDatabase = getContext().openOrCreateDatabase("Schema", Context.MODE_PRIVATE, null);

            Cursor c = myDatabase.rawQuery("SELECT status FROM usuario_exerc WHERE n_aluno = "+exercicio.getIDUsuario() + " AND n_curso = "+exercicio.getIDCurso() + " AND n_exerc = "+ exercicio.getId(),null);

            int indexStatus, status;
            indexStatus = c.getColumnIndex("status");
            if(c.getCount() != 0){

                c.moveToFirst();
                status = c.getInt(indexStatus);
                switch (status){
                    case 1:
                        numeroEx.setTextColor(ContextCompat.getColor(getContext(),R.color.green));
                        break;
                    case 2:
                        numeroEx.setTextColor(ContextCompat.getColor(getContext(),R.color.red));
                        break;
                }

            }
        }catch (Exception e){

            Log.i("ERROOOOOO",e.getMessage());

        }

        return customView;
    }
}
