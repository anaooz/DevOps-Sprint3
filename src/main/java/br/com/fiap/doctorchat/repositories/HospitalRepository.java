package br.com.fiap.doctorchat.repositories;

import br.com.fiap.doctorchat.models.Hospital;
import jakarta.persistence.*;

import java.util.List;

public class HospitalRepository {
    private EntityManager entityManager;

    public HospitalRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Hospital> findAll() {
        String jpql = "SELECT h FROM Hospital h";
        Query query = entityManager.createQuery(jpql, Hospital.class);
        return query.getResultList();
    }

    public Hospital findById(Long id){
        Hospital hospital = entityManager.find(Hospital.class, id);
        try {
            return hospital;
        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Hospital não encontrado.");
        }
    }

    public List<Hospital> findByCNPJ(String CNPJ) {
        String jpql = "SELECT h FROM Hospital h WHERE 'h.CNPJ' LIKE '%:CNPJ%'";
        Query query = entityManager.createQuery(jpql, Hospital.class);
        query.setParameter("cnpj", CNPJ);
        return query.getResultList();
    }

    public void cadastrar(Hospital hospital){
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(hospital);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void atualizar(Hospital hospital) {
        entityManager.getTransaction().begin();

        try {
            entityManager.merge(hospital);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void excluir(Hospital hospital){
        entityManager.getTransaction().begin();
        try {
            entityManager.remove(hospital);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void excluirPorId(Long id) {
        entityManager.getTransaction().begin();
        try {
            Hospital hospital = entityManager.find(Hospital.class, id);
            if (hospital != null) {
                entityManager.remove(hospital);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}
