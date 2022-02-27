package hu.lacztam.logistic.config;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;

@ConfigurationProperties(prefix = "logistic-app")
@Component
public class ConfigProperties {
	
private JwtTokenProperties jwtTokenProperties = new JwtTokenProperties();
	
	public JwtTokenProperties getJwtTokenProperties() {
		return jwtTokenProperties;
	}

	public void setJwtTokenProperties(JwtTokenProperties jwtTokenProperties) {
		this.jwtTokenProperties = jwtTokenProperties;
	}
	
	public static class JwtTokenProperties{
		private String auth;
		private String issuer;
		private Date expireTime;
		protected int expireMinute;
		private Algorithm algorithm;
		private String algorithmSpecification;
		private String algorithmSpecificationArgument;

		public Date getExpireTime() {
			this.expireTime = new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(this.expireMinute));
			return expireTime;
		}
		
		public Algorithm getAlgorithm() {
			switch (getAlgorithmSpecification()) {
			case "HMAC256":
				this.algorithm = Algorithm.HMAC256("HMAC256");
				break;
			case "HMAC384":
				this.algorithm = Algorithm.HMAC384("HMAC384");
				break;
			case "HMAC512":
				this.algorithm = Algorithm.HMAC512("HMAC512");
				break;
			default:
				throw new AlgorithmMismatchException("Unsupported algorithm: " + this.algorithmSpecificationArgument);
			}
				
			return algorithm;
		}
		public String getAlgorithmSpecification() {
			return algorithmSpecification;
		}
		public String getAlgorithmSpecificationArgument() {
			return algorithmSpecificationArgument;
		}
		public String getIssuer() {
			return issuer;
		}
		public void setIssuer(String issuer) {
			this.issuer = issuer;
		}
		public void setExpireTime(Date expireTime) {
			this.expireTime = expireTime;
		}
		public void setAlgorithm(Algorithm algorithm) {
			this.algorithm = algorithm;
		}
		public void setAlgorithmSpecification(String algorithmSpecification) {
			this.algorithmSpecification = algorithmSpecification;
		}
		public void setAlgorithmSpecificationArgument(String algorithmSpecificationArgument) {
			this.algorithmSpecificationArgument = algorithmSpecificationArgument;
		}
		public int getExpireMinute() {
			return expireMinute;
		}
		public void setExpireMinute(int expireMinute) {
			this.expireMinute = expireMinute;
		}
		public String getAuth() {
			return auth;
		}
		public void setAuth(String auth) {
			this.auth = auth;
		}
	}
	
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
