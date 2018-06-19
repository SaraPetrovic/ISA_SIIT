package ProjectIsa.bioskop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Membership {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private int bronzeMin;
	
	@Column(nullable = false)
	private int bronzeMax;
	
	@Column(nullable = false)
	private int silverMin;
	
	@Column(nullable = false)
	private int silverMax;
	
	@Column(nullable = false)
	private int goldMin;
	
	@Column(nullable = false)
	private int goldMax;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getBronzeMin() {
		return bronzeMin;
	}

	public void setBronzeMin(int bronzeMin) {
		this.bronzeMin = bronzeMin;
	}

	public int getBronzeMax() {
		return bronzeMax;
	}

	public void setBronzeMax(int bronzeMax) {
		this.bronzeMax = bronzeMax;
	}

	public int getSilverMin() {
		return silverMin;
	}

	public void setSilverMin(int silverMin) {
		this.silverMin = silverMin;
	}

	public int getSilverMax() {
		return silverMax;
	}

	public void setSilverMax(int silverMax) {
		this.silverMax = silverMax;
	}

	public int getGoldMin() {
		return goldMin;
	}

	public void setGoldMin(int goldMin) {
		this.goldMin = goldMin;
	}

	public int getGoldMax() {
		return goldMax;
	}

	public void setGoldMax(int goldMax) {
		this.goldMax = goldMax;
	}

	public Membership(Long id, int bronzeMin, int bronzeMax, int silverMin, int silverMax, int goldMin, int goldMax) {
		super();
		this.id = id;
		this.bronzeMin = bronzeMin;
		this.bronzeMax = bronzeMax;
		this.silverMin = silverMin;
		this.silverMax = silverMax;
		this.goldMin = goldMin;
		this.goldMax = goldMax;
	}

	public Membership() {
		super();
	}
	
	
	
}
