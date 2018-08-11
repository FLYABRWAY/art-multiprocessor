package part1;

public class Main {

    private static final Integer AMOUNT = 10;

    public static void main(String[] args) {

        final Fork[] forks = initForks();
        final Thread[] philosophers = initPhilosophers(forks);

        start(philosophers);
    }

    private static Fork[] initForks() {
        final Fork[] forks = new Fork[AMOUNT];
        for (int i = 0; i<forks.length; i++) {
            forks[i] = new Fork(i);
        }
        return forks;
    }

    private static Thread[] initPhilosophers(final Fork[] forks) {
        final Thread[] philosophers = new Thread[AMOUNT];
        for (int i = 0; i<philosophers.length; i++) {

            if (i == 0) {
                philosophers[i] = new Thread(new Action(forks[i], forks[forks.length - 1]));
            }
            else if (i + 1 != philosophers.length) {
                philosophers[i] = new Thread(new Action(forks[i], forks[i - 1]));
            }
            else if (i + 1 == philosophers.length) {
                philosophers[i] = new Thread(new Action(forks[0], forks[i]));
            }

        }
        return philosophers;
    }

    private static void start(final Thread[] philosophers) {
        for (Thread thread : philosophers) {
            thread.start();
        }
    }

}
