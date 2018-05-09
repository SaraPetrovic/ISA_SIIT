package ProjectIsa.bioskop.domain;

public class ChangedInstitution {
	private String name;
	private Adresa adress;
	private String description;
	private String selectInstitution;
	
	public ChangedInstitution() {
		
	}

	public ChangedInstitution(String name, Adresa adress, String description, String changedInstitution) {
		super();
		this.name = name;
		this.adress = adress;
		this.description = description;
		this.selectInstitution = changedInstitution;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Adresa getAdress() {
		return adress;
	}

	public void setAdress(Adresa adress) {
		this.adress = adress;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSelectInstitution() {
		return selectInstitution;
	}

	public void setSelectInstitution(String changedInstitution) {
		this.selectInstitution = changedInstitution;
	}
	
}
