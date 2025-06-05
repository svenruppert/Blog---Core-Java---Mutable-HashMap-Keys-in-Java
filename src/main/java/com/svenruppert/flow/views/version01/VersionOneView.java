package com.svenruppert.flow.views.version01;


import com.svenruppert.dependencies.core.logger.HasLogger;
import com.svenruppert.flow.MainLayout;
import com.svenruppert.flow.views.Person;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.HashMap;
import java.util.Map;

import static com.svenruppert.flow.views.version01.VersionOneView.PATH;

@Route(value = PATH, layout = MainLayout.class)
public class VersionOneView
    extends VerticalLayout
    implements HasLogger {

  public static final String PATH = "versionone";

  private final TextField nameField = new TextField("Name");
  private final TextField idField = new TextField("ID");
  private final TextArea output = new TextArea("Ausgabe");

  private final Person mutableKey = new Person("Alice", 42);
  private final Map<Person, String> map = new HashMap<>();

  public VersionOneView() {
    logger().info("Initializing VersionOneView");
    nameField.setValue(mutableKey.getName());
    idField.setValue(String.valueOf(mutableKey.getId()));
    output.setWidth("600px");

    Button putButton = new Button("put(key, value)", _ -> {
      logger().info("Putting value into map with key: {}", mutableKey);
      map.put(mutableKey, "Gespeichert");
      output.setValue("Eingefügt: " + mutableKey);
    });

    Button mutateButton = new Button("Name ändern", _ -> {
      logger().info("Changing name from {} to {}", mutableKey.getName(), nameField.getValue());
      mutableKey.setName(nameField.getValue());
      output.setValue("Name geändert zu: " + mutableKey.getName());
    });

    Button getButton = new Button("get(key)", _ -> {
      logger().info("Getting value for key: {}", mutableKey);
      String result = map.get(mutableKey);
      logger().info("Get result: {}", result);
      output.setValue("Resultat von get(): " + result);
    });

    Button iterateButton = new Button("entrySet() durchsuchen", _ -> {
      logger().info("Searching through entrySet for key: {}", mutableKey);
      String result = map.entrySet().stream()
          .filter(entry -> entry.getKey().equals(mutableKey))
          .map(Map.Entry::getValue)
          .findFirst()
          .orElse("Nicht gefunden via equals()");
      logger().info("EntrySet search result: {}", result);
      output.setValue("entrySet(): " + result);
    });

    add(nameField, idField, putButton, mutateButton, getButton, iterateButton, output);
    logger().info("VersionOneView initialization completed");
  }

}