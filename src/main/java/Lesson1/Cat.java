package Lesson1;

public class Cat implements Participant {
    private String name;
    private int maxJump;

    public Cat(String name, int maxJump) {
        this.name = name;
        this.maxJump = maxJump;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int jump(int height) {
        if (height <= maxJump) {
            return 1;
        } else {
            return 2;
        }

    }

}
