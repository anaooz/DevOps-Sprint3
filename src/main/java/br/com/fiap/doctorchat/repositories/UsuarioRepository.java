package br.com.fiap.doctorchat.repositories;

import br.com.fiap.doctorchat.models.Usuario;
import jakarta.persistence.*;

import java.util.List;

public class UsuarioRepository {
    private EntityManager entityManager;

    public UsuarioRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Usuario> findAll() {
        String jpql = "SELECT u FROM Usuario u";
        Query query = entityManager.createQuery(jpql, Usuario.class);
        return query.getResultList();
    }

    public Usuario findById(Long id){
        Usuario usuario = entityManager.find(Usuario.class, id);
        if (usuario == null) {
            throw new EntityNotFoundException("Usuário não encontrado.");
        }
        return usuario;
    }

    public void cadastrar(Usuario usuario){
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(usuario);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void atualizar(Usuario usuario) {
        entityManager.getTransaction().begin();

        try {
            entityManager.merge(usuario);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void excluir(Usuario usuario){
        entityManager.getTransaction().begin();
        try {
            entityManager.remove(usuario);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void excluirPorId(Long id) {
        entityManager.getTransaction().begin();
        try {
            Usuario usuario = entityManager.find(Usuario.class, id);
            if (usuario != null) {
                entityManager.remove(usuario);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}
