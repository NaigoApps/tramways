package tramways.dto.mappers;

import org.mapstruct.Mapper;

import tramways.core.model.persistable.projects.Analysis;
import tramways.dto.AnalysisDto;

@Mapper(config = MapperConfiguration.class, uses = {})
public abstract class AnalysisMapper {

	public abstract AnalysisDto map(Analysis a);

	public abstract Analysis map(AnalysisDto m);

}
