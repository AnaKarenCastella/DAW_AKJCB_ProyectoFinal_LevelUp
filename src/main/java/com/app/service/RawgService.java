package com.app.service;

import com.app.model.Videojuego;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RawgService {

    private final String API_KEY;

    public RawgService() {

        Properties properties = new Properties();

        try {

            properties.load(
                    getClass()
                            .getClassLoader()
                            .getResourceAsStream("config.properties")
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

        API_KEY = properties.getProperty("rawg.api.key");
    }

    public List<Videojuego> obtenerJuegos() {

        List<Videojuego> lista = new ArrayList<>();

        try {

            String endpoint =
                    "https://api.rawg.io/api/games?key="
                            + API_KEY;

            URL url = new URL(endpoint);

            HttpURLConnection conexion =
                    (HttpURLConnection) url.openConnection();

            conexion.setRequestMethod("GET");

            BufferedReader br =
                    new BufferedReader(
                            new InputStreamReader(
                                    conexion.getInputStream()
                            )
                    );

            StringBuilder respuesta =
                    new StringBuilder();

            String linea;

            while ((linea = br.readLine()) != null) {
                respuesta.append(linea);
            }

            br.close();

            JSONObject json =
                    new JSONObject(respuesta.toString());

            JSONArray resultados =
                    json.getJSONArray("results");

            for (int i = 0; i < resultados.length(); i++) {

                JSONObject juego =
                        resultados.getJSONObject(i);

                Videojuego v =
                        new Videojuego();

                v.setTitulo(
                        juego.getString("name")
                );

                v.setDescripcion(
                        "Videojuego obtenido desde RAWG API"
                );

                v.setPrecio(
                        999.00
                );

                v.setStock(
                        10
                );

                if (!juego.isNull("background_image")) {

                    v.setImagenUrl(
                            juego.getString("background_image")
                    );

                } else {

                    v.setImagenUrl(
                            "https://via.placeholder.com/300x170?text=LevelUp"
                    );
                }

                lista.add(v);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<Videojuego> buscarJuegos(String texto) {

        List<Videojuego> lista = new ArrayList<>();

        try {

            String endpoint =
                    "https://api.rawg.io/api/games?key="
                            + API_KEY
                            + "&search="
                            + texto.replace(" ", "%20");

            URL url = new URL(endpoint);

            HttpURLConnection conexion =
                    (HttpURLConnection) url.openConnection();

            conexion.setRequestMethod("GET");

            BufferedReader br =
                    new BufferedReader(
                            new InputStreamReader(
                                    conexion.getInputStream()
                            )
                    );

            StringBuilder respuesta =
                    new StringBuilder();

            String linea;

            while ((linea = br.readLine()) != null) {
                respuesta.append(linea);
            }

            br.close();

            JSONObject json =
                    new JSONObject(respuesta.toString());

            JSONArray resultados =
                    json.getJSONArray("results");

            for (int i = 0; i < resultados.length(); i++) {

                JSONObject juego =
                        resultados.getJSONObject(i);

                Videojuego v =
                        new Videojuego();

                v.setTitulo(
                        juego.getString("name")
                );

                v.setDescripcion(
                        "Videojuego obtenido desde RAWG API"
                );

                v.setPrecio(
                        999.00
                );

                v.setStock(
                        10
                );

                if (!juego.isNull("background_image")) {

                    v.setImagenUrl(
                            juego.getString("background_image")
                    );

                } else {

                    v.setImagenUrl(
                            "https://via.placeholder.com/300x170?text=LevelUp"
                    );
                }

                lista.add(v);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}