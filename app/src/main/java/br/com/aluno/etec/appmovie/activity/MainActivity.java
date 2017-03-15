package br.com.aluno.etec.appmovie.activity;

import android.os.Bundle;

import br.com.aluno.etec.appmovie.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ativa o Toolbar
        setUpToolbar();

        // Inicializa o NavigationDrawer
        setUpNavDrawer();

    }
}
