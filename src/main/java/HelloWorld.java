
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;
public class HelloWorld {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 8080; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    public static void main(String[] args) {
//port(8080);
        port(getHerokuAssignedPort());
        staticFiles.location("/public");

        List<String> names = new ArrayList<>();


        get("/hello/:name", (req, res) -> {
           String aname =req.params(":name");
            return  "Hello "+ aname;
        });


        get("/greet", (req, res) -> {
            Map<String, String> dataMap = new HashMap<>();
//            String name = req.params(":name");


            dataMap.put("Name", "Jack");
            names.add(String.valueOf(dataMap));

            System.out.println(dataMap + " " );


            return new ModelAndView(dataMap, "hello.handlebars");

        }, new HandlebarsTemplateEngine());

    }
}

