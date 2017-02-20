package apps.tirloni.com.agenda.converter;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

import apps.tirloni.com.agenda.modelo.Aluno;

/**
 * Created by alexsandro-rs on 20/02/2017.
 */

public class AlunoConverter {
    public String converteParaJSON(List<Aluno> alunos) {
        JSONStringer js = new JSONStringer();

        //objeto.chave(valor).lista
        try {
            js.object().key("list").array().object().key("aluno").array();
            for (Aluno aluno : alunos){
                js.object();
                js.key("nome").value(aluno.getNome());
                js.key("nota").value(aluno.getNota());
                js.endObject();
            }
            js.endArray().endObject().endObject();

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return js.toString();
    }
}
