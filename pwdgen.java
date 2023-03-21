import java.security.SecureRandom;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class PasswordGenerator extends Application {
    private TextField lengthTextField;
    private CheckBox uppercaseCheckBox;
    private CheckBox lowercaseCheckBox;
    private CheckBox digitsCheckBox;
    private CheckBox specialCharsCheckBox;
    private Label passwordLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Password Generator");

        // Create UI elements
        Label titleLabel = new Label("Password Generator");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        Label lengthLabel = new Label("Length:");
        lengthTextField = new TextField();
        lengthTextField.setPrefWidth(50);
        lengthTextField.setText("8");

        uppercaseCheckBox = new CheckBox("Include uppercase letters");
        lowercaseCheckBox = new CheckBox("Include lowercase letters");
        digitsCheckBox = new CheckBox("Include digits");
        specialCharsCheckBox = new CheckBox("Include special characters");

        Button generateButton = new Button("Generate Password");
        generateButton.setOnAction(e -> generatePassword());

        passwordLabel = new Label();
        passwordLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        // Create layout
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        gridPane.add(titleLabel, 0, 0, 2, 1);
        gridPane.add(lengthLabel, 0, 1);
        gridPane.add(lengthTextField, 1, 1);
        gridPane.add(uppercaseCheckBox, 0, 2);
        gridPane.add(lowercaseCheckBox, 0, 3);
        gridPane.add(digitsCheckBox, 1, 2);
        gridPane.add(specialCharsCheckBox, 1, 3);
        gridPane.add(generateButton, 0, 4, 2, 1);
        gridPane.add(passwordLabel, 0, 5, 2, 1);

        // Create scene
        Scene scene = new Scene(gridPane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void generatePassword() {
        try {
            int length = Integer.parseInt(lengthTextField.getText());
            boolean includeUppercase = uppercaseCheckBox.isSelected();
            boolean includeLowercase = lowercaseCheckBox.isSelected();
            boolean includeDigits = digitsCheckBox.isSelected();
            boolean includeSpecialChars = specialCharsCheckBox.isSelected();
            String password = generatePassword(length, includeUppercase, includeLowercase, includeDigits, includeSpecialChars);
            passwordLabel.setText("Your password is: " + password);
        } catch (NumberFormatException e) {
            passwordLabel.setText("Invalid length");
        } catch (IllegalArgumentException e) {
            passwordLabel.setText(e.getMessage());
        }
    }

    private String generatePassword(int length, boolean includeUppercase, boolean includeLowercase, boolean includeDigits, boolean includeSpecialChars) {
        String uppercaseChars= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercaseChars = "abcdefghijklmnopqrstuvwxyz";
        String digitChars = "0123456789";
        String specialChars = "!"#$%&'()*+,-./:;<=>?@[\]^_`{|}~";
     // Create character set based on user input
    String charSet = "";
    if (includeUppercase) {
        charSet += uppercaseChars;
    }
    if (includeLowercase) {
        charSet += lowercaseChars;
    }
    if (includeDigits) {
        charSet += digitChars;
    }
    if (includeSpecialChars) {
        charSet += specialChars;
    }

    // Throw exception if no character set is selected
    if (charSet.isEmpty()) {
        throw new IllegalArgumentException("Please select at least one character set");
    }

    // Generate random password
    SecureRandom random = new SecureRandom();
    StringBuilder password = new StringBuilder();
    for (int i = 0; i < length; i++) {
        int index = random.nextInt(charSet.length());
        password.append(charSet.charAt(index));
    }

    return password.toString();
}
}
