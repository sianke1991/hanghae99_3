import java.util.*;
public class Solution {
	
	private static int timeToInt(String time) {
		int hour = Integer.parseInt(time.substring(0, 2));
		int minute = Integer.parseInt(time.substring(3));
		return hour*60+minute;
	}
	
	private static class Job {
		public final String name;
		/**작업이 요청된 시각*/
		public final int callTime;
		/**작업 수행에 필요한 시간*/
		public final int timeRequired;
		/**최근에 작업을 시작 내지는 재개한 시각*/
		public int recentBeginTime;
		/**작업을 진행한 시간*/
		public int timeUsed;
		
		public Job(String[] arr) {
			this.name = arr[0];
			this.callTime = timeToInt(arr[1]);
			this.timeRequired = Integer.parseInt(arr[2]);
		}
		
		@Override
		public String toString() {
			return new StringBuilder()
						.append(name)
						.append(", ")
						.append(this.callTime)
						.append(", ")
						.append(this.timeRequired)
						.toString();
		}
	}
	
	public String[] solution(String[][] plans) {
		//수행을 시작한 일 Stack
		Deque<Job> jobStack = new ArrayDeque<>();
		//먼저 시작하는 일을 꺼내기 위한 Queue
		Queue<Job> jobQueue = new PriorityQueue<>(new Comparator<Job>() {
			@Override
			public int compare(Job j0, Job j1) {
				return Integer.compare(j0.callTime, j1.callTime);
			}
		});
        //완료된 순서대로 작업을 보관하는 리스트
		List<String> result = new ArrayList<>();
		
		//jobQueue에 입력받은 작업을 넣는다.
		for (String[] job:plans) {
			Job j = new Job(job);
			jobQueue.add(j);
		}
		
		while (!jobStack.isEmpty() || !jobQueue.isEmpty()) {
			//jobStack이 비어있으면 jobQueue에서 꺼내서 jobStack에 넣는다.
			if (jobStack.isEmpty()) {
				jobStack.add(jobQueue.poll());
				Job recentJob = jobStack.peekLast();
				recentJob.recentBeginTime = recentJob.callTime;
				continue;
			}
			
			//jobQueue가 비어있으면 jobStack의 일을 수행한다.
			if (jobQueue.isEmpty()) {
				while (!jobStack.isEmpty())
					result.add(jobStack.pollLast().name);
				break;
			}
			
			//스택에 최근에 추가된, 현재 진행중인 작업
			Job recentJob = jobStack.peekLast();
			//최근 작업의 예상 종료 시각
			int expectedEndTime = recentJob.recentBeginTime+(recentJob.timeRequired-recentJob.timeUsed);
			
			//jobQueue의 작업의 시작 보다 최근 작업의 끝이 먼저일 경우-
			if (jobQueue.peek().callTime>expectedEndTime) {
				result.add(jobStack.pollLast().name);
				if (!jobStack.isEmpty()) {
					recentJob = jobStack.peekLast();
					recentJob.recentBeginTime = expectedEndTime;
				}
				continue;
			}
			
			//jobQueue의 작업의 시작과 최근 작업의 끝이 같을 경우-
			if (jobQueue.peek().callTime==expectedEndTime) {
				result.add(jobStack.pollLast().name);
				jobStack.add(jobQueue.poll());
				recentJob = jobStack.peekLast();
				recentJob.recentBeginTime = expectedEndTime;
				continue;
			}
			
			//jobQueue의 작업의 시작이 최근 작업의 끝보다 먼저일 경우-
			recentJob.timeUsed += jobQueue.peek().callTime-recentJob.recentBeginTime; //recentJob의 작업 진행한 시간을 갱신한다.
			jobStack.add(jobQueue.poll());
			recentJob = jobStack.peekLast();
			recentJob.recentBeginTime = recentJob.callTime;
		} //while loop
		
        String[] resultArray = new String[result.size()];
        for (int i=0; i<resultArray.length; i++)
        	resultArray[i] = result.get(i);
        return resultArray;
    }
	
	
	public static void main(String[] args) {
		Solution sol = new Solution();
		String[] result = null;
		result = sol.solution(new String[][] {
			{"korean", "11:40", "30"}, {"english", "12:10", "20"}, {"math", "12:30", "40"}
		});
		System.out.println(Arrays.toString(result));
		
		result = sol.solution(new String[][] {
			{"science", "12:40", "50"}, {"music", "12:20", "40"}, {"history", "14:00", "30"}, {"computer", "12:30", "100"}
		});
		System.out.println(Arrays.toString(result));
		try {
			result = sol.solution(new String[][] {
				{"aaa", "12:00", "20"}, {"bbb", "12:10", "30"}, {"ccc", "12:40", "10"}
			});
			System.out.println(Arrays.toString(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		result = sol.solution(new String[][] {
			{"UmmLang", "11:00", "10"}, {"MSA", "11:08", "1"}, {"Redis", "11:10", "1"}, {"Kafka", "11:12", "1"}
		});
		System.out.println(Arrays.toString(result));
	}
}