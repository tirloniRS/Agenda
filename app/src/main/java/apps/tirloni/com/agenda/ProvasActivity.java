package apps.tirloni.com.agenda;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import apps.tirloni.com.agenda.modelo.Prova;

public class ProvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction tx = fragmentManager.beginTransaction();

        tx.replace(R.id.frame_principal,new ListaProvasFragment());
        if (estaNoMetodoPaisagem()){
            tx.replace(R.id.frame_secundario, new DetalhesProvaFragment());
        }
        tx.commit();


    }

    private boolean estaNoMetodoPaisagem() {
        return getResources().getBoolean(R.bool.modoPaisagem);
    }


    public void SelecionarProva(Prova prova) {
        FragmentManager manager = getSupportFragmentManager();
        if (!estaNoMetodoPaisagem()){
            FragmentTransaction tx = manager.beginTransaction();

            DetalhesProvaFragment detalhesFragment = new DetalhesProvaFragment();
            //passando paramentros com fragments
            Bundle parametros = new Bundle();
            parametros.putSerializable("prova", prova);
            detalhesFragment.setArguments(parametros);

            tx.replace(R.id.frame_principal,detalhesFragment);
            //guardar o estado da activity
            tx.addToBackStack(null);
            tx.commit();
        }else{
            DetalhesProvaFragment detalhesFragment =
                    (DetalhesProvaFragment) manager.findFragmentById(R.id.frame_secundario);
            detalhesFragment.populaCamposCom(prova);


        }

    }
}
