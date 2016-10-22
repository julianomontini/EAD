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


    Context mContext;

    public AdapterBotoes(Context context, List<EncapsulaInfoCurso> informacoes) {

        super(context, ClassChangeTheme.getThemeButton(context), informacoes.toArray(new EncapsulaInfoCurso[informacoes.size()]));
        setmContext(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());

        View customView = inflater.inflate(ClassChangeTheme.getThemeButton(mContext), parent, false);
        EncapsulaInfoCurso infos = getItem(position);

        ImageView imagemBotao = (ImageView) customView.findViewById(R.id.imagemBotao);
        TextView textoBotao = (TextView) customView.findViewById(R.id.textoBotao);

        imagemBotao.setImageResource(infos.getSrcImagem());
        textoBotao.setText(infos.getNomeCurso());

        return customView;

    }

    public void setmContext(Context context){
        mContext = context;
    }

    public Context getmContext(){
        return mContext;
    }
}
