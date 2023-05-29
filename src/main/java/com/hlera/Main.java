package com.hlera;

import com.google.gson.Gson;
import com.hlera.model.endereco.Endereco;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {
        var endereco = new Endereco("01151020");
        System.out.println(endereco.getBairro());
    }
}
