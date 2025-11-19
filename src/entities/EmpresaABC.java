package entities;

import java.util.Objects;

public class EmpresaABC {
	
	private Integer codEmp;
	private Integer codFil;
	private String user;
	private String pass;
	
	public EmpresaABC(Integer codEmp, Integer codFil, String user, String pass) {
		this.codEmp = codEmp;
		this.codFil = codFil;
		this.user = user;
		this.pass = pass;
	}
	
	public EmpresaABC() {};

	public Integer getCodEmp() {
		return codEmp;
	}

	public void setCodEmp(Integer codEmp) {
		this.codEmp = codEmp;
	}

	public Integer getCodFil() {
		return codFil;
	}

	public void setCodFil(Integer codFil) {
		this.codFil = codFil;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codEmp, codFil, pass, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmpresaABC other = (EmpresaABC) obj;
		return Objects.equals(codEmp, other.codEmp) && Objects.equals(codFil, other.codFil)
				&& Objects.equals(pass, other.pass) && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "EmpresaABC [codEmp=" + codEmp + ", codFil=" + codFil + ", user=" + user + ", pass=" + pass + "]";
	}
	
	
	

}
