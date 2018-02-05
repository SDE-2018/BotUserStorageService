package soap.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;

// TODO: delete? maybe could be used for internal databased to store additional interactions...
@Entity
public class UserResortRating  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private int ratingId;
	
	private int chatId;
	
	private int skiResortId;
	
	private int rating;
	
	private String comment;
	
	private Date timestamp;

	public UserResortRating() {};

	public UserResortRating(int ratingId, int chatId, int skiResortId,
			int rating, String comment, Date timestamp) {
		super();
		this.ratingId = ratingId;
		this.chatId = chatId;
		this.skiResortId = skiResortId;
		this.rating = rating;
		this.comment = comment;
		this.timestamp = timestamp;
	}

	
	public int getRatingId() {
		return ratingId;
	}

	public void setRatingId(int ratingId) {
		this.ratingId = ratingId;
	}

	public int getChatId() {
		return chatId;
	}

	public void setChatId(int chatId) {
		this.chatId = chatId;
	}

	public int getSkiResortId() {
		return skiResortId;
	}

	public void setSkiResortId(int skiResortId) {
		this.skiResortId = skiResortId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
}
