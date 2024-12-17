import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    // 만들 수 있는 DNA 종류의 수
    static int answer;

    /*
    * 부문문자열 최소 필수 포함 조건
    * 0 : A
    * 1 : C
    * 2 : G
    * 3 : T
    */ 
    static int[] conditions;
        
    /**
     * 현재 포함된 조건값
     * 0 : A
     * 1 : C
     * 2 : G
     * 3 : T
     */
    static int[] properties;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken()); // DNA 문자열 길이
        int P = Integer.parseInt(st.nextToken()); // 비밀번호로 사용할 문자열 길이
        String DNA = br.readLine(); // 전체 DNA
        answer = 0;
        conditions = new int[4];
        properties = new int[4];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            conditions[i] = Integer.parseInt(st.nextToken());
        }

        // 0 부터 P까지의 DNA properties를 구한다.
        for (int i = 0; i < P; i++) {
            char a = DNA.charAt(i);
            add(a);
        }
        check();

        // DNA 의 0 부터 n - P까지 반복하며 만들 수 있는 DNA 종류의 수를 구한다.
        // 각 자리수에 맞는 조건이 일치하면 answer++;
        for (int i = 0; i < S - P; i++) {
            remove(DNA.charAt(i));
            add(DNA.charAt(i+P));
            // 해당 값이 조건과 일치하는지 확인
            check();
        }

        System.out.println(answer);
    }

    /**
     * a값에 따라 properties 값 추가
     * @param a
     */
    public static void add(char a) {
        switch(a) {
            case 'A':
                properties[0]++;
                break;
            case 'C':
                properties[1]++;
                break;
            case 'G':
                properties[2]++;
                break;
            case 'T':
                properties[3]++;
                break;
        }
    }

    /**
     * a값에 따라 properties 값 제거
     * @param a
     */
    public static void remove(char a) {
        switch(a) {
            case 'A':
                properties[0]--;
                break;
            case 'C':
                properties[1]--;
                break;
            case 'G':
                properties[2]--;
                break;
            case 'T':
                properties[3]--;
                break;
        }

    }

    /**
     * 두 값을 비교해 하나라도 properties의 원소값이 conditions보다 작다면 return
     * 모든 원소값이 같거나 큰 경우 Answer++;
     */
    public static void check() {
        for (int j = 0; j < conditions.length; j++) {
            if (conditions[j] > properties[j]) {
                return;
            }
        }
        answer++;
    }
}
