public class Dog extends Animal{
    private String sound;
    @Override
    public String getSound() {
        return sound;
    }

    @Override
    public void setSound(String sound) {
        this.sound = sound;
    }
}
