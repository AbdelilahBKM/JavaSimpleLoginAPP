package com.example.javafxproject0.Controllers;
import com.example.javafxproject0.DAO.UserDAO;
import com.example.javafxproject0.MainApplication;
import com.example.javafxproject0.business.User;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class authController {
    private UserDAO userServices;

    @FXML
    private TextField emailField;
    @FXML
    private Label emailFailedLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private Label usernameFailedLabel;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label passwordFailedLabel;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Label confirmPasswordFailedLabel;
    @FXML
    private Button authButton;
    @FXML
    private Button toogleAuthButton;
    private boolean isLogin = true;
    @Inject
    @FXML
    private void handleAuth(){
        if(isLogin){
            handleLogin();
        } else {
            handleRegister();
        }
    }
    @FXML
    private void handleToogleAuth(){
        isLogin = !isLogin;
        if(isLogin){
            usernameField.setVisible(false);
            usernameField.setManaged(false);
            confirmPasswordField.setVisible(false);
            confirmPasswordField.setManaged(false);
            authButton.setText("Login");
            toogleAuthButton.setText("Don't have an account? Register");
        } else {
            usernameField.setVisible(true);
            usernameField.setManaged(true);
            confirmPasswordField.setVisible(true);
            confirmPasswordField.setManaged(true);
            authButton.setText("Register");
            toogleAuthButton.setText("Already have an account? Login");
        }
    }

    private void handleLogin(){
        String email = emailField.getText();
        String password = passwordField.getText();
        clearErrorLabels();
        if(!email.isEmpty() && !password.isEmpty()){
            User user = userServices.getUser(email);
            if (user == null){
                showLabel(emailFailedLabel, "Username or Password is incorrect");
            } else if(email.equals(user.getEmail()) && password.equals(user.getPassword())){
                try {
                    MainApplication.showHomePage();
                } catch (Exception e){
                    e.printStackTrace();
                }
            } else {
                showLabel(emailFailedLabel, "Username or Password is incorrect");
            }
        }
        if(email.isEmpty()){
            showLabel(emailFailedLabel, "Username is required");
        } else {
            hideLabel(emailFailedLabel);
        }
        if(password.isEmpty()){
            showLabel(passwordFailedLabel, "Password is required");
        } else {
            hideLabel(passwordFailedLabel);
        }
    }
    private void handleRegister(){
        String email = emailField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        clearErrorLabels();
        if(email.isEmpty()){
            showLabel(emailFailedLabel, "Email is required");
        } else {
            hideLabel(emailFailedLabel);
        }
        if(username.isEmpty()){
            showLabel(usernameFailedLabel, "Username is required");
        } else {
            hideLabel(usernameFailedLabel);
        }
        if(password.isEmpty()){
            showLabel(passwordFailedLabel, "Password is required");
        } else {
            hideLabel(passwordFailedLabel);
        }
        if(confirmPassword.isEmpty()){
            showLabel(confirmPasswordFailedLabel, "Confirm Password is required");
        } else {
            hideLabel(confirmPasswordFailedLabel);
        }
        if(!password.equals(confirmPassword)){
            showLabel(confirmPasswordFailedLabel, "Password and Confirm Password must be same");
        } else {
            hideLabel(confirmPasswordFailedLabel);
        }
        if(
                !email.isEmpty()
                        && !username.isEmpty()
                        && !password.isEmpty()
                        && !confirmPassword.isEmpty()
                        && password.equals(confirmPassword)
        ){
            if (userServices.getUser(email) != null){
                showLabel(emailFailedLabel, "Email is already taken");
            } else {
                User user = new User();
                user.setEmail(email);
                user.setName(username);
                user.setPassword(password);
                User saved = userServices.saveUser(user);
                if(saved != null) {
                    try {
                        MainApplication.showHomePage();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void hideLabel(Label label){
        label.setVisible(false);
        label.setManaged(false);
    }

    private void showLabel(Label label, String message){
        label.setVisible(true);
        label.setManaged(true);
        label.setText(message);
    }
    private void clearErrorLabels() {
        hideLabel(usernameFailedLabel);
        hideLabel(emailFailedLabel);
        hideLabel(passwordFailedLabel);
        hideLabel(confirmPasswordFailedLabel);
    }

}
