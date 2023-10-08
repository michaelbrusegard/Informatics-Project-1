package workoutplanner.core;

public class Exercise {

    private String name;
    private int sets;
    private int repMin;
    private int repMax;
    private int weight;

    public Exercise(String name, int sets, int repMin, int repMax, int weight) {
        this.name = name;
        this.sets = sets;
        this.repMin = repMin;
        this.repMax = repMax;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getSets() {
        return sets;
    }

    public int getRepMin() {
        return repMin;
    }

    public int getRepMax() {
        return repMax;
    }

    public int getWeight() {
        return weight;
    }
}
