package ohtu;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
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

        
        // Getting Courses and submissions
        String url = "https://studies.cs.helsinki.fi/courses/students/"+studentNr+"/submissions";
        String bodyText = Request.Get(url).execute().returnContent().asString();
        String urlCourses = "https://studies.cs.helsinki.fi/courses/courseinfo";
        String bodyTextCourses = Request.Get(urlCourses).execute().returnContent().asString();
        
        // Parsing Courses and submissions
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
        
        
        // Getting Stats
        String urlStatsOhtu2018 = "https://studies.cs.helsinki.fi/courses/ohtu2018/stats";
        String bodyTextStatsOhtu2018 = Request.Get(urlStatsOhtu2018).execute().returnContent().asString();
        String urlStatsRails2018 = "https://studies.cs.helsinki.fi/courses/rails2018/stats";
        String bodyTextStatsRails2018 = Request.Get(urlStatsRails2018).execute().returnContent().asString();
        
        // Parsing Stats
        JsonParser parserOhtu2018 = new JsonParser();
        JsonObject parsedOhtu2018 = parserOhtu2018.parse(bodyTextStatsOhtu2018).getAsJsonObject();
        JsonParser parserRails2018 = new JsonParser();
        JsonObject parsedRails2018 = parserRails2018.parse(bodyTextStatsRails2018).getAsJsonObject();
        
        
        for(Map.Entry<String, JsonElement> entry : parsedOhtu2018.entrySet()) {
            JsonElement jElement = entry.getValue();
            JsonObject jObject = jElement.getAsJsonObject();
            
            CourseSubmissions courseSubmissions = allCourseSubmissions.get("ohtu2018");
            courseSubmissions.addStudents(jObject.get("students").getAsInt());
            courseSubmissions.addHourTotal(jObject.get("hour_total").getAsDouble());
            courseSubmissions.addExerciseTotal(jObject.get("exercise_total").getAsInt());
        }
        for(Map.Entry<String, JsonElement> entry : parsedRails2018.entrySet()) {
            JsonElement jElement = entry.getValue();
            JsonObject jObject = jElement.getAsJsonObject();
            
            CourseSubmissions courseSubmissions = allCourseSubmissions.get("rails2018");
            courseSubmissions.addStudents(jObject.get("students").getAsInt());
            courseSubmissions.addHourTotal(jObject.get("hour_total").getAsDouble());
            courseSubmissions.addExerciseTotal(jObject.get("exercise_total").getAsInt());
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
                
                // Print stats if they exist.
                if(courseSubmissions.getStatsExist()) {
                    System.out.println("");
                    System.out.println("kurssilla yhteens\u00e4 " + courseSubmissions.getStudents() + 
                            " palautusta, palautettuja teht\u00e4vi\u00e4 " + courseSubmissions.getExerciseTotal() + 
                            " kpl, aikaa k\u00e4ytetty yhteens\u00e4 " + (int)courseSubmissions.getHourTotal() + " tuntia");
                }
            }
        }
        
    }
}
