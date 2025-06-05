package com.svenruppert.flow.views;

import java.io.Serializable;
import java.util.Objects;

public class Person
    implements Serializable, Comparable<Person> {
  String name;
  int id;

  public Person(String name, int id) {
    this.name = name;
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public int compareTo(Person other) {
    int nameComparison = this.name.compareTo(other.name);
    return nameComparison != 0 ? nameComparison : Integer.compare(this.id, other.id);
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