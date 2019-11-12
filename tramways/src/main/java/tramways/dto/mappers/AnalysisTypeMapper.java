package tramways.dto.mappers;

import org.mapstruct.Mapper;

import tramways.core.model.analysis.AnalysisType;
import tramways.dto.AnalysisTypeDto;

@Mapper(config = MapperConfiguration.class)
public interface AnalysisTypeMapper {

	public abstract AnalysisTypeDto map(AnalysisType at);

}
