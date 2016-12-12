package com.malabanan.localeventuring;

public class Venue {
	 
	 private int venueId;
	 private int contactId;
	 private String venueName;
	 private int roomSize; 
	 private int capacity; 
	 private int price;
	 private String category;
	 private String photoLink; 
	 private String calendarLink; 
	 private String description;
	 private String street;
	 private String city;
	 private String state;
	 private String zipcode;
	 
	public Venue() {
		 super();
	}

	/**
	 * @return the venueId
	 */
	public int getVenueId() {
		return venueId;
	}

	/**
	 * @param venueId the venueId to set
	 */
	public void setVenueId(int venueId) {
		this.venueId = venueId;
	}

	/**
	 * @return the contactId
	 */
	public int getContactId() {
		return contactId;
	}

	/**
	 * @param contactId the contactId to set
	 */
	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	/**
	 * @return the venueName
	 */
	public String getVenueName() {
		return venueName;
	}

	/**
	 * @param venueName the venueName to set
	 */
	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	/**
	 * @return the roomSize
	 */
	public int getRoomSize() {
		return roomSize;
	}

	/**
	 * @param roomSize the roomSize to set
	 */
	public void setRoomSize(int roomSize) {
		this.roomSize = roomSize;
	}

	/**
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the photoLink
	 */
	public String getPhotoLink() {
		return photoLink;
	}

	/**
	 * @param photoLink the photoLink to set
	 */
	public void setPhotoLink(String photoLink) {
		this.photoLink = photoLink;
	}

	/**
	 * @return the calendarLink
	 */
	public String getCalendarLink() {
		return calendarLink;
	}

	/**
	 * @param calendarLink the calendarLink to set
	 */
	public void setCalendarLink(String calendarLink) {
		this.calendarLink = calendarLink;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	} 
	 
	
}
