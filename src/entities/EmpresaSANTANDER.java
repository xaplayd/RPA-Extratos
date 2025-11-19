package entities;

import java.util.Objects;

public class EmpresaSANTANDER {
	
	private Integer codEmp;
	private Integer codFil;
	private Integer agencia;
	private Integer conta;
	private String user;
	private String pass;
	
	public EmpresaSANTANDER(Integer codEmp, Integer codFil, Integer agencia, Integer conta, String user, String pass) {
		this.codEmp = codEmp;
		this.codFil = codFil;
		this.agencia = agencia;
		this.conta = conta;
		this.user = user;
		this.pass = pass;
	}
	
	public EmpresaSANTANDER() {}

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

	public Integer getAgencia() {
		return agencia;
	}

	public void setAgencia(Integer agencia) {
		this.agencia = agencia;
	}

	public Integer getConta() {
		return conta;
	}

	public void setConta(Integer conta) {
		this.conta = conta;
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
		return Objects.hash(agencia, codEmp, codFil, conta, pass, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmpresaSANTANDER other = (EmpresaSANTANDER) obj;
		return Objects.equals(agencia, other.agencia) && Objects.equals(codEmp, other.codEmp)
				&& Objects.equals(codFil, other.codFil) && Objects.equals(conta, other.conta)
				&& Objects.equals(pass, other.pass) && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "EmpresaSANTANDER [codEmp=" + codEmp + ", codFil=" + codFil + ", agencia=" + agencia + ", conta=" + conta
				+ ", user=" + user + ", pass=" + pass + "]";
	};
	
	

	

}
