import java.util.Random;
import java.util.Scanner;

public class Main {
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType;

    public static int docturHealth = 450;
    public static int docturhilse = 20;
    public static String hilse = " hil";

    public static int[] heroesHealth = {250, 270, 280,};
    public static int[] heroesDamage = {25, 20, 15,};
    public static String[] heroesAttack = {" Physical", " magical", " Kinetic",};
    public static int roundNumber;

    public static void main(String[] args) {
        printStatistice();
        while (!isGameFinished()) {
            playRound();
        }
    }

    public static void playRound() {
        roundNumber++;
        chooseBossDefence();
        bossHits();
        heroesHits();
        docturHil();
        printStatistice();
    }

    public static void printStatistice() {
        System.out.println("______________ ROUND " + roundNumber + "________________");
        String defence;
        /*if (bossDefenceType == null){
            defence = " no defens";
        }else {
            defence = bossDefenceType;
        }*/
        System.out.println("Boss health " + bossHealth + "; damage"
                + bossDamage + "; defence " + (bossDefenceType == null ? "; No defence" : bossDefenceType));
        System.out.println("Doctor " + docturHealth + "; hil " + docturhilse);
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttack[i] + " health " + heroesHealth[i] + "; damage ;"
                    + heroesDamage[i]);
        }


    }

    public static void docturHil() {
        for (int i = 0; i <heroesHealth.length; i++) {
            heroesHealth[i] = heroesHealth[i] + docturhilse;
        }
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttack.length);
        bossDefenceType = heroesAttack[randomIndex];

    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes wom!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0 && heroesHealth[3] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss Won!!!");
        }
        return allHeroesDead;
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
        docturHealth = docturHealth - bossDamage;
    }

    public static void heroesHits() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                int hit = heroesDamage[i];
                if (heroesAttack[i] == bossDefenceType) {
                    Random random = new Random();
                    int coeff = random.nextInt(6) + 2;
                    hit = heroesDamage[i] * coeff;
                    System.out.println(" Critical damage: " + hit);
                }
                if (bossHealth - hit < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - hit;
                }
            }
        }
    }

    public static void Sort(double[] array) {
        for (int i = 0; i < array.length; i++) {
            double min = array[i];
            int min_i = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[j];
                    min_i = j;
                }
            }
            if (i != min_i) {
                double tmp = array[i];
                array[i] = array[min_i];
                array[min_i] = tmp;
            }
        }
    }
}
