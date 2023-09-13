package br.com.fiap.doctorchat.repositories;

import br.com.fiap.doctorchat.models.Medico;
import jakarta.persistence.*;

import java.util.List;

public class MedicoRepository {
    private EntityManager entityManager;

    public MedicoRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public List<Medico> findAll() {
        String jpql = "SELECT m FROM Medico m";
        Query query = entityManager.createQuery(jpql, Medico.class);
        return query.getResultList();
    }

    public Medico findById(Long id){
        Medico medico = entityManager.find(Medico.class, id);
        if (medico == null) {
            throw new EntityNotFoundException("Médico não encontrado.");
        }
        return medico;
    }

    public List<Medico> findByCNPJ(String CNPJ) {
        String jpql = "SELECT m FROM Medico m WHERE 'm.CNPJ' LIKE '%:cnpj%'";
        Query query = entityManager.createQuery(jpql, Medico.class);
        query.setParameter("cnpj", CNPJ);
        return query.getResultList();
    }

    public List<Medico> findByCPF(String CPF) {
        String jpql = "SELECT m FROM Medico m WHERE 'm.CPF' LIKE '%:cpf%'";
        Query query = entityManager.createQuery(jpql, Medico.class);
        query.setParameter("cpf", CPF);
        return query.getResultList();
    }

    public void cadastrar(Medico medico){
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(medico);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void atualizar(Medico medico) {
        entityManager.getTransaction().begin();

        try {
            entityManager.merge(medico);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void excluir(Medico medico){
        entityManager.getTransaction().begin();
        try {
            entityManager.remove(medico);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void excluirPorId(Long id) {
        entityManager.getTransaction().begin();
        try {
            Medico medico = entityManager.find(Medico.class, id);
            if (medico != null) {
                entityManager.remove(medico);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}
