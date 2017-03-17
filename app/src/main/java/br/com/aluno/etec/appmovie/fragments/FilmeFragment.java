package br.com.aluno.etec.appmovie.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import org.parceler.Parcels;

import br.com.aluno.etec.appmovie.R;
import br.com.aluno.etec.appmovie.domain.Filme;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilmeFragment extends BaseFragment{

    private Filme mFilme;
    private static final String API_KEY = "AIzaSyB39S69Fw4b-xKfqXSP1csBuCINkLeIFnE";
    private String mUrlVideo;


    public FilmeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filme, container, false);
        // Inflate the layout for this fragment

        mFilme = Parcels.unwrap(getArguments().getParcelable("filme"));
        mUrlVideo = mFilme.urlVideo;

        // Inicializa uma instancia do YouTubePlayerSupportFragment
        YouTubePlayerSupportFragment youTubePlayerSupportFragment = YouTubePlayerSupportFragment.newInstance();

        // Add o fragement do youttube
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.youtube_layout, youTubePlayerSupportFragment).commit();

        // Tratamento do play e de possiveis erros
        youTubePlayerSupportFragment.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if(!b){
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                    youTubePlayer.cueVideo(mUrlVideo);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                String error = youTubeInitializationResult.toString();
                Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
                Log.d("ErrorYouTube", error);
            }
        });

        TextView txtSinopse = (TextView) view.findViewById(R.id.tSinopse);
        txtSinopse.setText(mFilme.sinopse);

        TextView txtNomeCompleto = (TextView) view.findViewById(R.id.tNomeCompleto);
        txtNomeCompleto.setText("Título: " + mFilme.nome);

        TextView txtCategoria = (TextView) view.findViewById(R.id.tCategoria);
        txtCategoria.setText("Categoria: " + mFilme.categoria);

        TextView txtAnoTempo = (TextView) view.findViewById(R.id.tAnoTempo);
        txtAnoTempo.setText("Ano/Duração: " + mFilme.ano);


        Log.d("Filme", mFilme.categoria);

        return view;
    }
}
