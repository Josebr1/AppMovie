package br.com.aluno.etec.appmovie.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import br.com.aluno.etec.appmovie.R;
import br.com.aluno.etec.appmovie.fragments.FilmesFragment;
import br.com.aluno.etec.appmovie.utils.Dialog;
import livroandroid.lib.utils.AndroidUtils;
import livroandroid.lib.view.RoundedImageView;

/**
 * Created by Jose on 14/03/2017.
 */

public class BaseActivity extends livroandroid.lib.activity.BaseActivity {


    protected DrawerLayout mDrawerLayout;

    // Aticação da Toolbar
    protected void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    // Configura o nav drawer
    protected void setUpNavDrawer() {
        // Drawer Layout
        final ActionBar actionBar = getSupportActionBar();

        // Icone
        actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
        actionBar.setDisplayHomeAsUpEnabled(true);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null && mDrawerLayout != null) {
            // Propriedades do Header
            setNavViewValues(navigationView, R.string.app_name, R.drawable.logo);

            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem item) {
                    // Seleciona a linha
                    item.setCheckable(true);
                    // Fecha o menu
                    mDrawerLayout.closeDrawers();
                    // Trata o evento do menu
                    onNavDrawerItemSelected(item);
                    return true;
                }
            });
        }

    }

    // Propriedades do Header
    private static void setNavViewValues(NavigationView navView, int nome, int img) {
        View headerView = navView.getHeaderView(0);

        TextView tNome = (TextView) headerView.findViewById(R.id.tUserName);
        RoundedImageView imageView = (RoundedImageView) headerView.findViewById(R.id.imgUserPhoto);

        tNome.setText(nome);
        imageView.setImageResource(img);
    }

    // Definindo os eventos no itens do drawer
    private void onNavDrawerItemSelected(MenuItem menuItem) {
        Bundle args = new Bundle();
        switch (menuItem.getItemId()) {
            case R.id.nav_item_acao:
                args = new Bundle();
                args.putInt("tipo", R.string.acao);
                break;
            case R.id.nav_item_animacao:
                args = new Bundle();
                args.putInt("tipo", R.string.animacao);
                break;
            case R.id.nav_item_guerra:
                args = new Bundle();
                args.putInt("tipo", R.string.guerra);
                break;
            case R.id.nav_tem_classicos:
                args = new Bundle();
                args.putInt("tipo", R.string.classicos);
                break;
        }

        // Inicialização do fragment de acordo com a categoria do Bundle
        if (AndroidUtils.isNetworkAvailable(this)) {
            FilmesFragment fragAcao = new FilmesFragment();
            FilmesFragment.newIntance(R.string.acao);
            fragAcao.setArguments(args);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragAcao).commit();
        } else {
            Dialog.show(this, "Sem Conexão", "Tente se conectar a internet");
        }
    }

    // Icone home selecionado
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mDrawerLayout != null) {
                    openDrawer();
                    return true;
                }
        }
        return super.onOptionsItemSelected(item);
    }

    // Abre o menu lateral
    protected void openDrawer() {
        if (mDrawerLayout != null) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }

    // Fecha o menu lateral
    protected void closeDrawer() {
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
    }
}
