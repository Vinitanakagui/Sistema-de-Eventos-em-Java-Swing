package controle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class ControleDAO<T> {

    private EntityManagerFactory emf;

    public ControleDAO() {
        this.emf = Persistence
                .createEntityManagerFactory("conexaoPU");
    }
    
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void inserir(T entidade) {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(entidade);
        em.getTransaction().commit();
        em.close();

    }

    public void alterar(T entidade) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(entidade);
        em.getTransaction().commit();
        em.close();
    }

    public void excluir(T entidade) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(entidade));
        em.getTransaction().commit();
        em.close();
    }
    
    

}