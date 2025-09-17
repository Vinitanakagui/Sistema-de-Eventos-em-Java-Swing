package controle;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import modelo.Administrador;
import modelo.Participante;


public class AdministradorDAO extends ControleDAO<Administrador>
{
    public Administrador buscarLogin(String login, String senha)
   {
      try
      {
        EntityManager em = getEntityManager();
        TypedQuery<Administrador> query = em.createNamedQuery("Administrador.buscarLogin",Administrador.class)
               .setParameter("login", login)
               .setParameter("senha", senha);
       
       return query.getSingleResult();  
      }
      catch(NoResultException e)
      {
          return null;
      }
       
   }
}
