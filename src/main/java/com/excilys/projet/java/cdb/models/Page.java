package main.java.com.excilys.projet.java.cdb.models;

public class Page {
	
	private int currentPage;
	private int linesPerPage;
	
	public Page(int currentPage, int linesPerPage) {
		this.currentPage = currentPage;
		this.linesPerPage = linesPerPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getLinesPerPage() {
		return linesPerPage;
	}

	public void setLinesPerPage(int linesPerPage) {
		this.linesPerPage = linesPerPage;
	}

	@Override
	public String toString() {
		return "Page [currentPage=" + currentPage + ", linesPerPage=" + linesPerPage + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + currentPage;
		result = prime * result + linesPerPage;
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
		Page other = (Page) obj;
		if (currentPage != other.currentPage)
			return false;
		if (linesPerPage != other.linesPerPage)
			return false;
		return true;
	}
	
}
