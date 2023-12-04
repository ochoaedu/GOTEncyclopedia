package com.example.gotencyclopedia;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
public class CharacterData {
    private JSONArray characters;
    private int currentIndex;

    //constructor and setting the index to 0 to initiate from the first character
    public CharacterData() {
        characters = new JSONArray(); // Initialize the character array
        currentIndex = 0;
    }

    //Function to handle the data from the API through GET method
    public void fetchDataFromAPI() {
        try {
            URL url = new URL("https://thronesapi.com/api/v2/Characters");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // JSON parsing answer
                JSONArray charactersArray = new JSONArray(response.toString());
                characters = new JSONArray();

                for (int i = 0; i < charactersArray.length(); i++) {
                    JSONObject character = charactersArray.getJSONObject(i);
                    String imageUrl = character.optString("imageUrl", "");

                    if (!imageUrl.isEmpty() && !imageUrl.startsWith("http://") && !imageUrl.startsWith("https://")) {
                        imageUrl = "https://thronesapi.com/" + imageUrl;
                        character.put("imageUrl", imageUrl);
                    }

                    characters.put(character);
                }
            } else {
                // Connection error handling
                System.out.println("Connection error: // " + responseCode);
            }
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Method to move to the next character
    public JSONObject getNextCharacter() {
        if (currentIndex >= characters.length()) {

            return null;
        }

        JSONObject characterJSON = characters.getJSONObject(currentIndex);
        currentIndex++;
        return characterJSON;
    }

    // Method to get the current index
    public int getCurrentIndex() {
        return currentIndex;
    }

    // decrease index to bring the previous character information
    public void decrementIndex() {
       currentIndex--;
    }


    // Method to go to the previous character
    public JSONObject getPreviousCharacter() {
        if (currentIndex > 0) {
            currentIndex--;
            return characters.getJSONObject(currentIndex);
        }
        return null;
    }



}
