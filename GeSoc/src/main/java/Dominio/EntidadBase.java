package Dominio;

public class EntidadBase implements EntidadOrganizacional {
	
	private String nombreFicticio;
	private String descripcion;
	private EntidadOrganizacional entidadJuridica;
	
	public EntidadBase( String _nombreFicticio,String _descripcion)
	{
		this(_nombreFicticio,_descripcion, EntidadJuridica.EMPTY);
	}
	
	public EntidadBase( String _nombreFicticio,String _descripcion, EntidadOrganizacional _entidadJuridica)
	{
		nombreFicticio =_nombreFicticio;
		descripcion = _descripcion;
		entidadJuridica = _entidadJuridica;
	}
	
	@Override
	public String getNombreFicticio()
	{
		return nombreFicticio;
	}
	
	public String getDescripcion()
	{
		return descripcion;
	}
	
	public EntidadOrganizacional getentidadJuridica()
	{
		return entidadJuridica;
	}
}
