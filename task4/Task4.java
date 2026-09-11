import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Task4 {

    public static void main(String[] args) throws IOException {

        String content = Files.readString(Path.of(args[0])).trim();

        int[] nums = Arrays.stream(content.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        Arrays.sort(nums);

        int median = nums[nums.length / 2];

        long moves = 0;

        for (int num : nums) {
            moves += Math.abs((long) num - median);
        }

        if (moves <= 20) {
            System.out.println(moves);
        } else {
            System.out.println(
                    "20 ходов недостаточно для приведения всех элементов массива к одному числу"
            );
        }
    }
}