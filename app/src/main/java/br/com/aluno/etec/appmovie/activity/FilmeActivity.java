package br.com.aluno.etec.appmovie.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.aluno.etec.appmovie.R;
import br.com.aluno.etec.appmovie.fragments.FilmesFragment;

public class FilmeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filme);

        setUpToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Título
        getSupportActionBar().setTitle(getString(getIntent().getIntExtra("tipo", 0)));

        int tipo = R.string.acao;

        // Adiciona o fragment com o mesmo Bundle (args) da intent
        if (savedInstanceState == null) {
            FilmesFragment frag = new FilmesFragment();
            frag.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.container, frag).commit();
        }
    }
}
