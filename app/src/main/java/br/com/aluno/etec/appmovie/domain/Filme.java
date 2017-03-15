package br.com.aluno.etec.appmovie.domain;

/**
 * Created by Jose on 14/03/2017.
 *
 *
 */

@org.parceler.Parcel
public class Filme {

    private static final long serialVersionUID = 6601006766832473959L;

    public long id;
    public String categoria;
    public String nome;
    public String ano;
    public String urlFoto;
    public String urlVideo;

    @Override
    public String toString() {
        return "Filme{" + "nome='" + nome + '\'' + '}';
    }
}
