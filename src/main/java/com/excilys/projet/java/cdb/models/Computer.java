package main.java.com.excilys.projet.java.cdb.models;

import java.time.LocalDate;

public class Computer {
	
	private Long idComputer;
	private String name;
	private LocalDate IntroducedDate;
	private LocalDate DiscontinuedDate;
	
	public Computer(Long idComputer, String name, LocalDate IntroducedDate, LocalDate DiscontinuedDate) {
		this.idComputer = idComputer;
		this.name = name;
		this.IntroducedDate = IntroducedDate;
		this.DiscontinuedDate = DiscontinuedDate;
	}

	public Long getIdComputer() {
		return idComputer;
	}

	public void setIdComputer(Long idComputer) {
		this.idComputer = idComputer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getIntroducedDate() {
		return IntroducedDate;
	}

	public void setIntroducedDate(LocalDate introducedDate) {
		IntroducedDate = introducedDate;
	}

	public LocalDate getDiscontinuedDate() {
		return DiscontinuedDate;
	}

	public void setDiscontinuedDate(LocalDate discontinuedDate) {
		DiscontinuedDate = discontinuedDate;
	}

	@Override
	public String toString() {
		return "Computer [idComputer=" + idComputer + ", name=" + name + ", IntroducedDate=" + IntroducedDate
				+ ", DiscontinuedDate=" + DiscontinuedDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DiscontinuedDate == null) ? 0 : DiscontinuedDate.hashCode());
		result = prime * result + ((IntroducedDate == null) ? 0 : IntroducedDate.hashCode());
		result = prime * result + ((idComputer == null) ? 0 : idComputer.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		if (DiscontinuedDate == null) {
			if (other.DiscontinuedDate != null)
				return false;
		} else if (!DiscontinuedDate.equals(other.DiscontinuedDate))
			return false;
		if (IntroducedDate == null) {
			if (other.IntroducedDate != null)
				return false;
		} else if (!IntroducedDate.equals(other.IntroducedDate))
			return false;
		if (idComputer == null) {
			if (other.idComputer != null)
				return false;
		} else if (!idComputer.equals(other.idComputer))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
