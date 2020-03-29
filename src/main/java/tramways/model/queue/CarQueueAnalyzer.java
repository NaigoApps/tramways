/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tramways.model.queue;

/**
 *
 * @author naigo
 */
public class CarQueueAnalyzer {

//	private Queue queue;
//	private InterpolatedArray availability;
//
//	public CarQueueAnalyzer(Queue q) {
//		this.queue = q;
//	}
//
//	public void setAvailability(InterpolatedArray availability) {
//		this.availability = availability;
//	}
//
//	public InterpolatedArray[] computeByQueueSize(int tMax) {
//		int fps = 20;
//		double[][] result = new double[queue.getCapacity() + 1][tMax * fps + 1];
//
//		double pArrival = TaylorExpansionVisitor.acceptAndReturn(queue.getArrivalDistribution(), 1.0 / fps);
//		double pService = TaylorExpansionVisitor.acceptAndReturn(queue.getServiceDistribution(), 1.0 / fps);
//
//		result[0][0] = 1;
//
//		int cap = queue.getCapacity();
//
//		for (int t = 1; t < result[0].length; t++) {
//			double lastTime = (double) (t - 1) / fps;
//			result[0][t] = result[0][t - 1] + result[1][t - 1] * availability.get(lastTime) * pService
//					- result[0][t - 1] * pArrival;
//
//			for (int k = 1; k < cap; k++) {
//				result[k][t] = result[k][t - 1] - result[k][t - 1] * availability.get(lastTime) * pService
//						- result[k][t - 1] * pArrival + result[k + 1][t - 1] * availability.get(lastTime) * pService
//						+ result[k - 1][t - 1] * pArrival;
//			}
//			// Case k = QUEUE_SIZE
//			result[cap][t] = result[cap][t - 1] - result[cap][t - 1] * availability.get(lastTime) * pService
//					+ result[cap - 1][t - 1] * pArrival;
//		}
//
//		double[] tes = new double[tMax * fps + 1];
//		for (int t = 0; t < tes.length; t++) {
//			tes[t] = (double) t / fps;
//		}
//
//		InterpolatedArray[] realResult = new InterpolatedArray[cap + 1];
//		for (int i = 0; i < realResult.length; i++) {
//			realResult[i] = new InterpolatedArray(tes, result[i]);
//		}
//
//		return realResult;
//	}
//
//	/**
//	 * 
//	 * @param seconds
//	 * @return (seconds * fps + 1) arrays, each made of [0 - cap] knots
//	 */
//	public double[][] computeByTime(int tMax) {
//		int fps = 20;
//		double[][] result = new double[tMax * fps + 1][queue.getCapacity() + 1];
//
//		double pArrival = TaylorExpansionVisitor.acceptAndReturn(queue.getArrivalDistribution(), 1.0 / fps);
//		double pService = TaylorExpansionVisitor.acceptAndReturn(queue.getServiceDistribution(), 1.0 / fps);
//
//		result[0][0] = 1;
//
//		for (int t = 1; t < result.length; t++) {
//			//time = [0; seconds]
//			double lastTime = (double) (t - 1) / fps;
//			result[t][0] = result[t - 1][0] + result[t - 1][1] * availability.get(lastTime) * pService
//					- result[t - 1][0] * pArrival;
//
//			for (int k = 1; k < queue.getCapacity(); k++) {
//				result[t][k] = result[t - 1][k] - result[t - 1][k] * availability.get(lastTime) * pService
//						- result[t - 1][k] * pArrival
//						+ result[t - 1][k + 1] * availability.get(lastTime) * pService
//						+ result[t - 1][k - 1] * pArrival;
//			}
//			// Case k = QUEUE_SIZE
//			result[t][queue.getCapacity()] = result[t - 1][queue.getCapacity()]
//					- result[t - 1][queue.getCapacity()] * availability.get(lastTime) * pService
//					+ result[t - 1][queue.getCapacity() - 1] * pArrival;
//		}
//
//		double[][] realResult = new double[tMax + 1][queue.getCapacity() + 1];
//		for (int i = 0; i < realResult.length; i++) {
//			realResult[i] = result[i * fps];
//		}
//
//		return realResult;
//	}
}
