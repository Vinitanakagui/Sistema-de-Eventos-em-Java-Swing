package controle;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import modelo.Organizador;
import modelo.Organizador;

public class OrganizadorDAO extends ControleDAO<Organizador>
{
    public Organizador buscarOrganizadorPorSemana(String nomeOrganizador, Integer idSemana)
    {
        EntityManager em = getEntityManager();
        TypedQuery<Organizador> query = em.createNamedQuery("Organizador.buscarOrganizadorPorSemana", Organizador.class)
                .setParameter("nome", nomeOrganizador)
                .setParameter("idsemana", idSemana);
        
        try 
        {
            return query.getSingleResult();
        } 
        catch (NoResultException e) 
        {
            return null;
        } 
    }
}
