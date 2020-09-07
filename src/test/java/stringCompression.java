public class stringCompression {

    public static void main(String[] args) {

        String str = "aabcccccaaa";
        StringBuilder sb = new StringBuilder();

        char[] arr = str.toCharArray();
        char preChar = ' ';
        int count = 0;
        int i = 0;
        for (char ch : arr) {
            i += 1;
            if (ch == preChar) {
                count += 1;
            } else {
                if (count > 1) {
                    sb.append(count);
                    sb.append(ch);
                    count = 1;
                } else {
                    count = 1;
                    if (preChar != ' ' && preChar != ch) {
                        sb.append(1);
                    }
                    sb.append(ch);
                }
            }
            if (arr.length == i) {
                sb.append(count);
            }
            preChar = ch;
        }
        System.out.println(sb.toString());
    }

}