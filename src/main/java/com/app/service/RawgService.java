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
import java.net.URLEncoder;

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
        String endpoint =
                "https://api.rawg.io/api/games?key="
                        + API_KEY
                        + "&page_size=20";

        return consumirApi(endpoint);
    }

    public List<Videojuego> buscarJuegos(String texto) {

        String busquedaCodificada = "";

        try {
            busquedaCodificada =
                    URLEncoder.encode(texto, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String endpoint =
                "https://api.rawg.io/api/games?key="
                        + API_KEY
                        + "&search="
                        + busquedaCodificada
                        + "&page_size=20";

        return consumirApi(endpoint);
    }

    private List<Videojuego> consumirApi(String endpoint) {
        List<Videojuego> lista = new ArrayList<>();

        try {
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

            StringBuilder respuesta = new StringBuilder();
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
                JSONObject juego = resultados.getJSONObject(i);

                Videojuego v = new Videojuego();

                v.setRawgId(juego.getInt("id"));
                v.setTitulo(juego.optString("name", "Sin título"));
                v.setDescripcion("Videojuego obtenido desde RAWG API");

                v.setPrecio(generarPrecio(juego.optDouble("rating", 0)));
                v.setStock(10);

                v.setRating(juego.optDouble("rating", 0));
                v.setFechaLanzamiento(juego.optString("released", "Sin fecha"));

                if (!juego.isNull("background_image")) {
                    v.setImagenUrl(juego.getString("background_image"));
                } else {
                    v.setImagenUrl("https://via.placeholder.com/300x170?text=LevelUp");
                }

                v.setGeneros(obtenerGeneros(juego));
                v.setPlataforma(obtenerPlataformas(juego));

                lista.add(v);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    private String obtenerGeneros(JSONObject juego) {
        JSONArray generosJson = juego.optJSONArray("genres");

        if (generosJson == null || generosJson.length() == 0) {
            return "Sin género";
        }

        StringBuilder generos = new StringBuilder();

        for (int i = 0; i < generosJson.length(); i++) {
            JSONObject genero = generosJson.getJSONObject(i);

            generos.append(genero.optString("name", "Género"));

            if (i < generosJson.length() - 1) {
                generos.append(" | ");
            }
        }

        return generos.toString();
    }

    private String obtenerPlataformas(JSONObject juego) {
        JSONArray plataformasJson = juego.optJSONArray("platforms");

        if (plataformasJson == null || plataformasJson.length() == 0) {
            return "PC";
        }

        StringBuilder plataformas = new StringBuilder();

        int limite = Math.min(plataformasJson.length(), 3);

        for (int i = 0; i < limite; i++) {
            JSONObject item = plataformasJson.getJSONObject(i);
            JSONObject plataforma = item.getJSONObject("platform");

            plataformas.append(plataforma.optString("name", "Plataforma"));

            if (i < limite - 1) {
                plataformas.append(" | ");
            }
        }

        return plataformas.toString();
    }

    private double generarPrecio(double rating) {
        if (rating >= 4.5) {
            return 999.00;
        } else if (rating >= 4.0) {
            return 799.00;
        } else if (rating >= 3.5) {
            return 599.00;
        } else {
            return 399.00;
        }
    }

    //Con ayuda de IA generativa
    public String sugerirCorreccion(String texto) {

        String[] juegosConocidos = {
                "Zelda",
                "Minecraft",
                "Cyberpunk",
                "Mario",
                "Halo",
                "Doom",
                "Fortnite",
                "Valorant",
                "Resident Evil",
                "Silent Hill",
                "Grand Theft Auto",
                "GTA",
                "The Witcher",
                "Elden Ring",
                "Call of Duty",
                "Assassin's Creed",
                "God of War",
                "Final Fantasy",
                "FIFA",
                "Mortal Kombat",
                "Red Dead Redemption",
                "League of Legends",
                "Overwatch",
                "Dark Souls",
                "Portal",
                "Half-Life",
                "Tomb Raider",
                "Skyrim"
        };

        String textoNormalizado = texto.toLowerCase().trim();

        String mejorCoincidencia = null;
        int menorDistancia = Integer.MAX_VALUE;

        for (String juego : juegosConocidos) {

            String juegoNormalizado = juego.toLowerCase();

            int distancia =
                    calcularDistanciaLevenshtein(
                            textoNormalizado,
                            juegoNormalizado
                    );

            if (distancia < menorDistancia) {
                menorDistancia = distancia;
                mejorCoincidencia = juego;
            }
        }

        if (menorDistancia <= 3) {
            return mejorCoincidencia;
        }

        return null;
    }

    private int calcularDistanciaLevenshtein(String a, String b) {

        int[][] matriz =
                new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) {
            matriz[i][0] = i;
        }

        for (int j = 0; j <= b.length(); j++) {
            matriz[0][j] = j;
        }

        for (int i = 1; i <= a.length(); i++) {

            for (int j = 1; j <= b.length(); j++) {

                int costo =
                        a.charAt(i - 1) == b.charAt(j - 1)
                                ? 0
                                : 1;

                matriz[i][j] =
                        Math.min(
                                Math.min(
                                        matriz[i - 1][j] + 1,
                                        matriz[i][j - 1] + 1
                                ),
                                matriz[i - 1][j - 1] + costo
                        );
            }
        }

        return matriz[a.length()][b.length()];
    }
}