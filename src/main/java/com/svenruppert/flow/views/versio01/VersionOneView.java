package com.svenruppert.flow.views.versio01;


import com.svenruppert.dependencies.core.logger.HasLogger;
import com.svenruppert.flow.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.svenruppert.flow.views.versio01.VersionOneView.PATH;

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
    nameField.setValue(mutableKey.name);
    idField.setValue(String.valueOf(mutableKey.id));
    output.setWidth("600px");

    Button putButton = new Button("put(key, value)", _ -> {
      logger().info("Putting value into map with key: {}", mutableKey);
      map.put(mutableKey, "Gespeichert");
      output.setValue("Eingefügt: " + mutableKey);
    });

    Button mutateButton = new Button("Name ändern", _ -> {
      logger().info("Changing name from {} to {}", mutableKey.name, nameField.getValue());
      mutableKey.name = nameField.getValue();
      output.setValue("Name geändert zu: " + mutableKey.name);
    });

    Button getButton = new Button("get(key)", e -> {
      logger().info("Getting value for key: {}", mutableKey);
      String result = map.get(mutableKey);
      logger().info("Get result: {}", result);
      output.setValue("Resultat von get(): " + result);
    });

    Button iterateButton = new Button("entrySet() durchsuchen", e -> {
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

  public static class Person {
    String name;
    int id;

    public Person(String name, int id) {
      this.name = name;
      this.id = id;
    }

    @Override
    public boolean equals(Object o) {
      return o instanceof Person p && Objects.equals(name, p.name) && id == p.id;
    }

    @Override
    public int hashCode() {
      return Objects.hash(name, id);
    }

    @Override
    public String toString() {
      return name + " (" + id + ")";
    }
  }
}