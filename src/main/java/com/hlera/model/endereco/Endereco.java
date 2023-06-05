package com.hlera.model.endereco;

import com.google.gson.Gson;
import jakarta.persistence.*;
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

@Embeddable
public class Endereco {
    @Column(name = "DS_ESTADO")
    private String estado;
    @Column(name = "DS_CIDADE")
    private String cidade;
    @Column(name = "DS_LOGRADOURO")
    private String logradouro;
    @Column(name = "DS_BAIRRO")
    private String bairro;
    @Column(name = "NR_CEP")
    private String cep;
    @Column(name = "NR_NUMERO")
    private String numero;
    @Column(name = "DS_COMPLEMENTO")
    private String complemento;

    public Endereco(String cep) throws MalformedURLException {
        var dados = encontrarCep(cep);
        if (dados != null) {
            this.cep = dados.optString("cep");
            this.logradouro = dados.optString("logradouro");
            this.bairro = dados.optString("bairro");
            this.cidade = dados.optString("localidade");
            this.estado = dados.optString("uf");
        }
    }

    public JSONObject encontrarCep(String cep) throws MalformedURLException {
        URL url = new URL("http://viacep.com.br/ws/" + cep + "/json");
        HttpURLConnection conexao = null;

        try {
            conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            if (conexao.getResponseCode() == HttpURLConnection.HTTP_OK) {
                String conteudo = new String(conexao.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
                return new JSONObject(conteudo);
            } else {
                JSONObject json = new JSONObject();
                json.put("error", "Erro na solicitação");
                return json;
            }
        } catch (IOException e) {
            JSONObject json = new JSONObject();
            json.put("error", e.getStackTrace());
            return json;
        } finally {
            if (conexao != null) {
                conexao.disconnect();
            }
        }
    }
}
