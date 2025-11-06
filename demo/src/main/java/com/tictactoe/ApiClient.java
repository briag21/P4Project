package com.tictactoe;
import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;

public class ApiClient {
    private static final String API_URL = "http://127.0.0.1:5000/get_move";
    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public int[] getBestMove(Plateau plateau, char joueur){
        // Un objet Java qui a exactement la structure attendue par notre API Python
        ApiRequest requestPayload = new ApiRequest(plateau.getCases(), joueur);

        //Gson transforme un objet Java en un json (format géré par python)
        String jsonCorps = gson.toJson(requestPayload);
        
        //Le contenu de la requête envoyée à notre API Python
        RequestBody corps = RequestBody.create(jsonCorps, JSON);

        //On construit l'enveloppe de la requête
        Request request = new Request.Builder().url(API_URL).post(corps).build();

        System.out.println("L'IA ("+ joueur + ") réfléchit...");

        try(Response reponse = client.newCall(request).execute()){ //on attend une réponse après l'envoie de la requête et on stock la réponse dans la variable
            if(!reponse.isSuccessful()){
                System.out.println("Erreur de l'API : " + reponse.body().string());
                return null;
            }

            String jsonReponse = reponse.body().string();

            //Chemin inverse de la ligne 18 on transforme la réponse en un objet Java que notre code supporte
            ApiReponse apiReponse = gson.fromJson(jsonReponse, ApiReponse.class);

            return apiReponse.getMove();

        }catch(IOException exception){
            System.err.println("Impossible de contacter l'IA");
            System.err.println("Assurez vous que le serveur Python est lancé");
            exception.printStackTrace();
            return null;
        }


    }
}
