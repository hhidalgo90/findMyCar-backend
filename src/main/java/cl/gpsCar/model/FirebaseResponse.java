package cl.gpsCar.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FirebaseResponse {
	
    private long multicast_id;
    private Integer success;
    private Integer failure;
    private Object canonical_ids;
	public long getMulticast_id() {
		return multicast_id;
	}
	public void setMulticast_id(long multicast_id) {
		this.multicast_id = multicast_id;
	}
	public Integer getSuccess() {
		return success;
	}
	public void setSuccess(Integer success) {
		this.success = success;
	}
	public Integer getFailure() {
		return failure;
	}
	public void setFailure(Integer failure) {
		this.failure = failure;
	}
	public Object getCanonical_ids() {
		return canonical_ids;
	}
	public void setCanonical_ids(Object canonical_ids) {
		this.canonical_ids = canonical_ids;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FirebaseResponse [multicast_id=");
		builder.append(multicast_id);
		builder.append(", success=");
		builder.append(success);
		builder.append(", failure=");
		builder.append(failure);
		builder.append(", canonical_ids=");
		builder.append(canonical_ids);
		builder.append("]");
		return builder.toString();
	}    
}
