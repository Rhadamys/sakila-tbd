package cl.citiaps.spring.backend.entities;

public enum Rating {
	G("G"),
	PG("PG"),
	PG13("PG-13"),
	R("R"),
	NC17("NC-17");
	
	private String name;
	
	private Rating(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public static Rating getByName(String name) {
		for(Rating current: Rating.values()){
			if(current.getName().equalsIgnoreCase(name.trim())){
				return current;
	        }
	    }
	    
		return null;
	}
}