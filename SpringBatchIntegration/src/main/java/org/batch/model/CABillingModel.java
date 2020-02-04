package org.batch.model;

import java.io.Serializable;

import com.google.gson.GsonBuilder;

import lombok.Getter;
import lombok.Setter;

/**
 * Base class to represent models used in CA Billing Integration
 * @author B206777
 */
@Getter
@Setter
public class CABillingModel implements Serializable{
	public static final long serialVersionUID = -609435309461625032L;

	@Override
	public String toString() {
	    return new GsonBuilder().create().toJson(this);
	}
	
}
