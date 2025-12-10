import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

class Timer extends Thread {
    private AtomicBoolean inGame;
    private AtomicInteger timeLeft;

    public Timer(AtomicBoolean inGame, int seconds) {
        this.inGame = inGame;
        this.timeLeft = new AtomicInteger(seconds);
        setDaemon(true);
    }

    @Override
    public void run() {
        try {
            while (inGame.get() && timeLeft.get() > 0) {
                sleep(1000);
                int time = timeLeft.decrementAndGet();

                if (time % 60 == 0 && time > 0) {
                    System.out.printf("\nTime left: %02d:%02d%n", time / 60, 0);
                }

                if (time <= 0) {
                    System.out.println("\n\nFINISH!");
                    inGame.set(false);
                }
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
