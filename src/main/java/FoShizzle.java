
import java.util.Map;
import java.util.HashMap;
import java.io.Console;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class FoShizzleGame {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";
    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/home.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/foshizzle", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/foshizzle.vtl");

      String userInput = request.queryParams("userInput");

      String whoWins = whoWins(playerOne, playerTwo);

      model.put("playerOne", playerOne);
      model.put("playerTwo", playerTwo);
      model.put("whoWins", whoWins(playerOne, playerTwo));
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }

  public static String FoShizzle(String userInput){
    char[] inputArray = userInput.toCharArray();
    char[] firstChar= java.util.Arrays.copyOfRange(inputArray, 0, 1);
    String firstLetter = new String(firstChar);

    int i = inputArray.length;
    char[] arrayCopy = java.util.Arrays.copyOfRange(inputArray, 2, i);
    String x = new String(arrayCopy);
    String y = x.replace('s', 'z');
    String result = firstLetter + y;
    return result;
  }

}
