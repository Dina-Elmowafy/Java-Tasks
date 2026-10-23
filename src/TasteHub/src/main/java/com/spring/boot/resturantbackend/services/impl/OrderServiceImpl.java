package com.spring.boot.resturantbackend.services.impl;

import com.spring.boot.resturantbackend.controllers.vm.OrderVm;
import com.spring.boot.resturantbackend.dto.ProductDto;
import com.spring.boot.resturantbackend.dto.security.AccountDto;
import com.spring.boot.resturantbackend.mappers.ProductMapper;
import com.spring.boot.resturantbackend.mappers.security.AccountMapper;
import com.spring.boot.resturantbackend.models.Order;
import com.spring.boot.resturantbackend.models.Product;
import com.spring.boot.resturantbackend.repositories.OrderRepo;
import com.spring.boot.resturantbackend.services.OrderService;
import com.spring.boot.resturantbackend.controllers.vm.RequestOrderVm;
import com.spring.boot.resturantbackend.controllers.vm.ResponseOrderVm;
import com.spring.boot.resturantbackend.services.ProductService;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import com.spring.boot.resturantbackend.mappers.OrderMapper;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ProductService productService;

    @Override
    @CacheEvict(value = "orders", allEntries = true)
    public ResponseOrderVm requestOrder(RequestOrderVm requestOrderVm) {
        try {
            List<OrderVm> orderVms = requestOrderVm.getOrderVms();

            // user
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            AccountDto accountDto = (AccountDto)authentication.getPrincipal();

            //totalNumber
            Double totalNumber = orderVms.stream().mapToDouble(orderVm -> orderVm.getQuantity()).sum();

            // product
            List<Long> productsIds = orderVms.stream().map(orderVm -> orderVm.getId()).collect(Collectors.toList());
            List<ProductDto> productDtos = productService.getProductsByIds(productsIds);

            //totalPrice
            Double totalPrice = calculateTotalPrice(orderVms, productDtos);

            Order order = new Order();
            order.setProducts(ProductMapper.PRODUCT_MAPPER.toEntityList(productDtos));
            order.setAccount(AccountMapper.ACCOUNT_MAPPER.toAccount(accountDto));
            order.setTotalPrice(totalPrice);
            order.setTotalNumber(totalNumber);

            order = orderRepo.save(order);

            String code = getCode(order);
            order.setCode(code);

            orderRepo.save(order);
            return new ResponseOrderVm(code, totalPrice, totalNumber);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<ResponseOrderVm> getOrdersForCurrentUser(int page, int size) {
        AccountDto account = (AccountDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return orderRepo.findByAccountIdOrderByIdAsc(account.getId(), pageable(page, size)).getContent()
                .stream().map(OrderMapper.ORDER_MAPPER::toResponseOrderVm).toList();
    }

    @Override
    public List<ResponseOrderVm> getAllOrders(int page, int size) {
        return orderRepo.findAllByOrderByIdAsc(pageable(page, size)).getContent()
                .stream().map(OrderMapper.ORDER_MAPPER::toResponseOrderVm).toList();
    }

    private PageRequest pageable(int page, int size) {
        if (page < 1 || size < 1 || size > 100) {
            throw new IllegalArgumentException("error.invalid.pagination");
        }
        return PageRequest.of(page - 1, size, Sort.by(Sort.Direction.ASC, "id"));
    }

    private String getCode(Order order) {
        return "RES-" + order.getId();
    }

    private Double calculateTotalPrice(List<OrderVm> orderVms, List<ProductDto> productDtos){
        Map<Long, Double> productsMap = productDtos.stream().collect(
                Collectors.toMap(ProductDto::getId, ProductDto::getPrice)
        );

        return orderVms.stream().mapToDouble(orderVm -> {
            Double price = productsMap.get(orderVm.getId());
            return price * orderVm.getQuantity();
        }).sum();
    }
}
