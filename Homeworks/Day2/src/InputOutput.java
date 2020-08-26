import java.util.Scanner;

/**
 * 입력받아 처리하기
 * 
 * 3개의 정수를 입력받아, 그 중 최대값을 출력하고자 한다.
 * 이를 수행하는 프로그램을 작성하시오.
 * 
 */

public class InputOutput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int arrNum[] = new int[3];
        int nMax = 0;

        //3개의 정수 입력 받기
        System.out.print("정수를 입력하세요 : ");
        for(int i = 0; i < arrNum.length; i++)
            arrNum[i] = scanner.nextInt();

        //최대값 고르기
        for(int i = 0; i < arrNum.length; i++)
        {
               if(nMax < arrNum[i])
                   nMax = arrNum[i];
        }

        //최대값 출력
        System.out.println("최대 값 : " + nMax);



    }
}