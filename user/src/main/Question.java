package main;


//organizing questions for the test
public class Question{
    public String question;
    public String[] options=new String[4];
    long answer;

    public Question(String question, String[] options, long answer) {
        this.question = question;
        for(int i=0;i<4;i++)
            this.options[i]=options[i];
        this.answer = answer;
    }
}
