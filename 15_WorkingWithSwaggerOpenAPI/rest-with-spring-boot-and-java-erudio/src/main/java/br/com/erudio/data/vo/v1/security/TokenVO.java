package br.com.erudio.data.vo.v1.security;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class TokenVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String username;
	private Boolean authentcated;
	private Date created;
	private Date expiration;	
	private String acessToken;
	private String refreshToken;
	
	public TokenVO() {
	}

	public TokenVO(String username, Boolean authentcated, Date created, Date expiration, String acessToken,
			String refreshToken) {
		super();
		this.username = username;
		this.authentcated = authentcated;
		this.created = created;
		this.expiration = expiration;
		this.acessToken = acessToken;
		this.refreshToken = refreshToken;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Boolean getAuthentcated() {
		return authentcated;
	}

	public void setAuthentcated(Boolean authentcated) {
		this.authentcated = authentcated;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public String getAcessToken() {
		return acessToken;
	}

	public void setAcessToken(String acessToken) {
		this.acessToken = acessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	@Override
	public int hashCode() {
		return Objects.hash(acessToken, authentcated, created, expiration, refreshToken, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TokenVO other = (TokenVO) obj;
		return Objects.equals(acessToken, other.acessToken) && Objects.equals(authentcated, other.authentcated)
				&& Objects.equals(created, other.created) && Objects.equals(expiration, other.expiration)
				&& Objects.equals(refreshToken, other.refreshToken) && Objects.equals(username, other.username);
	}
	
}
