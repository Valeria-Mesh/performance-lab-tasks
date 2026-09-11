import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Task2 {

    public static void main(String[] args) throws IOException {

        String ellipseFile = args[0];
        String pointsFile = args[1];

        List<String> ellipseLines =
                Files.readAllLines(Path.of(ellipseFile));

        String[] center =
                ellipseLines.get(0).split("\\s+");

        String[] radiuses =
                ellipseLines.get(1).split("\\s+");

        BigDecimal centerX = new BigDecimal(center[0]);
        BigDecimal centerY = new BigDecimal(center[1]);

        BigDecimal radiusX = new BigDecimal(radiuses[0]);
        BigDecimal radiusY = new BigDecimal(radiuses[1]);

        BigDecimal radiusXSquared = radiusX.pow(2);
        BigDecimal radiusYSquared = radiusY.pow(2);

        BigDecimal right =
                radiusXSquared.multiply(radiusYSquared);


        List<String> pointLines =
                Files.readAllLines(Path.of(pointsFile));

        for (String line : pointLines) {

            String[] point = line.split("\\s+");

            BigDecimal x = new BigDecimal(point[0]);
            BigDecimal y = new BigDecimal(point[1]);

            BigDecimal dx = x.subtract(centerX);
            BigDecimal dy = y.subtract(centerY);

            BigDecimal left =
                    dx.pow(2).multiply(radiusYSquared)
                            .add(
                                    dy.pow(2).multiply(radiusXSquared)
                            );

            int comparison = left.compareTo(right);

            if (comparison == 0) {
                System.out.println(0);
            } else if (comparison < 0) {
                System.out.println(1);
            } else {
                System.out.println(2);
            }
        }
    }
}