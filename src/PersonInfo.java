
public class PersonInfo
{
	private String name,address,email;
	private int phone;

      public PersonInfo()
      {       
         name = "";
         address = "";
         email = ""; 
         phone = 0;
      }

	public PersonInfo(String name, String address, int phone, String email)
	{
            this.name = name;
            this.address = address;
            this.phone = phone;
            this.email = email;
	}

	public void setName(String n)
	{
		name=n;		
	}
	public void setAddress(String a)
	{
		address=a;
	}
	public void setPhone(int ph)
	{
		phone=ph;
	}
	public void setEmail(String e)
	{
		email=e;
	}

	public String getName()
	{
		return name;
	}

	public String getAddress()
	{
		return address;
	}

	public int getPhone()
	{
		return phone;
	}

	public String getEmail()
	{
		return email;
	}

}