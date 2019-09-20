package tramways.model.properties;

public interface PropertyVisitor {

	public void visit(IntegerProperty d);

	public void visit(DecimalProperty d);

	public void visit(StringProperty d);

	public void visit(DistributionProperty d);

}
