package main;

import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class Quiz {

    //function to read the json file
    public static void getQuestion(ArrayList<Question> list){
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("Resources/questions.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray questionList = (JSONArray) obj;

            //Iterate over employee array
            questionList.forEach( emp -> parseQuestionObject( list, (JSONObject) emp ) );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }



    //function to read 1 question at a time
    private static void parseQuestionObject(ArrayList<Question> list,JSONObject questionObject)
    {
        String[] option=new String[4];
        String question = (String) questionObject.get("question");
        JSONArray options=(JSONArray) questionObject.get("options");
        option[0]= (String) options.get(0);
        option[1]= (String) options.get(1);
        option[2]= (String) options.get(2);
        option[3]= (String) options.get(3);
        long answer = (long) questionObject.get("answer");
        list.add(new Question(question,option,answer));


    }
}


// a class for organizing the data from json file
class Question{
    String question;
    String[] options=new String[4];
    long answer;

    public Question(String question, String[] options, long answer) {
        this.question = question;
        for(int i=0;i<4;i++)
            this.options[i]=options[i];
        this.answer = answer;
    }
}
