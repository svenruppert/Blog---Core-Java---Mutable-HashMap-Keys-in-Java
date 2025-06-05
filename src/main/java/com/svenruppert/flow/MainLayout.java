package com.svenruppert.flow;

import com.svenruppert.flow.views.version01.VersionOneView;
import com.svenruppert.flow.views.version02.VersionTwoView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.theme.lumo.LumoUtility;

import static com.vaadin.flow.component.icon.VaadinIcon.*;

public class MainLayout
    extends AppLayout {

  public MainLayout() {
    createHeader();
  }

  private void createHeader() {
    H1 appTitle = new H1("Mutable HashCode");

    SideNav views = getPrimaryNavigation();
    Scroller scroller = new Scroller(views);
    scroller.setClassName(LumoUtility.Padding.SMALL);

    DrawerToggle toggle = new DrawerToggle();
    H2 viewTitle = new H2("Demo");

    HorizontalLayout wrapper = new HorizontalLayout(toggle, viewTitle);
    wrapper.setAlignItems(FlexComponent.Alignment.CENTER);
    wrapper.setSpacing(false);

    VerticalLayout viewHeader = new VerticalLayout(wrapper);
    viewHeader.setPadding(false);
    viewHeader.setSpacing(false);

    addToDrawer(appTitle, scroller);
    addToNavbar(viewHeader);

    setPrimarySection(Section.DRAWER);
  }

  private SideNav getPrimaryNavigation() {
    SideNav sideNav = new SideNav();
    sideNav.addItem(new SideNavItem("Version One", "/" + VersionOneView.PATH,
                                    BUG.create()),
                    new SideNavItem("Version Two", "/" + VersionTwoView.PATH,
                                    BUG.create()),
                    new SideNavItem("Youtube", "/youtube",
                                    YOUTUBE.create()),
                    new SideNavItem("About", "/about",
                                    USER_HEART.create())
    );
    return sideNav;
  }

}