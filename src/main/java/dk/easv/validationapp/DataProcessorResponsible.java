package dk.easv.validationapp;

public class DataProcessorResponsible {

    public ValidationResult validateAndProcessUser(String name, String ageString) {
        // Validate name
        if (name == null || name.trim().isEmpty()) {
            return new ValidationResult(false, "Name cannot be empty!");
        }
        
        if (name.trim().length() < 2) {
            return new ValidationResult(false, "Name must be at least 2 characters long!");
        }
        
        if (!name.matches("[a-zA-ZáéíóúýčďěňřšťžůÁÉÍÓÚÝČĎĚŇŘŠŤŽŮ\\s]+")) {
            return new ValidationResult(false, "Name can only contain letters!");
        }
        
        // Validate age
        if (ageString == null || ageString.trim().isEmpty()) {
            return new ValidationResult(false, "Age cannot be empty!");
        }
        
        int age;
        try {
            age = Integer.parseInt(ageString.trim());
        } catch (NumberFormatException e) {
            return new ValidationResult(false, "Age must be a valid number!");
        }
        
        if (age < 0) {
            return new ValidationResult(false, "Age cannot be negative!");
        }
        
        if (age > 150) {
            return new ValidationResult(false, "Age seems unrealistic max 150!");
        }
        
        if (age < 18) {
            return new ValidationResult(false, "You must be at least 18 years old!");
        }
        
        // All validations passed
        String message = String.format("✓ Success! User '%s' age %d has been validated.",
                                      name.trim(), age);
        return new ValidationResult(true, message);
    }

    public static class ValidationResult {
        private final boolean valid;
        private final String message;
        
        public ValidationResult(boolean valid, String message) {
            this.valid = valid;
            this.message = message;
        }
        
        public boolean isValid() {
            return valid;
        }
        
        public String getMessage() {
            return message;
        }
    }
}
