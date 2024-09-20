import java.util.*;

class Solution {
    public List<String> solution(String[] exs) {
        int N = exs.length;
        ArrayList<String> list = new ArrayList<>();
        boolean ch[] = new boolean[10];

        for (int z = 2; z <= 9; z++) {
            ch[z] = true;
            boolean can = true;

            for (String ex : exs) {
                StringTokenizer st = new StringTokenizer(ex);
                String a = st.nextToken();
                st.nextToken(); // 연산자 무시
                String b = st.nextToken();
                for(char c : a.toCharArray()){
                    if(z <= Integer.valueOf(c - '0')) can = false;
                }
                
                for(char c : b.toCharArray()){
                    if(z <= Integer.valueOf(c - '0')) can = false;
                }

                
                if (!isAmbigious(ex) && !satisfy(ex, z)) {
                    can = false;
                }
            }

            if (!can) ch[z] = false;
        }

        for (int i = 0; i < N; i++) {
            if (!isAmbigious(exs[i])) continue;
            String ex = exs[i];
            String app = ex.split("= ")[0] + "= ";
            String ret = "";
            boolean can = false;

            for (int z = 2; z <= 9; z++) {
                if (!ch[z]) continue;
                try {
                    StringTokenizer st = new StringTokenizer(ex);
                    int a = Integer.parseInt(st.nextToken(), z);
                    String cal = st.nextToken();
                    int b = Integer.parseInt(st.nextToken(), z);
                    int sum = "+".equals(cal) ? a + b : a - b;
                    String tt = Integer.toString(sum, z);

                    if ("".equals(ret) || ret.equals(tt)) {
                        ret = tt;
                    } else {
                        ret = "?";
                    }
                    can = true;
                } catch (NumberFormatException e) {
                    // 예외 발생 시 무시
                }
            }
            if (can) list.add(app + ret);
        }

        return list;
    }

    boolean satisfy(String ex, int z) {
        boolean can = true;
        try {
            StringTokenizer st = new StringTokenizer(ex);
            int a = Integer.parseInt(st.nextToken(), z);
            String cal = st.nextToken();
            int b = Integer.parseInt(st.nextToken(), z);

            int sum = "+".equals(cal) ? a + b : a - b;
            st.nextToken(); // "=" 무시
            String ret = st.nextToken();
            if (!ret.equals(Integer.toString(sum, z))) can = false;
        } catch (NumberFormatException e) {
            can = false;
        }

        return can;
    }

    boolean isAmbigious(String ex) {
        return ex.charAt(ex.length() - 1) == 'X';
    }

    int cmp(String s1, String s2) {
        return s1.charAt(s1.length() - 1) - s2.charAt(s2.length() - 1);
    }
}
