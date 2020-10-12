import java.util.ArrayList;
import java.util.List;

// The priority queue from scratch
// Sort and move the jobs in the list based on priority
public class PriorityQueue {
   private List<Job> queuedJobs;


   public PriorityQueue() {
       this.queuedJobs = new ArrayList<Job>();
   }
    // Add a new job to the queue
   public void enqueue(Job newJob) {
       // If the queue is empty, just add it to the queue
       if (queuedJobs.isEmpty()) {
           queuedJobs.add(newJob);
           return;
       }
        // Loop through the queued jobs and sort them by highest priority
       for (int i = 0; i < queuedJobs.size(); i++ ) {
           // once the priority is greater than the current job, pause that job and work of the highest priority job
           if (newJob.getPriority() <= queuedJobs.get(i).getPriority()) {
               queuedJobs.add(i, newJob);
               return;
           }
       }
        // if the priority is of least priority, add the end of the queue
       if (newJob.getPriority() > queuedJobs.get(queuedJobs.size() -1).getPriority()) {
           queuedJobs.add(newJob);
       }
   }

   // Remove the highest priority job from the queue
   public Job dequeue() {
       return queuedJobs.remove(0);
   }

   // Set the size of the priority queue
   public int size() {
       return queuedJobs.size();
   }

}