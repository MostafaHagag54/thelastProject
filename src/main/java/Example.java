import java.text.SimpleDateFormat;
import java.util.Date;

public class Example {


    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
        String dateAsString = simpleDateFormat.format(new Date());
        System.out.println(dateAsString);
    }
}
