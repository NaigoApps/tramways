package tramways.queue;

import java.math.BigDecimal;

import org.oristool.math.expression.Variable;
import org.oristool.math.function.EXP;

public class VecosQueue {

	// PARAMETRI DI DEFAULT
	private static final int DEFAULT_QUEUE_SIZE = 45;
	private static final int DEFAULT_QUEUED_ELEMENTS = 0;
	private static final double DEFAULT_ARRIVAL_RATE = 1.05;
	private static final double DEFAULT_SERVICE_RATE = 1.20; 
	
	
	// PARAMETRI DELLA CODA
	// dimensione della coda
	private int queueSize = DEFAULT_QUEUE_SIZE;
	private int initialDiscreteQueuedElements = DEFAULT_QUEUED_ELEMENTS;
	private double initialQueuedElements = (double)initialDiscreteQueuedElements;
	
	private double[] pNumberRequestsCurrent = new double[queueSize + 1]; //time = t + Delta
	
	// Tassi di 
	private double arrivalRate = DEFAULT_ARRIVAL_RATE;
	private double serviceRate = DEFAULT_SERVICE_RATE;
	private EXP arrivalDistribution = new EXP(Variable.X, new BigDecimal("" + arrivalRate));
	private EXP serviceDistribution = new EXP(Variable.X, new BigDecimal("" + serviceRate));
	
	// time step dell'analisi fatta da oris e limite temporale
	private double externalTimeStep = 1.0;
	
	// time step da usare nei calcoli
	private double NUMERICAL_TIME_STEP = 0.005;
	
	// curva di disponibilita' del semaforo (il verde)
	private double[] availability; 
	
	// DATA COLLECTOR
	protected double[][] queueSizeAlongTime;
	
	// ------- CONSTRUCTORS -----------------------------------------
	private VecosQueue(double availability[]) {
		// TBD defensive copy? 
		this.availability = availability;
		
	}
	
	public static VecosQueue newIstance(double[] availability) {
		if(availability!=null) {
			VecosQueue vq = new VecosQueue(availability);
			vq.queueSizeAlongTime = new double[availability.length][vq.queueSize+1];
			return vq;
		}else {
			return null;
		}
	}
	
	public static VecosQueue newIstance(double[] availability, int startingCars) {
		if(availability!=null) {
			VecosQueue vq = new VecosQueue(availability);
			vq.queueSizeAlongTime = new double[availability.length][vq.queueSize+1];
			vq.initialDiscreteQueuedElements = startingCars;
			return vq;
		}else {
			return null;
		}
	}
	
	public static VecosQueue newIstance(
			double[] availability, 
			double arrivalRate, 
			double serviceRate) {
		
			VecosQueue vq = VecosQueue.newIstance(availability);
			vq.setArrivalRate(arrivalRate);
			vq.setServiceRate(serviceRate);
			return vq;
		
	}
	
	public static VecosQueue newIstance(
			double[] availability, 
			double arrivalRate, 
			double serviceRate, 
			int startingCars) {
		
		VecosQueue vq = VecosQueue.newIstance(availability, arrivalRate, serviceRate);
		vq.setInitialQueuedElements(startingCars);
		return vq;
	}
	
	public static VecosQueue newIstance(double[] availability, 
										double arrivalRate, 
										double serviceRate, 
										int queueSize,
										int startingCars) {
		VecosQueue vq = VecosQueue.newIstance(availability, arrivalRate, serviceRate, startingCars);
		vq.setQueueSize(queueSize);
		return vq;
	}
	
	public double[][] getQueueSizeAlongTime(){
		return this.queueSizeAlongTime;
	}
	
	// una colonna rappresenta al variare del tempo la probabilita'
	// di vedere esattamente index-macchine nella coda
	public double[] getSolutionRow(int time) {
		double[] ret = new double[queueSizeAlongTime[0].length];
		for(int i=0; i<ret.length; i++){
			ret[i] = queueSizeAlongTime[time][i];
		}
		return ret;
	}
	
	// una riga rappresenta fissato un tempo t, le probabilita'
	// di vedere i diversi numeri di macchine in coda
	public double[] getSolutionColumn(int cars) {
		double[] ret = new double[queueSizeAlongTime.length];
		for(int t=0; t<ret.length; t++) {
			ret[t] = queueSizeAlongTime[t][cars];
		}
		return ret;
	}
	
	// -------- FUNZIONI DI SUPPORTO ----------------------------------------------
	
	private static double taylorExpansion(EXP distr, double value){
        return distr.getLambda().multiply(new BigDecimal(value)).doubleValue();        
    }
	
	// -------- ANALISI DELLA CODA SULLA BASE DELLA CURVA DI DISPONIBILITA' -------
	
	public VecosQueue analyze() {
		
        double[] pNumberRequestsPrevious; // time = t
        
        this.setInitialQueuedElements(this.initialQueuedElements);
		
		// cacolo le probabilita' di servizio dalle distribuzioni..
		double pArrival = taylorExpansion(arrivalDistribution, NUMERICAL_TIME_STEP);
        double pService = taylorExpansion(serviceDistribution, NUMERICAL_TIME_STEP);
		
		
        for(int t = 1; t < availability.length; t++){
            pNumberRequestsPrevious = pNumberRequestsCurrent;
            pNumberRequestsCurrent = new double[queueSize + 1];
            
            //Case k = 0
            pNumberRequestsCurrent[0] = pNumberRequestsPrevious[0]
                  + pNumberRequestsPrevious[1] * availability[t - 1] * pService
                  - pNumberRequestsPrevious[0] * pArrival;
            //Case k in [1, QUEUE_SIZE -1]
            for(int k = 1; k < queueSize; k++){
                pNumberRequestsCurrent[k] = pNumberRequestsPrevious[k]     
                      - pNumberRequestsPrevious[k] * availability[t - 1] * pService
                      - pNumberRequestsPrevious[k] * pArrival
                      + pNumberRequestsPrevious[k + 1] * availability[t - 1] * pService
                      + pNumberRequestsPrevious[k - 1] * pArrival;
            }
            //Case k = QUEUE_SIZE
            pNumberRequestsCurrent[queueSize] = pNumberRequestsPrevious[queueSize]
                  - pNumberRequestsPrevious[queueSize] * availability[t - 1] * pService 
                  + pNumberRequestsPrevious[queueSize -1] * pArrival;
          
            // salvare array per il tempo t.. 
            this.queueSizeAlongTime[t] = pNumberRequestsCurrent;
               
        }
        
        return this;

	}
	
	public double[] calculateAverageQueueSizeAlongTime() {
		double[] eDimCoda = new double[this.queueSizeAlongTime.length];
		double accum = 0.0;
		for(int t=0; t<this.queueSizeAlongTime.length; t++) {
			accum = 0.0;
			for(int c=0; c<this.queueSizeAlongTime[0].length; c++) {
		    	accum += (this.queueSizeAlongTime[t][c]) * (c);
		    }
		    eDimCoda[t]=accum;
		}
		return eDimCoda;
	}
	
	public double[] calculateOverflowProbabilityAlongTime() {
		// considero soltanto le prob nel tempo della coda
		// nello stato di indice massimo (coda piena)
		
		double[] res = new double[availability.length];
		int lastIndex = queueSizeAlongTime[0].length-1;
		for(int t=0; t<res.length; t++) {
			res[t] = queueSizeAlongTime[t][lastIndex];
		}
		return res;		
		
	}
	
	// ------ SETTERS AND GETTERS -----------------------------------
	
		public int getQueueSize() {
			return queueSize;
		}

		public void setQueueSize(int qs) {
			if(qs>0) {
				queueSize = qs;
			}
		}

		public double getArrivalRate() {
			return arrivalRate;
		}

		public VecosQueue setArrivalRate(double ar) {
			if(ar>0) {
				arrivalRate = ar;
				this.arrivalDistribution = new EXP(Variable.X, new BigDecimal("" + ar));
			}
			return this;
		}

		public double getServiceRate() {
			return serviceRate;
		}

		public VecosQueue setServiceRate(double sr) {
			if(sr>0) {
				serviceRate = sr;
				this.serviceDistribution = new EXP(Variable.X, new BigDecimal("" + sr));
			}
			return this;
		}

		public double getExternalTimeStep() {
			return externalTimeStep;
		}

		public void setExternalTimeStep(double ets) {
			if(ets>0) {
				externalTimeStep = ets;
			}
			
		}

		public double getNumericalTimeStep() {
			return NUMERICAL_TIME_STEP;
		}

		public void setNumericalTimeStep(double nts) {
			if(nts>0) {
				NUMERICAL_TIME_STEP = nts;
			}
		}
		
		public void setInitialQueuedElements(int cars) {
			this.initialDiscreteQueuedElements = cars;
			this.initialQueuedElements = (double)cars;
			this.pNumberRequestsCurrent = new double[this.queueSize+1];
			pNumberRequestsCurrent[cars] = 1.0;
		}
		
		public void setInitialQueuedElements(double value) {
			
			this.initialDiscreteQueuedElements = (int)value;
			this.initialQueuedElements = value;
			// --
			double down = Math.floor(value);
			double up = down + 1;
			
			double probDown = up - value;
			double probUp = 1 - probDown;
			
			pNumberRequestsCurrent = new double[this.queueSize+1];
			
			pNumberRequestsCurrent[(int)up] = probUp;
			pNumberRequestsCurrent[(int)down] = probDown;
			
			return;
			
		}


}
