package tramways.dto.mappers;

import org.mapstruct.Mapper;

import tramways.core.model.persistable.projects.AnalysisWrapper;
import tramways.dto.AnalysisDto;

@Mapper(config = MapperConfiguration.class, uses = {})
public abstract class AnalysisMapper {

	public abstract AnalysisDto map(AnalysisWrapper a);

	public abstract AnalysisWrapper map(AnalysisDto m);

}
