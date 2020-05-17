package tramways.core.model.properties;

import tramways.inbound.model.ChoiceElement;
import tramways.inbound.model.ChoiceProperty;
import tramways.inbound.model.DecimalProperty;
import tramways.inbound.model.IntegerProperty;
import tramways.inbound.model.StringProperty;

import java.math.BigDecimal;
import java.util.Arrays;

public final class Properties {

    private Properties(){}

    public static IntegerProperty intProperty(String name, Integer value){
        IntegerProperty property = new IntegerProperty();
        property.setName(name);
        property.setValue(value);
        property.setPropertyType(IntegerProperty.class.getSimpleName());
        return property;
    }

    public static DecimalProperty decimalProperty(String name, BigDecimal value){
        DecimalProperty property = new DecimalProperty();
        property.setName(name);
        property.setValue(value);
        property.setPropertyType(DecimalProperty.class.getSimpleName());
        return property;
    }

    public static StringProperty stringProperty(String name, String value) {
        StringProperty property = new StringProperty();
        property.setName(name);
        property.setValue(value);
        property.setPropertyType(StringProperty.class.getSimpleName());
        return property;
    }

    public static ChoiceProperty choiceProperty(String name, String value, ChoiceElement... elements) {
        ChoiceProperty property = new ChoiceProperty();
        property.setName(name);
        property.setValue(value);
        property.setChoices(Arrays.asList(elements));
        property.setPropertyType(ChoiceProperty.class.getSimpleName());
        return property;
    }

    public static ChoiceElement choiceElement(String id, String label){
        ChoiceElement element = new ChoiceElement();
        element.setId(id);
        element.setLabel(label);
        return element;
    }
}
