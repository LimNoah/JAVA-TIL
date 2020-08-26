/**
 * 369게임+
 * 
 * 369게임에 참여했을 때, 내가 외쳐야 하는 숫자나 동작을 순서대로 출력하시오.
 * 
 * 규칙
 * 1. 3, 6, 9, 5가 포함되지 않은 숫자는 숫자를 외친다. (2!)
 * 2. 숫자에 포함된 3, 6, 9의 개수 만큼 박수를 친다. (짝!)
 * 3. 숫자에 포함된 5의 개수 만큼 발을 구른다. (쿵!)
 * 4. 박수와 발구르기의 순서는 관계 없으나, 반드시 번갈아 수행한다.
 * 
 * 예시
 * 2   -> 2!
 * 33  -> 짝!짝!
 * 553 -> 쿵!짝!쿵! (짝!쿵!쿵!x)
 * 47  -> 47!
 * 
 * 인자
 * gameLength: 게임이 종료되는 데 걸리는 턴 수
 * numPeople: 게임에 참여하는 인원 수
 * myTurn: 내가 숫자를 외치는 순번
 */

public class ThreeSixNineAdvanced {
    public static void main(String[] args) {
        int gameLength = 1000;
        int numPeople = 14;
        int myTurn = 2;
        // write codes here

        for(int i = 1; i <= gameLength; i++)
        {
            if(i % numPeople == myTurn )
            {
                String sTmp = "";
                sTmp = Integer.toString(i);
                int nClap = 0;
                int nClonk = 0;
                for(int j = 0; j < sTmp.length(); j++)
                {
                    if(sTmp.charAt(j) == '3' || sTmp.charAt(j) == '6' || sTmp.charAt(j) == '9')
                        nClap++;
                    else if(sTmp.charAt(j) == '5')
                        nClonk++;
                }
                if(nClap > 0 || nClonk > 0){
                    if(nClap == 0){
                        for (int k = 0; k < nClonk; k++)
                            System.out.print("쿵!");
                    }
                    else if(nClonk == 0){
                        for (int k = 0; k < nClap; k++)
                            System.out.print("짝!");
                    }
                    else{
                        if(nClap > nClonk){
                            String sJjakKoong = "짝!";
                            for(int k = 0; k < nClonk; k++)
                                sJjakKoong += "쿵!짝!";
                            System.out.print(sJjakKoong);
                        }
                        else if(nClap < nClonk){
                            String sJjakKoong = "쿵!";
                            for(int k = 0; k < nClap; k++)
                                sJjakKoong += "짝!쿵!";
                            System.out.print(sJjakKoong);
                        }
                        else {
                            for(int k = 0; k < nClap; k++)
                                System.out.print("짝!쿵!");
                        }
                    }
                    System.out.println();
                }
                else
                    System.out.println(i);
            }
        }
    }
}