package com.spring.boot.resturantbackend.services;

import com.spring.boot.resturantbackend.controllers.vm.RequestOrderVm;
import com.spring.boot.resturantbackend.controllers.vm.ResponseOrderVm;
import java.util.List;

public interface OrderService {
    ResponseOrderVm requestOrder(RequestOrderVm requestOrderVm);
    List<ResponseOrderVm> getOrdersForCurrentUser(int page, int size);
    List<ResponseOrderVm> getAllOrders(int page, int size);
}
