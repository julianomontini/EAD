package julianomontini.ead;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ActivityVisaoGeral extends AppCompatActivity {

    int mIdUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTheme(ClassChangeTheme.getTheme(this));
        setContentView(R.layout.activity_visao_geral);

        mIdUsuario = (int)getIntent().getSerializableExtra("IdUsuario");
    }

    public void mostrarCursos(View view) {

        Intent mostrarCursos = new Intent(this, ActivityMostrarCursos.class);
        mostrarCursos.putExtra("IdUsuario",mIdUsuario);
        startActivity(mostrarCursos);

    }

    public void customizarLayout(View view) {

        Intent customizarLayout = new Intent(this, ActivityCustomizarLayout.class);
        customizarLayout.putExtra("IdUsuario",mIdUsuario);
        startActivity(customizarLayout);

    }

    public void desempenho(View view) {

        Intent desempenho = new Intent(this, ActivityDesempenho.class);
        desempenho.putExtra("IDUsuario",mIdUsuario);
        startActivity(desempenho);

    }

    public void exercicios(View view) {

        Intent exercicios = new Intent(this, ActivityCursosCadastrados.class);
        exercicios.putExtra("IdUsuario",mIdUsuario);
        startActivity(exercicios);

    }

}
