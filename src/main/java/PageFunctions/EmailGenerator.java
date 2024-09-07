package PageFunctions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class EmailGenerator {

    private static final String[] NAMES = {
            "John", "Jane", "Alex", "Emily", "Michael", "Sarah",
            "David", "Anna", "Chris", "Laura", "Robert", "Sophia"
    };
    private static final String DOMAIN = "@gmail.com";

    public static void main(String[] args) {
        System.out.println(generateRandomEmail());
    }

    public static String generateRandomEmail() {
        String name = getRandomName();
        String timestamp = getCurrentTimestamp();
        return name.toLowerCase() + timestamp + DOMAIN;
    }

    private static String getRandomName() {
        Random random = new Random();
        int index = random.nextInt(NAMES.length);
        return NAMES[index];
    }

    private static String getCurrentTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("mmss");
        return dateFormat.format(new Date());
    }

    public static String getRandomNme() {
        Random random = new Random();
        String name = NAMES[random.nextInt(NAMES.length)];
        return name;
    }

}
