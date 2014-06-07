package entity;

public class Future {

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	private int id;
	private String category;
	private String name;
	private String period;
	public Future(int id, String category, String name, String period) {
		super();
		this.id = id;
		this.category = category;
		this.name = name;
		this.period = period;
	}
	public Future() {
		super();
	}

}
