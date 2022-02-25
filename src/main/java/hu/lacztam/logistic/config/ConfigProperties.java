package hu.lacztam.logistic.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "logistic-app")
@Component
public class ConfigProperties {
	
	private Penalty penalty = new Penalty();
	
	public static class Penalty{
		private double percent1;
		private double percent2;
		private double percent3;
		
		public double getPercent1() {
			return percent1;
		}
		public double getPercent2() {
			return percent2;
		}
		public double getPercent3() {
			return percent3;
		}
		public void setPercent1(double percent1) {
			this.percent1 = percent1;
		}
		public void setPercent2(double percent2) {
			this.percent2 = percent2;
		}
		public void setPercent3(double percent3) {
			this.percent3 = percent3;
		}
	}

	public Penalty getPenalty() {
		return penalty;
	}

	public void setPenalty(Penalty penalty) {
		this.penalty = penalty;
	}

	@Override
	public String toString() {
		return "\n\nConfigProperties\n[penalty.getPercent1()=" + penalty.getPercent1() + 
									", penalty.getPercent2()=" + penalty.getPercent2() +
									", penalty.getPercent3()=" + penalty.getPercent3() + " ]";
	}
}
