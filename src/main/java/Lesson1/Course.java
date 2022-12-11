package Lesson1;

public class Course {
    private  int[] obstacle = new int[4];


    public Course(int [] obstacle) {
        this.obstacle = obstacle;
    }

    public void doIt(Participant participant)
    {
        for (int i=0; i<4; i++)
            {
                if (participant.jump(obstacle[i])==1) {
                    System.out.println("кот " + participant.getName() + " успешно перепрыгнул стену " + (i + 1));
                } else {
                    System.out.println("кот " + participant.getName() + " не перепрыгнул стену " + (i + 1) + " и не прошел полосу" );
                    i++;
                }
            }

        }

    }

