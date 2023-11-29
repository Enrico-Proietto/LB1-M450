package ch.app.bookoasis.views.login;

import at.favre.lib.crypto.bcrypt.BCrypt;
import ch.app.bookoasis.Data.Role.Role;
import ch.app.bookoasis.Data.User.User;
import ch.app.bookoasis.Service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import java.util.Set;

@Route(value = "register")
@PageTitle("Register | BookOasis")
@AnonymousAllowed
public class RegisterView extends VerticalLayout {
    private final TextField firstName = new TextField("First name");
    private final TextField lastName = new TextField("Last name");
    private final EmailField emailField = new EmailField("Email");
    private final PasswordField passwordField = new PasswordField("Password");
    private final PasswordField confirmPasswordField = new PasswordField("Confirm password");
    private final TextField addressField = new TextField("Adress");
    private final TextField cityField = new TextField("City");
    private final TextField zipField = new TextField("Zip");
    private final TextField countryField = new TextField("Country");
    private final TextField phoneField = new TextField("Phone number");
    private final Button registerButton = new Button("Register");

    public RegisterView(UserService userService) {
        this.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        this.setMargin(true);
        this.getStyle().set("margin-left", "800px");
        this.getStyle().set("margin-top", "250px");
        registerButton.setWidth("615px");

        H1 title = new H1("Register");

        Binder<User> binder = new Binder<>(User.class);
        binder.forField(firstName)
                .withValidator(name -> name.length() >= 3, "First name must contain at least 3 characters")
                .bind(User::getFirstName, User::setFirstName);
        binder.forField(lastName)
                .withValidator(name -> name.length() >= 3, "Last name must contain at least 3 characters")
                .bind(User::getLastName, User::setLastName);
        binder.forField(emailField)
                .withValidator(email -> email.length() >= 3, "Email must contain at least 3 characters")
                .withValidator(email -> email.contains("@"), "Email must contain @")
                .bind(User::getEmail, User::setEmail);
        binder.forField(passwordField)
                .withValidator(password -> password.length() >= 12, "Password must contain at least 12 characters")
                .bind(Customer -> "", (Customer, password) -> Customer.setPassword(BCrypt.withDefaults().hashToString(12, password.toCharArray())));
        binder.forField(addressField)
                .withValidator(address -> address.contains(" "), "Address needs House number")
                .bind(User::getAddress, User::setAddress);
        binder.forField(cityField)
                .withValidator(city -> city.length() >= 3, "City must contain at least 3 characters")
                .bind(User::getCity, User::setCity);
        binder.forField(zipField)
                .bind(User::getZip, User::setZip);
        binder.forField(countryField)
                .withValidator(country -> country.length() >= 3, "Country must contain 3 characters")
                .bind(User::getCountry, User::setCountry);
        binder.forField(phoneField)
                .withValidator(phone -> phone.contains("+"), "Phone number needs a + at the start")
                .bind(User::getPhone, User::setPhone);

        registerButton.addClickListener(event -> {
                User customer = new User();
                if (binder.writeBeanIfValid(customer)) {
                    customer.setRole(Set.of(Role.USER));
                    userService.save(customer);
                    getUI().ifPresent(ui -> ui.navigate("login"));
                } else {
                    Notification.show("Please fill all fields correctly").setThemeName("error");
                }
        });

        firstName.setWidth("300px");
        lastName.setWidth("300px");
        addressField.setWidth("300px");
        cityField.setWidth("300px");
        zipField.setWidth("300px");
        countryField.setWidth("300px");
        emailField.setWidth("615px");
        phoneField.setWidth("615px");
        passwordField.setWidth("300px");
        confirmPasswordField.setWidth("300px");

        HorizontalLayout nameLayout = new HorizontalLayout(firstName, lastName);
        HorizontalLayout addressLayout = new HorizontalLayout(addressField, cityField);
        HorizontalLayout cityLayout = new HorizontalLayout(zipField, countryField);
        HorizontalLayout emailLayout = new HorizontalLayout(emailField);
        HorizontalLayout phoneLayout = new HorizontalLayout(phoneField);
        HorizontalLayout passwordLayout = new HorizontalLayout(passwordField, confirmPasswordField);

        VerticalLayout wholeLayout = new VerticalLayout(nameLayout, addressLayout, cityLayout, emailLayout, phoneLayout, passwordLayout, registerButton);
        FormLayout formLayout = new FormLayout();
        formLayout.add(wholeLayout);

        add(title, formLayout);
    }
}
