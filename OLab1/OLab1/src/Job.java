
// the driver class
public class Job{
    //<job#><priority><arrivalTime(sec)><duration(sec)>

    // fields
    private int jobNumber;
    private int priority;
    private int arrivalTimeSec;
    private int durationSec;

    //constructor
    public Job(int job, int priority, int arrivalTime, int duration) {
        this.jobNumber = job;
        this.priority = priority;
        this.arrivalTimeSec = arrivalTime;
        this.durationSec = duration;
    }
    // methods
        // getters
    public int getJobNumber() {
        return jobNumber;
    }
    public int getPriority() {
        return priority;
    }

    public int getArrivalTimeSec() {
        return arrivalTimeSec;
    }

    public int getDurationSec() {
        return durationSec;
    }

    // setter
    public void setDurationSec(int newDuration) {
        this.durationSec = newDuration;
    }

    // format the print statement for our program
    @Override
    public String toString() {
        return String.format("Job #: %d Priority: %d Arrival: %d Duration: %d", jobNumber, priority, arrivalTimeSec, durationSec);
    }
}


