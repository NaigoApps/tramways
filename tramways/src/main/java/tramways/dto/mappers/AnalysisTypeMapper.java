package tramways.dto.mappers;

import org.mapstruct.Mapper;

import tramways.dto.AnalysisTypeDto;
import tramways.model.analysis.AnalysisType;

@Mapper(config = MapperConfiguration.class)
public interface AnalysisTypeMapper {

	public abstract AnalysisTypeDto map(AnalysisType<?> at);

}
