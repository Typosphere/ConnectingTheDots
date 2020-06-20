package controller;

import database.DBAccess;
import database.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import main.Main;
import model.User;

public class LoginSceneController {
    private UserDAO userDAO;
    private DBAccess dbAccess;
    //private String userName;
    //private String password;
    public static String currentRole;

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField passwordField;

    public LoginSceneController() {
        super();
        this.dbAccess = Main.getDBAccess();
        this.userDAO = new UserDAO(dbAccess);
    }

    public void setup(){

    }

    public void doLogin(ActionEvent actionEvent) {
        StringBuilder warningText = new StringBuilder();
        boolean correctInput = true;
        String userName = nameTextField.getText();
        String password = passwordField.getText();
        User user = userDAO.getOneByUsername(userName);
        if (userName.isEmpty()) {
            warningText.append("Vul in je gebruikersnaam.\n");
            correctInput = false;
        }
        if (!user.getPassword().equals(password)) {
            warningText.append("Gebruikersnaam in combinatie met dit wachtwoord is niet geldig");
            correctInput = false;
        }
        if (!correctInput) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText(warningText.toString());
            error.show();
        } else {
            Main.getSceneManager().showWelcomeScene();
        }
    }

    public void doQuit(ActionEvent actionEvent) {
        System.exit(0);
    }
}
