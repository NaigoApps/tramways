package tramways.model.analysis;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

public class AnalysisTypeFactory {

	@Inject
	Instance<AnalysisType<?>> availableTypes;
	
	public AnalysisType<?> getType(String name){
		for(AnalysisType<?> t : availableTypes) {
			if(name.equals(t.getName())) {
				return t;
			}
		}
		return null;
	}
	
	public List<AnalysisType<?>> getTypes(){
		return availableTypes.stream()
				.collect(Collectors.toList());
	}
	
}
