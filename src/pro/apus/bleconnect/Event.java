package pro.apus.bleconnect;

public class Event {

	private long id;
	private int session;
	private long time;
	private long eventdata;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getSession() {
		return session;
	}
	public void setSession(int session) {
		this.session = session;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public long getEventdata() {
		return eventdata;
	}
	public void setEventdata(long eventdata) {
		this.eventdata = eventdata;
	}
}
