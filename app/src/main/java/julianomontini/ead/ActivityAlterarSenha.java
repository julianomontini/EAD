package julianomontini.ead;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActivityAlterarSenha extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTheme(ClassChangeTheme.getTheme(this));
        setContentView(R.layout.activity_alterar_senha);
    }
}
