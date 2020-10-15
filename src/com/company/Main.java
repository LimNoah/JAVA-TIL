package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static void DFS(String[][] map, int startPointX, int startPointY, boolean[][] visited){
        boolean isAvailable = false;

        //해당 포인트에 방문했는지 확인
        if(visited[startPointX][startPointY]){
            return;
        }

        visited[startPointX][startPointY] = true;

        //출력


        //랜덤으로 방향을 정함. 0 = 왼쪽 / 1 = 오른쪽 / 2 = 위 / 3 = 아래
        Random random = new Random();
        int nDirection = random.nextInt(4);
        // 정한 방향이 갈 수 있는 곳인지 확인
        while(!isAvailable) {
            switch (nDirection) {
                case 0:
                    if(startPointY >= 0){
                        isAvailable = true;
                    }
                    break;
                case 1:
                    if(startPointY <= map[0].length){
                        isAvailable = true;
                    }
                    break;
                case 2:
                    if(startPointX >= 0){
                        isAvailable = true;
                    }
                    break;
                case 3:
                    if(startPointX <= map.length){
                        isAvailable = true;
                    }
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

        String[][] map = new String[nAllX][nAllY];
        boolean[][] visited = new boolean[nAllX][nAllY];
        // 테두리 구간 채우기
        // □ => 막혀있는 곳 / ■ => 뚫려있는 곳
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if(i % 2 != 0 || j % 2 != 0){
                    map[i][j] = "□";
                }else {
                    map[i][j] = "■";
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
                (nEndX != 0 && nEndX != nAllX - 1 && nEndY != 0 && nEndY == nAllY - 1));    // 모퉁이 부분이 아닐 때

        // 길을 찾으면서(DFS) 지나가는 막힌 부분 뚫기
        DFS(map, nStartX, nStartY,visited);
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