package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws IOException {
        // ÄLÄ laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012345678";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/courses/students/"+studentNr+"/submissions";

        String bodyText = Request.Get(url).execute().returnContent().asString();
        
        String urlCourses = "https://studies.cs.helsinki.fi/courses/courseinfo";

        String bodyTextCourses = Request.Get(urlCourses).execute().returnContent().asString();

        
        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        Course[] courses = mapper.fromJson(bodyTextCourses, Course[].class);
        
        
        HashMap<String, CourseSubmissions> allCourseSubmissions = new HashMap<>();
        
        for(Course course : courses) {
            allCourseSubmissions.put(course.getName(), new CourseSubmissions(course));
        }
        
        for(Submission submission : subs) {
            allCourseSubmissions.get(submission.getCourse()).addSubmission(submission);
        }
        
        
        System.out.println("opiskelijanumero " + studentNr);
        
        for(Map.Entry<String, CourseSubmissions> entry : allCourseSubmissions.entrySet()) {
            CourseSubmissions courseSubmissions = entry.getValue();
            if(!courseSubmissions.getSubmissions().isEmpty()) {
                
                System.out.println("");
                System.out.println(courseSubmissions.getCourse().getFullName());
                System.out.println("");
                
                Collections.sort(courseSubmissions.getSubmissions());
                
                for(Submission submission : courseSubmissions.getSubmissions()) {
                    System.out.println(submission.toString(courseSubmissions.getCourse().getExercises()[submission.getWeek()]));
                }
                
                System.out.println("");
                System.out.println("yhteens\u00e4: " + courseSubmissions.getTotalExcercisePointsGained() + 
                        "/" + courseSubmissions.getCourse().calculateTotalExercisePoints() + " teht\u00e4v\u00e4\u00e4 " + courseSubmissions.getTotalWorkingHours() + " tuntia");
            }
        }
        
    }
}
