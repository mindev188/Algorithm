import java.util.PriorityQueue;
class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;

        PriorityQueue<Job> queue = new PriorityQueue<>((a, b) -> {
            return a.time - b.time;
        });

        for (int i = 0; i < jobs.length; i++) {
            int[] arrayJob = jobs[i];
            Job job = new Job(i, arrayJob[0], arrayJob[1]);
            queue.add(job);
        }

        /* 작업 대기 공간 */
        PriorityQueue<Job> runningQueue = new PriorityQueue<>((a, b) -> {
            if (a.runningTime == b.runningTime) {
                if (a.time == b.time) {
                    return a.num - b.num;
                }
                return a.time - b.time;
            }
            return a.runningTime - b.runningTime;
        });

        int sec = 0;
        while (!queue.isEmpty() && queue.peek().time <= sec) {
            runningQueue.add(queue.poll());
        }
        
        /**
         * runningQueue에 공백이 생길 경우 nullExc
         */
        while (!queue.isEmpty() || !runningQueue.isEmpty()) {
            if (!runningQueue.isEmpty()) {
                Job job = runningQueue.poll();

                sec += job.runningTime;
                answer += sec - job.time;
            } else {
                sec++;
            }
            
            while (!queue.isEmpty() && queue.peek().time <= sec) {
                runningQueue.add(queue.poll());
            }
        }

        return answer / jobs.length;
    }

    class Job {
        int num = -1;
        int time = -1;
        int runningTime = -1;

        Job(int num, int time, int runningTime) {
            this.num = num;
            this.time = time;
            this.runningTime = runningTime;
        }
    }
}