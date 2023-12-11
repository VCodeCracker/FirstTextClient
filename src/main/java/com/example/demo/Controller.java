


    package com.example.demo;

    import javafx.fxml.FXML;
    import javafx.scene.control.Label;
    import java.net.URL;
    import java.security.spec.RSAOtherPrimeInfo;
    import java.util.ResourceBundle;
    import java.util.regex.Matcher;
    import java.util.regex.Pattern;

    import javafx.scene.control.Button;
    import javafx.scene.control.TextField;
    import javafx.scene.text.Text;

    public class Controller {

        public void setClient(Client client) {
            this.client = client;
            System.out.println("Client was set");
        }

        private Client client;

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private TextField MessageInput;

        @FXML
        private Text MessageOutput;

        @FXML
        private Button SendMessage;

        @FXML
        void initialize() {
            SendMessage.setOnAction(actionEvent -> {
                client.sendMessage(getMessageInput().getText());
                getMessageInput().setText("");
            });
        }

        public TextField getMessageInput() {
            return MessageInput;
        }

        public Text getMessageOutput() {
            return MessageOutput;
        }


    }

