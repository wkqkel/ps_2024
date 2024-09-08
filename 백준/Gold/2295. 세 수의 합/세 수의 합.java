import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int arr[] = new int[N];
        ArrayList<Integer> twosum = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                twosum.add(arr[i] + arr[j]);
            }
        }

        Arrays.sort(arr);
        Collections.sort(twosum);

        for (int i = N - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (Collections.binarySearch(twosum, arr[i] - arr[j]) >= 0) {
                    System.out.println(arr[i]);
                    return;
                }

            }
        }


    }
}