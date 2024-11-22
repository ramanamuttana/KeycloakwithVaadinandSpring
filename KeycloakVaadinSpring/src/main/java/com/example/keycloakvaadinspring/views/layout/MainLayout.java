package com.example.keycloakvaadinspring.views.layout;

import com.example.keycloakvaadinspring.views.index.IndexView;
import com.example.keycloakvaadinspring.views.user.ListView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;

@Route("initialpage")
public class MainLayout extends AppLayout {

    private static final long serialVersionUID = 1L;
    private final AccessAnnotationChecker accessChecker;
    private H2 viewTitle;

    public MainLayout(AccessAnnotationChecker accessChecker) {
        this.accessChecker = accessChecker;

        setPrimarySection(Section.DRAWER);
        createDrawer();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.getElement().setAttribute("aria-label", "Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        HorizontalLayout buttonLayout = new HorizontalLayout(createFooter());
        buttonLayout.getStyle().set("flex-wrap", "wrap");
        buttonLayout.setWidth("1550px");
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        addToNavbar(true, toggle, viewTitle, buttonLayout);
    }

    private Footer createFooter() {
        Footer layout = new Footer();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2AuthenticationToken token
                && token.getPrincipal() instanceof DefaultOidcUser oidcUser) {
            Avatar avatar = new Avatar(authentication.getName());
            avatar.setThemeName("xsmall");
            avatar.getElement().setAttribute("tabindex", "-1");

            MenuBar userMenu = new MenuBar();
            userMenu.setThemeName("tertiary-inline contrast");

            MenuItem userName = userMenu.addItem("");
            Div div = new Div();
            div.add(avatar);
            div.add(oidcUser.getFullName());
            div.add(new Icon("lumo", "dropdown"));
            div.getElement().getStyle().set("display", "flex");
            div.getElement().getStyle().set("align-items", "center");
            div.getElement().getStyle().set("gap", "var(--lumo-space-s)");
            userName.add(div);
            userName.getSubMenu().addItem("Sign out", e -> UI.getCurrent().getPage().setLocation("/logout"));

            layout.add(userMenu);
        } else {
            Button login = new Button("Sign in",
                    event -> UI.getCurrent().getPage().setLocation("/user"));
            layout.add(login);
        }

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }

    private void createDrawer() {
        addToDrawer(new VerticalLayout(
                new RouterLink("Index", IndexView.class),
                new RouterLink("List", ListView.class)
        ));
    }
}

