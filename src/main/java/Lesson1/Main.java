package Lesson1;

public class Main {
   public static void main(String[] args) {

      Participant[] team = new Participant[4];
      team[0] = new Cat("Барисик", 40);
      team[1] = new Cat("Сажик", 70);
      team[2] = new Cat("Рыжик", 49);
      team[3] = new Cat("Мурзик", 25);

      Team command = new Team("Котики", team);
     // Team team1 = new Team( new Object[] {"Сажик", "Сажик", "Рыжик", "Мурзик"});

      Course course1 = new Course( new int[] {20,15,40,50});

      command.printInfoTeam();
      for (int i = 0; i<4; i++) {
         course1.doIt(team[i]);
      }

   }
}
