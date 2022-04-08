package View;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.util.Pair;

import java.io.IOException;
import java.util.Optional;


public class UserLogin extends DialogPane {

    @FXML
    private TextField fxUserId, fxPassword;

    public UserLogin(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("userLoginView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }

    public Pair<Integer, String> getUser(){
        // Dialogurinn (umgjörðin um DialogPane) búin til
        Dialog<Pair<Integer, String>> d = new Dialog<>();

        // Innihaldið sett í dialog-inn umgjörðina
        d.setDialogPane(this);

        // Sett regla um hvenær í lagi hnappur er virkur
        //iLagiRegla(lookupButton(fxILagi));

        // Búum til hlut af nýjum nafnlausum innri klasa sem útfærir interface
        // Callback fyrir klasana ButtonType og Vidburdur
        // Callback hefur eina aðferð og við endurforritum hana
        d.setResultConverter(b -> {                                 // b er af taginu ButtonType
            if (b.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                return new Pair<Integer, String>(Integer.valueOf(fxUserId.getText()), fxPassword.getText());
            } else {
                System.exit(0);
                // KANNSKI SKILA GAMLA USERNUM EF VIÐ LÁTUM ÞETTA OPNAST LÍKA EF HÆGT ER AÐ SKRÁ SIG ÚT
                return null;
            }
        });

        // Dialog birtur og svarið fengið
        Optional<Pair<Integer, String>> utkoma = d.showAndWait();
        return utkoma.orElse(null);

    }
}
