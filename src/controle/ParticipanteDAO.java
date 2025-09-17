
package controle;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import modelo.Participante;

public class ParticipanteDAO extends ControleDAO<Participante>
{
    
   public Participante buscarLogin(String usuario, String senha)
   {
       try
       {
           EntityManager em = getEntityManager();
           TypedQuery<Participante> query = em.createNamedQuery("Participante.buscarLogin",Participante.class)
               .setParameter("usuario", usuario)
               .setParameter("senha", senha);
       
            return query.getSingleResult();
       } 
       catch(NoResultException e)
       {
        return null;
       }
   }
   
    
   public Participante buscarParticipante(String prontuario)
   {
        EntityManager em = getEntityManager();
        TypedQuery<Participante> query = em.createNamedQuery("Participante.buscarParticipante",Participante.class)
            .setParameter("prontuario", prontuario);

         return query.getSingleResult();     
   }
   
   public List<Participante> pesquisarPorNome(String nome)
   {
        EntityManager em = getEntityManager();
        TypedQuery<Participante> query = em.createNamedQuery("Participante.pesquisarPorNome",Participante.class)
            .setParameter("nome", "%" + nome + "%");

         return query.getResultList();     
   }
   
   public List<Participante> buscarTodos()
   {
        EntityManager em = getEntityManager();
        TypedQuery<Participante> query = em.createNamedQuery("Participante.buscarTodos",Participante.class);
           
         return query.getResultList();     
   }
   
   public List<Participante> pesquisarPorCurso(String nome, String curso)
   {
       
        EntityManager em = getEntityManager();
        TypedQuery<Participante> query = em.createNamedQuery("Participante.pesquisarPorCurso",Participante.class)
            .setParameter("nome", "%" + nome + "%")
            .setParameter("curso", curso);

         return query.getResultList(); 
   }
   
   public String buscarProntuarioPorNome(String nome)
   {
        EntityManager em = getEntityManager();
        TypedQuery<String> query = em.createNamedQuery("Participante.buscarProntuarioPorNome",String.class)
            .setParameter("nome", nome);

         return query.getSingleResult();     
   }
   
}
