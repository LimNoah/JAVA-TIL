import java.util.ArrayList;

/**
 * 람다식을 이용하여 알고리즘 정답을 테스트하는 프로그램을 작성하시오.
 *
 * 주어진 interface와 실행 메소드를 이용하여 알고리즘 정답을 테스트하는 프로그램을 작성하시오.
 * 이 테스트 방식을 이용하여 실제 알고리즘 문제를 하나 이상 풀이하시오.
 *
 */

@FunctionalInterface
interface Solution<T, R> {
    R solve(T t);
}

class Algorithm<T, R> {
    boolean test(T input, R groundtruth, Solution<? super T, ? super R> solution) {
        // TODO: solution을 실행하고, 이것이 정답(groundtruth)와 일치하는지 테스트하여 일치 여부를 출력.
        return groundtruth == solution.solve(input);
    }
}

public class LambdaExpressions {
    public static void main(String[] args) {
        // TODO: test를 이용하여 알고리즘 문제를 하나 이상 풀이하고 테스트 결과를 출력하시오.

        Algorithm<Integer, Boolean> algo = new Algorithm<>();

        int[] inputs = { 5, 2 ,6, 1, 7, 8, 4, 29, 10};
        boolean[] results = {false, true, true, false, false, true, true, false, true};

        for(int i = 0; i < inputs.length; i++){
            boolean test = false;
            if(!algo.test(inputs[i], results[i], (x) -> {
                return x % 2 == 0;
            })){
                System.out.println("Test Failed.");
                return;
            }
        }
        System.out.println("Test Success.");
    }
}
