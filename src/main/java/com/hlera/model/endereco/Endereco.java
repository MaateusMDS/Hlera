package com.hlera.model.endereco;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Endereco {

    private Long id;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;

    private String cep;

    public  Endereco (String cep) throws MalformedURLException {
        URL url = new URL("http://viacep.com.br/ws/" + cep + "/json");
        HttpURLConnection conexao = null;

        try {
            conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            if (conexao.getResponseCode() == HttpURLConnection.HTTP_OK) {
                String conteudo = new String(conexao.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
                JSONObject json = new JSONObject(conteudo);

                this.cep = json.optString("cep");
                this.logradouro = json.optString("logradouro");
                this.bairro = json.optString("bairro");
                this.localidade = json.optString("localidade");
                this.uf = json.optString("uf");

            } else {
                // Lida com o código de resposta HTTP não OK
                System.out.println("Erro na solicitação: " + conexao.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conexao != null) {
                conexao.disconnect();
            }
        }
    }
}
