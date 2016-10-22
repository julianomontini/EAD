package julianomontini.ead;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;


public class ClassChangeTheme {

    public static int getTheme(Context context){

        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
        int theme =  preference.getInt("Theme",0);


        switch (theme){
            case 1:
                return R.style.AppTheme;
            case 2:
                return R.style.AppThemeGreen;
            case 3:
                return R.style.AppThemeGrey;
            default:
                return R.style.AppTheme;
        }

    }

    public static int getThemeButton(Context context){

        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
        int theme =  preference.getInt("Theme",0);


        switch (theme){
            case 1:
                return R.layout.adapter_botoes;
            case 2:
                return R.layout.adapter_botoes_green;
            case 3:
                return R.layout.adapter_botoes_grey;
            default:
                return R.layout.adapter_botoes;
        }

    }

    public static int getThemeDesempenho(Context context){

        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
        int theme =  preference.getInt("Theme",0);


        switch (theme){
            case 1:
                return R.layout.adapter_desempenho;
            case 2:
                return R.layout.adapter_desempenho_green;
            case 3:
                return R.layout.adapter_desempenho_grey;
            default:
                return R.layout.adapter_desempenho;
        }

    }

}
