package apps.tirloni.com.agenda;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

import apps.tirloni.com.agenda.converter.AlunoConverter;
import apps.tirloni.com.agenda.dao.AlunoDao;
import apps.tirloni.com.agenda.modelo.Aluno;

/**
 * Created by alexsandro-rs on 20/02/2017.
 */

public class EnviaAlunosTask extends AsyncTask<Void, Void, String>{
    private Context context;
    private ProgressDialog dialog;

    public EnviaAlunosTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(context, "Aguarde", "Enviando alunos...", true, true);
    }

    @Override
    protected String doInBackground(Void... params) {
        //buscar alunos
        AlunoDao dao = new AlunoDao(context);
        List<Aluno> alunos = dao.buscaAlunos();
        dao.close();
        //converter para json
        AlunoConverter conversor =new AlunoConverter();
        String json = conversor.converteParaJSON(alunos);
        // fazer requisição
        WebClient client = new WebClient();
        //pegar a resposta
        String resposta = client.post(json);
        return resposta;
    }
    //é executato depois do nosso background. é executado na threath principal
    @Override
    protected void onPostExecute(String resposta) {
        dialog.dismiss();
        Toast.makeText(context,resposta, Toast.LENGTH_LONG).show();
    }

}
