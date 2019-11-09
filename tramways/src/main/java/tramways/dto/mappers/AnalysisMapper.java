package tramways.dto.mappers;

import org.mapstruct.Mapper;

import tramways.dto.AnalysisDto;
import tramways.model.persistable.projects.AnalysisWrapper;

@Mapper(config = MapperConfiguration.class, uses = {})
public abstract class AnalysisMapper {

	public abstract AnalysisDto map(AnalysisWrapper a);

	public abstract AnalysisWrapper map(AnalysisDto m);

}
