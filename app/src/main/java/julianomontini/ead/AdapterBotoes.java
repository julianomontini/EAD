package julianomontini.ead;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterBotoes extends ArrayAdapter<EncapsulaInfoCurso> {


    public AdapterBotoes(Context context, List<EncapsulaInfoCurso> informacoes) {

        super(context, R.layout.adapter_botoes, informacoes.toArray(new EncapsulaInfoCurso[informacoes.size()]));

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());

        View customView = inflater.inflate(R.layout.adapter_botoes, parent, false);
        EncapsulaInfoCurso infos = getItem(position);

        ImageView imagemBotao = (ImageView) customView.findViewById(R.id.imagemBotao);
        TextView textoBotao = (TextView) customView.findViewById(R.id.textoBotao);

        imagemBotao.setImageResource(infos.getSrcImagem());
        textoBotao.setText(infos.getNomeCurso());

        return customView;

    }
}
