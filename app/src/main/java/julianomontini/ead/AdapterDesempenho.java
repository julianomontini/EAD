package julianomontini.ead;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterDesempenho extends ArrayAdapter<EncapsulaDesempenho> {


    public AdapterDesempenho(Context context, List<EncapsulaDesempenho> infos) {
        super(context, R.layout.adapter_desempenho, infos.toArray(new EncapsulaDesempenho[infos.size()]));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());

        View customView = inflater.inflate(R.layout.adapter_desempenho, parent, false);
        EncapsulaDesempenho infos = getItem(position);

        TextView nomeCurso = (TextView) customView.findViewById(R.id.desempenho_materias);
        TextView acertos = (TextView) customView.findViewById(R.id.desempenho_acertos);
        TextView erros = (TextView) customView.findViewById(R.id.desempenho_erros);
        TextView respondidas = (TextView) customView.findViewById(R.id.desempenho_respondidos);
        TextView total = (TextView) customView.findViewById(R.id.desempenho_total);

        nomeCurso.setText(infos.getNomeCurso());
        acertos.setText(infos.getAcertos());
        erros.setText(infos.getErros());
        respondidas.setText(infos.getRespondidos());
        total.setText(infos.getTotalQuestoes());

        return customView;

    }
}
