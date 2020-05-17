package tramways.core.model.analysis;

import tramways.inbound.model.Property;

import java.util.ArrayList;
import java.util.List;

public class DefaultPropertiesCollector implements PropertiesCollector {

    private List<Property> properties;

    public DefaultPropertiesCollector() {
        properties = new ArrayList<>();
    }

    @Override
    public void collectProperty(Property property) {
        properties.add(property);
    }

    @Override
    public List<Property> listProperties() {
        return new ArrayList<>(properties);
    }
}
