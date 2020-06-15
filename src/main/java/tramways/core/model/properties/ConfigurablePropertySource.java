package tramways.core.model.properties;

import tramways.core.model.Configurable;
import tramways.inbound.model.Property;

public class ConfigurablePropertySource implements PropertySource {

    private final Configurable configurable;

    public ConfigurablePropertySource(Configurable configurable){
        this.configurable = configurable;
    }

    @Override
    public Property findProperty(String name) {
        return configurable.listProperties().stream()
                .filter(property -> PropertyFilter.of(property, property.getClass()).matches(PropertyFilter.of(name, null)))
                .findFirst()
                .orElse(null);
    }

    @Override
    public <T extends Property> T findProperty(String name, Class<T> type) {
        return configurable.listProperties().stream()
                .filter(property -> PropertyFilter.of(property, property.getClass()).matches(PropertyFilter.of(name, type)))
                .map(type::cast)
                .findFirst()
                .orElse(null);
    }
}
