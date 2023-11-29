package ch.app.bookoasis.views;

import ch.app.bookoasis.Data.User.User;
import ch.app.bookoasis.Security.AuthenticatedUser;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.util.Optional;

public class MainLayout extends AppLayout {
    private H2 viewTitle;
    private final AuthenticatedUser authenticatedCustomer;
    private final AccessAnnotationChecker accessChecker;

    public MainLayout(AuthenticatedUser authenticatedCustomer, AccessAnnotationChecker accessChecker) {
        this.authenticatedCustomer = authenticatedCustomer;
        this.accessChecker = accessChecker;

        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        H1 appName = new H1("Book Oasis");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());

    }

    private SideNav createNavigation() {
        SideNav nav = new SideNav();

        nav.addItem(new SideNavItem("Book", "/book"));
        nav.addItem(new SideNavItem("Movie", "/movie"));

        return nav;
    }


    private Footer createFooter() {
        Footer layout = new Footer();

        MenuBar menuBar = new MenuBar();

        Optional<User> user = authenticatedCustomer.get();
        if (user.isPresent()) {
            User customer = user.get();

            MenuItem customerItem = menuBar.addItem("");
            Div div = new Div();
            div.add(customer.getFirstName());
            customerItem.add(div);
            customerItem.getSubMenu().addItem("Profile", e -> {
                getUI().ifPresent(ui -> ui.navigate("profile"));
            });
            customerItem.getSubMenu().addItem("Logout", e -> { authenticatedCustomer.logout(); });
            layout.add(menuBar);
        } else {
            Anchor customerAnchor = new Anchor("login", "Login");
            layout.add(customerAnchor);
        }


        return layout;
    }
    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
