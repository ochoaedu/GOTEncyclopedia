package com.example.gotencyclopedia;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.json.JSONObject;

public class Controller {

    @FXML
    private ImageView characterImageView;

    @FXML
    private Label fullNameLabel;

    @FXML
    private Label titleLabel;

    @FXML
    private Label houseLabel;

    @FXML
    private Text fullNameText;

    @FXML
    private Text titleText;

    @FXML
    private Text houseText;

    @FXML
    private Button nextButton;

    @FXML
    private Button previousButton;


    private CharacterData characterData;

    //constructor using the data from the CharacterData Class
    public Controller() {
        characterData = new CharacterData();
    }

    //Initialization of the information (this is similar to asynchronisim) we get the information by the moment the view is loaded.
    @FXML
    private void initialize() {
        characterData.fetchDataFromAPI();
        loadNextCharacter();
    }


    //move forward
    @FXML
    private void loadNextCharacter() {
        JSONObject characterJSON = characterData.getNextCharacter();
        if (characterJSON != null) {
            displayCharacterInfo(characterJSON);
        } else {
            fullNameText.setText("There is no more characters");
        }
    }


    //move back
    @FXML
    private void loadPreviousCharacter() {
        if (characterData.getCurrentIndex() >= 0) {
            characterData.decrementIndex();

            JSONObject characterJSON = characterData.getPreviousCharacter();
            if (characterJSON != null) {
                displayCharacterInfo(characterJSON);
            } else {
                System.out.println("Something is going wrong here");
            }
        } else {
            System.out.println("We are in the first character");
        }
    }



//this method displays the information from the API and set it into the appropiated fields.
    @FXML
    private void displayCharacterInfo(JSONObject characterJSON) {
        String fullName = characterJSON.optString("fullName", "Name not available");
        String title = characterJSON.optString("title", "Title not available");
        String house = characterJSON.optString("family", "No house information");
        String imageURL = characterJSON.optString("imageUrl", "");
//trimming the url to avoid errors
        String img = imageURL.trim();

        //For debbuging purporses will print in the console the image url and the contend of the json to see how the information is coming.
        System.out.println("Img URL: " + img);
        System.out.println("JSON Content: " + characterJSON.toString());
//populating the fields with the necessary information.
        if (!imageURL.isEmpty()) {
            try {
                Image image = new Image(img);
                characterImageView.setImage(image);
            } catch (Exception e) {
                e.printStackTrace();
                characterImageView.setImage(null);
            }
        } else {
            characterImageView.setImage(null);
        }
        fullNameText.setText(fullName);
        titleText.setText(title);
        houseText.setText(house);
    }



}

