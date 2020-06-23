package com.excilys.projet.java.cdb.dtos;

public final class DtoComputer {

	private String id;
	private String name;
	private String introduced;
	private String discontinued;
	
	private DtoCompany company;

	public DtoComputer() {
		
		super();
	}
	
	public DtoComputer(String id, String name, String introduced, String discontinued, DtoCompany company) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
	}

	public DtoComputer(DtoComputerBuilder builder) {

		this.id = builder.id;
		this.name = builder.name;
		this.introduced = builder.introduced;
		this.discontinued = builder.discontinued;
		this.company = builder.company;
	}

	public String getId() {

		return id;
	}

	public void setId(String id) {

		this.id = id;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getIntroduced() {

		return introduced;
	}

	public void setIntroduced(String introduced) {

		this.introduced = introduced;
	}

	public String getDiscontinued() {

		return discontinued;
	}

	public void setDiscontinued(String discontinued) {

		this.discontinued = discontinued;
	}

	public DtoCompany getCompany() {

		return company;
	}

	public void setCompany(DtoCompany company) {

		this.company = company;
	}

	public static class DtoComputerBuilder {

		private String id;
		private String name;
		private String introduced;
		private String discontinued;
		private DtoCompany company;

		public DtoComputerBuilder(String name) {

			this.name = name;
		}

		public DtoComputerBuilder id(String id) {

			this.id = id;

			return this;
		}

		public DtoComputerBuilder introduced(String introduced) {

			if (introduced != null) {
				this.introduced = introduced;
			}

			return this;
		}

		public DtoComputerBuilder discontinued(String discontinued) {

			if (discontinued != null) {
				this.discontinued = discontinued;
			}

			return this;
		}

		public DtoComputerBuilder company(DtoCompany company) {

			if (company != null) {
				this.company = company;
			}

			return this;
		}

		public DtoComputer build() {

			return new DtoComputer(this);
		}
	}
}