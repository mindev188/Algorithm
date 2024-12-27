import java.util.Arrays;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        mData[] arr = new mData[N];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new mData(i, Integer.parseInt(br.readLine()));
        }

        // nlogn 정렬
        Arrays.sort(arr);

        /**
         * 각 mData에 정렬된 인덱스와 현재 인덱스 값을 비교해
         * 기존 인덱스 - 현재 인덱스 값이 가장 큰값이(가장 많이 왼쪽으로 이동한 값)
         * 총 버블정렬한 횟수 이므로 해당 값에 + 1을 하여 반환한다ㅣ
         */
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i].index - i) {
                max = arr[i].index - i;
            }
        }

        System.out.println(max + 1);
    }
}

class mData implements Comparable<mData>{
    int index;
    int value;

    public mData(int index, int value) {
        super();
        this.index = index;
        this.value = value;
    }

    /**
     * value 기준 오름차순 정렬
     */
    @Override
    public int compareTo(mData o) {
        return this.value - o.value;
    }
}