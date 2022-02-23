package hu.lacztam.logistic.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "logistic-app")
@Component
public class ConfigProperties {
	
	private Integer penaltyPercent1 = null;

	public Integer getPenaltyPercent1() {
		return penaltyPercent1;
	}

	public void setPenaltyPercent1(Integer penaltyPercent1) {
		this.penaltyPercent1 = penaltyPercent1;
	}
	
	private Integer penaltyPercent2 = null;

	public Integer getPenaltyPercent2() {
		return penaltyPercent2;
	}

	public void setPenaltyPercent2(Integer penaltyPercent2) {
		this.penaltyPercent2 = penaltyPercent2;
	}
	
	private Integer penaltyPercent3 = null;

	public Integer getPenaltyPercent3() {
		return penaltyPercent3;
	}

	public void setPenaltyPercent3(Integer penaltyPercent3) {
		this.penaltyPercent3 = penaltyPercent3;
	}
}
