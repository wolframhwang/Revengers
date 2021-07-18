import java.util.*;

class Pair {
    int x;
    int y;

    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};

    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        Arrays.fill(answer,1);

        List<Pair> person;

        for(int i=0;i<5;i++) {
            person = new ArrayList<>();

            String[][] map = new String[5][5];

            for(int j=0;j<5;j++) {
                for(int k=0;k<5;k++) {
                    map[j][k] = Character.toString(places[i][j].charAt(k));
                }
            }
            //배열을 탐색하며 Pelple의 위치를 저장
            for(int j=0;j<5;j++) {
                for(int k=0;k<5;k++) {
                    if(map[j][k].equals("P")){
                        person.add(new Pair(j,k));
                    }
                }
            }

            //각 점에서 거리가 2이하인 것이 있으면 false를 반환
            for (Pair p : person) {
                Deque<Pair> dq = new ArrayDeque<>();
                boolean[][] visited = new boolean[5][5];
                dq.add(p);
                int distance = 0;
                visited[p.x][p.y] = true;

                while (!dq.isEmpty()) {
                    Pair a = dq.poll();

                    for (int k = 0; k < 4; k++) {
                        int nextX = a.x + dx[k];
                        int nextY = a.y + dy[k];

                        if (nextX < 0 || nextX >= 5 || nextY < 0 || nextY >= 5 || visited[nextX][nextY]) {
                            continue;
                        }

                        if (map[nextX][nextY].equals("X"))
                            continue;

                        if (map[nextX][nextY].equals("P")) {
                            if((Math.abs(nextX-p.x)+Math.abs(nextY-p.y))<3 && distance < 3) {
                                answer[i] = 0;
                                break;
                            }
                        }
                        visited[nextX][nextY] = true;
                        dq.add(new Pair(nextX, nextY));
                    }
                    distance++;
                }
            }
        }

        return answer;
    }
}