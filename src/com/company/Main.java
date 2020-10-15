package com.company;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
public class Main {
    static List<Integer> checkAvailable(int x, int y, int[][] map, boolean[][] visited){
        List<Integer> tmpList = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            tmpList.add(i);
        }
        if(y - 2 < 0){
            tmpList.remove(tmpList.indexOf(0));
        } else if(visited[x][y - 2]){
            tmpList.remove(tmpList.indexOf(0));
        }
        if(y + 2 > map[0].length){
            tmpList.remove(tmpList.indexOf(1));
        } else if(visited[x][y + 2]){
            tmpList.remove(tmpList.indexOf(1));
        }
        if(x - 2 < 0){
            tmpList.remove(tmpList.indexOf(2));
        } else if(visited[x - 2][y]){
            tmpList.remove(tmpList.indexOf(2));
        }
        if(x + 2 > map.length){
            tmpList.remove(tmpList.indexOf(3));
        } else if(visited[x + 2][y]){
            tmpList.remove(tmpList.indexOf(3));
        }
        return tmpList;
    }
    static void DFS(int[][] map, int startPointX, int startPointY, int endPointX, int endPointY, boolean[][] visited){
        // 해당 포인트에 방문했는지 확인 / 해당 지점이 종료지점인지 확인
//        if(visited[startPointX][startPointY])
        if(visited[startPointX][startPointY] || (startPointX == endPointX && startPointY == endPointY) ){
            return;
        }
        visited[startPointX][startPointY] = true;
        List<Integer> availablePoints = new ArrayList<>();
        Random random = new Random();
        // 4 방향 중에 현재 좌표에서 갈 수 있는 방향들을 저장한 List
        availablePoints = checkAvailable(startPointX, startPointY, map, visited);
        // 4 방향 중에 갈 곳이 없어질 때까지 반복
        // 0 = Left / 1 = Right / 2 = Up / 3 = Down
        while(availablePoints.size() > 0){
            int nextPointX = startPointX;
            int nextPointY = startPointY;
            int nDir = 0;
            int randomIdx = random.nextInt(availablePoints.size());
            nDir = availablePoints.get(randomIdx);
            switch (nDir){
                case 0:
                    nextPointY = startPointY - 2;
                    if(!visited[nextPointX][nextPointY]){
                        map[nextPointX][nextPointY + 1] = 1;
                    }
                    availablePoints.remove(randomIdx);
                    DFS(map, nextPointX, nextPointY, endPointX, endPointY, visited);
                    break;
                case 1:
                    nextPointY = startPointY + 2;
                    if(!visited[nextPointX][nextPointY]){
                        map[nextPointX][nextPointY - 1] = 1;
                    }
                    availablePoints.remove(randomIdx);
                    DFS(map, nextPointX, nextPointY, endPointX, endPointY, visited);
                    break;
                case 2:
                    nextPointX = startPointX - 2;
                    if(!visited[nextPointX][nextPointY]) {
                        map[nextPointX + 1][nextPointY] = 1;
                    }
                    availablePoints.remove(randomIdx);
                    DFS(map, nextPointX, nextPointY, endPointX, endPointY, visited);
                    break;
                case 3:
                    nextPointX = startPointX + 2;
                    if(!visited[nextPointX][nextPointY]){
                        map[nextPointX - 1][nextPointY] = 1;
                    }
                    availablePoints.remove(randomIdx);
                    DFS(map, nextPointX, nextPointY, endPointX, endPointY, visited);
                    break;
            }
        }
    }
    public static void main(String[] args) {
        int nX = 0;     // 테두리 구간 제외한 미로의 세로 길이
        int nY = 0;     // 테두리 구간 제외한 미로의 가로 길이
        int nAllX = 0;  // 테두리 구간 포함한 미로의 세로 길이
        int nAllY = 0;  // 테두리 구간 포함한 미로의 가로 길이
        int nStartX = 0;// 시작 지점 Y좌표
        int nStartY = 0;// 시작 지점 X좌표
        int nEndX = 0;  // 도착 지점 Y좌표
        int nEndY = 0;  // 도착 지점 X좌표
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        //맵 크기 입력
        System.out.print("가로 길이를 입력하세요 : ");
        nY = sc.nextInt();
        nAllY = (nY * 2) - 1;
        System.out.print("세로 길이를 입력하세요 : ");
        nX = sc.nextInt();
        nAllX = (nX * 2) - 1;
        int[][] map = new int[nAllX][nAllY];
        boolean[][] visited = new boolean[nAllX][nAllY];
        // 테두리 구간 채우기
        // 0 => 막혀있는 곳 / 1 => 뚫려있는 곳
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if(i % 2 != 0 || j % 2 != 0){
                    map[i][j] = 0;
                }else {
                    map[i][j] = 1;
                }
            }
        }
        //시작, 도착 지점 좌표 랜덤 생성.
        do {                                                                                // 시작지점
            nStartX = random.nextInt(nAllX);
            nStartY = random.nextInt(nAllY);
        } while((nStartX % 2 != 0) || (nStartY % 2 != 0));
        do {                                                                                //도착지점
            nEndX = random.nextInt(nAllX);
            nEndY = random.nextInt(nAllY);
        } while(((nStartX == nEndX) && (nStartY == nEndY)) ||                               // 시작지점과 종료지점이 같을때
                ((nEndX % 2 != 0) || (nEndY % 2 != 0)) ||                                   // 테두리 지점일 때
                !(nEndX == 0 || nEndX == nAllX - 1 || nEndY == 0 || nEndY == nAllY - 1));    // 모퉁이 부분이 아닐 때
        // 길을 찾으면서 지나가는 막힌 부분 뚫기
        DFS(map, nStartX, nStartY, nEndX, nEndY,visited);
        //map 출력
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
//        System.out.println("가로 : " + nY + ", 세로 : " + nX);
    }
}