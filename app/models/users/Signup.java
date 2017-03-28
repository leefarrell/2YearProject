package models.users;

// A view Model class to back the Login form
public class Signup {

    private String name;
    private String email;
    private String password;
    private String address;

    // Validate method - invoked during error checking
    // after form based on a Login object has been submitted
    public String validate() {

        // Call the static authenticate method in User
        if (User.authenticate(email, password) == null) {
            return "Invalid name, user or password";
        }
        return null;
    }

     public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}