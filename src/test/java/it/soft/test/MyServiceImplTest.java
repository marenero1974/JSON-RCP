package it.soft.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import it.soft.jsonrpc.impl.MyServiceImpl;
import it.soft.jsonrpc.model.InputParams;
import java.util.List;
import org.junit.jupiter.api.Test;

public class MyServiceImplTest {

  MyServiceImpl toTest = new MyServiceImpl();

  @Test
  void testGetMessaggiLimit1LoveTopic() {
    InputParams input = new InputParams();
    input.setTopic("love");
    input.setAmount(1);
    List<String> messaggi = toTest.GiveMeAdvice(input);
    assertEquals(1, messaggi.size());
  }

  @Test
  void testGetMessaggiLimit2LoveTopic() {
    InputParams input = new InputParams();
    input.setTopic("love");
    input.setAmount(2);
    List<String> messaggi = toTest.GiveMeAdvice(input);
    assertEquals(2, messaggi.size());
  }

  @Test
  void testGetMessaggiLimit1SpiderTopic() {
    InputParams input = new InputParams();
    input.setTopic("spiders");
    input.setAmount(1);
    List<String> messaggi = toTest.GiveMeAdvice(input);
    assertEquals(1, messaggi.size());
  }

  @Test
  void testGetMessaggiLimit2SpiderTopic() {
    InputParams input = new InputParams();
    input.setTopic("spiders");
    input.setAmount(1);
    List<String> messaggi = toTest.GiveMeAdvice(input);
    assertEquals(1, messaggi.size());
  }

}
