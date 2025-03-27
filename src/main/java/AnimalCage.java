public class AnimalCage {
    private final Animal animal;
    private final Timer timer;


    public AnimalCage (Animal animal, Timer timer) {
        this.animal = animal;
        this.timer = timer;
    }
    public void sound(){
        System.out.println(animal.getSound());
        System.out.println(timer.getTime());
    }
}

