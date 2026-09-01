# Algorithm

코딩테스트 문제 풀이와 복습 기록을 관리하는 저장소입니다.

## 운영 목표

- 매일 오전 8시에 오늘의 `Next Step` 문제를 받는다.
- 문제를 푼 뒤 풀이 코드와 학습 메모를 이 저장소에 저장한다.
- 풀이 단위로 커밋하고 GitHub에 푸시한다.
- 단순 정답 저장이 아니라, 이직 시장에서 요구하는 문제 해결력을 만들기 위해 `접근법 → 구현 → 복잡도 → 회고 → 재풀이`를 함께 기록한다.

## 권장 학습 흐름

1. 문제 읽기
2. 제약 조건에서 시간복잡도 후보 추론
3. 자료구조/알고리즘 유형 가설 세우기
4. 손으로 예제와 반례 확인
5. 코드 구현
6. 로컬 테스트
7. `README.md`에 접근법/복잡도/실수 기록
8. 커밋 및 푸시

## 디렉토리 규칙

```text
problems/
  baekjoon/
    0000-problem-title/
      Main.java
      README.md
  programmers/
    level-2-problem-title/
      Solution.java
      README.md
  leetcode/
    0000-problem-title/
      Solution.java
      README.md
roadmap/
  coding-test-roadmap.md
  review-log.md
templates/
  problem-readme.md
  JavaMainTemplate.java
```

## 커밋 메시지 규칙

```text
solve: 플랫폼 문제번호 문제명
study: 유형/개념 정리
refactor: 풀이 개선
fix: 반례/오답 수정
```

예시:

```text
solve: baekjoon 1260 DFS와 BFS
study: graph traversal basics
fix: baekjoon 1260 visited reset bug
```

## 문제 선정 전략

초기에는 Java 기준으로 진행합니다. 필요하면 Python 풀이도 병행할 수 있습니다.

- 1단계: 구현, 문자열, 배열, 해시, 정렬
- 2단계: 스택/큐/덱, 완전탐색, 재귀
- 3단계: DFS/BFS, 그래프 탐색
- 4단계: 이분탐색, 투 포인터, 슬라이딩 윈도우
- 5단계: 그리디, 우선순위 큐
- 6단계: DP 기본/응용
- 7단계: 실전 혼합 문제와 시간 제한 최적화

## 매일 기록할 것

- 오늘 문제
- 핵심 유형
- 처음 떠올린 접근
- 막힌 지점
- 최종 풀이
- 시간복잡도 / 공간복잡도
- 다음에 다시 볼 포인트

<!---LeetCode Topics Start-->
# LeetCode Topics
## Depth-First Search
|  |
| ------- |
| [0743-network-delay-time](https://github.com/mindev188/Algorithm/tree/master/0743-network-delay-time) |
## Breadth-First Search
|  |
| ------- |
| [0743-network-delay-time](https://github.com/mindev188/Algorithm/tree/master/0743-network-delay-time) |
## Graph Theory
|  |
| ------- |
| [0743-network-delay-time](https://github.com/mindev188/Algorithm/tree/master/0743-network-delay-time) |
## Heap (Priority Queue)
|  |
| ------- |
| [0743-network-delay-time](https://github.com/mindev188/Algorithm/tree/master/0743-network-delay-time) |
## Shortest Path
|  |
| ------- |
| [0743-network-delay-time](https://github.com/mindev188/Algorithm/tree/master/0743-network-delay-time) |
## Dijkstra's Algorithm
|  |
| ------- |
| [0743-network-delay-time](https://github.com/mindev188/Algorithm/tree/master/0743-network-delay-time) |
## Array
|  |
| ------- |
| [0055-jump-game](https://github.com/mindev188/Algorithm/tree/master/0055-jump-game) |
| [0064-minimum-path-sum](https://github.com/mindev188/Algorithm/tree/master/0064-minimum-path-sum) |
| [0198-house-robber](https://github.com/mindev188/Algorithm/tree/master/0198-house-robber) |
| [0213-house-robber-ii](https://github.com/mindev188/Algorithm/tree/master/0213-house-robber-ii) |
| [0452-minimum-number-of-arrows-to-burst-balloons](https://github.com/mindev188/Algorithm/tree/master/0452-minimum-number-of-arrows-to-burst-balloons) |
| [0605-can-place-flowers](https://github.com/mindev188/Algorithm/tree/master/0605-can-place-flowers) |
## Dynamic Programming
|  |
| ------- |
| [0055-jump-game](https://github.com/mindev188/Algorithm/tree/master/0055-jump-game) |
| [0064-minimum-path-sum](https://github.com/mindev188/Algorithm/tree/master/0064-minimum-path-sum) |
| [0198-house-robber](https://github.com/mindev188/Algorithm/tree/master/0198-house-robber) |
| [0213-house-robber-ii](https://github.com/mindev188/Algorithm/tree/master/0213-house-robber-ii) |
## Matrix
|  |
| ------- |
| [0064-minimum-path-sum](https://github.com/mindev188/Algorithm/tree/master/0064-minimum-path-sum) |
## Greedy
|  |
| ------- |
| [0055-jump-game](https://github.com/mindev188/Algorithm/tree/master/0055-jump-game) |
| [0452-minimum-number-of-arrows-to-burst-balloons](https://github.com/mindev188/Algorithm/tree/master/0452-minimum-number-of-arrows-to-burst-balloons) |
| [0605-can-place-flowers](https://github.com/mindev188/Algorithm/tree/master/0605-can-place-flowers) |
## Sorting
|  |
| ------- |
| [0452-minimum-number-of-arrows-to-burst-balloons](https://github.com/mindev188/Algorithm/tree/master/0452-minimum-number-of-arrows-to-burst-balloons) |
<!---LeetCode Topics End-->