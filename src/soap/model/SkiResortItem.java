package soap.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

@Entity
public class SkiResortItem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int resortId;
	
	private String name;
	
	private int liftCount;
	
	private int top;
	
	private int drop;
	
	private String website;
	
	private boolean nightSkiing;
	
	private String skiMapId;
	
	private Date lastUpdated;

	public SkiResortItem() {};
	
	public SkiResortItem(int id, String name, int liftCount, int top, int drop, 
			String website, boolean nightSkiing,
			String skiMapId, Date lastUpdated) {
		super();
		this.resortId = id;
		this.name = name;
		this.liftCount = liftCount;
		this.top = top;
		this.drop = drop;
		this.website = website;
		this.nightSkiing = nightSkiing;
		this.skiMapId = skiMapId;
		this.lastUpdated = lastUpdated;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLiftCount() {
		return liftCount;
	}

	public void setLiftCount(int liftCount) {
		this.liftCount = liftCount;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getDrop() {
		return drop;
	}

	public void setDrop(int drop) {
		this.drop = drop;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public boolean isNightSkiing() {
		return nightSkiing;
	}

	public void setNightSkiing(boolean nightSkiing) {
		this.nightSkiing = nightSkiing;
	}

	public String getSkiMapId() {
		return skiMapId;
	}

	public void setSkiMapId(String skiMapId) {
		this.skiMapId = skiMapId;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public int getId() {
		return resortId;
	}

	public void setId(int id) {
		this.resortId = id;
	}

	
	
}
