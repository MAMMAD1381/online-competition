package main;

import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class Quiz {
    public static void main(String[] args)
    {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("resources/questions.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray questionList = (JSONArray) obj;

            //Iterate over employee array
            questionList.forEach( emp -> parseQuestionObject( (JSONObject) emp ) );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void parseQuestionObject(JSONObject questionObject)
    {
        String question = (String) questionObject.get("question");
        System.out.println(question);
        JSONArray options=(JSONArray) questionObject.get("options");
        String op1= (String) options.get(0);
        String op2= (String) options.get(1);
        String op3= (String) options.get(2);
        String op4= (String) options.get(3);
        System.out.println(""+op1 +"  "+op2 +"  "+ op3 +"  "+op4);
        String answer = String.valueOf(questionObject.get("answer"));
        System.out.println(answer);

    }
}
