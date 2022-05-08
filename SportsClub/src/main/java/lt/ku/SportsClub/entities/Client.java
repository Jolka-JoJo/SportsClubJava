package lt.ku.SportsClub.entities;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="client")
public class Client implements UserDetails{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	@NotBlank( message = "Vartotojo vardas privalomas")
	private String username;
	
	@Column(nullable = false)
	@NotBlank( message = "slaptažodis privalomas")
	private String password;
	
	@Column(nullable = false)
	private String role="user";

	@Column(nullable = false, length = 20)
	@Length(min=3, max=20, message = "Vardas turi būti ilgesnis nei 3 simboliai ir trumpesnis už 20 simbolius")
	@NotBlank( message = "Vardas privalomas")
	private String name;
	
	@Column(nullable = false, length = 25)
	@Length(min=3, max=20, message = "Pavardė turi būti ilgesnė nei 3 simboliai ir trumpesnė už 25 simbolius")
	@NotBlank( message = "Pavardė privaloma")
	private String surname;
	
	@Column(nullable = false)
	@Email(message = "El. pašto adresas turi būti tvarkingas")
	@NotBlank( message = "El. paštas privalomas")
	private String email;
	
	@Column (length = 15)
	@Length(max=15, message = "Telefono numeris turi būti trumpesnis už 15 simbolius")
	private String phone;

	public Client() {
	}

	public Client(String name, String surname, String email) {
		this.name = name;
		this.surname = surname;
		this.email = email;
	}

	public Client(String username, String password, String name, String surname, String email, String role) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		HashSet<GrantedAuthority> auth=new HashSet<>();
		auth.add(new SimpleGrantedAuthority(this.role));
		return auth;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
