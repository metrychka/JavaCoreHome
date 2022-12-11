package Lesson1;

public class Team {
    private String nameTeam; //название команды
    private Participant[] participants = new Participant[4]; //массив участников

  // private String[] teamMember= new String[4];

    public Team(String nameTeam) {
        this.nameTeam = nameTeam;
    }

   public Team(String nameTeam, Participant[] participants) {
    this.nameTeam = nameTeam;
    this.participants = participants;
    }


    public String getNameTeam() {
        return nameTeam;
    }

    public void setNameTeam(String nameTeam) {

        this.nameTeam = nameTeam;
    }

    public Object[] getTeamMember() {
        return participants;
    }

    public void setTeamMember(Participant[] participants) {
        this.participants = participants;
    }
    
    public void printInfoTeam() {
        System.out.println("Команда " + this.nameTeam);
        for (Participant participant : participants) {
            System.out.println("Котик " + participant.getName());
        }

        }

    public void doIt(Course obstacle) {
        for (Participant participant : participants) {
        obstacle.doIt(participant);
        }
    }
            @Override
            public String toString () {
                return super.toString();
            }


}

