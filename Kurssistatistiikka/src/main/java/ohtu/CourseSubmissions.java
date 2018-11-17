/**
 *
 * @author Hannu
 */
package ohtu;

import java.util.ArrayList;

public class CourseSubmissions {
    
    private Course course;
    private ArrayList<Submission> submissions;
    private int totalExcercisePointsGained;
    private int totalWorkingHours;
    
    public CourseSubmissions(Course course) {
        this.course = course;
        this.submissions = new ArrayList<>();
        this.totalExcercisePointsGained = 0;
        this.totalWorkingHours = 0;
    }

    public void addSubmission(Submission submission) {
        this.submissions.add(submission);
        this.totalExcercisePointsGained += submission.getExercises().length;
        this.totalWorkingHours += submission.getHours();
    }
    
    public Course getCourse() {
        return course;
    }
    
    public ArrayList<Submission> getSubmissions() {
        return submissions;
    }

    public int getTotalExcercisePointsGained() {
        return totalExcercisePointsGained;
    }

    public int getTotalWorkingHours() {
        return totalWorkingHours;
    }
    
}
