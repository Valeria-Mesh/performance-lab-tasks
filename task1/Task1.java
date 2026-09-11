public class Task1 {

    public static void main(String[] args) {
        int n1 = Integer.parseInt(args[0]);
        int m1 = Integer.parseInt(args[1]);

        int n2 = Integer.parseInt(args[2]);
        int m2 = Integer.parseInt(args[3]);

        String path1 = buildPath(n1, m1);
        String path2 = buildPath(n2, m2);

        System.out.print(path1 + path2);
    }

    public static String buildPath(int n, int m) {
        StringBuilder path = new StringBuilder();

        int current = 0;

        do {
            path.append(current + 1);
            current = (current + m - 1) % n;
        } while (current != 0);

        return path.toString();
    }
}