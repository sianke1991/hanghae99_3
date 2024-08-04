/*
바위 사이의 거리의 최소치에 따라 제거해야 할 바위의 개수가 달라진다.
예: 출발 지점0, 바위의 위치: [2, 11, 14, 17, 21], 도착 지점 25

바위 사이의 거리의 최소치를 5라고 한다면
제거해야 할 바위: 2, 14, 21 => 바위 (최소)3 개를 제거한다.

바위 사이의 거리의 최소치를 4라고 한다면
제거해야 할 바위: 2, 14 => 바위 (최소)2 개를 제거한다.

바위 사이의 거리의 최소치를 3이라고 한다면
제거해야 할 바위: 2 => 바위 (최소)1 개를 제거한다.

따라서 바위 사이의 거리의 최소치를 다양하게 두고 그것에 대해 제거해야 할 바위의 개수를 헤아릴 수 있다.
제거해야 할 바위의 개수를 헤아릴 때는
[유지할 수 있는 바위의 개수를 헤아리는 방법]을 사용할 수도 있다.

출발 지점0, 바위의 위치: [2, 11, 14, 17, 21], 도착 지점 25
바위 사이의 거리의 최소치가 4라고 한다면
출발 지점0에서 오른쪽으로 4 이상 떨어져 있는 가장 왼쪽에 있는 바위 11 유지
바위 위치11에서 오른쪽으로 4 이상 떨어져 있는 가장 왼쪽에 있는 바위 17 유지
바위 위치17에서 오른쪽으로 4 이상 떨어져 있는 가장 왼쪽에 있는 바위 21 유지
바위 위치21에서 오른쪽으로 4 이상 떨어져 있는 가장 왼쪽에 있는 바위 없음 => 반복 종료
총 바위 5개 중 3개를 유지하므로 바위 2개 제거

* 유지한 마지막 바위가 도착 지점과 4 미만 떨어져 있으면 마지막 유지된 바위는 제거해야 한다.
출발 지점0, 바위의 위치: [2, 11, 14, 17, 21], 도착 지점 25
바위 사이의 거리의 최소치가 7이라고 한다면
출발 지점0에서 오른쪽으로 7 이상 떨어져 있는 가장 왼쪽에 있는 바위 11 유지
출발 지점11에서 오른쪽으로 7 이상 떨어져 있는 가장 왼쪽에 있는 바위 21 유지
바위 위치21에서 오른쪽으로 7 이상 떨어져 있는 가장 왼쪽에 있는 바위 없음
하지만 마지막으로 유지된 바위21은 도착 지점25와 7미만으로 떨여져 있으므로 이 바위도 제거되어야 한다.
총 바위 5개 중 1개를 유지하므로 바위 4개 제거
 */

class Solution {
	private int searchElemAtLeast(int target, int[] array) {
		int leftIndex=0, rightIndex=array.length-1, midIndex=0;
		int result = -1;
		while (leftIndex<=rightIndex) {
			midIndex = (leftIndex+rightIndex)/2;
			if (array[midIndex]>=target) {
				result = midIndex;
				rightIndex = midIndex-1;
			} else {
				leftIndex = midIndex+1;
			}
		}
		return result;
	}
	
	private int searchElemAtLeast(int target, int[] array, int leftIndex) {
		int rightIndex=array.length-1, midIndex=0;
		int result = -1;
		while (leftIndex<=rightIndex) {
			midIndex = (leftIndex+rightIndex)/2;
			if (array[midIndex]>=target) {
				result = midIndex;
				rightIndex = midIndex-1;
			} else {
				leftIndex = midIndex+1;
			}
		}
		return result;
	}
	
	int[] rocks;
	int distance;
	
    /**
     * 바위 간 최소 거리를 입력받아,
     * 제거해야 할 바위의 개수를 반환한다.
     */
	private int countRemovedRocks(int minDistance) {
		int numRemainingRocks = 0;
        //마지막으로 유지하는 바위의 인덱스: 이 바위가 도착 지점과 충분한 거리에 떨어져 있지 않으면 이 바위도 제거해야 한다.
		int previousPositionIndex = -1;
		int currentPositionIndex = searchElemAtLeast(0+minDistance, rocks);
		while (currentPositionIndex>=0) {
			numRemainingRocks++;
			previousPositionIndex = currentPositionIndex;
			currentPositionIndex = searchElemAtLeast(rocks[currentPositionIndex]+minDistance, rocks, currentPositionIndex);
		} //while loop
        //마지막으로 유지한 바위가 도착 지점과 충분한 거리에 떨어져 있지 않은 경우 이를 제거하는 로직
		if (previousPositionIndex>=0 && distance-rocks[previousPositionIndex]<minDistance)
			numRemainingRocks--;
		return rocks.length-numRemainingRocks;
	}
	
	public int solution(int distance, int[] rocks, int n) {
        //step a. 바위의 위치를 정렬한다.
		java.util.Arrays.sort(rocks);
		this.rocks = rocks;
		this.distance = distance;
		//step b. 바위 간 거리의 최소치에 대해 이진 탐색을 수행한다.
        //NOTE: 이진 탐색의 하한(바위 간 거리의 최소치)을 1로 둔다.
        //바위 간 거리의 최소치를 0으로 두면 제거해야 할 바위의 개수를 구할 때 무한 루프에 빠질 수 있다. 
		int leftIndex=1, rightIndex=distance, midIndex=0;
		int result=-1, numRemovedRocks;
		while (leftIndex<=rightIndex) {
			midIndex=(leftIndex+rightIndex)/2;
			numRemovedRocks = countRemovedRocks(midIndex);
			if (numRemovedRocks<=n) {
				result = midIndex;
				leftIndex = midIndex+1;
			} else {
				rightIndex = midIndex-1;
			}
		}
		
		return result;
    }
}