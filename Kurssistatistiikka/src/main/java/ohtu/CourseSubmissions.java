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
    
    // Stats for all
    private boolean statsExist;
    private int students;
    private double hourTotal;
    private int exerciseTotal;
    
    public CourseSubmissions(Course course) {
        this.course = course;
        this.submissions = new ArrayList<>();
        this.totalExcercisePointsGained = 0;
        this.totalWorkingHours = 0;
        
        this.statsExist = false;
        this.students = 0;
        this.hourTotal = 0.0;
        this.exerciseTotal = 0;
    }

    public void addStudents(int students) {
        this.students += students;
        this.statsExist = true;
    }

    public void addHourTotal(double hourTotal) {
        this.hourTotal += hourTotal;
        this.statsExist = true;
    }

    public void addExerciseTotal(int exerciseTotal) {
        this.exerciseTotal += exerciseTotal;
        this.statsExist = true;
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

    public int getStudents() {
        return students;
    }

    public double getHourTotal() {
        return hourTotal;
    }

    public int getExerciseTotal() {
        return exerciseTotal;
    }
    
    public boolean getStatsExist() {
        return statsExist;
    }

    public void addSubmission(Submission submission) {
        this.submissions.add(submission);
        this.totalExcercisePointsGained += submission.getExercises().length;
        this.totalWorkingHours += submission.getHours();
    }
    
}
