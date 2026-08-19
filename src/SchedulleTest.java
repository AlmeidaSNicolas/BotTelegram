import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchedulleTest {
    public static void main(String[] args) {

        ScheduledExecutorService schedule = Executors.newScheduledThreadPool(1);

        schedule.scheduleWithFixedDelay(() -> {
            try {
                System.out.println("funcionando");
            } catch (Exception e) {
                System.err.println("Erro: " + e.getMessage());
            }
        }, 0, 2, TimeUnit.HOURS);

        System.out.println(schedule);
        schedule.shutdown();

    }
}
