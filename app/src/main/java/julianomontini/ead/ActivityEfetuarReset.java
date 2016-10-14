package julianomontini.ead;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityEfetuarReset extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);
    }

    public void enviarReset(View view) {

        ClassDadosAluno classDadosAluno = new ClassDadosAluno();

        TextView email = (TextView) findViewById(R.id.campo_reset);

        try {

            classDadosAluno.setEmail(email.getText().toString());

        } catch (Exception e) {

            Toast.makeText(ActivityEfetuarReset.this, e.getMessage(), Toast.LENGTH_LONG).show();

        }

    }
}
