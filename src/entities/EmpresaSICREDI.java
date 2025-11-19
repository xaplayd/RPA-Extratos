package entities;

import java.util.Objects;

public class EmpresaSICREDI {

	private Integer codEmp;
	private Integer codFil;
	private String user;
	private String pass;
	private String trans;
	
	public EmpresaSICREDI(Integer codEmp, Integer codFil, String user, String pass, String trans) {
		this.codEmp = codEmp;
		this.codFil = codFil;
		this.user = user;
		this.pass = pass;
		this.trans = trans;
	}
	
	public EmpresaSICREDI() {}

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

	public String getTrans() {
		return trans;
	}

	public void setTrans(String trans) {
		this.trans = trans;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codEmp, codFil, pass, trans, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmpresaSICREDI other = (EmpresaSICREDI) obj;
		return Objects.equals(codEmp, other.codEmp) && Objects.equals(codFil, other.codFil)
				&& Objects.equals(pass, other.pass) && Objects.equals(trans, other.trans)
				&& Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "EmpresaSANTANDER [codEmp=" + codEmp + ", codFil=" + codFil + ", user=" + user + ", pass=" + pass
				+ ", trans=" + trans + "]";
	};
	
}

	


