package br.com.aluno.etec.appmovie.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.parceler.Parcels;

import br.com.aluno.etec.appmovie.R;
import br.com.aluno.etec.appmovie.domain.Filme;
import livroandroid.lib.utils.IntentUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilmeFragment extends BaseFragment {

    private Filme mFilme;

    public FilmeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filme, container, false);
        // Inflate the layout for this fragment

        mFilme = Parcels.unwrap(getArguments().getParcelable("filme"));


        TextView txtSinopse = (TextView) view.findViewById(R.id.tSinopse);
        txtSinopse.setText(mFilme.sinopse);

        TextView txtCategoria = (TextView) view.findViewById(R.id.tCategoria);
        txtCategoria.setText("Categoria: " + mFilme.categoria);

        TextView txtAnoTempo = (TextView) view.findViewById(R.id.tAnoTempo);
        txtAnoTempo.setText("Ano/Duração: " + mFilme.ano);


        Log.d("Filme", mFilme.categoria);

        view.findViewById(R.id.imgPlayVideo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTrailer(mFilme.urlVideo);
            }
        });

        return view;
    }

    private void showTrailer(String url){
        IntentUtils.showVideo(getContext(), url);
    }

}
