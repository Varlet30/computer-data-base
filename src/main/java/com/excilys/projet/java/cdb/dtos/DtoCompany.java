package com.excilys.projet.java.cdb.dtos;

public final class DtoCompany {

	private String id;
	private String name;

	public DtoCompany() {
		
		super();
	}

	public DtoCompany(String id, String name) {

		super();
		this.id = id;
		this.name = name;
	}

	public DtoCompany(DtoCompanyBuilder builder) {

		this.id = builder.id;
		this.name = builder.name;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getId() {

		return id;
	}

	public void setId(String id) {

		this.id = id;
	}

	public static class DtoCompanyBuilder {

		private String id;
		private String name;

		public DtoCompanyBuilder(String id, String name) {

			this.id = id;
			this.name = name;
		}

		public DtoCompany build() {

			return new DtoCompany(this);
		}
	}

}
