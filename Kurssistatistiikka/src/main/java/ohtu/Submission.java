package ohtu;

public class Submission {
    
    private int week;
    private int hours;
    private int[] exercises;
    private String course;

    public void setWeek(int week) {
        this.week = week;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getHours() {
        return hours;
    }

    public int[] getExercises() {
        return exercises;
    }

    public String getCourse() {
        return course;
    }

    public int getWeek() {
        return week;
    }

    @Override
    public String toString() {
        String exercisesPrint = "";
        
        int i = 0;
        while(i < exercises.length){
            exercisesPrint += exercises[i];
            if(i < exercises.length - 1){
                exercisesPrint += ", ";
            }
            ++i;
        }
        
        return  course + 
                ", viikko " + week + 
                " tehtyj\u00e4 teht\u00e4vi\u00e4 yhteens\u00e4 " + exercises.length + 
                " aikaa kului " + hours + 
                " tehdyt teht\u00e4v\u00e4t: " + exercisesPrint;
    }
    
}