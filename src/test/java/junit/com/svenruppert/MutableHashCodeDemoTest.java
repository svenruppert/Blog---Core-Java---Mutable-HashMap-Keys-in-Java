package junit.com.svenruppert;

import com.svenruppert.dependencies.core.logger.HasLogger;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MutableHashCodeDemoTest
    implements HasLogger {

  @Test
  void test001() {
    Person p = new Person("Alice", 42);
    Map<Person, String> map = new HashMap<>();
    map.put(p, "Value");

    logger().info("Before change:");
    logger().info("map.get(p): " + map.get(p));

    // Key mutation
    p.name = "Bob";
    logger().info("After change:");
    logger().info("map.get(p): {}", map.get(p));
    var entries = map.entrySet();
    logger().info("Contains key via entrySet: {}",
                  entries
                      .stream()
                      .anyMatch(e -> e.getKey().equals(p)));

  }

  public static class Person {
    String name;
    int id;

    Person(String name, int id) {
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
      return "Person{" +
             "name='" + name + '\'' +
             ", id=" + id +
             '}';
    }
  }
}