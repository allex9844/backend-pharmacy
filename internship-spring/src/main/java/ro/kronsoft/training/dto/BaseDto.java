package ro.kronsoft.training.dto;

import java.io.Serializable;

public abstract class BaseDto implements Serializable {
	private static final long serialVersionUID = 4777536843431700272L;

	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
