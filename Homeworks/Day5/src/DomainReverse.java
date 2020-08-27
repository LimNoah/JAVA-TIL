/**
 * 도메인 뒤집기
 * 
 * 주어진 홈페이지 주소를 .을 기준으로 각각 뒤집어 출력하시오.
 * 
 * ex) www.google.com -> www.elgoog.moc
 * 
 * 인자
 * string: 홈페이지 주소
 */

public class DomainReverse {
    public static void main(String[] args) {
        String string = "www.google.com";
        // write codes here

        StringBuilder sbResult = new StringBuilder(string.length());
        int nPos = 0;
        int nDotPos = string.indexOf('.', nPos);
        if(nDotPos > 0){
            while(nDotPos >= 0){
                StringBuilder sb = new StringBuilder(string.length());
                int nOriginPos = nPos;
                nPos = nDotPos + 1;

                sb.append(string.substring(nOriginPos, nDotPos));
                sbResult.append(sb.reverse()).append(".");
                nDotPos = string.indexOf('.', nPos);
                if(nDotPos == -1 && string.length() > nPos){
                    StringBuilder sb2 = new StringBuilder(string.length());
                    sb2.append(string.substring(nPos));
                    sbResult.append(sb2.reverse());
                }
            }
        }
        else{
            sbResult.append(string);
            sbResult.reverse();
        }

        System.out.println(sbResult);
    }
}