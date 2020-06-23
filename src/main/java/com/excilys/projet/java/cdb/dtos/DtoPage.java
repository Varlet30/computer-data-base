package com.excilys.projet.java.cdb.dtos;

public final class DtoPage {

	private String lines;
	private String page;
	private String search;
	private String order;
	private String direction;

	public DtoPage() {
		
		super();
	}

	public DtoPage(DtoPageBuilder builder) {

		this.page = builder.page;
		this.lines = builder.lines;
		this.search = builder.search;
		this.order = builder.order;
		this.direction = builder.direction;
	}

	public String getLines() {

		return lines;
	}

	public String getPage() {

		return page;
	}

	public String getSearch() {

		return search;
	}

	public String getOrder() {

		return order;
	}

	public String getDirection() {

		return direction;
	}

	public static class DtoPageBuilder {

		private String lines;
		private String page;
		private String search;
		private String order;
		private String direction;

		public DtoPageBuilder(String lines, String page, String search) {

			this.page = page;
			this.lines = lines;
			this.search = search;
		}

		public DtoPage build() {

			return new DtoPage(this);
		}

		public DtoPageBuilder order(String order) {

			this.order = order;

			return this;
		}

		public DtoPageBuilder direction(String direction) {

			this.direction = direction;

			return this;
		}
	}
}
