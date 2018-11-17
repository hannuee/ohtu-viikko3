/**
 *
 * @author Hannu
 */
package ohtu;

public class Course implements Comparable<Course> {
    
    private String id;
    private String name;
    private String url;
    private int week;
    private boolean enabled;
    private String term;
    private int year;
    private String fullName;
    private int[] exercises;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public int getWeek() {
        return week;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getTerm() {
        return term;
    }

    public int getYear() {
        return year;
    }

    public String getFullName() {
        return fullName;
    }

    public int[] getExercises() {
        return exercises;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }
    
    public int calculateTotalExercisePoints() {
        int total = 0;
        for(int exercise : exercises) {
            total += exercise;
        }
        return total;
    }
    
    @Override
    public int compareTo(Course course) {
        return this.name.compareTo(course.name);
    }
    
}
