package tramways.core.model.analysis;

import java.util.List;
import tramways.core.model.properties.Properties;
import tramways.core.model.properties.builder.PropertyBuilder;
import tramways.inbound.model.Property;
import tramways.inbound.model.RoadMapContent;
import tramways.services.MessagesCollector;

public abstract class BasicAnalysisType implements AnalysisType {

    @Override
    public void prepareAnalysis(RoadMapContent map, List<Property> parameters,
        PropertiesCollector propertiesCollector,
        MessagesCollector messagesCollector) {
        parameters.forEach(parameter -> {
            parameter.setValid(true);
            propertiesCollector.collectProperty(parameter);
        });
        String name = Properties
            .findString(parameters, DefaultAnalysisProperties.ANALYSIS_NAME.name());
        if (name == null) {
            propertiesCollector.collectProperty(PropertyBuilder
                .string(DefaultAnalysisProperties.ANALYSIS_NAME, "Analysis name"));
        }
    }

}
