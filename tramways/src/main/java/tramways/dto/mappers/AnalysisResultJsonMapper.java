package tramways.dto.mappers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import tramways.core.model.analysis.AnalysisResultType;
import tramways.core.model.analysis.result.AnalysisResult;
import tramways.core.model.analysis.result.XYAnalysisResult;

public class AnalysisResultJsonMapper {
	
	private Gson mapper;
	
	public AnalysisResultJsonMapper() {
		mapper = initMapper();
	}

	public AnalysisResult map(String json) {
		return mapper.fromJson(json, AnalysisResult.class);
	}

	public String map(AnalysisResult result) {
		return mapper.toJson(result, AnalysisResult.class);
	}
	
	private Gson initMapper() {
		RuntimeTypeAdapterFactory<AnalysisResult> resultsFactory = RuntimeTypeAdapterFactory
				.of(AnalysisResult.class)
				.registerSubtype(XYAnalysisResult.class, AnalysisResultType.XY.getName());
		
		return new GsonBuilder().setPrettyPrinting()
			.registerTypeAdapterFactory(resultsFactory)
			.create();
	}
	
}
