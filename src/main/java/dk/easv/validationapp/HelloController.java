package dk.easv.validationapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private TextField nameField;
    
    @FXML
    private TextField ageField;
    
    @FXML
    private Label resultLabel;
    
    // Business Logic Layer
    private final UserValidationService validationService;
    
    public HelloController() {
        this.validationService = new UserValidationService();
    }
    
    @FXML
    protected void onSubmitButtonClick() {
        // Get input from text fields
        String name = nameField.getText();
        String age = ageField.getText();
        
        // Send data to Business Logic Layer for processing
        UserValidationService.ValidationResult result = 
            validationService.validateAndProcessUser(name, age);
        
        // Display result
        resultLabel.setText(result.getMessage());
        
        // Change color based on validation result
        if (result.isValid()) {
            resultLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: green;");
            // Clear fields on success
            nameField.clear();
            ageField.clear();
        } else {
            resultLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: red;");
        }
    }
}
