package tramways.model.core.queue;

import org.apache.commons.math3.distribution.RealDistribution;

public class Queue {

	private RealDistribution arrivalDistribution;
	private RealDistribution serviceDistribution;
	private Integer capacity;

	public Queue(RealDistribution arrivalDistribution, RealDistribution serviceDistribution, Integer capacity) {
		super();
		this.arrivalDistribution = arrivalDistribution;
		this.serviceDistribution = serviceDistribution;
		this.capacity = capacity;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public RealDistribution getArrivalDistribution() {
		return arrivalDistribution;
	}

	public RealDistribution getServiceDistribution() {
		return serviceDistribution;
	}
}
