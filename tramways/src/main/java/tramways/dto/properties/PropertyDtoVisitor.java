package tramways.dto.properties;

public interface PropertyDtoVisitor {

	public void visit(IntegerPropertyDto d);

	public void visit(DecimalPropertyDto d);

	public void visit(StringPropertyDto d);

	public void visit(DistributionPropertyDto d);

}
