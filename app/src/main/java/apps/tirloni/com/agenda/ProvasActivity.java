package apps.tirloni.com.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import apps.tirloni.com.agenda.modelo.Prova;

public class ProvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas);

        List<String> topicosPort = Arrays.asList("Sujeito", "Objeto direto", "Objeto indireto");
        Prova provaPortugues = new Prova("Português", "25/05/2016", topicosPort);

        List<String> topicosMat = Arrays.asList("Equações de segundo grau", "Trigonometria");
        Prova provaMatematica = new Prova("Matematica", "27/05/2016", topicosMat);

        List<Prova> provas = Arrays.asList(provaPortugues,provaMatematica);

        ArrayAdapter<Prova> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, provas);

        ListView lista = (ListView) findViewById(R.id.provas_lista);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Prova prova = (Prova) parent.getItemAtPosition(position);
                Toast.makeText(ProvasActivity.this, "Clicou na prova de " + prova, Toast.LENGTH_SHORT ).show();
                Intent vaiParaDetalhes = new Intent(ProvasActivity.this, DetalhesProvaActivity.class);
                startActivity(vaiParaDetalhes);
            }
        });

    }


}
