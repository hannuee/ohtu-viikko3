package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // \u00e4L\u00e4 laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012345678";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/courses/students/"+studentNr+"/submissions";

        String bodyText = Request.Get(url).execute().returnContent().asString();

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        
        System.out.println("opiskelijanumero " + studentNr);
        System.out.println("");
        int exercisesTotal = 0;
        int hoursTotal = 0;
        for (Submission submission : subs) {
            exercisesTotal += submission.getExercises().length;
            hoursTotal += submission.getHours();
            
            System.out.println(submission);
        }
        System.out.println("");
        System.out.println("yhteens\u00e4: " + exercisesTotal + " teht\u00e4v\u00e4\u00e4 " + hoursTotal + " tuntia");

    }
}
