import java.math.BigInteger;

/**
 * 피보나치 수열
 * 
 * 피보나치 수열을 출력하는 프로그램을 작성하시오.
 * 
 * 피보나치 수열의 점화식 : f(n) = f(n-1) + f(n-2)
 *                        f(1) = 0, f(2) = 1 
 * 
 * 0, 1, 1, 2, 3, 5, 8, ...
 * 
 * 인자
 * seqLength: 출력하고자 하는 피보나치 수열의 길이
 */

public class Fibonacci {
    public static void main(String[] args) {
        int seqLength = 100;
        // write codes here
        int result = Fibo(seqLength);// 무한루프 why?
        System.out.println(result);
    }

    public static int Fibo(int seqLength){

        if(seqLength == 0)
            return 0;
        else if(seqLength == 1)
            return 1;
        else
            return Fibo(seqLength - 2) + Fibo(seqLength - 1);
    }
    // 콘솔 창에 출력 값이 나오지 않음 ( => 자료형 크기의 문제? 재귀함수 구성 문제?
}