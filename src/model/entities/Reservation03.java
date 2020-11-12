package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.excepctions.DomainException;

public class Reservation03 {
	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

//  Não é obrigado a tratar Exceção do tipo RuntimeException, apenas a do tipo Exception:
//	public Reservation03(Integer roomNumber, Date checkIn, Date checkOut) throws DomainException {
	public Reservation03(Integer roomNumber, Date checkIn, Date checkOut)  {
		if (!checkOut.after(checkIn)) {
			throw new DomainException("Check-out date must be after check-in date");
		}		
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}
	
	public long duration() {
		//Diferença entre duas datas em milisegundos:
		long diff = checkOut.getTime() - checkIn.getTime();
		//Converte milisegundos em dias:
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
//  public void updateDates(Date checkIn, Date checkOut) throws DomainException {
	public void updateDates(Date checkIn, Date checkOut) {
		//Para pegar a Data Atual (de agora):
		Date now = new Date();
		if(checkIn.before(now) || checkOut.before(now)) {
		//	throw new IllegalArgumentException("Reservation dates for update must be future dates");
			throw new DomainException("Reservation dates for update must be future dates");
		}
		if (!checkOut.after(checkIn)) {
		//	throw new IllegalArgumentException("Check-out date must be after check-in date");
			throw new DomainException("Check-out date must be after check-in date");
		}		
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	
	@Override
	public String toString() {
		return "Room " 
				+ roomNumber
				+ ", check-in: "
				+ sdf.format(checkIn)
				+ ", check-out: "
				+ sdf.format(checkOut)
				+ ", "
				+ duration()
				+ " nights";
	}
}
