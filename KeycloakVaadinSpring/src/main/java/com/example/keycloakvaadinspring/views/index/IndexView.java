package com.example.keycloakvaadinspring.views.index;

import com.example.keycloakvaadinspring.views.layout.MainLayout;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Home")
@Route(value="index",layout = MainLayout.class)
@AnonymousAllowed
public class IndexView extends HorizontalLayout {

    public IndexView() {
        setPadding(true);
        VerticalLayout vert = new VerticalLayout();
        vert.add(new H1("Welcome"));
        vert.add(new Text("An independent communications network and a flexible picture of the situation in the acute phase of a disaster: That is the goal of the lokik project, short for Local Initial Crisis Management, on which scientists from the Fraunhofer Institute for Communication, Information Processing and Ergonomics FKIE have been working since the devastating flood night in Ahr Valley 2021 work. In the spring of 2022, the researchers officially presented their project for the first time in the building of the Winzer-Verein in Mayschoß. A lot has happened since then - in the flood areas on the Ahr, but also in the lokik project."));

        Image image = new Image("./images/myimage.png", "Lokik alter  Image");
        vert.add(image);
        add(vert);
    }


}
