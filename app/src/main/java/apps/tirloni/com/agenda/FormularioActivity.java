package apps.tirloni.com.agenda;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

import apps.tirloni.com.agenda.dao.AlunoDao;
import apps.tirloni.com.agenda.modelo.Aluno;

public class FormularioActivity extends AppCompatActivity {

    private static final int CODIGO_CAMERA = 567;
    private FormularioHelper helper;
    private String caminhoFoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);

        Intent intent = getIntent();
        Aluno aluno = (Aluno) getIntent().getSerializableExtra("aluno");
        //sempre devemos verificar se o conteúdo é nulo
        if (aluno!=null){
            helper.preencheFormulario(aluno);
        }

        //instânciando o botão foto
        Button botaoFoto = (Button) findViewById(R.id.formulario_botao_foto);
        botaoFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //criando o caminho concatenando com o timestamp
                caminhoFoto = getExternalFilesDir(null)+ "/" + System.currentTimeMillis() + ".jpg";
                //cria o arquivo
                File arquivoFoto = new File(caminhoFoto);
                //passando por parametro o caminho da foto
                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(arquivoFoto));
                startActivityForResult(intentCamera,CODIGO_CAMERA);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK){
            if (requestCode == CODIGO_CAMERA){
                helper.carregaImagem(caminhoFoto);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario_ok:

                Aluno aluno = helper.pegaAluno();

                AlunoDao dao = new AlunoDao(this);
                if (aluno.getId() != null){
                    dao.altera(aluno);
                }else {
                    dao.insere(aluno);
                }
                dao.close();

                Toast.makeText(FormularioActivity.this,"Aluno " + aluno.getNome() + " salvo!", Toast.LENGTH_SHORT).show();

                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
