package controllers.security;

import play.api.Environment;
import play.mvc.*;
import play.data.*;

import javax.inject.Inject;

import views.html.*;

// Import user models
import models.users.*;

public class SignupController extends Controller {

    /** Dependency Injection **/
    /** http://stackoverflow.com/questions/15600186/play-framework-dependency-injection **/
    private FormFactory formFactory;

    /** http://stackoverflow.com/a/37024198 **/
    private Environment env;

    /** http://stackoverflow.com/a/10159220/6322856 **/
    @Inject
    public SignupController(Environment e, FormFactory f) {
        this.env = e;
        this.formFactory = f;
    }

    // Render and return  the Login view
    public Result signup() {

        // Create a form by wrapping the Product class
        // in a FormFactory form instance
        Form<Signup> signupForm = formFactory.form(Signup.class);

        // Render the Add Product View, passing the form object
        return ok(signup.render(signupForm, User.getUserById(session().get("email"))));
    }

    // Handle login submit
    public Result signupSubmit() {
        // Bind form instance to the values submitted from the form
        Form<User> signupForm = formFactory.form(Signup.class).bindFromRequest();

       try{
        Signup signup = signupForm.get();

        signup.save();

        flash("success","Account " + signup.getName() + " has been created");

        return redirect(controllers.routes.SignupController.signup());
    }catch(PersistenceException e){
        flash("Email has already been used.");
        return badRequest(signup.render(signupForm));
    }
    }

    // Logout
    public Result editProfile() {
        // Delete the current session
        // Generates a new session id
        return redirect(routes.SignupController.signup());
    }

}
