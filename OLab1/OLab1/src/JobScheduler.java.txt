import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JobScheduler {


    // Pause function for the program, to wait 1 sec before continuing with the program
    // to speed up the program I used 1 sec = 1 ms :)
    public static void pause(int sec) {
        try {
            Thread.sleep(sec);
        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }
    }
    // Read in the file
    public static void main(String[] args) {
        Scanner input;
        try {
            input = new Scanner(new File(args[0]));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found!");
            return;
        }

        List<Job> jobList = new ArrayList<Job>();

        while (input.hasNextLine()) {
            String line = input.nextLine();

            String[] lineArray = line.split(" ");
            int[] intLineArray = new int[lineArray.length];

            for (int i = 0; i < lineArray.length; i++) {
                intLineArray[i] = Integer.parseInt(lineArray[i]);
                if (intLineArray[i] < 0) {
                    System.out.println("Positive integers only");
                    return;
                }
            }
            // If there are not 4 elements in the array run error message
            if (intLineArray.length != 4) {
                System.out.println("Incorrect length for Job");
                continue;
            }

            Job myJob = new Job(intLineArray[0], intLineArray[1], intLineArray[2], intLineArray[3]);

            jobList.add(myJob);
        }

        // lambda function comparator :)
        jobList.sort((d1, d2) -> {
            return d1.getArrivalTimeSec() - d2.getArrivalTimeSec();
        });



        PriorityQueue queue = new PriorityQueue();

        // Start the timer at 0, and count
        int currentTime = 0;
        while(true) {
           // Check if jobs need to be added to the queue
            for (Job job : jobList) {
                // Every second, check if the arrival time is the same as the current time and run for one sec
                if (job.getArrivalTimeSec() == currentTime) {
                    queue.enqueue(job);
                }
            }
            // Once all jobs are completed in the queue and the job list, the simulation with be done
            if (queue.size() == 0 && jobList.get(jobList.size()-1).getArrivalTimeSec() < currentTime) {
                System.out.println("Simulation Complete!");
                return;
            }

            if (queue.size() == 0) {
                currentTime++;
                System.out.printf("Current Time: %d\n", currentTime);
                pause(1);
                continue;
            }

            // Once the highest priority job is done, do the next highest priority job

            Job currentJob = queue.dequeue();
           if (currentJob.getDurationSec() == 1) {
               System.out.println(currentJob + " --------------------Job Complete");
           } else {
               // Put the current job back in the queue and pause for another second
               System.out.println(currentJob);
               currentJob.setDurationSec(currentJob.getDurationSec() - 1);
               queue.enqueue(currentJob);
           }

           pause(1);
           currentTime++;
           System.out.printf("Current Time: %d, ", currentTime);
        }


    }



}