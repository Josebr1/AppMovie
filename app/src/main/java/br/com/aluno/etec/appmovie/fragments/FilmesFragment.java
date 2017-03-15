package br.com.aluno.etec.appmovie.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.aluno.etec.appmovie.FilmeApplication;
import br.com.aluno.etec.appmovie.R;
import br.com.aluno.etec.appmovie.adapter.FilmeAdapter;
import br.com.aluno.etec.appmovie.domain.Filme;
import br.com.aluno.etec.appmovie.domain.FilmeService;
import livroandroid.lib.utils.AndroidUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilmesFragment extends BaseFragment {


    private int mTipo;
    protected RecyclerView mRecyclerView;
    private List<Filme> mFilmes;
    private SwipeRefreshLayout mSwipeLayout;

    public static FilmesFragment newIntance(int tipo) {
        Bundle args = new Bundle();
        args.putInt("tipo", tipo);
        FilmesFragment filmesFragment = new FilmesFragment();
        filmesFragment.setArguments(args);

        Log.d("Tipo", tipo + "");
        return filmesFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            this.mTipo = getArguments().getInt("tipo");
        }

        FilmeApplication.getmInstance().getBus().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FilmeApplication.getmInstance().getBus().unregister(this);
    }

    public FilmesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filmes, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);

        // Inflate the layout for this fragment
        return view;
    }


    private SwipeRefreshLayout.OnRefreshListener OnRefreshListener() {
        return new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (AndroidUtils.isNetworkAvailable(getContext())) {
                    taskFilme(true);
                } else {
                    mSwipeLayout.setRefreshing(false);
                    snack(mRecyclerView, R.string.acao);
                }
            }
        };
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        taskFilme(false);
    }

    private void taskFilme(boolean pullToRefresh) {
        startTask("filmes", new GetFilmesTask(pullToRefresh), pullToRefresh ? R.id.swipeToRefresh : R.id.progress);
    }

    private class GetFilmesTask implements TaskListener<List<Filme>> {
        private boolean refresh;

        GetFilmesTask(boolean refresh) {
            this.refresh = refresh;
        }

        @Override
        public List<Filme> execute() throws Exception {

            return FilmeService.getFilmesFromWebService(getContext(), mTipo);
        }

        @Override
        public void updateView(List<Filme> response) {
            if (response != null) {

                FilmesFragment.this.mFilmes = response;

                mRecyclerView.setAdapter(new FilmeAdapter(getContext(), response, onClickFilme()));
            }
        }

        @Override
        public void onError(Exception exception) {
            alert("Ocorreu um error");
        }

        @Override
        public void onCancelled(String cod) {

        }
    }

    private FilmeAdapter.FilmeOnClickListener onClickFilme() {
        return new FilmeAdapter.FilmeOnClickListener() {
            @Override
            public void onClickFilme(View view, int idx) {

            }
        };
    }

}
