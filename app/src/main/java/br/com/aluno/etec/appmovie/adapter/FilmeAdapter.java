package br.com.aluno.etec.appmovie.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.aluno.etec.appmovie.R;
import br.com.aluno.etec.appmovie.domain.Filme;

/**
 * Created by Jose on 14/03/2017.
 *
 */

public class FilmeAdapter extends RecyclerView.Adapter<FilmeAdapter.FilmeViewHolder> {

    protected static final String TAG = "movie";
    private final List<Filme> mFilme;
    private final Context mContext;
    private FilmeOnClickListener mFilmeOnClickListener;

    public FilmeAdapter(Context mContext, List<Filme> mFilme, FilmeOnClickListener mFilmeOnClickListener) {
        this.mFilme = mFilme;
        this.mContext = mContext;
        this.mFilmeOnClickListener = mFilmeOnClickListener;
    }

    @Override
    public int getItemCount() {
        return this.mFilme != null ? this.mFilme.size() : 0;
    }

    @Override
    public FilmeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_filme, parent, false);

        FilmeViewHolder holder = new FilmeViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final FilmeViewHolder holder, final int position) {
        Filme filme = mFilme.get(position);

        holder.tNome.setText(filme.nome);
        holder.progress.setVisibility(View.INVISIBLE);

        Picasso.with(mContext).load(filme.urlFoto).fit().into(holder.img, new Callback() {
            @Override
            public void onSuccess() {
                holder.progress.setVisibility(View.GONE);
            }

            @Override
            public void onError() {
                holder.progress.setVisibility(View.VISIBLE);
            }
        });

        if(mFilmeOnClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mFilmeOnClickListener.onClickFilme(holder.itemView, position);
                }
            });
        }
    }

    public interface FilmeOnClickListener{
        void onClickFilme(View view, int idx);
    }

    public class FilmeViewHolder extends RecyclerView.ViewHolder{

        public TextView tNome;
        ImageView img;
        ProgressBar progress;
        CardView cardView;

        public FilmeViewHolder(View view) {
            super(view);
            tNome = (TextView) view.findViewById(R.id.text);
            img = (ImageView) view.findViewById(R.id.img);
            progress = (ProgressBar) view.findViewById(R.id.progressImg);
            cardView = (CardView) view.findViewById(R.id.card_view);
        }
    }
}
