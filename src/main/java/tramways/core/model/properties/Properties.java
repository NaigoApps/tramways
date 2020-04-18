package tramways.core.model.properties;

public final class Properties {
    public static IntegerProperty intProperty(String name, Long value){
        IntegerProperty property = new IntegerProperty();
        property.setName(name);
        property.setValue(value);
        return property;
    }
}
