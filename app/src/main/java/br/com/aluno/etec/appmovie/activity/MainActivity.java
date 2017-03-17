package br.com.aluno.etec.appmovie.activity;

import android.os.Bundle;

import br.com.aluno.etec.appmovie.R;
import br.com.aluno.etec.appmovie.fragments.FilmesFragment;
import br.com.aluno.etec.appmovie.utils.Dialog;
import livroandroid.lib.utils.AndroidUtils;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ativa o Toolbar
        setUpToolbar();

        // Inicializa o NavigationDrawer
        setUpNavDrawer();

        // Bundle que seta o tipo de filme
        Bundle args = new Bundle();
        args.putInt("tipo", R.string.acao);


        if (savedInstanceState == null) {
            if (AndroidUtils.isNetworkAvailable(this)) {
                // Inicializando o fragment
                FilmesFragment fragAcao = new FilmesFragment();
                FilmesFragment.newIntance(R.string.acao);
                fragAcao.setArguments(args);
                getSupportFragmentManager().beginTransaction().add(R.id.container, fragAcao).commit();
            } else {
                Dialog.show(this, "Sem Conexão", "Tente se conectar a internet");
            }
        }

    }
}
