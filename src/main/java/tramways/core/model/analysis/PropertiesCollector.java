package tramways.core.model.analysis;

import tramways.inbound.model.Property;

import java.util.List;

public interface PropertiesCollector {

    void collectProperty(Property property);
    List<Property> listProperties();

}
