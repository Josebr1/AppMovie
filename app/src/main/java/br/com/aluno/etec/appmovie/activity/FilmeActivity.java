package br.com.aluno.etec.appmovie.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import br.com.aluno.etec.appmovie.R;
import br.com.aluno.etec.appmovie.domain.Filme;
import br.com.aluno.etec.appmovie.fragments.FilmeFragment;
import br.com.aluno.etec.appmovie.fragments.FilmesFragment;

public class FilmeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filme);

        setUpToolbar();

        Filme filme = Parcels.unwrap(getIntent().getParcelableExtra("filme"));

        getSupportActionBar().setTitle(filme.nome);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Imagem de header na action bar
        ImageView appBarImg = (ImageView) findViewById(R.id.appBarImg);
        Picasso.with(getContext()).load(filme.urlFoto).into(appBarImg);

        if(savedInstanceState == null){
            FilmeFragment fragment = new FilmeFragment();
            fragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.FilmeFragment, fragment).commit();
        }
    }
}
