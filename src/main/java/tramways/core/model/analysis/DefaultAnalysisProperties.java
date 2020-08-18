package tramways.core.model.analysis;

public enum DefaultAnalysisProperties {
    ANALYSIS_NAME("Analysis name");

    private final String description;

    DefaultAnalysisProperties(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
