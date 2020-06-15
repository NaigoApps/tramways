package tramways.core.model.analysis.availability;

public enum LaneType {
    CAR("Car"), TRAM("Tram");

    private final String description;

    LaneType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
