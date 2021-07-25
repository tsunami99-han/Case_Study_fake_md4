package com.codegym.repository;

import com.codegym.model.Review;
import org.hibernate.event.internal.ReattachVisitor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReviewRepository extends CrudRepository<Review,Long> {
}
