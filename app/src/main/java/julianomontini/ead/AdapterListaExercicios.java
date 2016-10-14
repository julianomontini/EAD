package julianomontini.ead;

import android.content.Context;
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

        return customView;
    }
}
