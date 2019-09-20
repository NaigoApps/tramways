package tramways.dto.distributions;

public interface DistributionDto {
	public void accept(DistributionDtoVisitor visitor);
}
