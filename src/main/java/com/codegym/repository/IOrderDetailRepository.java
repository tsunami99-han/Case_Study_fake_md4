package com.codegym.repository;

import com.codegym.model.OrderDetail;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderDetailRepository extends PagingAndSortingRepository<OrderDetail,Long> {

}
