package soap.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;


@Entity
public class BotUser implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int chatId;
	
	private String name;

	private int age;
	
	private Date registerDate;
	
	/* Preferences **/
	
	/**
	 * What user does in Trentino.
	 */
	private String occupation;
	
	/**
	 * Can be one of: <Skiing>, <Snowboarding>, <Free-ride>
	 */
	private String preferredSkiType;
	
	/**
	 * Budget that user can spend in one day of skiing.	
	 */
	private int budget;
	
	/**
	 * Should the ski resort be close to the Trento city or not.
	 */
	private boolean nearTrento;

	
	public BotUser() {};
	
	
	public BotUser(int chatId, String name, int age, String occupation) {
		super();
		this.chatId = chatId;
		this.name = name;
		this.age = age;
		this.occupation = occupation;
	}



	public BotUser(int chatId, String name, int age, String occupation, 
			String preferredSkiType, int budget,
			boolean nearTrento, Date registerDate) {
		super();
		this.chatId = chatId;
		this.name = name;
		this.age = age;
		this.occupation = occupation;
		this.preferredSkiType = preferredSkiType;
		this.budget = budget;
		this.nearTrento = nearTrento;
		this.setRegisterDate(registerDate);
	}



	public int getChatId() {
		return chatId;
	}

	public void setChatId(int chatId) {
		this.chatId = chatId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getPreferredSkiType() {
		return preferredSkiType;
	}

	public void setPreferredSkiType(String preferredSkiType) {
		this.preferredSkiType = preferredSkiType;
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public boolean isNearTrento() {
		return nearTrento;
	}

	public void setNearTrento(boolean nearTrento) {
		this.nearTrento = nearTrento;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

}

