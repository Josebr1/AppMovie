package br.com.aluno.etec.appmovie.domain;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.aluno.etec.appmovie.R;
import livroandroid.lib.utils.HttpHelper;

/**
 * Created by Jose on 14/03/2017.
 *
 */

public class FilmeService {

    private static final boolean LOG_ON = false;

    private static final String URL = "http://etprogramador.ga/json/filme_{tipo}.json";

    // Faz uma requisição
    public static List<Filme> getFilmesFromWebService(Context context, int tipo) throws IOException {
        String tipoString = getTipo(tipo);
        String url = URL.replace("{tipo}", tipoString);
        Log.d("FilmeJson", url);
        HttpHelper http = new HttpHelper();
        String json = http.doGet(url);
        List<Filme> filmes = parserJSON(context, json);

        return filmes;
    }

    private static String getTipo(int tipo) {
        if (tipo == R.string.acao) {
            return "acao";
        } else if (tipo == R.string.animacao) {
            return "animacao";
        } else if (tipo == R.string.guerra) {
            return "guerra";
        } else {
            return "classico";
        }
    }

    private static List<Filme> parserJSON(Context context, String json) throws IOException {
        List<Filme> filmes = new ArrayList<>();

        try{
            JSONObject root = new JSONObject(json);
            JSONObject obj = root.getJSONObject("filmes");
            JSONArray jsonFilmes = obj.getJSONArray("filme");

            for(int i = 0; i < jsonFilmes.length(); i++){
                JSONObject jsonFilme = jsonFilmes.getJSONObject(i);

                Filme filme = new Filme();

                filme.nome = jsonFilme.optString("nome");
                Log.i("Nome", filme.nome);
                filme.categoria = jsonFilme.optString("categoria");
                filme.ano = jsonFilme.optString("ano");
                filme.urlFoto = jsonFilme.optString("url_foto");
                filme.urlVideo = jsonFilme.optString("url_video");

                if(LOG_ON){
                    Log.d("Json", "Filme " + filme.nome + " > " + filme.urlFoto);
                }

                filmes.add(filme);
            }

            if(LOG_ON){
                Log.d("Encontrados", filmes.size() + "encontrados");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return filmes;
    }

}
