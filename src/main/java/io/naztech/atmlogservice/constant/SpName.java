package io.naztech.atmlogservice.constant;

public enum SpName {
	INS_cbs_npsb_link_insert("INS_cbs_npsb_link_insert");
	
	private final String spName;
	
	private SpName(String spName) {
		this.spName = spName;
	}

	@Override
	public String toString() {
		return spName;
	}
}
