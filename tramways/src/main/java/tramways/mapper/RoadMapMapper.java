package tramways.mapper;

import org.mapstruct.Mapper;

import tramways.dto.RoadMapDto;
import tramways.model.projects.RoadMap;

@Mapper
public abstract class RoadMapMapper {

	public abstract RoadMap map(RoadMapDto map);
	public abstract RoadMapDto map(RoadMap map);
	
}
