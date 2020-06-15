package tramways.core.model.properties;

import tramways.inbound.model.Property;

public interface PropertySource {
    Property findProperty(String name);
    <T extends Property> T findProperty(String name, Class<T> type);
}
