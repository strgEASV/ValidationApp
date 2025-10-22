package dk.easv.validationapp;

public class DataProcessor {

    public String processUserData(String name, String ageString) {
        // Validate name
        String nameValidationError = validateName(name);
        if (nameValidationError != null) {
            return nameValidationError;
        }
        
        // Validate and parse age
        Integer age = validateAndParseAge(ageString);
        if (age == null) {
            return "Error: Age must be a valid number!";
        }
        
        // Check age range
        String ageRangeError = validateAgeRange(age);
        if (ageRangeError != null) {
            return ageRangeError;
        }
        
        // Generate personalized greeting
        return generateGreeting(name.trim(), age);
    }
    
    private String validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return "Error: Name cannot be empty!";
        }
        
        if (name.trim().length() < 2) {
            return "Error: Name must be at least 2 characters long!";
        }
        
        if (!name.matches("[a-zA-ZáéíóúýčďěňřšťžůÁÉÍÓÚÝČĎĚŇŘŠŤŽŮ\\s]+")) {
            return "Error: Name can only contain letters!";
        }
        
        return null;
    }
    
    private Integer validateAndParseAge(String ageString) {
        if (ageString == null || ageString.trim().isEmpty()) {
            return null;
        }
        
        try {
            return Integer.parseInt(ageString.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
    
    private String validateAgeRange(int age) {
        if (age < 0) {
            return "Error: Age cannot be negative!";
        }
        
        if (age > 150) {
            return "Error: Age seems unrealistic (maximum 150)!";
        }
        
        if (age < 18) {
            return "Error: You must be at least 18 years old!";
        }
        
        return null;
    }
    
    private String generateGreeting(String name, int age) {
        String ageCategory = determineAgeCategory(age);
        
        return String.format("Hello, %s! You are %d years old. %s", 
                           name, age, ageCategory);
    }
    
    private String determineAgeCategory(int age) {
        if (age >= 18 && age < 30) {
            return "You're in your energetic young adult years!";
        } else if (age >= 30 && age < 50) {
            return "You're in your productive prime years!";
        } else if (age >= 50 && age < 65) {
            return "You're in your experienced mature years!";
        } else {
            return "You're in your golden years of wisdom!";
        }
    }
}
